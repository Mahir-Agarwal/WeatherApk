plugins {
    alias(libs.plugins.android.application)  // Ensure you have this alias in your libs.versions.toml file
}

android {
    namespace = "com.example.weatherpromax"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.weatherpromax"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // Core Android dependencies
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Networking libraries
    implementation("com.android.volley:volley:1.2.1")


    // Image loading library
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")  // Add annotationProcessor for Glide

    // JSON parsing library
    implementation("com.google.code.gson:gson:2.8.9")

    // Optional: For networking to work with Volley and Android Networking
    implementation("com.android.volley:volley:1.2.1")
}
