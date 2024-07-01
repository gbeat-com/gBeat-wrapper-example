buildscript {
    dependencies {
        classpath("com.android.tools.build","gradle","8.2.0")
        classpath("org.jetbrains.kotlin", "kotlin-gradle-plugin", "1.9.0")
        classpath("com.google.dagger", "hilt-android-gradle-plugin", "2.50")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.

// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}
true // Needed to make the Suppress annotation work for the plugins block