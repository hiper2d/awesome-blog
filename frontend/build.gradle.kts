import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

val springCloudVersion: Any? by project

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
    commandLine = listOf("yarn", "install")
  }
  "yarnBuild"(type = Exec::class) {
    workingDir = file("src/main/ng")
    commandLine = listOf("yarn", "run", "build")
  }
}

tasks.findByName("yarnBuild")?.dependsOn("yarnInstall")
tasks.findByName("compileKotlin")?.finalizedBy("yarnBuild")

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    // spring-boot-dependencies bom is already included via spring-boot-gradle-plugin
  }
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
}