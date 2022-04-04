package com.backend.fidelizacion.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.backend.fidelizacion.ejb.BolsaDAO;
import com.backend.fidelizacion.ejb.ClienteDAO;
import com.backend.fidelizacion.ejb.ConceptoDAO;
import com.backend.fidelizacion.ejb.UsoDAO;
import com.backend.fidelizacion.ejb.UsoDetalleDAO;
import com.backend.fidelizacion.model.Bolsa;
import com.backend.fidelizacion.model.Cliente;
import com.backend.fidelizacion.model.Concepto;
import com.backend.fidelizacion.model.Uso;
import com.backend.fidelizacion.model.UsoDetalle;

import org.json.JSONObject;

@Path("/usos")
@Consumes("application/json")
@Produces("application/json")
public class UsoController {
    
    @Inject
    private UsoDAO usoDAO;
    @Inject
    private BolsaDAO bolsaDAO;
    @Inject
    private UsoDetalleDAO usoDetalleDAO;
    @Inject
    private ClienteDAO clienteDAO;
    @Inject
    private ConceptoDAO conceptoDAO;


    @GET
    public Response listar(@QueryParam("concepto") String concepto, @QueryParam("fecha") String fecha, @QueryParam("cliente") Long clienteId) {
        return Response.ok(usoDAO.listar(concepto, fecha, clienteId)).build();
    }

    @POST
    public Response utilizarPuntos(Uso uso) {
        Concepto concepto = conceptoDAO.obtener(uso.getConcepto().getId());
        Cliente cliente = clienteDAO.obtener(uso.getCliente().getId());
        Integer puntos = concepto.getPuntosRequeridos();
        List<Bolsa> bolsas = bolsaDAO.listarPorCliente(cliente.getId());
        List<UsoDetalle> usoDetalles = new ArrayList<>();
        uso.setPuntajeUtilizado(puntos);
        for (Bolsa bolsa : bolsas) {
            if (bolsa.getSaldo() > 0) {
                Integer utilizado = Math.min(bolsa.getSaldo(), puntos);
                bolsa.setSaldo(bolsa.getSaldo() - utilizado);
                bolsa.setPuntosUtilizados(bolsa.getPuntosUtilizados() + utilizado);
                puntos -= utilizado;
                UsoDetalle usoDetalle = new UsoDetalle();
                usoDetalle.setBolsa(bolsa);
                usoDetalle.setPuntajeUtilizado(utilizado);
                usoDetalles.add(usoDetalle);
            }
            if(puntos == 0) {
                break;
            }
        }
        if(puntos > 0) {
            JSONObject mensaje = new JSONObject();
            mensaje.put("mensaje", "No hay puntos suficientes");
            return Response.status(Response.Status.BAD_REQUEST).entity(mensaje.toString()).build();
        }
        usoDAO.crear(uso);
        for (UsoDetalle usoDetalle : usoDetalles) {
            bolsaDAO.actualizar(usoDetalle.getBolsa());
            usoDetalle.setUsoId(uso.getId());
            usoDetalleDAO.crear(usoDetalle);
        }
        uso = usoDAO.obtener(uso.getId());
        return Response.ok(uso).build();
    }
}
