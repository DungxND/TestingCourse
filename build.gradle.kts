plugins {
    kotlin("jvm") version "2.3.0"
    id("info.solidsoft.pitest") version "1.19.0-rc.3"
    id("org.sonarqube") version "7.2.3.7755"
    jacoco
}

group = "vn.io.dungxnd"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:6.1.7")
    testImplementation("io.kotest:kotest-assertions-core:6.1.7")
    testImplementation("com.michaelstrasser:kotest-html-reporter:0.8.2")
}

jacoco {
    toolVersion = "0.8.14"
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }
}

// Pitest config
pitest {
    targetClasses.set(setOf("vn.io.dungxnd.*"))
    outputFormats.set(setOf("XML", "HTML"))
    timestampedReports.set(false)
}

sonar {
    properties {
        property("sonar.projectKey", "DungxND_TestingCourse")
        property("sonar.organization", "dungxnd")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.jacoco.xmlReportPaths",
            "build/reports/jacoco/test/jacocoTestReport.xml")
        property("sonar.junit.reportPaths",
            "build/test-results/test")
    }
}

kotlin {
    jvmToolchain(25)
}
