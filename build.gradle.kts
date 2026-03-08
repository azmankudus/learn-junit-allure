plugins {
  application
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get()))
  }
}

dependencies {
  testImplementation(platform(libs.junit.bom))
  testImplementation(libs.junit.jupiter)
  testRuntimeOnly(libs.junit.platform.launcher)

  testImplementation(libs.allure.junit.platform) {
    exclude(group = libs.junit.jupiter.get().group)
    exclude(group = libs.junit.platform.launcher.get().group)
  }
  testImplementation(libs.jackson.yaml)
  testImplementation(libs.jackson.databind)
}

application {
  mainClass = "org.example.App"
}

val allureReport = tasks.register<Exec>("allureReport") {
  group = "verification"
  commandLine("allure", "awesome", "--single-file", "--output", "build/allure-report", "--history-path", "allure/history.json", "build/allure-results")
}

tasks.test {
  useJUnitPlatform()
  systemProperty("allure.results.directory", layout.buildDirectory.dir("allure-results").get().asFile)
  systemProperty("test.proceed", project.findProperty("test.proceed") ?: "false")
  finalizedBy(allureReport)
}