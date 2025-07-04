package com.Amasong.Microservicio_Reporte.servicio;


import com.Amasong.Microservicio_Reporte.modelo.Reporte;
import com.Amasong.Microservicio_Reporte.repositorio.ReporteRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;

@Service
@Transactional
public class ReporteServicio {
    @Autowired
    private ReporteRepositorio repository;

    public List<Reporte> findAll() {
        return repository.findAll();
    }

    public Reporte findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Reporte save(Reporte reporte) {
        reporte.setFechaGeneracion(LocalDate.now()); // Fecha automática
        return repository.save(reporte);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Reporte> findByTipo(String tipo) {
        return repository.findByTipoReporte(tipo);
    }
}