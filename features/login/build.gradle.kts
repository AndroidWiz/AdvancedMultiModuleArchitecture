import dependencies.androidTestImpl
import dependencies.androidX
import dependencies.debugImpl
import dependencies.hilt
import dependencies.room
import dependencies.testImpl
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

  testImpl()
  androidTestImpl()
  debugImpl()
}
