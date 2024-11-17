import dependencies.androidTestImpl
import dependencies.androidX
import dependencies.debugImpl
import dependencies.hilt
import dependencies.testImpl
import test.TestBuildConfig

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
    id(plugs.BuildPlugins.KOTLIN_ANDROID)
    id(plugs.BuildPlugins.KAPT)
}

android {
    namespace = "com.demo.home"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    androidX()
    testImpl()
    androidTestImpl()
    debugImpl()

    hilt()
}