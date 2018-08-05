val jaxbVersion: Any? by project
val jjwtVersion: Any? by project
val junitJupiterVersion: Any? by project
val springCloudVersion: Any? by project

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-webflux") {
        exclude(module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("io.projectreactor.ipc:reactor-netty")
    implementation("io.jsonwebtoken:jjwt:$jjwtVersion")

    // NoClassDefFoundError: javax/xml/bind/DatatypeConverter workaround
    implementation("javax.xml.bind:jaxb-api:$jaxbVersion")
    implementation("com.sun.xml.bind:jaxb-core:$jaxbVersion")
    implementation("com.sun.xml.bind:jaxb-impl:$jaxbVersion")
    implementation("javax.activation:activation:1.1.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}