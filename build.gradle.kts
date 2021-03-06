import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val kotlinVersion = "1.3.21"
    val dockerPlugin = "4.1.0"
    val springBootVersion = "2.1.3.RELEASE"
    val springDependencyManagementVersion = "1.0.6.RELEASE"

    base
    kotlin("jvm") version kotlinVersion
    id("io.spring.dependency-management") version springDependencyManagementVersion

    id("org.springframework.boot") version springBootVersion apply false
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion apply false
    id("com.bmuschko.docker-remote-api") version dockerPlugin apply false
}

val disruptorVersion: String by project
val springCloudVersion: String by project

repositories {
    jcenter()
}

dependencyManagement {
    imports {
        // spring-boot-dependencies bom is already included via spring-boot-gradle-plugin
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

subprojects {
    group = "com.hiper2d"
    version = "1.0"

    configurations.all {
        exclude(module = "spring-boot-starter-logging")
    }

    apply {
        plugin("org.gradle.base")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.springframework.boot")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("io.spring.dependency-management")
        plugin("com.bmuschko.docker-remote-api")
    }

    repositories {
        jcenter()
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }

        withType<Test> {
            useJUnitPlatform()
        }

        withType<BootJar> {
            mainClassName = "com.hiper2d.AppKt"
        }
    }

    tasks["build"].finalizedBy("buildDockerImage")

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.springframework.boot:spring-boot-starter-log4j2")
        implementation("org.springframework.cloud:spring-cloud-config-client")
        implementation("org.springframework.retry:spring-retry") // Reconnect to Config Server
        implementation("com.lmax:disruptor:$disruptorVersion") // Log4j2 async appender
    }
}
