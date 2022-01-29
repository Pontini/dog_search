
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-android")
    id("kotlin-kapt")
}



android {
    compileSdkVersion(Config.Dog.compileSdkVersion)
    buildToolsVersion(Config.Dog.buildToolsVersion)

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    defaultConfig {

        minSdkVersion(Config.Dog.minSdkVersion)
        targetSdkVersion(Config.Dog.targetSdkVersion)

        testInstrumentationRunner = Config.Dog.testInstrumentationRunner
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles (
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }


    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/*.kotlin_module")
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(mapOf("path" to ":network")))
    implementation(project(mapOf("path" to ":dog:publ")))
    implementation(project(mapOf("path" to ":base_android")))
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":base_android")))

    implementation(Lib.Koin.koinAndroid)

    implementation(Lib.Glide.glide)

    implementation(Lib.Retrofit.retrofit2)

    implementation(Lib.Gson.converterGson)

    implementation (Lib.Room.runtime)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt (Lib.Room.compiler)
    implementation (Lib.Room.ktx)

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

    implementation(Lib.Test.Junit.junit)
    implementation(Lib.Test.Mockito.mockito)
    implementation(Lib.Test.Mockito.mockitoKotlin)
    implementation(Lib.Test.Mockk.mockk)
    testImplementation(Lib.Test.Kotlinx.kotlinx)
    
    testImplementation (Lib.Kotlin.coroutinesTest)

    implementation( "com.squareup.moshi:moshi:1.8.0")
    kapt ("com.squareup.moshi:moshi-kotlin-codegen:1.8.0")

}