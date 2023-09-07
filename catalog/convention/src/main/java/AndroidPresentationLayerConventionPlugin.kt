import com.android.build.gradle.LibraryExtension
import com.jt17.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidPresentationLayerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("fakestorecompose.android.library")
                apply("fakestorecompose.android.library.compose")
            }

            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }

            dependencies {
                add("androidTestImplementation", libs.findLibrary("runner").get())
            }

        }
    }
}