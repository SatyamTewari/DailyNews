This is basic Daily News application which leverages the open source newsapi. It has total of 3 screens namely - Home, Search and Detail.
Home Screen is used to display top trending headlines.
Search Screen is used to display top trending news as per user query such as sports, music, politics, etc
Detail Screen is used to show full details of the selected news item from either Home or Search Screen.

It has the following features :-

Navigations - The Navigation library of Jetpack suite to handle navigations between Home, Search and Detail news screen.
Retrofit - To make api calls
HttpClient - This is used as network client for retrofit
Interceptor - This is used to make custom requests before each api call
Kotlinx Serialization - This is used for serialization purposes to convert api response in json data and later inserted into Room db.
Room - It is used for offline support
Paging 3 - The paging 3 library of jetpack suite is used to handle paging calls.
Hilt - This library is used for dependency injection
Coil - This library is used to load images from internet into the composables
ViewModel - This library is used for state management across configuration changes
Coroutines - It is used to handle asynchronous task operations such as api calls.
