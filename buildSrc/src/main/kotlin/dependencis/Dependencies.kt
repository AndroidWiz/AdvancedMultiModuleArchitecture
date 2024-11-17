package dependencis

object Dependencies {

    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${DependencyVersions.CORE_KTX}"
    const val ANDROIDX_LIFECYCLE_RUNTIME_KTX =
        "androidx.lifecycle:lifecycle-runtime-ktx:${DependencyVersions.LIFECYCLE_RUNTIME_KTX}"
    const val ANDROIDX_ACTIVITY_COMPOSE =
        "androidx.activity:activity-compose:${DependencyVersions.ACTIVITY_COMPOSE}"
    const val ANDROIDX_UI = "androidx.compose.ui:ui:${DependencyVersions.COMPOSE_UI}"
    const val ANDROIDX_UI_GRAPHICS =
        "androidx.compose.ui:ui-graphics:${DependencyVersions.COMPOSE_UI}"
    const val ANDROIDX_UI_TOOLING =
        "androidx.compose.ui:ui-tooling:${DependencyVersions.COMPOSE_UI}"
    const val ANDROIDX_UI_TOOLING_PREVIEW =
        "androidx.compose.ui:ui-tooling-preview:${DependencyVersions.COMPOSE_UI}"
    const val ANDROIDX_MATERIAL3 =
        "androidx.compose.material3:material3:${DependencyVersions.MATERIAL3}"


    const val HILT_ANDROID = "com.google.dagger:hilt-android:${DependencyVersions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${DependencyVersions.HILT}"
    const val HILT_AGP = "com.google.dagger:hilt-android-gradle-plugin:${DependencyVersions.HILT}"
    const val HILT_WORK = "androidx.hilt:hilt-work:${DependencyVersions.HILT_COMPOSE}"
    const val HILT_COMPILER_KAPT = "androidx.hilt:hilt-compiler:${DependencyVersions.HILT_COMPOSE}"
    const val HILT_NAVIGATION = "androidx.hilt:hilt-navigation-compose:${DependencyVersions.HILT_COMPOSE}"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${DependencyVersions.RETROFIT}"
    const val RETROFIT_CONVERTER_GSON =
        "com.squareup.retrofit2:converter-gson:${DependencyVersions.RETROFIT}"
    const val RETROFIT_KOTLIN_COROUTINES_ADAPTER =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${DependencyVersions.RETROFIT_COROUTINES_ADAPTER_VERSION}"


    const val OKHTTP = "com.squareup.okhttp3:okhttp:${DependencyVersions.OKHTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${DependencyVersions.OKHTTP}"


    const val ROOM_RUNTIME = "androidx.room:room-runtime:${DependencyVersions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${DependencyVersions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${DependencyVersions.ROOM}"
}