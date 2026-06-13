import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class KmpLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        pluginManager.apply("com.android.library")

        configureKotlinMultiplatform()
        extensions.configure<LibraryExtension> {
            compileSdk = requiredVersion("android-compileSdk")
            defaultConfig {
                minSdk = requiredVersion("android-minSdk")
                consumerProguardFiles("consumer-rules.pro")
            }
        }
    }
}

