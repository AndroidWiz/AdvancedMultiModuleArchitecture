import build.BuildConfig
import build.BuildCreator
import build.BuildDimensions
import dependencies.DependencyVersions
import dependencies.androidTestImpl
import dependencies.androidX
import dependencies.debugImpl
import dependencies.hilt
import dependencies.loginModule
import dependencies.okHttp
import dependencies.retrofit
import dependencies.room
import dependencies.testImpl
import flavors.BuildFlavor
import release.ReleaseConfig
import signing.BuildSigning
import signing.SigningTypes
import test.TestBuildConfig

plugins {
  id(plugs.BuildPlugins.ANDROID_APPLICATION)
  id(plugs.BuildPlugins.KOTLIN_ANDROID)
  id(plugs.BuildPlugins.ANDROID)
  id(plugs.BuildPlugins.KAPT)
  id(plugs.BuildPlugins.KTLINT)
  id(plugs.BuildPlugins.SPOTLESS)
  id(plugs.BuildPlugins.DETEKT)
  id(plugs.BuildPlugins.UPDATE_DEPENDENCY_VERSIONS)
  id(plugs.BuildPlugins.DOKKA)
}

android {
  namespace = BuildConfig.APP_ID
  compileSdk = BuildConfig.COMPILE_SDK_VERSION

  defaultConfig {
    applicationId = BuildConfig.APP_ID
    minSdk = BuildConfig.MIN_SDK_VERSION
    targetSdk = BuildConfig.TARGET_SDK_VERSION
    versionCode = ReleaseConfig.VERSION_CODE
    versionName = ReleaseConfig.VERSION_NAME

    testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
    vectorDrawables.useSupportLibrary = true
  }

  signingConfigs {
    BuildSigning.Debug(project).create(this)
    BuildSigning.Release(project).create(this)
    BuildSigning.Qa(project).create(this)
  }

  buildTypes {
    BuildCreator.Debug(project).create(this).apply {
      signingConfig = signingConfigs.getByName(SigningTypes.DEBUG)
    }

    BuildCreator.Release(project).create(this).apply {
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
      )
      signingConfig = signingConfigs.getByName(SigningTypes.RELEASE)
    }

    BuildCreator.Qa(project).create(this).apply {
      signingConfig = signingConfigs.getByName(SigningTypes.QA)
    }
  }

  // flavor types
  flavorDimensions.add(BuildDimensions.APP)
  flavorDimensions.add(BuildDimensions.STORE)
  productFlavors {
    BuildFlavor.Google.create(this)
    BuildFlavor.Huawei.create(this)
    BuildFlavor.Driver.create(this)
    BuildFlavor.User.create(this)
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    compose = true
    buildConfig = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = DependencyVersions.KOTLIN_COMPILER
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  loginModule()
  androidX()
  hilt()
  room()
  okHttp()
  retrofit()

  testImpl()
  androidTestImpl()
  debugImpl()
}
