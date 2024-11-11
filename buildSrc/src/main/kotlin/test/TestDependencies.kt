package test

import dependencies.DependencyVersions

object TestDependencies {

    const val JUNIT = "junit:junit:${DependencyVersions.JUNIT}"
    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${DependencyVersions.JUNIT_VERSION}"
    const val ANDROIDX_ESPRESSO_CORE =
        "androidx.test.espresso:espresso-core:${DependencyVersions.ESPRESSO_CORE}"

    const val ANDROIDX_COMPOSE_UI_TEST_MANIFEST =
        "androidx.compose.ui:ui-test-manifest:${DependencyVersions.COMPOSE_UI}"
    const val ANDROIDX_COMPOSE_UI_TEST_JUNIT4 =
        "androidx.compose.ui:ui-test-junit4:${DependencyVersions.COMPOSE_UI}"
}