
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-android")
    id("kotlin-kapt")
}



android {
    compileSdkVersion(Config.BaseAndroid.compileSdkVersion)
    buildToolsVersion(Config.BaseAndroid.buildToolsVersion)

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    defaultConfig {

        minSdkVersion(Config.BaseAndroid.minSdkVersion)
        targetSdkVersion(Config.BaseAndroid.targetSdkVersion)

        testInstrumentationRunner = Config.BaseAndroid.testInstrumentationRunner
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
}

dependencies {
    implementation(Lib.Koin.koinAndroid)
    implementation(Lib.AndroidX.Navigation.fragment)
    implementation(Lib.AndroidX.Navigation.ui)
    implementation(Lib.AndroidX.ConstraintLayout.constraintLayout)

    implementation(Lib.LottieAnimation.lottie)
}