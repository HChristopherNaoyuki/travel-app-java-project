# Travel Log Application

## Overview
The Travel Log Application is a Java-based GUI application that allows users to log their travel plans by selecting a start location, an end location, and a method of travel. The app stores travel entries in a text file and displays the travel log within the application.

## Features
- User-friendly interface with dropdown selections for locations and travel methods.
- Input validation to ensure start and end locations are not the same.
- Persistent storage of travel logs in a text file.
- Dynamic display of travel logs within the application.

## Requirements
- Java Development Kit (JDK) 8 or higher.
- An IDE such as IntelliJ IDEA or Eclipse for running the application.

## Getting Started

### Installation
1. Clone the repository or download the source code.
2. Open the project in your preferred Java IDE.
3. Ensure you have the necessary libraries (Swing) included in your project.

### Running the Application
1. Compile the `TravelApp` class.
2. Run the `main` method in the `TravelApp` class.
3. The application window will open, allowing you to log your travels.

### Usage
1. Select your start location from the dropdown.
2. Choose your end location.
3. Select your method of travel (Airplane or Train).
4. Click the "Submit" button to log your travel entry.
5. View your travel log in the text area below the input fields.

### File Storage
- The travel logs are stored in a text file named `travel.txt` in the application’s root directory.
- Each entry consists of the start location, end location, and travel method, formatted for easy reading.

## Code Structure
- **TravelApp.java**: The main class that contains the GUI setup and functionality.
  - **handleSubmission()**: Validates input and handles travel entry submission.
  - **loadTravelData()**: Loads existing travel logs from the file on application startup.

## Contributing
If you would like to contribute to this project, please fork the repository and submit a pull request with your changes.

## License
This project is open source and available under the [MIT License](LICENSE).

## Acknowledgments
- Inspired by travel enthusiasts looking for a simple way to keep track of their journeys. 
