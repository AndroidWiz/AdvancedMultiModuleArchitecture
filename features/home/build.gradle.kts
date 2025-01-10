import dependencis.DependencyVersions
import dependencis.androidTestImpl
import dependencis.androidX
import dependencis.dataModule
import dependencis.debugImpl
import dependencis.domainModule
import dependencis.hilt
import dependencis.navigatorModule
import dependencis.presentationModule
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
  namespace = "com.demo.home"

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
  presentationModule()
  dataModule()
  domainModule()
  navigatorModule()

  testImpl()
  androidTestImpl()
  debugImpl()
}
