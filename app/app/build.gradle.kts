plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.emilygranville.videogamelist"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.emilygranville.videogamelist"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }

}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    testRuntimeOnly(libs.junit.platform.launcher)
    //testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    //androidTestImplementation("androidx.test.ext:junit:1.2.1")
//
//    testImplementation(libs.junit.v5114)
//    androidTestImplementation(libs.junit.v5114)

    implementation(libs.cardview)

}