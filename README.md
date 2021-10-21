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

***Android Components used:***

*Development Language* - Kotlin

*Architecture* - MVVM

*Dependency Injection* - Dagger Hilt

*JetPack* - NavigationComponent

*Networking Libraries* - Retrofit

*Asynchronous calls* - LiveData, Coroutines

*UI* - ConstraintLayout, Bottom Navigation

*Third-Party Library (For Video)* - ExoPlayer

*API For Event List* - https://us-central1-dazn-sandbox.cloudfunctions.net/getEvents

*API For Schedule List* - https://us-central1-dazn-sandbox.cloudfunctions.net/getSchedule


<img width="366" alt="EventVideos" src="https://user-images.githubusercontent.com/16866972/138211697-efc4dc40-10c8-4fe6-bd0a-a5947134ea80.PNG"> <img width="190" alt="logout_vid" src="https://user-images.githubusercontent.com/16866972/138211732-1671e9fd-d295-4d4e-b38f-935c561f592b.PNG">

<img width="322" alt="ScheduleVideos" src="https://user-images.githubusercontent.com/16866972/138211710-1d354809-ea87-4da9-b1ba-ac0628ba0045.PNG">



