package com.Amasong.Microservicio_Reporte.util;

import com.Amasong.Microservicio_Reporte.modelo.Reporte;
import com.Amasong.Microservicio_Reporte.repositorio.ReporteRepositorio;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@Component
@Profile("!test") // No ejecutar en pruebas
public class DataLoader implements CommandLineRunner {

    private final ReporteRepositorio reporteRepositorio;

    public DataLoader(ReporteRepositorio reporteRepositorio) {
        this.reporteRepositorio = reporteRepositorio;
    }

    @Override
    public void run(String... args) throws Exception {
        if (reporteRepositorio.count() == 0) {
            Faker faker = new Faker();
            List<Reporte> reportes = IntStream.rangeClosed(1, 20)
                .mapToObj(i -> {
                    String tipo = faker.options().option("INVENTARIO", "VENTAS", "PAGOS");
                    return new Reporte(
                        null,
                        "Reporte de " + tipo + " " + faker.lorem().word(),
                        tipo,
                        String.format("{\"total\": %d, \"fecha\": \"%s\"}", 
                            faker.number().numberBetween(10, 1000),
                            faker.date().past(30, java.util.concurrent.TimeUnit.DAYS)),
                        LocalDate.now().minusDays(faker.number().numberBetween(1, 30))
                    );
                })
                .toList();
            
            reporteRepositorio.saveAll(reportes);
        }
    }
}