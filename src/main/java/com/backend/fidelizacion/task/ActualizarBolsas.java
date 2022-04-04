package com.backend.fidelizacion.task;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import com.backend.fidelizacion.ejb.BolsaDAO;
import com.backend.fidelizacion.model.Bolsa;


@Singleton
public class ActualizarBolsas {

    @Inject
    private BolsaDAO bolsaDAO;
    
    @Schedule(hour = "*", minute = "*", second = "*/60", persistent = false)
    public void atSchedule() throws InterruptedException {
        List<Bolsa> bolsas = bolsaDAO.vencidas();
        for (Bolsa bolsa : bolsas) {
            bolsa.setSaldo(0);
            bolsaDAO.actualizar(bolsa);
        }
        System.out.println("Bolsas vencidas: " + bolsas.size());
    }
}
