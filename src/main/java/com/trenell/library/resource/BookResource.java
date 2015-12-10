package com.trenell.library.resource;

import com.trenell.library.model.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/books")
public interface BookResource {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Book> getBooks();

  @GET
  @PathParam("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Book findById(@PathParam("id") String id);

  @GET
  @PathParam("/search/{isbn}")
  @Produces(MediaType.APPLICATION_JSON)
  public Book findByISBN(@PathParam("isbn") String isbn);

  @PUT
  @Path("{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Book update(Book book);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Book create(Book book);

  @DELETE
  @Path("{id}")
  public void delete(@PathParam("id") String id);
}
