plugins {
      alias(libs.plugins.android.application)
      alias(libs.plugins.kotlin.android)
      alias(libs.plugins.kotlin.compose)
      alias(libs.plugins.ksp)
      alias(libs.plugins.hilt)
      id("androidx.navigation.safeargs.kotlin")
}

android {
      namespace = "com.minphone.weatherforecast"
      compileSdk = 34

      defaultConfig {
            applicationId = "com.minphone.weatherforecast"
            minSdk = 24
            targetSdk = 34
            versionCode = 1
            versionName = "1.0"

            buildConfigField("String", "API_KEY", "\"2ebf13efde1a49afb3022024243010\"")
            buildConfigField("String", "Base_URL", "\"https://api.weatherapi.com/\"")
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
      kotlinOptions {
            jvmTarget = "17"
      }
      buildFeatures {
            buildConfig = true
            compose = true
      }
}

dependencies {

      implementation(libs.androidx.core.ktx)
      implementation(libs.androidx.lifecycle.runtime.ktx)
      implementation(libs.androidx.activity.compose)
      implementation(platform(libs.androidx.compose.bom))
      implementation(libs.androidx.ui)
      implementation(libs.androidx.ui.graphics)
      implementation(libs.androidx.ui.tooling.preview)
      implementation(libs.androidx.material3)
      implementation(libs.androidx.material)
      implementation(libs.androidx.lifecycle)
      implementation(libs.androidx.viewmodel)
      implementation(libs.androidx.navigation)
      implementation(libs.androidx.hilt)
      implementation(libs.retrofit)
      implementation(libs.retrofitConverterGson)
      implementation(libs.loggingInterceptor)
      implementation(libs.coilCompose)
      implementation(libs.hilt.android)
      ksp(libs.hilt.compiler)

      testImplementation(libs.junit)
      androidTestImplementation(libs.androidx.junit)
      androidTestImplementation(libs.androidx.espresso.core)
      androidTestImplementation(platform(libs.androidx.compose.bom))
      androidTestImplementation(libs.androidx.ui.test.junit4)
      debugImplementation(libs.androidx.ui.tooling)
      debugImplementation(libs.androidx.ui.test.manifest)
}