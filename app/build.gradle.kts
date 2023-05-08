plugins {
  id(Build.androidApplication)
  kotlin("android")
  kotlin("kapt")
  id("dagger.hilt.android.plugin")

}

android {
  namespace = "me.darthwithap.android.calorie_tracker"
  compileSdk = ProjectConfig.compileSdk

  defaultConfig {
    applicationId = ProjectConfig.applicationId
    minSdk = ProjectConfig.minSdk
    targetSdk = ProjectConfig.targetSdk
    versionCode = ProjectConfig.versionCode
    versionName = ProjectConfig.versionName

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
  }
  packagingOptions {
    resources.excludes.add("META-INF/AL2.0")
    resources.excludes.add("META-INF/LGPL2.1")
    // Needed to add on windows machine
    resources.excludes.add("**/attach_hotspot_windows.dll")
    resources.excludes.add("META-INF/licenses/ASM")
  }
}

dependencies {

  //AndroidX
  implementation(AndroidX.coreKtx)
  implementation(AndroidX.lifecycleKtx)
  implementation(AndroidX.appCompat)

  //Dagger-Hilt
  implementation(DaggerHilt.hiltAndroid)
  kapt(DaggerHilt.hiltCompiler)

  //Compose
  implementation(Compose.activityCompose)
  implementation(Compose.ui)
  implementation(Compose.uiToolingPreview)
  implementation(Compose.material)
  implementation(Compose.compiler)
  implementation(Compose.hiltNavigationCompose)
  implementation(Compose.runtime)
  implementation(Compose.navigation)
  implementation(Compose.viewModelCompose)

  implementation(Coil.coilCompose)

  implementation(Google.material)

  //Retrofit
  implementation(Retrofit.okHttp)
  implementation(Retrofit.retrofit)
  implementation(Retrofit.okHttpLoggingInterceptor)
  implementation(Retrofit.moshiConverter)

  //Room
  kapt(Room.roomCompiler)
  implementation(Room.roomKtx)
  implementation(Room.roomRuntime)

  implementation(project(Modules.core))
  implementation(project(Modules.onboardingPresentation))
  implementation(project(Modules.onboardingDomain))
  implementation(project(Modules.trackerPresentation))
  implementation(project(Modules.trackerDomain))
  implementation(project(Modules.trackerData))

  testImplementation(Test.junit4)
  testImplementation(Test.truth)
  testImplementation(Test.coroutines)
  testImplementation(Test.turbine)
  testImplementation(AndroidTest.composeUiTest)
  testImplementation(Test.mockk)
  testImplementation(Test.mockWebServer)

  androidTestImplementation(Test.testRunner)
  androidTestImplementation(Test.junit4)
  androidTestImplementation(Test.truth)
  androidTestImplementation(Test.coroutines)
  androidTestImplementation(AndroidTest.junitAndroidExt)
  androidTestImplementation(AndroidTest.espresso)
  androidTestImplementation(Test.turbine)
  androidTestImplementation(AndroidTest.composeUiTest)
  androidTestImplementation(Test.mockkAndroid)
  androidTestImplementation(Test.mockWebServer)
  androidTestImplementation(Test.hiltTest)
  androidTestImplementation(AndroidTest.composeUiTooling)

  kaptAndroidTest(DaggerHilt.hiltCompiler)
  debugImplementation(AndroidTest.composeUiManifest)
}
