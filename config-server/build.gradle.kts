import com.bmuschko.gradle.docker.tasks.image.*

val disruptorVersion: String by project
val springCloudVersion: String by project

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks {
    create<DockerBuildImage>("buildDockerImage") {
        inputDir = file(".")
        tag = "hiper2d/config:latest"
    }
}

tasks["build"].finalizedBy("buildDockerImage")

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.springframework.cloud:spring-cloud-config-server")
    implementation("com.lmax:disruptor:$disruptorVersion") // Log4j2 async appender
}