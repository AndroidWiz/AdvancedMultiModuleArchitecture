import dependencis.DependencyVersions
import dependencis.androidTestImpl
import dependencis.androidX
import dependencis.dataModule
import dependencis.debugImpl
import dependencis.domainModule
import dependencis.hilt
import dependencis.retrofit
import dependencis.room
import dependencis.testImpl
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
  id(plugs.BuildPlugins.HILT) version dependencis.DependencyVersions.HILT
}

apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.demo.login"

  composeOptions {
    kotlinCompilerExtensionVersion = DependencyVersions.KOTLIN_COMPILER
  }

  buildFeatures {
    compose = true
  }
}

dependencies {
  androidX()
  hilt()
  room()
  retrofit()
  dataModule()
  domainModule()

  testImpl()
  androidTestImpl()
  debugImpl()
}
