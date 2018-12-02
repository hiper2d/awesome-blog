import com.bmuschko.gradle.docker.tasks.image.*

val springCloudVersion: String by project

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks {
    create<DockerBuildImage>("buildDockerImage") {
        inputDir.set(file("."))
        tag.set("hiper2d/config:latest")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.cloud:spring-cloud-config-server")
}