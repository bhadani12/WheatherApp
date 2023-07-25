import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String apiUrl = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London&appid=b6907d289e10d714a6e88b30761fae22";

        while (true) {
            displayMenu();
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()

            switch (option) {
                case 1:
                    getWeather(apiUrl, scanner);
                    break;
                case 2:
                    getWindSpeed(apiUrl, scanner);
                    break;
                case 3:
                    getPressure(apiUrl, scanner);
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Please choose an option:");
        System.out.println("1. Get weather");
        System.out.println("2. Get Wind Speed");
        System.out.println("3. Get Pressure");
        System.out.println("0. Exit");
    }

    private static void getWeather(String apiUrl, Scanner scanner) {
        System.out.println("Enter the date (YYYY-MM-DD):");
        String date = scanner.nextLine();
        String jsonResponse = getJsonResponse(apiUrl);
        System.out.println("JSON Response for weather on date " + date + ":\n" + jsonResponse);
    }

    private static void getWindSpeed(String apiUrl, Scanner scanner) {
        System.out.println("Enter the date (YYYY-MM-DD):");
        String date = scanner.nextLine();
        String jsonResponse = getJsonResponse(apiUrl);
        System.out.println("JSON Response for wind speed on date " + date + ":\n" + jsonResponse);
    }

    private static void getPressure(String apiUrl, Scanner scanner) {
        System.out.println("Enter the date (YYYY-MM-DD):");
        String date = scanner.nextLine();
        String jsonResponse = getJsonResponse(apiUrl);
        System.out.println("JSON Response for pressure on date " + date + ":\n" + jsonResponse);
    }

    private static String getJsonResponse(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}


