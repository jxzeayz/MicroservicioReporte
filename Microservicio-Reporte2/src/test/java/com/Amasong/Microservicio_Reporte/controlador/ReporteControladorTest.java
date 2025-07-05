package com.Amasong.Microservicio_Reporte.controlador;

import com.Amasong.Microservicio_Reporte.modelo.Reporte;
import com.Amasong.Microservicio_Reporte.servicio.ReporteServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReporteControlador.class)
class ReporteControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReporteServicio servicio;

    @Test
    void listar_shouldReturnReports() throws Exception {
        // Arrange
        Reporte reporte = new Reporte(1L, "Inventario", "INVENTARIO", "{}", LocalDate.now());
        when(servicio.findAll()).thenReturn(List.of(reporte));

        // Act & Assert
        mockMvc.perform(get("/api/v1/reportes"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].nombreReporte").value("Inventario"));
    }

    @Test
    void listar_shouldReturnNoContentWhenEmpty() throws Exception {
        // Arrange
        when(servicio.findAll()).thenReturn(List.of());

        // Act & Assert
        mockMvc.perform(get("/api/v1/reportes"))
               .andExpect(status().isNoContent());
    }

    // Más pruebas para otros endpoints...
}