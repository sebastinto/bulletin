import java.io.FileInputStream
import java.util.*

// Create variables with empty default values
val propFile = File(rootProject.rootDir, "local.properties")
val prop = Properties().apply {
    if (propFile.exists()) {
        FileInputStream(propFile).use {
            load(it)
        }
    }
}

extra.apply {
    set("ossrhUsername", System.getenv("OSSRH_USERNAME") ?: prop.getProperty("ossrhUsername"))
    set("ossrhPassword", System.getenv("OSSRH_PASSWORD") ?: prop.getProperty("ossrhPassword"))
    set("sonatypeStagingProfileId", System.getenv("SONATYPE_STAGING_PROFILE_ID") ?: prop.getProperty("sonatypeStagingProfileId"))
    set("signingKeyId", System.getenv("SIGNING_KEY_ID") ?: prop.getProperty("signingKeyId"))
    set("signingPassword", System.getenv("SIGNING_PASSWORD") ?: prop.getProperty("signingPassword"))
    set("signingKey", System.getenv("SIGNING_KEY") ?: prop.getProperty("signingKey"))
}