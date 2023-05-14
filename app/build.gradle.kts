plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.base"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
        compose = true
    }
}

dependencies {

    //AndroidX
    implementation(Dependency.AndroidX.APP_COMPAT)
    implementation(Dependency.AndroidX.MATERIAL)
    implementation(Dependency.AndroidX.CONSTRAINT_LAYOUT)
    implementation(Dependency.AndroidX.RECYCLERVIEW)

    //KTX
    implementation(Dependency.KTX.CORE)
    implementation(Dependency.KTX.ACTIVITY_KTX)
    implementation(Dependency.KTX.FRAGMENT_KTX)

    // Compose
    implementation(Dependency.Compose.ACTIVITY_COMPOSE)
    implementation(Dependency.Compose.MATERIAL)
    implementation(Dependency.Compose.RUNTIME)
    implementation(Dependency.Compose.UI)
    debugImplementation(Dependency.Compose.UI_TOOLING)
    implementation(Dependency.Compose.PREVIEW)
    implementation(Dependency.Hilt.HILT_NAVIGATION)
    implementation(Dependency.Lifecycle.VIEWMODEL_COMPOSE)
    implementation(Dependency.Lifecycle.RUNTIME_COMPOSE)
    implementation(Dependency.Paging.COMPOSE)
    implementation(Dependency.Compose.COMPOSE_CONSTRAINT)
    implementation(Dependency.Compose.NAVIGATION)

    // Retrofit
    implementation(Dependency.Retrofit.RETROFIT)
    implementation(Dependency.Retrofit.GSON_CONVERTER)
    implementation(Dependency.OkHttp.LOGGING_INTERCEPTOR)

    // Glide
    implementation(Dependency.Glide.GLIDE)

    // Timber
    implementation(Dependency.Timber.TIMBER)

    // Etc
    implementation(Dependency.Etc.RECYCLERVIEW_DIVIDER)
    implementation(Dependency.Etc.TED_PERMISSION)

    //TEST
    testImplementation(Dependency.Test.JUNIT)
    androidTestImplementation(Dependency.AndroidTest.TEST_RUNNER)
    androidTestImplementation(Dependency.AndroidTest.ESPRESSO_CORE)

    // Hilt
    implementation(Dependency.Hilt.ANDROID)
    kapt(Dependency.Hilt.COMPILER)
    testImplementation(Dependency.Hilt.TESTING)
}