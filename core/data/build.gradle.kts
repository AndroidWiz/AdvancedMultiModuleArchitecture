import dependencis.androidTestImpl
import dependencis.debugImpl
import dependencis.testImpl
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.demo.data"
}

dependencies {
  testImpl()
  androidTestImpl()
  debugImpl()
}
