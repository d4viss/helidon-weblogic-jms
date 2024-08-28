
package controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bean.RequestBean;
import jms.Producer;
import model.PayloadHolder;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/jmsconn")
@ApplicationScoped
public class Controller {

    private Producer producer;

    @Inject
    public Controller(Producer producer) {
        this.producer = producer;
    }

    /**
     * endpoint to receive a message and classify it
     * @param bean Request body
     * @return
     */
    @POST
    @Path("/send/test-queue")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendTestQueue(@RequestBody RequestBean bean) {
        PayloadHolder holder = new PayloadHolder(bean.getMessage(), bean.getCorrelationId(), bean.getDestination());
        System.out.println(holder);
        producer.submit(holder);

        return Response.ok().build();
    }
}
