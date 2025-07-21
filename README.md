# Bookie: The Book Reviewer made using Android Studio IDE

<img width="236" height="513" alt="homepage" src="https://github.com/user-attachments/assets/160907b3-89f8-41d8-9547-610d85a7f181" />
<img width="238" height="520" alt="search" src="https://github.com/user-attachments/assets/0c7bbbe6-46b7-47bb-9f4a-49e5154769e5" />
<img width="241" height="518" alt="hunger games" src="https://github.com/user-attachments/assets/15306add-922a-44ad-a3a3-4bcfec69e803" />
<img width="239" height="520" alt="tml" src="https://github.com/user-attachments/assets/3f67edfe-9852-4036-b49f-45a1833dfb56"/>
<img width="234" height="515" alt="a" src="https://github.com/user-attachments/assets/e6880cf3-417e-4936-aaab-f48082dcc2b5" />

<img width="239" height="521" alt="8" src="https://github.com/user-attachments/assets/3f85616b-6755-42da-aa9f-19581934ba88" />

## About
The Android app has been made in the Android Studio IDE using Java.

Database has been itegrated in the Android Application using MySQL, which has been used for storing and retrieving the data which consists of the Book details such as the book name, author's name along with the book reviews which can be entered and read by the users.

## Features

- **Book Info Retrieval:** Pulls book details such as the title, author and reviews from the database.
- **Stores Reviews in DB:** Saves user entered reviews along with the user name in the database.
- **Star Rating System:** Allows users to rate books between 1 to 5 stars (including float values).
- **Search & Reviews Submission:** Allows users to search books by title then write and submit the reviews for the book

## Dependencies

```bash
implementation 'androidx.appcompat:appcompat:1.3.1'
implementation 'com.google.android.material:material:1.12.0'
implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
testImplementation 'junit:junit:4.13.2'
androidTestImplementation 'androidx.test.ext:junit:1.2.1'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.6.1'

// https://mvnrepository.com/artifact/mysql/mysql-connector-java
implementation 'mysql:mysql-connector-java:5.1.49'
```

## SDKs used


```bash
compileSdk 34
minSdk 25
targetSdk 34
```
    
## Acknowledgements

- [MySQL Connection](https://youtu.be/mbHvLSwhdLA?si=fZ8Wv3NaYcBAWXPF)
