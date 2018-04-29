import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

val springCloudVersion: Any? by project

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
  }
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")

  // Solving an error for Java 9 and JAXB dependencies: Type javax.xml.bind.JAXBContext not present
  // See details: https://stackoverflow.com/questions/43574426/how-to-resolve-java-lang-noclassdeffounderror-javax-xml-bind-jaxbexception-in-j/46484873
  implementation("org.glassfish.jaxb:jaxb-runtime:2.3.0")
  implementation("javax.activation:activation:1.1.1")
}