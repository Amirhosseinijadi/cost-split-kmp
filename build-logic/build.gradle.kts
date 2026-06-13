plugins {
    `kotlin-dsl`
}

group = "com.costsplit.buildlogic"

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.compose.gradle.plugin)
    implementation(libs.kotlin.compose.compiler.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.kotlin.serialization.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("kmpLibrary") {
            id = "costsplit.kmp.library"
            implementationClass = "KmpLibraryConventionPlugin"
        }
        register("kmpApplication") {
            id = "costsplit.kmp.application"
            implementationClass = "KmpApplicationConventionPlugin"
        }
        register("composeMultiplatform") {
            id = "costsplit.compose.multiplatform"
            implementationClass = "ComposeMultiplatformConventionPlugin"
        }
        register("kotlinSerialization") {
            id = "costsplit.kotlin.serialization"
            implementationClass = "KotlinSerializationConventionPlugin"
        }
    }
}
