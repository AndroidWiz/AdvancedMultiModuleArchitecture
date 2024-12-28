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
}

apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.demo.login"
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
