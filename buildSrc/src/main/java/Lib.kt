
object Lib {

    object Kotlin {

        const val version = "1.4.0"

        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"

        const val coroutines ="org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"

        const val  coroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"

        const val coroutinesTest ="org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2"

    }
    
    object Test {
        object Kotlinx {
            private const val version = "1.5.1"
            const val kotlinx = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }

        object Junit {
            private const val version = "4.12"

            const val junit = "junit:junit:$version"
        }

        object Mockito {
            private const val mockitoVersion = "2.27.0"

            private const val mockitoKotlinVersion = "2.1.0"

            const val mockito = "org.mockito:mockito-core:$mockitoVersion"

            const val mockitoKotlin =
                "com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoKotlinVersion"
        }

        object Mockk {
            private const val version = "1.12.0"

            const val mockk = "io.mockk:mockk:$version"
        }

    }

    object AndroidX {
        private const val navigationVersion = "2.3.4"

        object Navigation {
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
            const val ui = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
        }

        object Test {

            private const val version = "1.1.0"

            const val runner = "androidx.test:runner:$version"
            const val core = "androidx.test:core:$version"
            const val extJunit = "androidx.test.ext:junit-ktx:$version"

        }

        object Arch {
            private const val version = "2.1.0"
            const val core = "androidx.arch.core:core-testing:$version"
        }

        object Espresso {
            private const val version = "3.1.0"
            const val core = "androidx.test.espresso:espresso-core:$version"
            const val contrib = "androidx.test.espresso:espresso-contrib:$version"
        }

        object Lifecycle {
            private const val viewModelVersion = "2.0.0"
            private const val liveDataVersion = "2.3.0"

            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$liveDataVersion"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$viewModelVersion"
        }

        object AppCompat {
            private const val version = "1.3.1"

            const val appcompat = "androidx.appcompat:appcompat:$version"
        }

        object ConstraintLayout{
            private const val version = "2.0.4"

            const val constraintLayout = "androidx.constraintlayout:constraintlayout:$version"
        }
    }


    object Room {
        private const val roomVersion = "2.4.0"

        const val runtime = "androidx.room:room-runtime:$roomVersion"
        const val compiler = "androidx.room:room-compiler:$roomVersion"
        const val ktx ="androidx.room:room-ktx:2.3.0"
    }

    object Material{
        private const val version = "1.4.0"

        const val material = "com.google.android.material:material:$version"
    }

    object LottieAnimation{
        private const val lottieVersion  = "4.0.0"

        const val lottie = "com.airbnb.android:lottie:$lottieVersion"
    }

    object Retrofit{
        private const val version="2.9.0"

        const val retrofit2 = "com.squareup.retrofit2:retrofit:$version"

    }

    object Gson{
        private const val version="2.3.0"
        const val converterGson ="com.squareup.retrofit2:converter-gson:$version"
        const val scalarsGson = "com.squareup.retrofit2:converter-scalars:2.6.0"

    }

    object Okhttp{
        private const val version ="4.9.0"
        const val okhttp ="com.squareup.okhttp3:okhttp:$version"
        const val okhttpInterceptor ="com.squareup.okhttp3:logging-interceptor:4.7.2"
    }

    object Koin{
        private const val version="3.1.2"

        const val koinCore = "io.insert-koin:koin-core:$version"
        const val koinTest = "io.insert-koin:koin-test:$version"
        const val koinAndroid = "io.insert-koin:koin-android:$version"
        const val extAndroid = "io.insert-koin:koin-android-ext:$version"
    }

    object Glide{
        private const val version ="4.11.0"
        const val glide ="com.github.bumptech.glide:glide:$version"
    }


}

