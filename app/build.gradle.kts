plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.senaaksoy.smartshop"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.senaaksoy.smartshop"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {

//coil
    implementation("io.coil-kt:coil-compose:2.6.0")
    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    // Gson
    implementation ("com.google.code.gson:gson:2.10.1")
    // Retrofit with Scalar Converter
    implementation(libs.converter.scalars)
    // Room
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.runtime.livedata)
    kapt(libs.androidx.room.compiler)

    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    // Coroutine components
    implementation(libs.androidx.lifecycle.viewmodel.ktx) // veya güncel sürüm
    //hilt
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt( libs.hilt.android.compiler.v2432)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
kapt {
    correctErrorTypes = true
}
