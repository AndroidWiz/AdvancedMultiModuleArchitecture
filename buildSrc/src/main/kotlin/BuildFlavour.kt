import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.LibraryProductFlavor
import org.gradle.api.NamedDomainObjectContainer

sealed class BuildFlavor(val name: String) {

    // used for apps
    abstract fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor

    // used for modules
    abstract fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor


    // flavor GOOGLE
    object Google : BuildFlavor(FlavorTypes.GOOGLE) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimensions.STORE
                applicationIdSuffix = ".${name.lowercase()}"
                versionNameSuffix = "-${name.lowercase()}"
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimensions.STORE
            }
        }

    }

    // flavor HUAWEI
    object Huawei : BuildFlavor(FlavorTypes.HUAWEI) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimensions.STORE
                applicationIdSuffix = ".${name.lowercase()}"
                versionNameSuffix = "-${name.lowercase()}"
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimensions.STORE
            }
        }

    }

    // flavor Driver
    object Driver : BuildFlavor(FlavorTypes.DRIVER) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimensions.APP
                applicationIdSuffix = ".${name.lowercase()}"
                versionNameSuffix = "-${name.lowercase()}"
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimensions.APP
            }
        }

    }

    // flavor User
    object User : BuildFlavor(FlavorTypes.USER) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimensions.APP
                applicationIdSuffix = ".${name.lowercase()}"
                versionNameSuffix = "-${name.lowercase()}"
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimensions.APP
            }
        }

    }
}