import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import vn.io.dungxnd.calculateTotal

class CalculateTotalTest : FunSpec({
    test("TC-01: Giá tạm tính không hợp lệ - subtotal=0 ném IllegalArgumentException") {
        shouldThrow<IllegalArgumentException> {
            calculateTotal(
                subtotal      = 0L,
                memberTier    = "standard",
                voucherAmount = 20_000L
            )
        }
    }

    test("TC-02: Giá tạm tính hợp lệ - subtotal=1, kết quả=1") {
        val result = calculateTotal(
            subtotal      = 1L,
            memberTier    = "standard",
            voucherAmount = 20_000L
        )
        result shouldBe 1L
    }

    test("TC-03: Giá tạm tính hợp lệ - subtotal=2, kết quả=2") {
        val result = calculateTotal(
            subtotal      = 2L,
            memberTier    = "standard",
            voucherAmount = 20_000L
        )
        result shouldBe 2L
    }

    test("TC-04: Giá tạm tính hợp lệ, không đủ điều kiện áp voucher - subtotal=50.000, kết quả=50.000") {
        val result = calculateTotal(
            subtotal      = 50_000L,
            memberTier    = "standard",
            voucherAmount = 20_000L
        )
        result shouldBe 50_000L
    }

    test("TC-05: Không đủ giá trị áp voucher - subtotal=99.999, kết quả=99.999") {
        val result = calculateTotal(
            subtotal      = 99_999L,
            memberTier    = "standard",
            voucherAmount = 50_000L
        )
        result shouldBe 99_999L
    }

    test("TC-06: Đủ giá trị áp voucher - subtotal=100.000, kết quả=50.000") {
        val result = calculateTotal(
            subtotal      = 100_000L,
            memberTier    = "standard",
            voucherAmount = 50_000L
        )
        result shouldBe 50_000L
    }

    test("TC-07: Đủ giá trị áp voucher - subtotal=100.001, kết quả=50.001") {
        val result = calculateTotal(
            subtotal      = 100_001L,
            memberTier    = "standard",
            voucherAmount = 50_000L
        )
        result shouldBe 50_001L
    }

    test("TC-08: Không đủ giá trị áp voucher hạng VIP - subtotal=111.110, kết quả=99.999") {
        val result = calculateTotal(
            subtotal      = 111_110L,
            memberTier    = "VIP",
            voucherAmount = 50_000L
        )
        result shouldBe 99_999L
    }

    test("TC-09: Đủ giá trị áp voucher hạng VIP - subtotal=111.111, kết quả=50.000") {
        val result = calculateTotal(
            subtotal      = 111_111L,
            memberTier    = "VIP",
            voucherAmount = 50_000L
        )
        result shouldBe 50_000L
    }

    test("TC-10: Đủ giá trị áp voucher hạng VIP - subtotal=111.112, kết quả=50.001") {
        val result = calculateTotal(
            subtotal      = 111_112L,
            memberTier    = "VIP",
            voucherAmount = 50_000L
        )
        result shouldBe 50_001L
    }

    test("TC-11: Giá trị voucher không hợp lệ - voucherAmount=-10.000 ném IllegalArgumentException") {
        shouldThrow<IllegalArgumentException> {
            calculateTotal(
                subtotal      = 1_000_000L,
                memberTier    = "standard",
                voucherAmount = -10_000L
            )
        }
    }

    test("TC-12: Giá trị voucher hợp lệ - voucherAmount=0, kết quả=1.000.000") {
        val result = calculateTotal(
            subtotal      = 1_000_000L,
            memberTier    = "standard",
            voucherAmount = 0L
        )
        result shouldBe 1_000_000L
    }

    test("TC-13: Giá trị voucher hợp lệ - voucherAmount=10.000, kết quả=990.000") {
        val result = calculateTotal(
            subtotal      = 1_000_000L,
            memberTier    = "standard",
            voucherAmount = 10_000L
        )
        result shouldBe 990_000L
    }

    test("TC-14: Giá trị voucher hợp lệ - voucherAmount=100.000, kết quả=900.000") {
        val result = calculateTotal(
            subtotal      = 1_000_000L,
            memberTier    = "standard",
            voucherAmount = 100_000L
        )
        result shouldBe 900_000L
    }

    test("TC-15: Giá trị voucher hợp lệ - voucherAmount=490.000, kết quả=510.000") {
        val result = calculateTotal(
            subtotal      = 1_000_000L,
            memberTier    = "standard",
            voucherAmount = 490_000L
        )
        result shouldBe 510_000L
    }

    test("TC-16: Giá trị voucher hợp lệ - voucherAmount=500.000, kết quả=500.000") {
        val result = calculateTotal(
            subtotal      = 1_000_000L,
            memberTier    = "standard",
            voucherAmount = 500_000L
        )
        result shouldBe 500_000L
    }

    test("TC-17: Giá trị voucher không hợp lệ - voucherAmount=510.000 ném IllegalArgumentException") {
        shouldThrow<IllegalArgumentException> {
            calculateTotal(
                subtotal      = 1_000_000L,
                memberTier    = "standard",
                voucherAmount = 510_000L
            )
        }
    }

    test("TC-18: Giá trị voucher không hợp lệ - voucherAmount=55.000 không chia hết 10.000 ném IllegalArgumentException") {
        shouldThrow<IllegalArgumentException> {
            calculateTotal(
                subtotal      = 200_000L,
                memberTier    = "standard",
                voucherAmount = 55_000L
            )
        }
    }

    test("TC-19: Hạng thành viên không phân biệt hoa thường - stAnDard, kết quả=80.000") {
        val result = calculateTotal(
            subtotal      = 100_000L,
            memberTier    = "stAnDard",
            voucherAmount = 20_000L
        )
        result shouldBe 80_000L
    }

    test("TC-20: Hạng thành viên không phân biệt hoa thường - ViP, kết quả=160.000") {
        val result = calculateTotal(
            subtotal      = 200_000L,
            memberTier    = "ViP",
            voucherAmount = 20_000L
        )
        result shouldBe 160_000L
    }

    test("TC-21: Hạng thành viên không hợp lệ - hihihaha ném IllegalArgumentException") {
        shouldThrow<IllegalArgumentException> {
            calculateTotal(
                subtotal      = 100_000L,
                memberTier    = "hihihaha",
                voucherAmount = 20_000L
            )
        }
    }

    test("TC-22: Giá trị voucher lớn hơn đơn hàng sau chiết khấu - standard, kết quả=0") {
        val result = calculateTotal(
            subtotal      = 200_000L,
            memberTier    = "standard",
            voucherAmount = 300_000L
        )
        result shouldBe 0L
    }

    test("TC-23: Giá trị voucher lớn hơn đơn hàng sau chiết khấu - vip, kết quả=0") {
        val result = calculateTotal(
            subtotal      = 300_000L,
            memberTier    = "vip",
            voucherAmount = 300_000L
        )
        result shouldBe 0L
    }

    test("TC-24: Giá trị đơn hàng sau chiết khấu lớn hơn voucher - vip, kết quả=350.000") {
        val result = calculateTotal(
            subtotal      = 500_000L,
            memberTier    = "vip",
            voucherAmount = 100_000L
        )
        result shouldBe 350_000L
    }

    test("TC-25: Không đủ điều kiện áp voucher - vip subtotal=50.000, kết quả=45.000") {
        val result = calculateTotal(
            subtotal      = 50_000L,
            memberTier    = "vip",
            voucherAmount = 20_000L
        )
        result shouldBe 45_000L
    }


})
