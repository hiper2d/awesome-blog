buildscript {
  val bootVersion = "2.0.0.RELEASE"

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

repositories {
  maven { setUrl("https://repo.spring.io/milestone") }
  maven { setUrl("https://repo.spring.io/snapshot") }
}

dependencyManagement {
  imports {
    val springCloudVersion = "Finchley.M8"
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    // spring-boot-dependencies bom is already included via spring-boot-gradle-plugin
  }
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jre8")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
}