plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.proyectobase"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.proyectobase"
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)



    // Núcleo de Retrofit: cliente HTTP para consumir APIs REST con interfaces anotadas (@GET, @POST, etc.)
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    // Convertidor JSON <-> data classes usando Moshi dentro de Retrofit (MoshiConverterFactory)
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    // Interceptor de OkHttp para loguear requests/responses (útil en debug)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    // Coroutines Soporte de coroutines en Android (Dispatcher.Main, launch en UI, etc.)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    // Extensiones KTX de Lifecycle: lifecycleScope, repeatOnLifecycle y helpers para coroutines
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")
    // Adaptadores de Moshi para Kotlin (null-safety, data classes, KotlinJsonAdapterFactory)
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    // implementaciones camerax

    implementation ("androidx.camera.camera2:camera-camera2:1.1.0")
    implementation ("androidx.camera.lifecycle:lifecycle-camera:1.1.0")

    implementation("androidx.camera:camera-core:1.3.0")
    implementation("androidx.camera:camera-camera2:1.3.0")
    implementation("androidx.camera:camera-lifecycle:1.3.0")
    implementation("androidx.camera:camera-view:1.3.0")
    implementation("androidx.camera:camera-extensions:1.3.0")


}