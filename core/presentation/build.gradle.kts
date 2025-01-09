import dependencis.DependencyVersions
import dependencis.androidTestImpl
import dependencis.androidX
import dependencis.debugImpl
import dependencis.domainModule
import dependencis.testImpl
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.demo.presentation"

  composeOptions {
    kotlinCompilerExtensionVersion = DependencyVersions.KOTLIN_COMPILER
  }

  buildFeatures {
    compose = true
  }
}

dependencies {
  androidX()
  domainModule()

  testImpl()
  androidTestImpl()
  debugImpl()
}
