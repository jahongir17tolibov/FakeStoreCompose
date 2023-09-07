@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `kotlin-dsl`
}

group = "com.jt17.fakestorecompose.fakestorecompose.catalog"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "fakestorecompose.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "fakestorecompose.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

//        register("androidLibrary") {
//            id = "fakestorecompose.android.library"
//            implementationClass = "AndroidLibraryConventionPlugin"
//        }
//
//        register("androidLibraryCompose") {
//            id = "fakestorecompose.android.library.compose"
//            implementationClass = "AndroidLibraryComposeConventionPlugin"
//        }
//
//        register("androidPresentationLayer") {
//            id = "fakestorecompose.android.feature.presentation"
//            implementationClass = "AndroidPresentationLayerConventionPlugin"
//        }
//
//        register("androidRoom") {
//            id = "fakestorecompose.android.room"
//            implementationClass = "AndroidRoomConventionPlugin"
//        }

    }
}