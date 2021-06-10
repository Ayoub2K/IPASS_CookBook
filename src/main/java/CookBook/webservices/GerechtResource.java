package CookBook.webservices;

import CookBook.model.Book;
import CookBook.model.Gerecht;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;

@Path("gerechten/")
public class GerechtResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response gerechten(){
        Book book = Book.getBook();
        List<Gerecht> alleGerechten = book.getAlleGerechten();

        if(alleGerechten == null){
            return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<>("error", "Deze Book is leeg")).build();
        }else {
            return Response.ok(alleGerechten).build();
        }
    }

    @GET
    @Path("{naam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGerecht(@PathParam("naam") String naam){
        return Response.ok(Book.getBook().getGerechtByName(naam)).build();
    }

    @POST
    @Path("gerecht")
    @Produces(MediaType.APPLICATION_JSON)
   // @Consumes(MediaType.APPLICATION_JSON)
    public Response setGerecht(@FormParam("naam") String naam,
                               @FormParam("beschrijving") String beschrijving,
                               @FormParam("bereidingstijd") String bereidingstijd,
                               @FormParam("bereidingswijze") String bereidingswijze,
                               @FormParam("categorie") String categorie,
                               @FormParam("portie") String portie){

        Book book = Book.getBook();
        Gerecht newGerecht = new Gerecht(naam, beschrijving, bereidingstijd, bereidingswijze, categorie, portie );

        book.addGerecht(newGerecht);

        return Response.ok(newGerecht).build();
    }
}
