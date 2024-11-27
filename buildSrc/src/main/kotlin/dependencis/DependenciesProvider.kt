package dependencis

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import test.TestDependencies

fun DependencyHandler.androidX() {
    implementation(Dependencies.ANDROIDX_CORE_KTX)
    implementation(Dependencies.ANDROIDX_LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.ANDROIDX_ACTIVITY_COMPOSE)
    implementation(Dependencies.ANDROIDX_UI)
    implementation(Dependencies.ANDROIDX_UI_GRAPHICS)
    implementation(Dependencies.ANDROIDX_UI_TOOLING_PREVIEW)
    implementation(Dependencies.ANDROIDX_MATERIAL3)
    implementation(Dependencies.WORK_RUNTIME)
}

fun DependencyHandler.testImpl() {
    testImplementation(TestDependencies.JUNIT)
}

fun DependencyHandler.androidTestImpl() {
    androidTestImplementation(TestDependencies.ANDROIDX_JUNIT)
    androidTestImplementation(TestDependencies.ANDROIDX_ESPRESSO_CORE)
    androidTestImplementation(TestDependencies.ANDROIDX_COMPOSE_UI_TEST_JUNIT4)
}

fun DependencyHandler.debugImpl() {
    debugImplementation(Dependencies.ANDROIDX_UI_TOOLING)
    debugImplementation(TestDependencies.ANDROIDX_COMPOSE_UI_TEST_MANIFEST)
}

fun DependencyHandler.room() {
    implementation(Dependencies.ROOM_RUNTIME)
    implementation(Dependencies.ROOM_KTX)
    kapt(Dependencies.ROOM_COMPILER)
}

fun DependencyHandler.dataStore() {
    implementation(Dependencies.DATA_STORE)
    implementation(Dependencies.KOTLIN_COLLECTION)
    implementation(Dependencies.KOTLIN_SERIALIZATION)
}

fun DependencyHandler.protoDataStore() {
    implementation(Dependencies.DATA_STORE)
    implementation(Dependencies.PROTO_BUFF_JAVA_LITE)
    implementation(Dependencies.PROTO_BUFF_KOTLIN_LITE)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER_GSON)
    implementation(Dependencies.RETROFIT_KOTLIN_COROUTINES_ADAPTER)
}

fun DependencyHandler.okHttp() {
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_LOGGING_INTERCEPTOR)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.HILT_ANDROID)
    implementation(Dependencies.HILT_WORK)
    implementation(Dependencies.HILT_NAVIGATION)
    kapt(Dependencies.HILT_COMPILER)
    kapt(Dependencies.HILT_AGP)
    kapt(Dependencies.HILT_COMPILER_KAPT)
}

fun DependencyHandler.loginModule() {
    moduleImplementation(project(":features:login"))
}

fun DependencyHandler.homeModule() {
    moduleImplementation(project(":features:home"))
}

fun DependencyHandler.dataModule() {
    moduleImplementation(project(":core:data"))
}

fun DependencyHandler.domainModule() {
    moduleImplementation(project(":core:domain"))
}

fun DependencyHandler.presentationModule() {
    moduleImplementation(project(":core:presentation"))
}

fun DependencyHandler.datastoreModule() {
    moduleImplementation(project(":core:datastore"))
}

fun DependencyHandler.protoDatastoreModule() {
    moduleImplementation(project(":core:protodatastore"))
}