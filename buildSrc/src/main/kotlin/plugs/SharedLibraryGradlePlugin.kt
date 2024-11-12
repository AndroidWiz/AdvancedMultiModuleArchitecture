package plugs

import build.BuildConfig
import build.BuildDimensions
import com.android.build.api.dsl.LibraryExtension
import flavors.BuildFlavor
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.withType
import test.TestBuildConfig

class SharedLibraryGradlePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.addPluginConfigurations()
        target.addAndroidConfigurations()
        target.addKotlinOptions()
    }

    private fun Project.addPluginConfigurations() {
        plugins.apply(BuildPlugins.KOTLIN_ANDROID)
        plugins.apply(BuildPlugins.KAPT)
    }

    private fun Project.addAndroidConfigurations() {
        extensions.getByType(LibraryExtension::class.java).apply {
            compileSdk = BuildConfig.COMPILE_SDK_VERSION

            defaultConfig {
                minSdk = BuildConfig.MIN_SDK_VERSION
                testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
            }

            // flavor types
            flavorDimensions.add(BuildDimensions.APP)
            flavorDimensions.add(BuildDimensions.STORE)
            productFlavors {
                BuildFlavor.Google.createLibrary(this)
                BuildFlavor.Huawei.createLibrary(this)
                BuildFlavor.Driver.createLibrary(this)
                BuildFlavor.User.createLibrary(this)
            }

            buildFeatures {
                compose = true
                buildConfig = true
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
        }
    }

    private fun Project.addKotlinOptions() {
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }
}