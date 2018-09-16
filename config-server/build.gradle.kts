import com.bmuschko.gradle.docker.tasks.image.*

val springCloudVersion: String by project

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks {
    register<DockerBuildImage>("buildDockerImage") {
        inputDir = file(".")
        tag = "hiper2d/config:latest"
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.cloud:spring-cloud-config-server")
}