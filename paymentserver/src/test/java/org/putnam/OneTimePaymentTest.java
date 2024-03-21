package org.putnam;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OneTimePaymentTest {

    private HttpServer server;
    private WebTarget target;

    @BeforeEach
    public void setUp() throws Exception {
        server = Server.startServer();
        Client c = ClientBuilder.newClient();


        target = c.target(Server.BASE_URI);
    }

    @AfterEach
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Test to see that the info returned is what we expect
     */
    @Test
    public void testBasicPost() {
        OneTimePaymentPostRequest request = new OneTimePaymentPostRequest();
        request.setPaymentAmount(50.00);

        Response clientResponse = target.path("one-time-payment").request().post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));
        OneTimePaymentPostResponse oneTimePaymentPostResponse = clientResponse.readEntity(OneTimePaymentPostResponse.class);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatus());
        assertEquals(47.50, oneTimePaymentPostResponse.getNewBalance());
        assertNotNull(oneTimePaymentPostResponse.getNextDueDate());
    }

    @Test
    public void testOnePercentMatch() {

        OneTimePaymentPostRequest request = new OneTimePaymentPostRequest();
        request.setPaymentAmount(5.00);

        Response clientResponse = target.path("one-time-payment").request().post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));
        OneTimePaymentPostResponse oneTimePaymentPostResponse = clientResponse.readEntity(OneTimePaymentPostResponse.class);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatus());
        assertEquals(94.95, oneTimePaymentPostResponse.getNewBalance());
        assertNotNull(oneTimePaymentPostResponse.getNextDueDate());
    }

    @Test
    public void testThreePercentMatch() {
        OneTimePaymentPostRequest request = new OneTimePaymentPostRequest();
        request.setPaymentAmount(20.00);

        Response clientResponse = target.path("one-time-payment").request().post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));
        OneTimePaymentPostResponse oneTimePaymentPostResponse = clientResponse.readEntity(OneTimePaymentPostResponse.class);
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatus());
        assertEquals(79.40, oneTimePaymentPostResponse.getNewBalance());
        assertNotNull(oneTimePaymentPostResponse.getNextDueDate());

    }

}
