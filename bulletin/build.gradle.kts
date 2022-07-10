import Dependencies.composeFoundation
import Dependencies.composeMaterial
import Dependencies.composeRuntime
import Dependencies.composeTooling
import Dependencies.composeToolingPreview
import Dependencies.coreKtx
import Dependencies.desugar
import Dependencies.loremKotlinum
import Dependencies.mockWebServer
import Dependencies.mockitoCore
import Dependencies.mockitoKotlin
import Dependencies.okhttp
import Dependencies.okhttpBom
import Dependencies.testCoroutines
import Dependencies.testJson
import Dependencies.testJunit
import Dependencies.testTruth
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    id("maven-publish")
    id("signing")
}

apply(from = "$rootDir/scripts/publish-root.gradle.kts")

group = Configuration.groupId
version = Configuration.versionName

android {

    namespace = "com.tobianoapps.bulletin"

    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            freeCompilerArgs  = listOf(*kotlinOptions.freeCompilerArgs.toTypedArray(), "-opt-in=kotlin.RequiresOptIn")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }
}

dependencies {

    /*** COMPOSE ***/
    implementation(composeMaterial)
    implementation(composeFoundation)
    implementation(composeRuntime)
    implementation(composeTooling)
    implementation(composeToolingPreview)
    implementation(coreKtx)

    implementation(platform(okhttpBom))
    implementation(okhttp)

    /*** Text Generator ***/
    implementation(loremKotlinum)

    /*** JAVA 8+ APIS ANDROID SDK < 26 ***/
    coreLibraryDesugaring(desugar)

    /*** TEST ***/
    testImplementation(testJunit)
    testImplementation(testTruth)
    testImplementation(testJson)
    testImplementation(testCoroutines)
    testImplementation(mockitoCore)
    testImplementation(mockitoKotlin)
    testImplementation(mockWebServer)

}

val sourcesJar = task<Jar>("androidSourcesJar") {
    archiveClassifier.set("sources")
    from(kotlin.sourceSets["main"].kotlin.srcDirs)

}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = Configuration.groupId
                artifactId = Configuration.artifactId
                version = Configuration.versionName

                artifact(sourcesJar).apply {
                    classifier = "sources"
                }

                // Metadata
                pom {
                    name.set(artifactId)
                    description.set("Easy changelog with Jetpack Compose")
                    url.set("https://github.com/sebastinto/bulletin")
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("sebastinto")
                            name.set("Sebastien Victor Pinto")
                            email.set("sebastienvictorpinto@gmail.com")
                        }
                    }

                    // VCS info
                    scm {
                        connection.set("scm:git:github.com/sebastinto/bulletin.git")
                        developerConnection.set("scm:git:ssh://github.com/sebastinto/bulletin.git")
                        url.set("https://github.com/sebastinto/bulletin/tree/main")
                    }
                }
            }
        }
    }
}

signing {
    val signingKeyId: String by extra
    val signingPassword: String by extra
    val signingKey: String by extra
    useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
    sign(publishing.publications)
}