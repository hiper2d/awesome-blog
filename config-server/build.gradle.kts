import com.bmuschko.gradle.docker.tasks.image.*

val springCloudVersion: Any? by project

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks {
    "buildDockerImage"(type = DockerBuildImage::class) {
        inputDir = file(".")
        tag = "hiper2d/config:latest"
    }
}

tasks.findByName("build")?.finalizedBy("buildDockerImage")

configurations.all {
    exclude(module = "spring-boot-starter-logging")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.springframework.cloud:spring-cloud-config-server")
}