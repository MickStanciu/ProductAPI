package com.example.core.rest;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Stateless
@Path("/health")
@Produces("application/json")
public class HealthRest {

    @GET
    @Path("/ping")
    public Response pong() {
        return Response.status(Response.Status.OK).build();
    }
}