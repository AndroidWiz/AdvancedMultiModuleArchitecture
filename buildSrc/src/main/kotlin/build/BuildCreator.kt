package build

import extensions.buildConfigBooleanField
import extensions.buildConfigIntField
import extensions.buildConfigStringField
import com.android.build.api.dsl.ApplicationBuildType
import extensions.getLocalProperty
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

sealed class BuildCreator(val name: String) {

    abstract fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType

    class Debug(private val project: Project) : BuildCreator(BuildTypes.DEBUG) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType {
            return namedDomainObjectContainer.getByName(name) {
                isMinifyEnabled = Build.Debug.isMinifyEnabled
                enableUnitTestCoverage = Build.Debug.isEnabledUnitTestCoverage
                isDebuggable = Build.Debug.isDebuggable
                applicationIdSuffix = Build.Debug.applicationIdSuffix
                versionNameSuffix = Build.Debug.versionNameSuffix

                buildConfigStringField(name = BuildVariables.BASE_URL, value = project.getLocalProperty("dev.debug_endpoint"))
                buildConfigIntField(name = BuildVariables.DB_VERSION, value = project.getLocalProperty("dev.db_version"))
                buildConfigBooleanField(name = BuildVariables.CAN_CLEAR_CACHE, value = project.getLocalProperty("dev.clear_cache"))
                buildConfigStringField(name = BuildVariables.MAP_KEY, value = project.getLocalProperty("dev.map_key"))
            }
        }
    }

    class Release(private val project: Project) : BuildCreator(BuildTypes.RELEASE) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType {
            return namedDomainObjectContainer.getByName(name) {
                isMinifyEnabled = Build.Release.isMinifyEnabled
                enableUnitTestCoverage = Build.Release.isEnabledUnitTestCoverage
                isDebuggable = Build.Release.isDebuggable

                buildConfigStringField(name = BuildVariables.BASE_URL, value = project.getLocalProperty("dev.prod_endpoint"))
                buildConfigIntField(name = BuildVariables.DB_VERSION, value = project.getLocalProperty("dev.db_version"))
                buildConfigBooleanField(name = BuildVariables.CAN_CLEAR_CACHE, value = project.getLocalProperty("dev.clear_cache"))
                buildConfigStringField(name = BuildVariables.MAP_KEY, value = project.getLocalProperty("release.map_key"))
            }
        }
    }

    class Qa(private val project: Project) : BuildCreator(BuildTypes.QA) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType {
            return namedDomainObjectContainer.create(name) {
                isMinifyEnabled = Build.QA.isMinifyEnabled
                enableUnitTestCoverage = Build.QA.isEnabledUnitTestCoverage
                isDebuggable = Build.QA.isDebuggable
                applicationIdSuffix = Build.QA.applicationIdSuffix
                versionNameSuffix = Build.QA.versionNameSuffix

                buildConfigStringField(name = BuildVariables.BASE_URL, value = project.getLocalProperty("dev.qa_endpoint"))
                buildConfigIntField(name = BuildVariables.DB_VERSION, value = project.getLocalProperty("dev.db_version"))
                buildConfigBooleanField(name = BuildVariables.CAN_CLEAR_CACHE, value = project.getLocalProperty("dev.clear_cache"))
                buildConfigStringField(name = BuildVariables.MAP_KEY, value = project.getLocalProperty("dev.map_key"))
            }
        }
    }

}