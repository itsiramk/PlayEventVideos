# PlayEventVideos
## Fetch Event Details from API

### This is a Kotlin Project.
User needs to fetch event and schedule details from API given below.

The screen has a bottom navigation with two tabs - ***"Event"*** and ***"Schedule"***

***The Event Tab:***

-*Displays event list with Date sorted in Ascending order.*

-*On click of each item in the event list, respective Video is played on a new screen.*

***The Schedule Tab:***  

-*Displays Tomorrow Schedule list with Date sorted in Ascending order.*

-*The Schedule screen is refreshed every 30 seconds.*

-*If tomorrow schedule is unavailable, default text message is displayed "Tomorrow Data Unavailable!!".*

Android Components used:

*Development Language* - Kotlin

*Architecture* - MVVM

*Dependency Injection* - Dagger Hilt

*JetPack* - NavigationComponent

*Networking Libraries* - Retrofit

*Asynchronous calls* - LiveData, Coroutines

*UI* - ConstraintLayout, Bottom Navigation

*API For Event List* - https://us-central1-dazn-sandbox.cloudfunctions.net/getEvents

*API For Schedule List* - https://us-central1-dazn-sandbox.cloudfunctions.net/getSchedule
