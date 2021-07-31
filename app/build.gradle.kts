plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = "com.truk.weatherapp"
        versionCode(AppConfig.versionCode)
        versionName(AppConfig.versionName)
        multiDexEnabled = true
    }
    buildTypes {
        getByName("release") {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            applicationIdSuffix(".debug")
        }

        create("production") {
            applicationIdSuffix(".prod")
        }

        create("development") {
            applicationIdSuffix(".dev")
        }


    }




    packagingOptions {
        exclude("META-INF/notice.txt")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }


}



dependencies {

    implementation(AppDependencies.appLibraries)
    implementation(project(":common"))
    implementation(project(":network"))
    implementation(project(":business"))
    implementation(project(":citiesRepository"))
    implementation(project(":cities"))
    implementation(project(":weatherRepository"))
    implementation(project(":weather"))
    implementation(project(":LocalPersistance"))
    implementation(project(":dtos"))

}