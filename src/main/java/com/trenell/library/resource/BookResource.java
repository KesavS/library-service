package com.trenell.library.resource;

import com.trenell.library.model.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.core.Response;

@Path("/books")
public interface BookResource {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Book> findAll();

  @GET
  @PathParam("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findById(@PathParam("id") String id);

  @GET
  @PathParam("/search/{isbn}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findByISBN(@PathParam("isbn") String isbn);

  @PUT
  @Path("{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response update(Book book);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response create(Book book);

  @DELETE
  @Path("{id}")
  public Response delete(@PathParam("id") String id);
}
