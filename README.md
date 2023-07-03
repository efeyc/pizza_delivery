# Pizza Delivery

In this assignment, the following methodologies and native libraries are used:

- MVVM
- Jetpack Compose
- Compose Navigation
- StateFlow
- ViewModel
- Coroutines
- Unit tests
- Dependency Injection

And following 3rd party libraries are used to make things easier.
- Hilt (Dependency Injection)
- Detekt (code quality)
- Retrofit (Network Layer)
- Gson (Json parsing)
- Firebase Crashlytics (monitoring)

Some notes for code reviewers:
- I chose to present the UI Layer with Jetpack Compose since it is highly promoted by Google lately.
- I only added unit tests for MenuViewModel class due to the time limit
- Pizza images are only added into the drawables directory for simplicity 
- I kept the UI simple and didn't add any extra architecture layer due to the simplicity of the assignment (No data layer, no analytics, 
no caching, paging, sorting, filtering, etc.)

What can be improved in this assignment?
- Cart, caching, sorting, searching, filtering, paging, UI animations... There is too much space for improvement :)
- Adding a continuous integration, running tests for each commit

