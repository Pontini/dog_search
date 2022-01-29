
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-android")
    id("kotlin-kapt")
}
apply(from = "$rootDir/ktlint.gradle")

android {
    compileSdkVersion(Config.App.compileSdkVersion)
    buildToolsVersion(Config.App.buildToolsVersion)

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    defaultConfig {
        applicationId = Config.App.applicationId
        minSdkVersion(Config.App.minSdkVersion)
        targetSdkVersion(Config.App.targetSdkVersion)
        versionCode = Config.App.versionCode
        versionName = Config.App.versionName

        testInstrumentationRunner = Config.App.testInstrumentationRunner
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

    kotlinOptions {
        jvmTarget = "1.8"
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
}

dependencies {

    implementation(project(mapOf("path" to ":dog:impl")))
    implementation(project(mapOf("path" to ":network")))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")

    implementation(Lib.AndroidX.ConstraintLayout.constraintLayout)
    implementation(Lib.AndroidX.AppCompat.appcompat)
    implementation(Lib.AndroidX.Lifecycle.livedata)
    implementation(Lib.AndroidX.Lifecycle.viewmodel)
    implementation(Lib.AndroidX.Navigation.fragment)
    implementation(Lib.AndroidX.Navigation.ui)

    implementation(Lib.Material.material)

    implementation(Lib.Kotlin.stdlib)

    implementation(Lib.Koin.koinCore)
    implementation(Lib.Koin.koinTest)
    implementation(Lib.Koin.koinAndroid)

    implementation(Lib.LottieAnimation.lottie)

}