package at.htl.formula1.boundary;

import at.htl.formula1.entity.Driver;
import at.htl.formula1.entity.Result;

import javax.json.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Path("results")
public class ResultsEndpoint {


    @PersistenceContext
    EntityManager em;

    /**
     * @param name als QueryParam einzulesen
     * @return JsonObject
     */
    @GET
    public JsonObject getPointsSumOfDriver(@QueryParam("name") String name) {
        Long points = em.createNamedQuery("Result.getPointsSumOfDriver", Long.class).setParameter("NAME", name).getSingleResult();

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("driver", name)
                .add("points", points)
                .build();

        return jsonObject;
    }

    /**
     * @param id des Rennens
     * @return
     */
    @GET
    @Path("winner/{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findWinnerOfRace(@PathParam("country") String country) {

        Long driverId = em.createNamedQuery("Result.getWinner", Driver.class)
                        .setParameter("COUNTRY", country).getSingleResult().getId();

        Driver winner = em.find(Driver.class, driverId);

        return Response.ok(winner).build();
    }


    // Ergänzen Sie Ihre eigenen Methoden ...

}
