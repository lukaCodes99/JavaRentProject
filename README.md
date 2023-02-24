# JavaRentProject
My first app that I made start to finish all on my own. It uses Java and JavaFX. Check README for more info!
As the author of this Java and H2-based Rent-a-Car application, I am pleased to present a software solution for managing vehicle rentals. This app includes many features to help users manage their rental business with ease. For a full demonstration of the app's functionality, interested parties can contact the author for a database export.
The app is far from perfect, but I am proud as it is my very first comprehensive app.

To ensure data security, the app uses SHA-256 password hashing. Users can add new vehicles to the database, as well as make reservations, which are stored in a separate table and connected via a unique ID. The app includes two roles, an admin who can add new vehicles and users and update registration expiry dates, and a user who can make reservations.

One of the key features of the app is its custom exception handling, which throws exceptions when a specific condition is not met. This ensures that the data is always accurate and up-to-date.

Another important feature of the app is a thread that constantly checks the registration expiry dates of all vehicles in the database. The app displays the number of vehicles that will expire within the next seven days in the title bar, which is refreshed constantly and updated if a change is detected.
