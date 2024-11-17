import dependencies.androidTestImpl
import dependencies.debugImpl
import dependencies.testImpl
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