import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class GeocodingApp {
    private static final String API_KEY = "YOUR_API_KEY";
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter address: ");
            String address = reader.readLine();

            if (isValidAddress(address)) {
                String coordinates = getGeocoding(address);
                System.out.println("Coordinates: " + coordinates);
            } else {
                System.out.println("Invalid address. Please provide a valid address.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidAddress(String address) {
        // Implement validation logic based on your requirements
        return !address.trim().isEmpty();
    }

    private static String getGeocoding(String address) {
        try {
            address = address.replace(" ", "+");
            String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + API_KEY;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                String status = jsonResponse.getString("status");

                if ("OK".equals(status)) {
                    JSONArray results = jsonResponse.getJSONArray("results");

                    if (results.length() == 1) {
                        JSONObject result = results.getJSONObject(0);
                        JSONObject location = result.getJSONObject("geometry").getJSONObject("location");
                        double latitude = location.getDouble("lat");
                        double longitude = location.getDouble("lng");

                        // Additional information
                        String formattedAddress = result.getString("formatted_address");
                        String locationType = result.getJSONArray("types").getString(0);

                        return "Coordinates: (" + latitude + ", " + longitude + ")\nFormatted Address: " + formattedAddress +
                                "\nLocation Type: " + locationType;
                    } else if (results.length() > 1) {
                        // Handle multiple results
                        System.out.println("Multiple results found. Please choose one:");

                        for (int i = 0; i < results.length(); i++) {
                            System.out.println(i + 1 + ". " + results.getJSONObject(i).getString("formatted_address"));
                        }

                        System.out.print("Enter the number of your choice: ");
                        int choice = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine()) - 1;

                        if (choice >= 0 && choice < results.length()) {
                            JSONObject location = results.getJSONObject(choice).getJSONObject("geometry").getJSONObject("location");
                            double latitude = location.getDouble("lat");
                            double longitude = location.getDouble("lng");

                            return "Coordinates: (" + latitude + ", " + longitude + ")";
                        } else {
                            return "Invalid choice. Aborting.";
                        }
                    } else {
                        return "No results found for the given address.";
                    }
                } else {
                    return "Geocoding API request failed with status: " + status;
                }
            } else {
                return "Error in HTTP request. Response code: " + responseCode;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Exception occurred";
        }
    }
}
