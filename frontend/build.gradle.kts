import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import org.gradle.internal.os.OperatingSystem

val springCloudVersion: Any? by project
val isWindows = OperatingSystem.current().name.startsWith("Windows")
val yarnCmd = if (isWindows) "yarn.cmd" else "yarn"

java.sourceSets[SourceSet.MAIN_SOURCE_SET_NAME].resources {
  srcDir("src/main/ng/dist")
}

tasks {
  "yarnStart"(type = Exec::class) {
    workingDir = file("src/main/ng")
    commandLine = listOf(yarnCmd, "start")
  }
  "yarnInstall"(type = Exec::class) {
    workingDir = file("src/main/ng")
    commandLine = listOf(yarnCmd, "install")
  }
  "yarnBuild"(type = Exec::class) {
    workingDir = file("src/main/ng")
    commandLine = listOf(yarnCmd, "run", "build")
  }
  "buildDockerImage"(type = DockerBuildImage::class) {
    inputDir = file(".")
    tag = "hiper2d/frontend:latest"
  }
}

tasks.findByName("yarnBuild")?.dependsOn("yarnInstall")
tasks.findByName("compileKotlin")?.finalizedBy("yarnBuild")
tasks.findByName("build")?.finalizedBy("buildDockerImage")

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
  }
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.springframework.cloud:spring-cloud-starter-config")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-zuul")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
}