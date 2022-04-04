package com.backend.fidelizacion.task;

import javax.ejb.Schedule;
import javax.inject.Singleton;


@Singleton
public class ActualizarBolsas {
    @Schedule(hour = "*", minute = "*", second = "*/5", persistent = false)
    public void atSchedule() throws InterruptedException {
        System.out.println("DeclarativeScheduler:: In atSchedule()");
    }
}
