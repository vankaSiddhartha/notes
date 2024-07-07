# Notes App

Welcome to the Notes App! This application is designed to help you manage your notes effortlessly. It's built with a user-friendly interface and leverages several core Android development concepts. Below is a comprehensive overview of the app's features and the technology stack used.
## Youtube
 - app demo link [link](https://www.youtube.com/watch?v=IUufm9exn-k)

## Features

### Single Activity Architecture

- **Modular Structure:** The app uses a single `MainActivity` to host multiple fragments, each representing a different screen. This architecture ensures a clean and maintainable codebase.

### Google Sign-In

- **User Authentication:** Upon startup, users are greeted with a login screen if they are not already logged in. 
- **Secure Login:** Authentication is managed through Google Sign-In, and user sessions are tracked using `SharedPreferences`.

### Notes Management

- **Display Notes:** Users can view all their notes in a `RecyclerView`. Each note is displayed in full, without truncation, ensuring readability.
- **Add New Notes:** A dedicated button allows users to create and save new notes, which are immediately visible in the list.
- **Update Existing Notes:** Users can update notes by selecting them from the list. Changes are saved and updated in real-time.
- **Delete Notes:** Users can remove unwanted notes. Deleted notes are removed from both the list and the database.

## Technical Overview

### Architecture

- **Single Activity, Multiple Fragments:** The `MainActivity` hosts fragments and manages navigation. Key fragments include:
  - **LoginFragment:** Manages Google Sign-In.
  - **HomeFragment:** Displays the list of notes and includes search functionality.
  - **AddNoteFragment:** Allows users to add or edit notes.

- **MVVM Pattern:** The app uses the Model-View-ViewModel (MVVM) pattern to manage UI-related data efficiently.
  - **ViewModel:** Manages UI-related data in a lifecycle-aware manner.
  - **LiveData:** Observes data changes and updates the UI accordingly.

### Data Storage

- **SharedPreferences:** Used to store the user's login state, ensuring a seamless user experience across sessions.
- **SQLite Database:** The `NotesDatabase` helper class manages note storage in an SQLite database. The database stores note details, including ID, title, content, and timestamp.

### UI Components

- **RecyclerView:** Displays notes in a grid layout, managed by a `GridLayoutManager`.
- **Fragments:** The UI is divided into fragments for login, viewing, and editing notes, ensuring a modular and cohesive design.

### User Authentication

- **Google Sign-In:** Integrated via the `GoogleSignInClient` API, allowing secure user authentication and profile retrieval.

### Third-Party Libraries

- **Glide:** Utilized for efficient image loading and display within the app.

## Setup and Installation

To set up and run the project locally, follow these steps:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/vankaSiddhartha/notes
   ```

2. **Open the Project in Android Studio:**
   - Launch Android Studio.
   - Open the cloned project directory.

3. **Configure Google Sign-In:**
   - Obtain a `client_id` from the [Google Cloud Console](https://console.cloud.google.com/).

4. **Build and Run:**
   - Ensure your Android device or emulator is connected.
   - Click on the "Run" button in Android Studio.

## How to Use

1. **Launch the App:** Open the Notes app on your device. You'll see a login screen if not already logged in.
   
2. **Login via Google:** Click the Google login button and select your Google account to log in.

3. **View Notes:** After logging in, you’ll see your notes displayed in a grid format.

4. **Add a New Note:** Click the "Add Note" button, enter the title and content, and save.

5. **Update a Note:** Click on any note to edit its content, make changes, and save.

6. **Delete a Note:** Long press on a note to delete it and confirm the deletion in the dialog that appears.



## Contributing

We welcome contributions! If you'd like to contribute to the project, feel free to open a pull request or issue on our [GitHub repository](https://github.com/vankaSiddhartha/notes).

---

