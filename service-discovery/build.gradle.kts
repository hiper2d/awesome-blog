import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage

val activationVersion: String by project
val jaxbVersion: String by project
val springCloudVersion: String by project

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks {
    create<DockerBuildImage>("buildDockerImage") {
        inputDir = file(".")
        tag = "hiper2d/discovery:latest"
    }
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")

    // Details about these four libs: https://stackoverflow.com/a/43574427/2349970
    implementation("javax.xml.bind:jaxb-api:$jaxbVersion")
    implementation("com.sun.xml.bind:jaxb-core:$jaxbVersion")
    implementation("com.sun.xml.bind:jaxb-impl:$jaxbVersion")
    implementation("javax.activation:activation:$activationVersion")
}