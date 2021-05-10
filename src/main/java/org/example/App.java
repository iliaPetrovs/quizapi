package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static java.net.http.HttpRequest.BodyPublishers.noBody;

public class App 
{
    public static void main( String[] args ) {

        String url = "https://http-challenge.multiverse-coaches.io";

        try {
            HttpClient client = HttpClient.newHttpClient();

            // first request (GET)
            HttpRequest request = HttpRequest.newBuilder(
                    URI.create(url))
                    .header("accept", "application/json")
                    .method("GET", noBody())
                    .build();

            System.out.println(request);

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

            // TODO - add code for second request (POST) here
            //  .method("POST", HttpRequest.BodyPublishers.ofString(jsonName))

            // second request (POST)
            String secondUrl = "https://http-challenge.multiverse-coaches.io/apprentices";

            String json = "{" +
                    "\"name\":\"ilja\"" +
                    "}";

            HttpRequest secondRequest = HttpRequest.newBuilder(
                    URI.create(secondUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            System.out.println(secondRequest);

            HttpResponse<String> secondResponse = client.send(secondRequest, HttpResponse.BodyHandlers.ofString());

            String headerValue = "";

            Map<String, List<String>> map = secondResponse.headers().map();
            for (Map.Entry<String, List<String>> entry:map.entrySet()) {
                if ("your-id".equalsIgnoreCase(entry.getKey())) {
                    headerValue = entry.getValue().get(0);
                    break;
                }
            }
            System.out.println(secondResponse.body());



            // TODO - add code for third request (header) here
            String thirdUrl = String.format("https://http-challenge.multiverse-coaches.io/apprentices/%s", headerValue);

            HttpRequest thirdRequest = HttpRequest.newBuilder(
                    URI.create(thirdUrl))
                    .header("accept", "application/json")
                    .method("GET", noBody())
                    .build();

            System.out.println(thirdRequest);

            HttpResponse<String> thirdResponse = client.send(thirdRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(thirdResponse.body());

            // The code below will help you retrieve the "your-id" header from the HTTP Response

            System.out.println(headerValue);
            // TODO - add code for forth request (PATCH) here
            String urlParameters  = "guests=Ronald%20McDonald,Colonel%20Sanders,The%20Burger%20King";
            HttpRequest fourthRequest = HttpRequest.newBuilder(
                    URI.create(thirdUrl))
                    .header("Content-type", "application/x-www-form-urlencoded")
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(urlParameters))
                    .build();

            System.out.println(fourthRequest);

            HttpResponse<String> fourthResponse = client.send(fourthRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(fourthResponse.body());

            // TODO - add code for fifth request (GET with Query Parameters) here

            String finalUrl = String.format("https://http-challenge.multiverse-coaches.io/apprentices/%s/menus?starter=Olives&main=Salmon&dessert=Haribos", headerValue);
            HttpRequest finalRequest = HttpRequest.newBuilder(
                    URI.create(finalUrl))
                    .header("accept", "applicaion/json")
                    .method("GET", noBody())
                    .build();

            System.out.println(finalRequest);

            HttpResponse<String> finalResponse = client.send(finalRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(finalResponse.body());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
