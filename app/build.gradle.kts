plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.moviesearch"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.moviesearch"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures { viewBinding = true }
}

val retrofitVersion = "2.11.0"
val gsonVersion = "2.9.0"
val paddingVersion = "3.2.1"
val glideVersion = "4.16.0"
val daggerVersion = "2.48"

dependencies {


    //Network
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$gsonVersion")

    //Paging library
    implementation("androidx.paging:paging-runtime-ktx:$paddingVersion")

    //Glide
    implementation("com.github.bumptech.glide:glide:$glideVersion")

    //Dagger
    implementation("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")
    implementation ("com.google.dagger:dagger-android:2.24")
    kapt ("com.google.dagger:dagger-android-processor:2.23.1")


    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")


    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}