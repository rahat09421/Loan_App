plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.harwaqt"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.harwaqt"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation ("com.google.android.gms:play-services-ads:22.5.0")
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation ("com.github.hamzaahmedkhan:CounterAnimationTextView:1.0.1")
    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    implementation ("com.github.GrenderG:Toasty:1.5.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.airbnb.android:lottie:3.4.0")
    implementation ("com.github.thomper:sweet-alert-dialog:1.4.0")
    implementation("com.google.android.material:material:1.10.0")
    implementation ("com.github.shuhart:stepview:1.5.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment:2.7.5")
    implementation("androidx.navigation:navigation-ui:2.7.5")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.google.android.gms:play-services-auth:20.7.0")
}