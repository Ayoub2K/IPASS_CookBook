package CookBook.webservices;

import CookBook.model.Book;
import CookBook.model.Gerecht;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;

@Path("/gerechten")
public class BookResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response gerechten(){
        Book book = Book.getBook();
        List<Gerecht> alleGerechten = book.getAlleGerechten();

        if(alleGerechten == null){
            return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<>("error", "Deze Book is leeg")).build();
        }else {
            return Response.ok(Book.getBook().getAlleGerechten()).build();
        }
    }

    @GET
    @Path("{naam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response gerecht(@PathParam("naam") String naam){
        return Response.ok(Book.getBook().getGerechtByName(naam)).build();
    }

}
