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

  // These stupid dependencies are required by Spring Security and Java 9/10 to avoid 'NoClassDefFoundError: javax/xml/bind/JAXBException'
  // https://stackoverflow.com/questions/43574426/how-to-resolve-java-lang-noclassdeffounderror-javax-xml-bind-jaxbexception-in-j/46455026
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