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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.backend.fidelizacion.ejb.BolsaDAO;
import com.backend.fidelizacion.ejb.ClienteDAO;
import com.backend.fidelizacion.model.Cliente;

import org.json.JSONObject;

@Path("/clientes")
@Consumes("application/json")
@Produces("application/json")
public class ClienteController {

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private BolsaDAO bolsaDAO;

    @GET
    public Response listar(@QueryParam("nombre") String nombre, @QueryParam("apellido") String apellido, @QueryParam("cumpleanhos") String cumpleanhos) {
        return Response.ok(clienteDAO.listar(nombre, apellido, cumpleanhos)).build();
    }

    @POST
    public Response crear(Cliente p) {
        clienteDAO.crear(p);
        return Response.ok(p).build();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Long id) {
        return Response.ok(clienteDAO.obtener(id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, Cliente p) {
        p.setId(id);
        clienteDAO.actualizar(p);
        return Response.ok(p).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        clienteDAO.eliminar(id);
        JSONObject response = new JSONObject();
        response.put("message", "Cliente eliminado");
        return Response.ok(response.toString()).build();
    }

    @GET
    @Path("/{id}/bolsas")
    public Response listarBolsas(@PathParam("id") Long id) {
        return Response.ok(bolsaDAO.listarPorCliente(id)).build();
    }

    @GET
    @Path("/puntos-por-vencer")
    public Response listarConPuntosPorVencer(@QueryParam("dias") Integer dias) {
        if(dias == null) {
            JSONObject mensaje = new JSONObject();
            mensaje.put("mensaje", "Par??metro 'dias' es obligatorio");
            return Response.status(Response.Status.BAD_REQUEST).entity(mensaje.toString()).build();
        }
        return Response.ok(clienteDAO.conPuntosPorVencer(dias)).build();
    }
}
