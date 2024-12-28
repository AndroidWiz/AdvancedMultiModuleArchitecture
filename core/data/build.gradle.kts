
import dependencis.androidTestImpl
import dependencis.chucker
import dependencis.dataStore
import dependencis.debugImpl
import dependencis.domainModule
import dependencis.hilt
import dependencis.okHttp
import dependencis.protoDatastoreModule
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
  domainModule()
  chucker()
  dataStore()
  protoDatastoreModule()

  testImpl()
  androidTestImpl()
  debugImpl()
}
