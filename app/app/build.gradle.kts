plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.emilygranville.videogamelist"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.emilygranville.videogamelist"
        minSdk = 24
        targetSdk = 36
        versionCode = 2
        versionName = "1.2"

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
    testOptions {
        animationsDisabled = true
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

    testRuntimeOnly(libs.junit.platform.launcher)

    // adds card view
    implementation(libs.cardview)

    // espresso
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.rules)

    // espresso for recyclerview
    androidTestImplementation(libs.espresso.contrib)

    //firestore
    implementation(platform(libs.firebase.bom))

    implementation (libs.firebase.auth)
    implementation (libs.firebase.firestore)
}