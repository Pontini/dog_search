plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 24
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
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
}

dependencies {
    implementation(project(mapOf("path" to ":dog:domain")))
    implementation(project(mapOf("path" to ":network")))
    implementation(project(mapOf("path" to ":base_android")))
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":base_android")))
    implementation(Lib.Koin.koinAndroid)
    implementation(Lib.Glide.glide)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation(Lib.Material.material)
    implementation(Lib.AndroidX.ConstraintLayout.constraintLayout)
    implementation(Lib.AndroidX.Navigation.fragment)
    implementation(Lib.AndroidX.Navigation.ui)
    implementation(Lib.Koin.koinAndroid)
    implementation(Lib.Koin.koinTest)
    implementation(Lib.AndroidX.AppCompat.appcompat)
    implementation(Lib.AndroidX.Arch.core)
    implementation(Lib.AndroidX.Espresso.core)
    implementation(Lib.AndroidX.Espresso.contrib)
    implementation(Lib.AndroidX.Navigation.fragment)
    implementation(Lib.AndroidX.Navigation.ui)
    implementation(Lib.AndroidX.ConstraintLayout.constraintLayout)
    implementation(Lib.AndroidX.Test.runner)
    implementation(Lib.AndroidX.Test.core)
    implementation(Lib.AndroidX.Test.extJunit)
    testImplementation (Lib.Kotlin.coroutinesTest)
}