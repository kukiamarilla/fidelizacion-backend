
package com.backend.fidelizacion.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.backend.fidelizacion.ejb.ReglaDAO;

@Path("/reglas")
@Consumes("application/json")
@Produces("application/json")
public class ReglaController {

    @Inject
    private ReglaDAO reglaDAO;

    @GET
    public Response listar() {
        return Response.ok(reglaDAO.listar()).build();
    }
}

