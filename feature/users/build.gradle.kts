plugins {
    id("costsplit.kmp.library")
    id("costsplit.kotlin.serialization")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":core:common"))
            implementation(project(":core:network"))
            implementation(libs.koin.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.client.core)
        }
    }
}

android {
    namespace = "com.costsplit.feature.users"
}
