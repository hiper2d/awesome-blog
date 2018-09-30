import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage

val activationVersion: String by project
val jaxbVersion: String by project
val jjwtVersion: String by project
val junitJupiterVersion: String by project
val springCloudVersion: String by project

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
  }
}

tasks {
  create<DockerBuildImage>("buildDockerImage") {
    inputDir = file(".")
    tag = "hiper2d/api:latest"
  }
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-reflect") // used by a router in Spring WebFlux
  implementation("org.springframework.boot:spring-boot-starter-webflux") {
    exclude(module = "spring-boot-starter-tomcat")
  }
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
  implementation("io.projectreactor.ipc:reactor-netty")
  implementation("io.jsonwebtoken:jjwt:$jjwtVersion")

  // The JAXB APIs are considered to be Java EE APIs, and therefore are no longer contained on the default class path in Java SE 9.
  // In Java 11 they are completely removed from the JDK. They are used by Spring Security hence I have to add them manually.
  // Details: https://stackoverflow.com/a/43574427/2349970
  implementation("javax.xml.bind:jaxb-api:$jaxbVersion")
  implementation("com.sun.xml.bind:jaxb-core:$jaxbVersion")
  implementation("com.sun.xml.bind:jaxb-impl:$jaxbVersion")
  implementation("javax.activation:activation:$activationVersion")

  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(module = "junit")
  }
  testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}