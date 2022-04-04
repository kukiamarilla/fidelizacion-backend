package com.backend.fidelizacion.rest;

import java.sql.Date;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.backend.fidelizacion.ejb.BolsaDAO;
import com.backend.fidelizacion.ejb.ReglaDAO;
import com.backend.fidelizacion.ejb.VencimientoDAO;
import com.backend.fidelizacion.model.Bolsa;
import com.backend.fidelizacion.model.Operacion;

import org.json.JSONObject;

@Path("/bolsas")
@Produces("application/json")
@Consumes("application/json")
public class BolsaController {

    @Inject
    private BolsaDAO bolsaDAO;
    @Inject
    private ReglaDAO reglaDAO;
    @Inject
    private VencimientoDAO vencimientoDAO;

    @GET
    @Path("/por-rango")
    public Response obtenerBolsas(@QueryParam("min") Integer min, @QueryParam("max") Integer max) {
        if(min == null || max == null) {
            JSONObject mensaje = new JSONObject();
            mensaje.put("mensaje", "Los parámetros min y max son obligatorios");
            return Response.status(Response.Status.BAD_REQUEST).entity(mensaje.toString()).build();
        }
        return Response.ok(bolsaDAO.listarPorRango(min, max)).build();
    }   

    @POST
    public Response cargarPuntos(Operacion op) {
        Integer puntos = reglaDAO.puntosPorMonto(op.getMonto());
        Date hoy = new Date(System.currentTimeMillis());
        Integer validez = vencimientoDAO.diasValidezPorFecha(hoy);
        Bolsa bolsa = new Bolsa(
            op.getClienteId(),
            hoy,
            Date.valueOf(hoy.toLocalDate().plusDays(validez)),
            puntos,
            0,
            puntos,
            op.getMonto()
        );

        bolsaDAO.crear(bolsa);
        return Response.ok(bolsa).build();
    }

    @GET
    @Path("/puntos-por-monto")
    public Response puntosPorMonto(@QueryParam("monto") Integer monto) {
        JSONObject mensaje = new JSONObject();
        if(monto == null) {
            mensaje.put("mensaje", "Parámentro 'monto' es obligatorio");
            return Response.status(Response.Status.BAD_REQUEST).entity(mensaje.toString()).build();
        }
        Integer puntos = reglaDAO.puntosPorMonto(monto);
        mensaje.put("puntos", puntos);
        return Response.ok(mensaje.toString()).build();
    }

     
}
