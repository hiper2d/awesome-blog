buildscript {
  val bootVersion = "2.0.0.RC2"

  repositories {
    mavenCentral()
    maven { setUrl("https://repo.spring.io/milestone") }
    maven { setUrl("https://repo.spring.io/snapshot") }
  }

  dependencies {
    classpath("org.springframework.boot:spring-boot-gradle-plugin:$bootVersion")
  }
}

plugins {
  val kotlinVersion = "1.2.30"

  application
  kotlin("jvm")
  id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
  id("io.spring.dependency-management") version "1.0.4.RELEASE"
}

apply {
  plugin("org.springframework.boot")
}

application {
  mainClassName = "com.hiper2d.AppKt"
}

java.sourceSets[SourceSet.MAIN_SOURCE_SET_NAME].resources {
  srcDir("src/main/ng/dist")
}

tasks {
  "yarnStart"(type = Exec::class) {
    workingDir = file("src/main/ng")
    commandLine = listOf("yarn", "start")
  }
  "yarnInstall"(type = Exec::class) {
    workingDir = file("src/main/ng")
    commandLine = listOf("yarn", "run", "build")
  }
}

tasks.findByName("compileKotlin")?.finalizedBy("yarnInstall")

repositories {
  maven { setUrl("https://repo.spring.io/milestone") }
  maven { setUrl("https://repo.spring.io/snapshot") }
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jre8")
  implementation("org.springframework.boot:spring-boot-starter-web")
}