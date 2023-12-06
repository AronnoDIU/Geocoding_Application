# Geocoding Application

This is a simple Java application that uses the Google Geocoding API to convert addresses into geographic coordinates (geocoding).

## Table of Contents

- [Prerequisites](#prerequisites)
- [Features](#features)
- [Requirements](#requirements)
- [Usage](#usage)
- [Note](#note)
- [License](#license)

## Prerequisites

- A Google Geocoding API key. You can get
  one [here](https://developers.google.com/maps/documentation/geocoding/get-api-key).
- Java 8 or higher.
- A Java IDE (e.g. [Eclipse](https://www.eclipse.org/downloads/), [IntelliJ IDEA](https://www.jetbrains.com/idea/)).
- [Maven](https://maven.apache.org/).
- [Git](https://git-scm.com/).
- [Google Maps Platform](https://cloud.google.com/maps-platform/).
- [Google Cloud Platform](https://cloud.google.com/).
- [Google Cloud SDK](https://cloud.google.com/sdk/).
- [Google Cloud SDK Maven Plugin](https://cloud.google.com/appengine/docs/standard/java/tools/maven).

## Features

- Takes an address input from the user.
- Validates the address input.
- Sends a request to the Google Geocoding API.
- Parses the JSON response and extracts the geographic coordinates.
- Handles multiple results by allowing the user to choose one.
- Handles errors and exceptions.

## Requirements

- Java 8 or higher.
- A valid Google Geocoding API key.

## Usage

1. Clone the repository.
2. Replace `YOUR_API_KEY` in `GeocodingApp.java` with your actual Google Geocoding API key.
3. Compile and run `GeocodingApp.java`.
4. When prompted, enter an address.
5. If multiple results are found, choose one by entering its corresponding number.

## Note

This application is for educational purposes and is not intended for use in production environments.

## License

This project is licensed under the MIT License.