import dependencis.androidTestImpl
import dependencis.dataStore
import dependencis.debugImpl
import dependencis.hilt
import dependencis.okHttp
import dependencis.retrofit
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
  retrofit()
  okHttp()
  hilt()
  dataStore()

  testImpl()
  androidTestImpl()
  debugImpl()
}
