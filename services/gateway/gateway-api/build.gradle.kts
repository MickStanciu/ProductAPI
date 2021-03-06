description = "Gateway API"
group = "com.example.gateway.api"
version = "0.0.1-SNAPSHOT"

plugins {
    java
    kotlin("jvm")
    kotlin ("plugin.spring")
    application
    id("io.spring.dependency-management")
    id("org.springframework.boot")
}

repositories {
    jcenter()
    mavenCentral()
}

val protobufVersion: String = rootProject.extra.get("protobufVersion") as String
val jacksonVersion: String = rootProject.extra.get("jacksonVersion") as String
val jwtVersion: String = rootProject.extra.get("jwtVersion") as String
val junitVersion: String = rootProject.extra.get("junitVersion") as String
val kotlinTestVersion: String = rootProject.extra.get("kotlinTestVersion") as String
val arrowVersion: String = rootProject.extra["arrowVersion"] as String


configurations {
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
        exclude(group = "junit", module = "junit")
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

dependencies {
    implementation (kotlin("stdlib"))
    implementation (project(":services:common"))
    implementation (project(":services:gateway:gateway-api-spec"))
    implementation (project(":services:account:account-api-spec"))
    implementation (project(":services:tradelog:tradelog-api-spec"))
    implementation (project(":services:stockdata:stockdata-api-spec"))

    implementation ("org.springframework.boot:spring-boot-starter-web");
    implementation ("org.springframework.boot:spring-boot-starter-undertow")
    implementation ("org.springframework.boot:spring-boot-starter-amqp")
    implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation ("org.springframework.boot:spring-boot-starter-actuator")

    implementation ("com.google.protobuf:protobuf-java-util:$protobufVersion")
    implementation ("com.google.code.findbugs:jsr305:3.0.2")
    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.10.0")
    implementation (group = "javax.xml.bind", name = "jaxb-api", version = "2.3.1")
    implementation ("io.jsonwebtoken:jjwt:$jwtVersion")
    implementation (group = "io.arrow-kt", name = "arrow-core", version = arrowVersion)
    
    runtimeOnly ("org.springframework.boot:spring-boot-devtools")

    testImplementation ("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation (group = "io.kotest", name = "kotest-runner-junit5", version = kotlinTestVersion)
    testImplementation (group = "io.kotest", name = "kotest-assertions-core", version = kotlinTestVersion)
    testImplementation (group = "io.kotest", name = "kotest-extensions-spring", version = kotlinTestVersion)
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
}

sourceSets {
    main {
        java.srcDir("src/main/java")
        java.srcDir("src/main/kotlin")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks {
    test {
        useJUnitPlatform()
        testLogging.showExceptions = true
        testLogging {
            events = setOf(
                    org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED)
        }
    }
}
