import dependencis.androidTestImpl
import dependencis.dataStore
import dependencis.debugImpl
import dependencis.kotlinxSerialization
import dependencis.testImpl
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.demo.datastore"
}

dependencies {
  dataStore()
  kotlinxSerialization()

  testImpl()
  androidTestImpl()
  debugImpl()
}
