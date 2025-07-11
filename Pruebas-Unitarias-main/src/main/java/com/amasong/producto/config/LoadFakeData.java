package com.amasong.producto.config;

import com.amasong.producto.modelo.Producto;
import com.amasong.producto.repositorio.ProductoRepositorio;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadFakeData {

    @Bean
    CommandLineRunner initData(ProductoRepositorio repositorio) {
        return args -> {
            if (repositorio.count() == 0) { // Solo crea si no hay datos
                Faker faker = new Faker();
                for (int i = 0; i < 5; i++) {
                    Producto producto = new Producto();
                    producto.setNombre(faker.commerce().productName());
                    producto.setDescripcion(faker.lorem().sentence());
                    producto.setCategoria(faker.commerce().department());
                    producto.setPrecio(Double.parseDouble(faker.commerce().price(10000, 50000)));
                    producto.setStock(faker.number().numberBetween(10, 100));
                    repositorio.save(producto);
                }
                System.out.println("🟢 5 productos generados con éxito.");
            }
        };
    }
}
