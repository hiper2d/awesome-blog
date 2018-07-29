import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val kotlinVersion = "1.2.51"
    val springBootVersion = "2.0.3.RELEASE"
    val springDependencyManagementVersion = "1.0.5.RELEASE"

    base
    kotlin("jvm") version kotlinVersion apply false
    id("org.springframework.boot") version springBootVersion apply false
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion apply false
    id("io.spring.dependency-management") version springDependencyManagementVersion apply false
}

allprojects {
    group = "com.hiper2d"
    version = "1.0"

    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.springframework.boot")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("io.spring.dependency-management")
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

dependencies {
    // Make the root project archives configuration depend on every subproject
    subprojects.forEach {
        archives(it)
    }
}