
package com.backend.fidelizacion.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.backend.fidelizacion.ejb.VencimientoDAO;
import com.backend.fidelizacion.model.Vencimiento;

import org.json.JSONObject;

@Path("/vencimientos")
@Consumes("application/json")
@Produces("application/json")
public class VencimientoController {

    @Inject
    private VencimientoDAO vencimientoDAO;

    @GET
    public Response listar() {
        return Response.ok(vencimientoDAO.listar()).build();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Long id) {
        return Response.ok(vencimientoDAO.obtener(id)).build();
    }

    @POST
    public Response crear(Vencimiento vencimiento) {
        if(!vencimiento.isValid()) {
            JSONObject mensaje = new JSONObject();
            mensaje.put("mensaje", "Límite superior debe ser mayor al límite inferior");
            return Response.status(Response.Status.BAD_REQUEST).entity(mensaje.toString()).build();
        }
        vencimientoDAO.crear(vencimiento);
        return Response.ok(vencimiento).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, Vencimiento vencimiento) {
        Vencimiento current = vencimientoDAO.obtener(id);
        current.setFechaInicio(vencimiento.getFechaInicio());
        current.setFechaFin(vencimiento.getFechaFin());
        current.setDiasValidez(vencimiento.getDiasValidez());
        if(!current.isValid()) {
            JSONObject mensaje = new JSONObject();
            mensaje.put("mensaje", "La fecha de inicio debe ser mayor a la fecha de fin");
            return Response.status(Response.Status.BAD_REQUEST).entity(mensaje.toString()).build();
        }
        vencimientoDAO.actualizar(current);
        return Response.ok(current).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        vencimientoDAO.eliminar(id);
        JSONObject mensaje = new JSONObject();
        mensaje.put("mensaje", "Vencimiento eliminada");
        return Response.ok(mensaje.toString()).build();
    }
}

