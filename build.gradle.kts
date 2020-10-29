plugins {
    java
    kotlin("jvm") version "1.4.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.reactivex.rxjava3:rxjava:3.0.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0-M1")
    testImplementation("junit", "junit", "4.12")
    testImplementation("com.google.truth:truth:1.0.1")
}
