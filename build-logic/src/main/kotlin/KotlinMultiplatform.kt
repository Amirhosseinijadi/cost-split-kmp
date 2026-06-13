import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform() {
    extensions.configure<KotlinMultiplatformExtension> {
        jvmToolchain(17)

        androidTarget {
            compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
        }
        jvm("desktop") {
            compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
        }
        iosX64()
        iosArm64()
        iosSimulatorArm64()

        applyDefaultHierarchyTemplate()
    }
}

