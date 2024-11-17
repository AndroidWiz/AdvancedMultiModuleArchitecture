import dependencis.androidTestImpl
import dependencis.debugImpl
import dependencis.testImpl
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.demo.domain"
}

dependencies {
  testImpl()
  androidTestImpl()
  debugImpl()
}
