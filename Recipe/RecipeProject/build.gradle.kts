// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
    }
    dependencies {

        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
        classpath("com.google.gms:google-services:4.4.0")

    }
}

plugins {
    id("com.google.gms.google-services") version "4.3.15" apply false
    id("com.android.application") version "8.0.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("com.android.library") version "8.0.0" apply false
}