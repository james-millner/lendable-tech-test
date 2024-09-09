plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"

    //This is great in a personal project I host. Not sure why its not playing ball here - although suspect its gradle verison related.
    //Commenting for visibility.
    //id("org.jetbrains.dokka") version "1.9.20"
}

group = "com.lendable"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register("copyDokkaDocs") {
    dependsOn("dokkaHtml")
    doLast {
        val dokkaDocsDir = file("${buildDir}/documentation/html")
        val targetDocsDir = file("../docs")

        if (dokkaDocsDir.exists()) {
            targetDocsDir.deleteRecursively()
            targetDocsDir.mkdirs()
            dokkaDocsDir.copyRecursively(targetDocsDir)
        } else {
            logger.warn("Dokka documentation directory not found. Please ensure Dokka has been executed.")
        }
    }
}
