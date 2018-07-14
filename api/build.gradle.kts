import org.springframework.boot.gradle.tasks.run.BootRun

val springCloudVersion: Any? by project

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    // spring-boot-dependencies bom is already included via spring-boot-gradle-plugin
  }
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.jetbrains.kotlin:kotlin-reflect")

  // Spring Boot, Webflux
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

  // Security, OAuth2
  implementation("org.springframework.cloud:spring-cloud-starter-oauth2")
  implementation("org.springframework.security:spring-security-jwt")

  // These stupid dependencies are required by Spring Security and Java 9/10 to avoid 'NoClassDefFoundError: javax/xml/bind/JAXBException'
  // https://stackoverflow.com/questions/43574426/how-to-resolve-java-lang-noclassdeffounderror-javax-xml-bind-jaxbexception-in-j/46455026
  val jaxbVersion = "2.3.0"
  implementation("javax.xml.bind:jaxb-api:$jaxbVersion")
  implementation("com.sun.xml.bind:jaxb-core:$jaxbVersion")
  implementation("com.sun.xml.bind:jaxb-impl:$jaxbVersion")
  implementation("javax.activation:activation:1.1.1")
}