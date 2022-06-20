import Dependencies.activityCompose
import Dependencies.appcompat
import Dependencies.composeFoundation
import Dependencies.composeMaterial
import Dependencies.composeRuntime
import Dependencies.composeTooling
import Dependencies.composeToolingPreview
import Dependencies.constraintLayout
import Dependencies.coreKtx
import Dependencies.desugar
import Dependencies.loremKotlinum
import Dependencies.material
import Dependencies.navigationCompose
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = Configuration.compileSdk

    defaultConfig {
        applicationId = "com.tobianoapps.bulletin.sample"
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.sampleVersionCode
        versionName = Configuration.sampleVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            /* Uncomment the line below to test release builds locally. Performance is significantly
             improved compared to debug builds. */
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
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

    /*** LIB ***/
    implementation(project(mapOf("path" to ":bulletin")))

    /*** CORE ***/
    implementation(material)
    implementation(coreKtx)
    implementation(appcompat)
    implementation(constraintLayout)

    debugImplementation(composeTooling) // fix broken preview

    /*** COMPOSE ***/
    implementation(composeMaterial)
    implementation(composeFoundation)
    implementation(composeRuntime)
    implementation(composeTooling)
    implementation(composeToolingPreview)
    implementation(activityCompose)
    implementation(navigationCompose)

    /*** Text Generator */
    implementation(loremKotlinum)

    /* JAVA 8+ APIS ANDROID SDK < 26 */
    coreLibraryDesugaring(desugar)

}