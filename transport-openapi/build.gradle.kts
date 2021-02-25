plugins {
    kotlin("jvm")
    id("org.openapi.generator")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {
    val kotlinVersion: String by project

    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("com.squareup.moshi:moshi-kotlin:1.9.2")
    implementation("com.squareup.moshi:moshi-adapters:1.9.2")
    implementation("com.squareup.okhttp3:okhttp:4.2.2")

    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.1.0")
}

openApiGenerate {

    // only models
    globalProperties.put("models", "")

    val basePackage = "${project.group}.openapi.channel"
    packageName.set(basePackage)
    generatorName.set("kotlin")
//    generatorName.set("kotlin-server")
    configOptions.apply {
        put("requestDateConverter", "toString")
        put("enumPropertyNaming", "UPPERCASE")
    }
    inputSpec.set("${rootProject.projectDir}/openapi/specs/messaging-channels.yml")
}

sourceSets.main {
    java.srcDirs("$buildDir/generate-resources/main/src/main/kotlin")
}

tasks {
    compileKotlin.get().dependsOn(openApiGenerate)
}
