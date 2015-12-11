package com.trenell.library.resource;

import com.trenell.library.model.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/books")
public interface BookResource {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Book> findAll();

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Book findById(@PathParam("id") int id);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Book create(Book book);

  @PUT
  @Path("{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Book update(Book book);
}