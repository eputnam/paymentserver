package org.putnam;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Root resource (exposed at "one-time-payment" path)
 */
@Path("one-time-payment")
public class OneTimePayment {

    /**
     *
     * Consumers can use this endpoint to submit one time payments.
     *
     * @return response with new balance and the next due date
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPayment(OneTimePaymentPostRequest request) {
        double payment = request.getPaymentAmount();
        if(payment <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Payment must be greater than 0").build();
        }

        double newBalance = calculateNewBalance(payment);
        String formattedDueDate = calculateNextDueDate();

        OneTimePaymentPostResponse response = new OneTimePaymentPostResponse().withNewBalance(newBalance).withNextDueDate(formattedDueDate);

        return Response.status(Response.Status.OK).entity(response).build();
    }

    private double calculateNewBalance(double payment){
        double currentBalance = 100.00;
        return currentBalance - processMatch(payment);
    }

    private double processMatch(double payment) {
        if(payment < 10.00) {
            return payment + (payment * .01);
        } else if(payment < 50.00) {
            return payment + (payment * .03);
        } else if(payment >= 50.00) {
            return payment + (payment * .05);
        } else {
            return payment;
        }
    }

    private String calculateNextDueDate() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextDueDate = now.plusDays(15);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        return formatter.format(nextDueDate);
    }
}
