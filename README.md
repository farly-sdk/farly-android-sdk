# Skyly Android SDK

[![Version](https://jitpack.io/v/farly-sdk/farly-android-sdk.svg)](https://jitpack.io/#farly-sdk/farly-android-sdk)

## Full Documentation

[Access the full documentation here >](https://mobsuccess.notion.site/Android-SDK-64c9efa6b34e4c9d8e1f3869dfe484b4)
## Example

To run the example project, clone the repo, and open the project in Android Studio, then launch the MainActivity. 
Inject your API Key and Publisher ID in `MainActivity.java`.

## Installation

Farly is available through [jitpack](https://jitpack.io/#farly-sdk/farly-android-sdk). 
To install it, confiure your root `build.gradle` file :
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Then add your dependency in your app's `build.gradle`
```
dependencies {
    implementation 'com.github.farly-sdk:farly-android-sdk:Tag' // replace Tag with the wanted version
}
```

## Author

Farly, [farly.io](https://www.farly.io)

## License

Farly is available under the MIT license. See the LICENSE file for more info.
