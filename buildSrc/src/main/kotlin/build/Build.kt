package build

sealed class Build {
    open val isMinifyEnabled: Boolean = false
    open val isEnabledUnitTestCoverage: Boolean = false
    open val isDebuggable: Boolean = false
    open val applicationIdSuffix: String = ""
    open val versionNameSuffix: String = ""

    object Debug : Build() {
        override val isMinifyEnabled = false
        override val isEnabledUnitTestCoverage = true
        override val isDebuggable = true
        override val applicationIdSuffix = ".debug"
        override val versionNameSuffix = "-DEBUG"
    }

    object QA : Build() {
        override val isMinifyEnabled = false
        override val isEnabledUnitTestCoverage = true
        override val isDebuggable = false
        override val applicationIdSuffix = ".qa"
        override val versionNameSuffix = "-QA"
    }

    object Release : Build() {
        override val isMinifyEnabled = true
        override val isEnabledUnitTestCoverage = false
        override val isDebuggable = false
    }
}