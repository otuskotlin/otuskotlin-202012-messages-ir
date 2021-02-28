val serializationPluginVersion: String by project

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {

    jvm {
        withJava()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
//                api("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationPluginVersion")
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationPluginVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }
        val jvmTest by getting {
            dependencies {
                val junitVersion: String by project
                implementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
                implementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
                implementation("org.jetbrains.kotlin:kotlin-test-junit5")
            }
        }
    }
}

tasks.withType<Test>() {
    useJUnitPlatform()
}
