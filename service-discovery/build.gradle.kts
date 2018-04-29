import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

val springCloudVersion: Any? by project

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    // spring-boot-dependencies bom is already included via spring-boot-gradle-plugin
  }
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
}