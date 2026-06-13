import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("costsplit.kmp.application")
    id("costsplit.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:network"))
            implementation(project(":feature:expenses"))
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
        }
        androidMain.dependencies { implementation(libs.androidx.activity.compose) }
        desktopMain.dependencies { implementation(compose.desktop.currentOs) }
    }
}

android {
    namespace = "com.costsplit.app"
    defaultConfig {
        applicationId = "com.costsplit.app"
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "BASE_URL", "\"http://10.0.2.2:8080/\"")
    }
}

compose.desktop {
    application {
        mainClass = "com.costsplit.app.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Cost Split"
            packageVersion = "1.0.0"
        }
    }
}
