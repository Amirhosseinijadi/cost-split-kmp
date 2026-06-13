import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class KmpApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        pluginManager.apply("com.android.application")

        configureKotlinMultiplatform()
        extensions.configure<ApplicationExtension> {
            compileSdk = requiredVersion("android-compileSdk")
            defaultConfig {
                minSdk = requiredVersion("android-minSdk")
                targetSdk = requiredVersion("android-targetSdk")
            }
            buildFeatures {
                buildConfig = true
            }
            packaging {
                resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}

