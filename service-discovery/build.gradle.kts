import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage

val activationVersion: Any? by project
val jaxbVersion: Any? by project
val springCloudVersion: Any? by project

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
  }
}

tasks {
    "buildDockerImage"(type = DockerBuildImage::class) {
        inputDir = file(".")
        tag = "hiper2d/discovery:latest"
    }
}

tasks.findByName("build")?.finalizedBy("buildDockerImage")

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.springframework.cloud:spring-cloud-starter-config")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")

  // Solving an error for Java 9 and JAXB dependencies: Type javax.xml.bind.JAXBContext not present
  // See details: https://stackoverflow.com/questions/43574426/how-to-resolve-java-lang-noclassdeffounderror-javax-xml-bind-jaxbexception-in-j/46484873
  implementation("org.glassfish.jaxb:jaxb-runtime:$jaxbVersion")
  implementation("javax.activation:activation:$activationVersion")
}