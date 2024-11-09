import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.LibraryProductFlavor
import org.gradle.api.NamedDomainObjectContainer

sealed class BuildFlavour(val name: String) {

    // used for apps
    abstract fun create(action: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor

    // used for modules
    abstract fun createLibrary(action: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor
}