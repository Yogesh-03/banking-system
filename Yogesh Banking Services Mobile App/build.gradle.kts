// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories{
        google()
        mavenCentral()
    }
    dependencies {
        //classpath("com.android.tools.build:gradle:8.0.2")  // Ensure this is up-to-date
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21")
        //classpath("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    alias(libs.plugins.hilt.android) apply false


}