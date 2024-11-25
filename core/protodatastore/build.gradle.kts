import dependencis.androidTestImpl
import dependencis.debugImpl
import dependencis.protoDataStore
import dependencis.testImpl
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.demo.protodatastore"
}

dependencies {
  protoDataStore()
  testImpl()
  androidTestImpl()
  debugImpl()
}
