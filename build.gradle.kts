import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val kotlinVersion = "1.2.70"
    val springBootVersion = "2.0.4.RELEASE"
    val springDependencyManagementVersion = "1.0.5.RELEASE"

    base
    kotlin("jvm") version kotlinVersion apply false
    id("org.springframework.boot") version springBootVersion apply false
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion apply false
    id("io.spring.dependency-management") version springDependencyManagementVersion apply false
    id("com.bmuschko.docker-remote-api") version "3.6.0" apply false
}

allprojects {
    group = "com.hiper2d"
    version = "1.0"

    configurations.all {
        exclude(module = "spring-boot-starter-logging")
    }

    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.springframework.boot")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("io.spring.dependency-management")
        plugin("com.bmuschko.docker-remote-api")
    }

    repositories {
        jcenter()
        maven("http://repo.spring.io/milestone")
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
                freeCompilerArgs = listOf("-Xjsr305=strict")
            }
        }

        withType<Test> {
            useJUnitPlatform()
        }

        withType<BootJar> {
            mainClassName = "com.hiper2d.AppKt"
        }
    }
}