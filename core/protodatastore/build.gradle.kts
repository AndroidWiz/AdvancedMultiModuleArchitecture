import dependencis.Dependencies.PROTO_BUFF_ARTIFACT
import dependencis.androidTestImpl
import dependencis.debugImpl
import dependencis.protoDataStore
import dependencis.testImpl
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
  id(plugs.BuildPlugins.GOOGLE_PROTO_BUF)
}

apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.demo.protodatastore"

  protobuf {
    protoc {
      artifact = PROTO_BUFF_ARTIFACT
    }
    generateProtoTasks {
      all().forEach { task ->
        task.plugins {
          create("kotlin").apply {
            option("lite")
          }
        }
        task.plugins {
          create("java").apply {
            option("lite")
          }
        }
      }
    }
  }
}

dependencies {
  protoDataStore()
  testImpl()
  androidTestImpl()
  debugImpl()
}
