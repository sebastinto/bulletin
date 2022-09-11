// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.2.2" apply false
    id("com.android.library") version "7.2.2" apply false
    kotlin("android") version "1.6.21" apply false
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

apply(from = "$rootDir/scripts/publish-root.gradle.kts")

// Sonatype repository setup
nexusPublishing {
    repositories {
        sonatype {
            val ossrhUsername: String by extra
            val ossrhPassword: String by extra
            val sonatypeStagingProfileId: String by extra
            stagingProfileId.set(sonatypeStagingProfileId)
            username.set(ossrhUsername)
            password.set(ossrhPassword)
            // needed for new Sonatype infrastructure
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}