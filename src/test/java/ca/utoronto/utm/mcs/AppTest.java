package ca.utoronto.utm.mcs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

// TODO Please Write Your Tests For CI/CD In This Class. You will see
// these tests pass/fail on github under github actions.
public class AppTest {

    @Test
    public void exampleTest() {
        assertTrue(true);
    }

    @Test
    public void addActorPass() throws IOException, URISyntaxException, InterruptedException {

        // TODO: Ensure that there are no actors with this id

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addActor"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"name\": \"John Pass\", \"actorId\": \"actorpass\" }"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("addActorPass: The response status is " + response.statusCode());

        assertTrue(response.statusCode() == 200);
    }

    // Test for request with missing information
    @Test
    public void addActorFail() throws JSONException, IOException, URISyntaxException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addActor"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"name\": \"John Fail 1\", \"actorId\": \"actorfail\" }"))
                .build();

        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addActor"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"name\": \"John Fail 2\", \"actorId\": \"actorfail\" }"))
                .build();

        client.send(request1, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> response = client.send(request2, HttpResponse.BodyHandlers.ofString());
        System.out.println("addActorFail: The response status is " + response.statusCode());

        assertTrue(response.statusCode() == 400);
    }

    @Test
    public void addMoviePass() throws IOException, URISyntaxException, InterruptedException {

        // TODO: Ensure that there are no actors with this id

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addMovie"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"name\": \"Parasite Pass\", \"movieId\": \"moviepass\" }"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("addActorPass: The response status is " + response.statusCode());

        assertTrue(response.statusCode() == 200);
    }

    // Test for request with missing information
    @Test
    public void addMovieFail() throws IOException, URISyntaxException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addMovie"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"name\": \"Parasite Fail 1\", \"movieId\": \"moviefail\" }"))
                .build();

        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addMovie"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"name\": \"Parasite Fail 2\", \"movieId\": \"moviefail\" }"))
                .build();

        client.send(request1, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> response = client.send(request2, HttpResponse.BodyHandlers.ofString());
        System.out.println("addMovieFail: The response status is " + response.statusCode());

        assertTrue(response.statusCode() == 400);
    }

    @Test
    public void addRelationshipPass() throws IOException, URISyntaxException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest requestAddActor = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addActor"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"name\": \"Actor Relationship Pass\", \"actorId\": \"arp\" }"))
                .build();

        HttpRequest requestAddMovie = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addMovie"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"name\": \"Movie Relationship Pass\", \"movieId\": \"mrp\" }"))
                .build();

        HttpRequest requestAddRelationship = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addRelationship"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"actorId\": \"arp\", \"movieId\": \"mrp\" }"))
                .build();

        client.send(requestAddActor, HttpResponse.BodyHandlers.ofString());
        client.send(requestAddMovie, HttpResponse.BodyHandlers.ofString());

        HttpResponse<String> response = client.send(requestAddRelationship, HttpResponse.BodyHandlers.ofString());
        System.out.println("addRelationshipPass: The response status is " + response.statusCode());

        assertTrue(response.statusCode() == 200);
    }

    @Test
    public void addRelationshipFail() throws IOException, URISyntaxException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest requestAddActor = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addActor"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"name\": \"Actor Relationship Fail\", \"actorId\": \"arf\" }"))
                .build();

        HttpRequest requestAddMovie = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addMovie"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"name\": \"Movie Relationship Fail\", \"movieId\": \"mrf\" }"))
                .build();

        HttpRequest requestAddRelationship = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addRelationship"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"actorId\": \"arf\", \"movieId\": \"mrf\" }"))
                .build();

        HttpRequest requestAddDuplicateRelationship = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addRelationship"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"actorId\": \"arf\", \"movieId\": \"mrf\" }"))
                .build();

        client.send(requestAddActor, HttpResponse.BodyHandlers.ofString());
        client.send(requestAddMovie, HttpResponse.BodyHandlers.ofString());
        client.send(requestAddRelationship, HttpResponse.BodyHandlers.ofString());

        HttpResponse<String> response = client.send(requestAddDuplicateRelationship, HttpResponse.BodyHandlers.ofString());
        System.out.println("addRelationshipFail: The response status is " + response.statusCode());

        assertTrue(response.statusCode() == 400);
    }

    @Test
    public void getActorPass() throws IOException, URISyntaxException, InterruptedException, JSONException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest requestAddActor = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addActor"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"name\": \"Actor GetActor Pass\", \"actorId\": \"aap\" }"))
                .build();

        HttpRequest requestAddMovie = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addMovie"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"name\": \"Movie GetActor Pass\", \"movieId\": \"map\" }"))
                .build();

        HttpRequest requestAddRelationship = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addRelationship"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"actorId\": \"aap\", \"movieId\": \"map\" }"))
                .build();

        HttpRequest requestGetActor = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/getActor"))
                .method("GET", HttpRequest.BodyPublishers.ofString("{ \"actorId\": \"aap\" }"))
                .build();

        client.send(requestAddActor, HttpResponse.BodyHandlers.ofString());
        client.send(requestAddMovie, HttpResponse.BodyHandlers.ofString());
        client.send(requestAddRelationship, HttpResponse.BodyHandlers.ofString());

        HttpResponse<String> response = client.send(requestGetActor, HttpResponse.BodyHandlers.ofString());
        System.out.println("getActorPass: The response status is " + response.statusCode() + " with response body " + response.body());

        JSONObject obj = null;
        try {
            obj = new JSONObject(response.body());
        }
        catch (JSONException e)
        {
            assertTrue(false);
        }

        boolean correctBody = false;

        if (obj == null || !obj.has("actorId") | !obj.has("name") || !obj.has("movies"))
            correctBody = false;
        else if (obj.getString("actorId").equals("aap") && obj.getString("name").equals("Actor GetActor Pass") && obj.getJSONArray("movies").toString().equals("[\"map\"]"))
            correctBody = true;

        assertTrue(response.statusCode() == 200 && correctBody);
    }

    @Test
    public void getActorFail() throws IOException, URISyntaxException, InterruptedException, JSONException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest requestGetActor = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/getActor"))
                .method("GET", HttpRequest.BodyPublishers.ofString("{ \"actorId\": \"aaf\" }"))
                .build();

        HttpResponse<String> response = client.send(requestGetActor, HttpResponse.BodyHandlers.ofString());
        System.out.println("getActorFail: The response status is " + response.statusCode() + " with response body " + response.body());

        assertTrue(response.statusCode() == 404);
    }

    @Test
    public void getMoviePass() throws IOException, URISyntaxException, InterruptedException, JSONException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest requestAddActor = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addActor"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"name\": \"Actor GetMovie Pass\", \"actorId\": \"amp\" }"))
                .build();

        HttpRequest requestAddMovie = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addMovie"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"name\": \"Movie GetMovie Pass\", \"movieId\": \"mmp\" }"))
                .build();

        HttpRequest requestAddRelationship = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/addRelationship"))
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"actorId\": \"amp\", \"movieId\": \"mmp\" }"))
                .build();

        HttpRequest requestGetMovie = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/getMovie"))
                .method("GET", HttpRequest.BodyPublishers.ofString("{ \"movieId\": \"mmp\" }"))
                .build();

        client.send(requestAddActor, HttpResponse.BodyHandlers.ofString());
        client.send(requestAddMovie, HttpResponse.BodyHandlers.ofString());
        client.send(requestAddRelationship, HttpResponse.BodyHandlers.ofString());

        HttpResponse<String> response = client.send(requestGetMovie, HttpResponse.BodyHandlers.ofString());
        System.out.println("getMoviePass: The response status is " + response.statusCode() + " with response body " + response.body());

        JSONObject obj = null;
        try {
            obj = new JSONObject(response.body());
        }
        catch (JSONException e)
        {
            assertTrue(false);
        }

        boolean correctBody = false;

        if (obj == null || !obj.has("movieId") | !obj.has("name") || !obj.has("actors"))
            correctBody = false;
        else if (obj.getString("movieId").equals("mmp") && obj.getString("name").equals("Movie GetMovie Pass") && obj.getJSONArray("actors").toString().equals("[\"amp\"]"))
            correctBody = true;

        assertTrue(response.statusCode() == 200 && correctBody);
    }

    @Test
    public void getMovieFail() throws IOException, URISyntaxException, InterruptedException, JSONException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest requestGetMovie = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/api/v1/getMovie"))
                .method("GET", HttpRequest.BodyPublishers.ofString("{ \"movieId\": \"mmf\" }"))
                .build();

        HttpResponse<String> response = client.send(requestGetMovie, HttpResponse.BodyHandlers.ofString());
        System.out.println("getMovieFail: The response status is " + response.statusCode() + " with response body " + response.body());

        assertTrue(response.statusCode() == 404);
    }
}
