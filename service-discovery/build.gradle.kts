buildscript {
  val bootVersion = "2.0.0.RELEASE"

  repositories {
    mavenCentral()
    maven { setUrl("https://repo.spring.io/milestone") }
    maven { setUrl("https://repo.spring.io/snapshot") }
  }

  dependencies {
    classpath("org.springframework.boot:spring-boot-gradle-plugin:$bootVersion")

    // Workaround to avoid Deprecated Java 9 Javax Dependencies
    // See more here: https://github.com/aws/aws-sdk-java/issues/1092
    implementation("javax.xml.bind:jaxb-api:2.3.0")
    implementation("com.sun.xml.bind:jaxb-core:2.3.0")
    implementation("com.sun.xml.bind:jaxb-impl:2.3.0")
    implementation("javax.activation:activation:1.1.1")
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