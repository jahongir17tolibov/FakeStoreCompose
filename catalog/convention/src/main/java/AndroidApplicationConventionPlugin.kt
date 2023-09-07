import com.android.build.api.dsl.ApplicationExtension
import com.jt17.convention.configureKotlinAndroid
import com.jt17.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = Integer.parseInt(libs.findVersion("projectTargetSdkVersion").get().toString())
            }
        }
    }
}