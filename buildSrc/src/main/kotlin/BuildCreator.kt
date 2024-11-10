import com.android.build.api.dsl.ApkSigningConfig
import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

sealed class BuildCreator(val name: String) {

    abstract fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType

    class Debug(private val project: Project) : BuildCreator(BuildTypes.DEBUG) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType {
            return namedDomainObjectContainer.getByName(name){
                isMinifyEnabled = Build.Debug.isMinifyEnabled
                enableUnitTestCoverage = Build.Debug.isEnabledUnitTestCoverage
                isDebuggable = Build.Debug.isDebuggable
                applicationIdSuffix = Build.Debug.applicationIdSuffix
                versionNameSuffix = Build.Debug.versionNameSuffix
            }
        }
    }

    class Release(private val project: Project) : BuildCreator(BuildTypes.RELEASE) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType {
            return namedDomainObjectContainer.getByName(name){
                isMinifyEnabled = Build.Release.isMinifyEnabled
                enableUnitTestCoverage = Build.Release.isEnabledUnitTestCoverage
                isDebuggable = Build.Release.isDebuggable
            }
        }
    }

    class Qa(private val project: Project) : BuildCreator(BuildTypes.QA) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType {
            return namedDomainObjectContainer.create(name){
                isMinifyEnabled = Build.QA.isMinifyEnabled
                enableUnitTestCoverage = Build.QA.isEnabledUnitTestCoverage
                isDebuggable = Build.QA.isDebuggable
                applicationIdSuffix = Build.QA.applicationIdSuffix
                versionNameSuffix = Build.QA.versionNameSuffix
            }
        }
    }

}