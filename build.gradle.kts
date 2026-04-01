plugins {
    kotlin("jvm") version "2.3.20"
    id("org.sonarqube") version "7.2.3.7755"
    id("info.solidsoft.pitest") version "1.19.0-rc.3"
    jacoco
}

val kotestVersion = "6.1.10"

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-extensions-junitxml:$kotestVersion")
}

tasks.test {
    useJUnitPlatform()
    systemProperty("gradle.build.dir", project.buildDir)
    reports {
        junitXml.required.set(false)
    }
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

sonar {
    properties {
        property("sonar.projectKey", "DungxND_TestingCourse")
        property("sonar.organization", "dungxnd")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.sources", "src/main/kotlin")
        property("sonar.tests", "src/test/kotlin")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
        property("sonar.junit.reportPaths", "build/test-results/kotest")
    }
}

kotlin {
    jvmToolchain(25)
}