# RoboMarket

A fictitious robot market to practice MVVM design pattern in Android.

<p align="center">
  <img src="img/screen1.png" alt="Login screen"/>
  <img src="img/screen2.png" alt="Register screen"/>
</p>

<p align="center">
  <img src="img/screen3.png" alt="Sales screen"/>
  <img src="img/screen4.png" alt="Robots screen"/>
</p>

<p align="center">
  <img src="img/screen5.png" alt="Search robots screen"/>
  <img src="img/screen6.png" alt="Profile screen"/>
  <img src="img/screen7.png" alt="Create Robot screen"/>
</p>

<p align="center">
  <img src="img/screen7.png" alt="Create Robot screen"/>
</p>

## Description

The project counts with the following features:
- User sign in with username and password
- User sign up
- List and creation of *Sales*
- List and creation of *Robots*
- Profile details and profile edit

The project also counts with:
- MVVM pattern to improve the consistency, legibility and maintainability
- Reactive Programming in API calls
- Integration with [Back4App](https://back4app.com) as back-end
- List items pagination and list reload with SwipeRefreshLayout
- All loaded images are [Robohash](https://robohash.org/) images

And the following dependencies:
- [Retrofit](http://square.github.io/retrofit/) to easy API calls
- [Gson](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) as json parser
- [Groupie](https://github.com/Genius/genius-groupie) to easy item adapter binding
- [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid) to Reactive Programming
- [Glide](https://github.com/bumptech/glide) as image loader
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/)'s ViewModel
