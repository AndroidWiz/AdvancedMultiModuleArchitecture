import dependencis.androidTestImpl
import dependencis.androidX
import dependencis.debugImpl
import dependencis.hilt
import dependencis.testImpl
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.demo.navigator"
}

dependencies {
    androidX()
    hilt()

    testImpl()
    androidTestImpl()
    debugImpl()
}
