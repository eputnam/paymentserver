package org.putnam;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

/** Root resource (exposed at "one-time-payment" path) */
@Path("one-time-payment")
public class OneTimePayment {

  /**
   * Consumers can use this endpoint to submit one time payments.
   *
   * @return response with new balance and the next due date
   */
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response addPayment(OneTimePaymentPostRequest request) {
    double payment = request.getPaymentAmount();
    if (payment <= 0) {
      return Response.status(Response.Status.BAD_REQUEST)
          .entity("Payment must be greater than 0")
          .build();
    }

    double newBalance = OneTimePaymentService.calculateNewBalance(payment);
    LocalDateTime now = LocalDateTime.now();
    String formattedDueDate = OneTimePaymentService.formattedDueDate(now);

    OneTimePaymentPostResponse response =
        new OneTimePaymentPostResponse()
            .withNewBalance(newBalance)
            .withNextDueDate(formattedDueDate);

    return Response.status(Response.Status.OK).entity(response).build();
  }
}
