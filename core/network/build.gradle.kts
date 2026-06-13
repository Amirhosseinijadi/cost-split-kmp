plugins {
    id("costsplit.kmp.library")
    id("costsplit.kotlin.serialization")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.json)
            implementation(libs.kotlinx.serialization.json)
        }
        androidMain.dependencies { implementation(libs.ktor.client.okhttp) }
        desktopMain.dependencies { implementation(libs.ktor.client.java) }
        iosMain.dependencies { implementation(libs.ktor.client.darwin) }
    }
}

android {
    namespace = "com.costsplit.core.network"
}
