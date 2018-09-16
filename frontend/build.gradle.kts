import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import org.gradle.internal.os.OperatingSystem

val disruptorVersion: String by project
val springCloudVersion: String by project

val isWindows = OperatingSystem.current().name.startsWith("Windows")
val yarnCmd = if (isWindows) "yarn.cmd" else "yarn"

sourceSets[SourceSet.MAIN_SOURCE_SET_NAME].resources {
  srcDir("src/main/ng/dist")
}

tasks {
  create<Exec>("yarnStart") {
    workingDir = file("src/main/ng")
    commandLine = listOf(yarnCmd, "start")
  }
  create<Exec>("yarnInstall"){
    workingDir = file("src/main/ng")
    commandLine = listOf(yarnCmd, "install")
  }
  create<Exec>("yarnBuild") {
    workingDir = file("src/main/ng")
    commandLine = listOf(yarnCmd, "run", "build")
  }
  create<DockerBuildImage>("buildDockerImage") {
    inputDir = file(".")
    tag = "hiper2d/frontend:latest"
  }
}

tasks["yarnBuild"].dependsOn("yarnInstall")
tasks["compileKotlin"].finalizedBy("yarnBuild")
tasks["build"].finalizedBy("buildDockerImage")

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
  }
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.springframework.boot:spring-boot-starter-log4j2")
  implementation("org.springframework.cloud:spring-cloud-starter-config")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-zuul")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
  implementation("com.lmax:disruptor:$disruptorVersion")
}