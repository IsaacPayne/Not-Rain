# Not Rain

What if I told you there is an app on the market... That could tell if you it was going to rain or not?

Name and idea inspired by [Jian Yang's](https://silicon-valley.fandom.com/wiki/J%C3%ACan-Y%C3%A1ng) [Not Hotdog App](https://play.google.com/store/apps/details?id=com.seefoodtechnologies.nothotdog&hl=en)

## API Change
The orginally scoped [Daily API](https://openweathermap.org/forecast16) has been moved to the paid tier. After consulting with the stakeholders, it was decided to use the [5 day / 3 hour forecast API](https://openweathermap.org/forecast5).

This caused the orginal idea of having multiple cities and a card per city to move out of scope.


## Getting Started

Once you've checked out a copy of this code and opened it up in Android Studio. You're going to want an [OpenWeatherMap](https://openweathermap.org/appid) API key, you can obtain this by signing up to their website (it takes up to 10 minutes to activate the keys).

Once you have that add the key to the `gradle.properties` file in your home directory under `.gradle` directory (Not the `gradle.properties` file in the project directory). 

- Windows: C:\Users\\<Your Username\>\\.gradle
- Mac: /Users\/\<Your Username\>/.gradle
- Linux: /home/\<Your Username\>/.gradle

[More info on storing the API key](https://medium.com/code-better/hiding-api-keys-from-your-android-repository-b23f5598b906)

**Now you should be able to build the app!**

### Formatting

We're using [Spotless](https://github.com/diffplug/spotless/tree/master/plugin-gradle) for formatting, run this by running the command `gradlew spotlessApply`

## Features

Uses:
- Retrofit (Networking)
- Room (Offline storage)
- Databinding 
- Picasso (Downloading and displaying weather icons)
- DebugDb (Viewing RoomDB on debug)
- Play Services (Get users last location)

## Potential Improvements

- Retry logic on the API Requests and better error handling.
- Validate API Response
- Ability to manually select/search a location.
- More reliable locations ([Receiving Location Updates](https://developer.android.com/training/location/receive-location-updates.html))


## Authors

* **Isaac Payne** - *Initial work*
