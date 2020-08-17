package it;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

public class StaticContentIT {

    private String port = System.getProperty("http.port");
    private String url = "http://localhost:" + port;

    @Test
    public void testEndpoint() throws Exception {
        System.out.println("Testing endpoint " + url);
        Client client = ClientBuilder.newClient();
        Invocation.Builder invoBuild = client.target(url)
            .request()
            .accept(MediaType.TEXT_HTML);
        Response response = invoBuild.get();
        int responseCode = response.getStatus();
        String content = response.readEntity(String.class);
        response.close();
        assertTrue(responseCode == 200, "Incorrect response code: " + responseCode);
        assertTrue(content.contains("IBM Cloud Starter"), "Incorrect contents, expected to see 'IBM Cloud Starter', found: " + content);
    }
}
