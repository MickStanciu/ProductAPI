package com.example.core.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface TimesheetRestInterface {

    @GET
    @Path("/{tenantId}/{accountId}")
    Response getTimeSheetEntries(
            @PathParam("tenantId") @DefaultValue("0") String tenantId,
            @PathParam("accountId") @DefaultValue("0") BigInteger accountId,
            @QueryParam("from") String from,
            @QueryParam("to") String to
    );
}
