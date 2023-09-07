@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("fakestorecompose.android.application")
    id("fakestorecompose.android.application.compose")
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
//    id("fakestorecompose.android.room")
}

android {
    namespace = libs.versions.projectApplicationId.get()
//    compileSdk = libs.versions.projectCompileSdkVersion.get().toInt()

    defaultConfig {
        libs.versions.apply {
            applicationId = projectApplicationId.get()
//            minSdk = projectMinSdkVersion.get().toInt()
//            targetSdk = projectTargetSdkVersion.get().toInt()
            versionCode = projectVersionCode.get().toInt()
            versionName = projectVersionName.get()
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val release by getting {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_17
//        targetCompatibility = JavaVersion.VERSION_17
//    }
//    kotlinOptions {
//        jvmTarget = JavaVersion.VERSION_17.toString()
//    }
//    buildFeatures {
//        compose = true
//    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = libs.versions.runner.get()
//    }
//    packaging {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }
}

dependencies {

    libs.apply {
        implementation(androidx.core.ktx)

        //compose
        implementation(platform(compose.bom))
        implementation(bundles.compose)
        implementation(compose.activity)
        //coil
        implementation(compose.coil)
        //google fonts
        implementation(compose.google.fonts)

        //lifecycle
        implementation(bundles.lifecycle)

        //ktor
        implementation(bundles.ktor)

        //navigation
        implementation(androidx.navigation.compose)

        //coroutines
        implementation(kotlinx.coroutines.core)
        implementation(kotlinx.coroutines.android)

        //koin
        implementation(koin.androidx.compose)

        //data store
        implementation(androidx.datastore)

        //kotlinx serialization
        implementation(kotlinx.serialization.json)

        //tests
        testImplementation(junit)
        androidTestImplementation(junit.ext)
        androidTestImplementation(androidx.espresso.core)
        androidTestImplementation(runner)
        androidTestImplementation(platform(compose.bom))

        //work manager
        implementation(androidx.startup.runtime)
        implementation(androidx.work.runtime)

        //collections immutable
        implementation(kotlinx.collections.immutable)

        //room
        implementation(room.runtime)
        implementation(room.ktx)
        implementation(room.paging)
        ksp(room.compiler)
    }

//    implementation("androidx.core:core-ktx:1.10.1")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
//    implementation("androidx.activity:activity-compose:1.7.2")
//    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
//    implementation("androidx.compose.ui:ui")
//    implementation("androidx.compose.ui:ui-graphics")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    implementation("androidx.compose.material3:material3")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
//    debugImplementation("androidx.compose.ui:ui-tooling")
//    debugImplementation("androidx.compose.ui:ui-test-manifest")

}