buildscript{

    val kotlin_version by extra(Lib.Kotlin.version)
    repositories{
        google()
        jcenter()
    }
    dependencies{

        classpath("com.android.tools.build:gradle:7.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    }
}
allprojects {
    repositories {
        google()
        jcenter()
    }
}
tasks.create<Delete>("clean"){
    delete(rootProject.buildDir)
}
