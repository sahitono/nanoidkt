plugins {
    kotlin("multiplatform") version "1.8.0"
    id("maven-publish")
}

group = "id.sahitono.nanoidkt"
version = "0.1.0"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        jvmToolchain(8)
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    sourceSets {
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }

    dependencies {
        commonMainImplementation("com.soywiz.korlibs.krypto:krypto:3.4.0")
    }
}


