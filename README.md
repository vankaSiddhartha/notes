Notes App
Welcome to the Notes App! This application is a simple and effective way to manage your notes. The app is designed to be user-friendly and showcases several fundamental Android development concepts. Below you'll find a detailed overview of the app's features and the technology stack used.

Features
Single Activity Architecture:

The app uses a single MainActivity to host multiple fragments.
Each screen is represented by a fragment, providing a modular and maintainable code structure.
Google+ Login:

On startup, the app presents a login screen if the user is not logged in.
Authentication is handled through Google Sign-In.
User login state is tracked using SharedPreferences to remember logged-in users.
Display Notes:

Once logged in, users can see all their notes in a RecyclerView.
Notes are displayed in full, without truncation, ensuring users can read the complete content.
Add New Notes:

Users can add new notes using a dedicated button.
New notes are saved and immediately displayed in the list.
Update Existing Notes:

Users can update any note by selecting it from the list.
Changes are saved and reflected in the displayed notes.
Delete Notes:

Users can delete notes that they no longer need.
Deleted notes are removed from the list and the database.
Technical Overview
Architecture
Single Activity with Multiple Fragments:

MainActivity hosts the fragments and handles navigation.
Fragments used include LoginFragment, HomeFragment, and AddNoteFragment.
ViewModel and LiveData:

The app follows the MVVM pattern using ViewModel to manage UI-related data in a lifecycle-conscious way.
LiveData is used to observe and react to data changes in the notes.
Data Storage
SharedPreferences:

Used to store the login state of the user.
Manages user session by storing a boolean flag indicating whether the user is logged in or not.
SQLite Database:

Notes are stored in an SQLite database using the NotesDatabase helper class.
The database contains a table to store note details such as ID, title, content, and timestamp.
UI Components
RecyclerView:

Displays the list of notes.
Uses a GridLayoutManager for layout management, providing a grid display of notes.
Fragments:

LoginFragment: Handles Google+ login.
HomeFragment: Displays the list of notes and includes search functionality.
AddNoteFragment: Allows users to add and edit notes.
User Authentication
Google Sign-In:
Integrated using the GoogleSignInClient API.
Securely manages user authentication and retrieves user profile details.
Third-Party Libraries
Glide:
Used for loading and displaying images within the app.
Setup and Installation
To set up and run the project locally, follow these steps:

Clone the Repository:

bash
Copy code
git clone https://github.com/yourusername/notes-app.git
Open the Project in Android Studio:

Launch Android Studio.
Open the cloned project directory.
Configure Google Sign-In:

Obtain a client_id from the Google Cloud Console.
Update the google-services.json file in the project with your client_id.
Build and Run:

Ensure your Android device or emulator is connected.
Click on the "Run" button in Android Studio.
How to Use
Launch the App:

Open the Notes app on your device.
If not logged in, you'll be presented with the login screen.
Login via Google+:

Click the Google+ login button.
Select your Google account to log in.
View Notes:

After logging in, you'll see a list of all your notes displayed in a grid format.
Add a New Note:

Click the "Add Note" button to create a new note.
Enter the title and content, then save.
Update a Note:

Click on any note to edit its content.
Make the necessary changes and save them.
Delete a Note:

Long press on a note to delete it.
Confirm the deletion in the dialog that appears.
Screenshots
Include relevant screenshots of your app here to give users a visual overview of its functionality.

Contributing
If you'd like to contribute to the project, feel free to open a pull request or issue on the GitHub repository.

License
Include the licensing information for your project.
