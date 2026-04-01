package io.kotest.provided

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.allure.AllureTestReporter
import io.kotest.extensions.junitxml.JunitXmlReporter

object ProjectConfig : AbstractProjectConfig() {
    override val extensions = listOf(
        JunitXmlReporter(
            includeContainers = false,
            useTestPathAsName = true,
            outputDir = "test-results/kotest"
        ),
        AllureTestReporter()
    )
}