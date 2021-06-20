package CookBook.webservices;

import CookBook.model.Book;
import CookBook.model.Gerecht;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;

@Path("gerechten")
public class GerechtResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response gerechten() {
        Book book = Book.getBook();
        List<Gerecht> alleGerechten = book.getAlleGerechten();

        if (alleGerechten == null) {
            return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<>("error", "Deze Book is leeg")).build();
        } else {
            return Response.ok(alleGerechten).build();
        }
    }

    @GET
    @Path("favorieten")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavorieten() {
        Book book = Book.getBook();
        List<Gerecht> alleGerechten = book.getFavorieten();

        if (alleGerechten == null) {
            return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<>("error", "Deze Book is leeg")).build();
        } else {
            return Response.ok(alleGerechten).build();
        }
    }

    @GET
    @Path("/{naam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGerecht(@PathParam("naam") String naam) {
        return Response.ok(Book.getBook().getGerechtByName(naam)).build();
    }

    @GET
    @Path("favoriet/{naam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setFavoriet(@PathParam("naam") String naam) {
        try {
            Book.getBook().getGerechtByName(naam).setFavoriet(true);
            return Response.ok().build();
        }catch (Exception e){
           return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @DELETE
    @Path("/{naam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("naam") String naam) {
        Book book = Book.getBook();
        return book.verwijderGerecht(naam)
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{gerechtNaam}/{ingredientNaam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("gerechtNaam") String gerechtNaam, @PathParam("ingredientNaam") String ingredientNaam) {
        Book book = Book.getBook();
        return book.getGerechtByName(gerechtNaam).verwijderIngredient(ingredientNaam)
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{gerechtNaam}")
    @RolesAllowed("editor")
    public Response putGerecht(@PathParam("gerechtNaam") String nm,
                                  @FormParam("beschrijving") String bs,
                                  @FormParam("bereidingstijd") String bt,
                                  @FormParam("bereidingswijze") String bw,
                                  @FormParam("categorie") String ct,
                                  @FormParam("portie") String pr) {
        try{
            Book.getBook().getGerechtByName(nm).bewerkGerecht(bs, bt, bw, ct, pr);
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("editor")
    public Response createGerecht(@FormParam("naam") String nm,
                                  @FormParam("beschrijving") String bs,
                                  @FormParam("bereidingstijd") String bt,
                                  @FormParam("bereidingswijze") String bw,
                                  @FormParam("categorie") String ct,
                                  @FormParam("portie") String pr) {

        Gerecht gerecht = new Gerecht(nm, bs, bt, bw, ct, pr);

        if (!Book.getBook().containsName(gerecht.getNaam())) {
            Book.getBook().addGerecht(gerecht);
            return Response.ok(gerecht).build();
        } else {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Path("object")
    public Response createGerechtObject(String body) throws JsonProcessingException {

        JsonNode jsonNode = new ObjectMapper().readTree(body);
        String naam = jsonNode.get("naam").asText();

        if (!Book.getBook().containsName(naam)) {
            Book.getBook().createGerecht(jsonNode);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Path("{gerechtNaam}/ingredient")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGerecht(@PathParam("gerechtNaam") String gerechtNaam,
                                  @FormParam("naam") String naam,
                                  @FormParam("hoeveelheid") int hoeveelheid,
                                  @FormParam("calper") int calper) {

        if (Book.getBook().containsName(gerechtNaam)) {
            Book.getBook().getGerechtByName(gerechtNaam).voegIngredient(naam, hoeveelheid, calper);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{gerechtNaam}/{ingredientNaam}")
    public Response putIngredient(@PathParam("gerechtNaam") String gerechtNaam,
                                  @PathParam("ingredientNaam") String ingredientNaam,
                                  @FormParam("hoeveelheidIngr") int hoeveelheid) {
        try{
            Book.getBook().getGerechtByName(gerechtNaam).getIngredientbyName(ingredientNaam).setHoeveelheid(hoeveelheid);
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    }
