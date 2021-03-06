import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

description = "Account API"
group = "com.example.account.api"
version = "0.0.1-SNAPSHOT"

var devProfile = "dev"

plugins {
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
val postgreSqlVersion: String = rootProject.extra.get("postgreSqlVersion") as String
val flywayDbVersion: String = rootProject.extra.get("flywayDbVersion") as String
val jacksonVersion: String = rootProject.extra.get("jacksonVersion") as String
val junitVersion: String = rootProject.extra.get("junitVersion") as String
val jasyptVersion: String = rootProject.extra.get("jasyptVersion") as String
val isDev: Boolean = rootProject.extra["isDev"] as Boolean

configurations {
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
        exclude(group = "junit", module = "junit")
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(group = "org.slf4j", module = "slf4j-log4j12")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":services:common"))
    implementation(project(":services:account:account-api-spec"))

    implementation(group = "org.springframework.boot", name = "spring-boot-starter-web")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-undertow")

    implementation(group = "org.springframework.boot", name = "spring-boot-starter-data-jpa")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-actuator")

    implementation("org.postgresql:postgresql:${postgreSqlVersion}")
    implementation("org.flywaydb:flyway-core:${flywayDbVersion}")

    implementation(group = "com.github.ulisesbocchio", name = "jasypt-spring-boot-starter", version = jasyptVersion)

    implementation("com.google.protobuf:protobuf-java-util:$protobufVersion")
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.10.0")

    runtimeOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.junit.jupiter:junit-jupiter:${junitVersion}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
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

tasks {
    test {
        useJUnitPlatform()
        testLogging.showExceptions = true
        testLogging {
            events = setOf(
                    TestLogEvent.PASSED,
                    TestLogEvent.FAILED,
                    TestLogEvent.SKIPPED)
        }
    }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}