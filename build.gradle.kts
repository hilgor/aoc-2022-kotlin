import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.JavaVersion.VERSION_21

plugins {
    kotlin("jvm") version "2.1.21"
    application
}

group = "de.iits"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

kotlin {
    jvmToolchain(17)
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("script-runtime"))
}

tasks.test {
    useJUnitPlatform()
}

//tasks.withType<KotlinCompile> {
//    kotlinOptions.jvmTarget = "17"
//}

application {
    mainClass.set("MainKt")
}