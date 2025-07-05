package com.Amasong.Microservicio_Reporte.servicio;

import com.Amasong.Microservicio_Reporte.modelo.Reporte;
import com.Amasong.Microservicio_Reporte.repositorio.ReporteRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReporteServicioTest {

    @Mock
    private ReporteRepositorio repository;

    @InjectMocks
    private ReporteServicio servicio;

    @Test
    void findAll_shouldReturnAllReports() {
        // Arrange
        when(repository.findAll()).thenReturn(List.of(
            new Reporte(1L, "Inventario", "INVENTARIO", "{}", LocalDate.now()),
            new Reporte(2L, "Ventas", "VENTAS", "{}", LocalDate.now())
        ));

        // Act
        List<Reporte> reportes = servicio.findAll();

        // Assert
        assertEquals(2, reportes.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findById_shouldReturnReportWhenExists() {
        // Arrange
        Long id = 1L;
        Reporte reporte = new Reporte(id, "Inventario", "INVENTARIO", "{}", LocalDate.now());
        when(repository.findById(id)).thenReturn(Optional.of(reporte));

        // Act
        Reporte result = servicio.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    // Más pruebas para otros métodos...
}