package vn.io.dungxnd

import kotlin.math.roundToLong

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    try {
        val total = calculateTotal(200000, "Silver", 500000)
        println("Thanh tien: $total")
    } catch (e: IllegalArgumentException) {
        println("Lỗi: ${e.message}")
    }
}

fun calculateTotal(subtotal: Long, memberTier: String, voucherAmount: Long): Long {
    if (subtotal <= 0) throw IllegalArgumentException("subTotal should be > 0")

    if (voucherAmount !in 0..500000 || voucherAmount % 10000 != 0L) throw IllegalArgumentException("voucherAmount should be >= 0 and <=500.000 and is a multiple of 10.000")

    val tier = memberTier.lowercase()
    val discountRate = when (tier) {
        "standard" -> 0.0
        "vip" -> 0.10
        else -> throw IllegalArgumentException("tier ($tier) is not valid")
    }

    val subtotalAfterDisCount = subtotal - (discountRate*subtotal).roundToLong()


    var totalAmount = subtotalAfterDisCount
    if (totalAmount >=100000 && voucherAmount > 0) {
        totalAmount -= voucherAmount
    }

    return if (totalAmount < 0) 0 else totalAmount
}

fun calculateTotal(subtotal: Int, memberTier: String, voucherAmount: Int): Long =
    calculateTotal(subtotal.toLong(), memberTier, voucherAmount.toLong())