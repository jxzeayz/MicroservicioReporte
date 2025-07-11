package com.amasong.producto.controlador;

import com.amasong.producto.modelo.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")  // Para usar application-test.properties y base de datos de prueba
@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)  // Carga datos antes de cada test
public class ProductoControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenerProductoPorId() throws Exception {
        mockMvc.perform(get("/api/producto/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Zapato")))
                .andExpect(jsonPath("$.categoria", is("Calzado")));
    }

    @Test
    void testGuardarProducto() throws Exception {
        Producto producto = new Producto(null, "Nuevo Producto", "Descripción prueba", "Prueba", 10000, 5);

        mockMvc.perform(post("/api/producto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Nuevo Producto")))
                .andExpect(jsonPath("$.stock", is(5)));
    }

    @Test
    void testEliminarProducto() throws Exception {
        mockMvc.perform(delete("/api/producto/1"))
                .andExpect(status().isOk());

        // Verificar que ya no existe
        mockMvc.perform(get("/api/producto/1"))
                .andExpect(status().is4xxClientError());
    }

}
