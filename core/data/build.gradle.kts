import dependencis.androidTestImpl
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

  testImpl()
  androidTestImpl()
  debugImpl()
}
