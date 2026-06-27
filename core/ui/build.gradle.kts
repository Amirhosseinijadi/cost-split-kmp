plugins {
    id("costsplit.kmp.library")
    id("costsplit.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(compose.foundation)
            api(compose.material3)
            api(compose.runtime)
            api(compose.ui)
        }
    }
}

android {
    namespace = "com.costsplit.core.ui"
}
