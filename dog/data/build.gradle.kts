plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
    implementation(project(mapOf("path" to ":network")))
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":dog:domain")))
    implementation(Lib.Gson.converterGson)
    implementation(Lib.Koin.koinCore)
    testImplementation(Lib.Koin.koinTest)
    testImplementation("junit:junit:4.13.2")
    implementation("com.squareup.moshi:moshi:1.8.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.8.0")
    implementation(Lib.Room.runtime)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(Lib.Room.compiler)
    implementation(Lib.Room.ktx)
}