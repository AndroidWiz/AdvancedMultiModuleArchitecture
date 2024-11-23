import dependencis.androidTestImpl
import dependencis.dataStore
import dependencis.debugImpl
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

  testImpl()
  androidTestImpl()
  debugImpl()
}
