package com.project.Project.project.controller;

import com.project.Project.project.model.Articulo;
import com.project.Project.project.model.ArticulosCompraDTO;
import com.project.Project.project.model.CompraArticulosDTO;
import com.project.Project.project.model.DevoUpdateDTO;
import com.project.Project.project.service.CompraService;
import com.project.Project.project.service.ErrorLoggingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;


@WebMvcTest(CompraController.class)
@ActiveProfiles("test")
public class CompraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompraService compraService;

    @MockBean
    private ErrorLoggingService errorLoggingService;

    @Test
    public void cuandoCompraExitosa_entoncesRetornaStatusOk() throws Exception {
        CompraArticulosDTO compraArticulosDTO = new CompraArticulosDTO();
        // Configura el DTO según sea necesario
        List<ArticulosCompraDTO> articulosCompraList = new ArrayList<>();

// Crear y añadir el primer ArticulosCompraDTO
        ArticulosCompraDTO articulo1 = new ArticulosCompraDTO();
        articulo1.setArticulo(new Articulo("Esfero", "BIC", "2021", "Negro", "Unidades"));
        articulo1.setUnidadesCompradas(10);
        articulo1.setValorUnidad(100);
        articulo1.setIdCategoria(1);
        articulo1.setEstado(1);
        articulosCompraList.add(articulo1);

// Crear y añadir el segundo ArticulosCompraDTO
        ArticulosCompraDTO articulo2 = new ArticulosCompraDTO();
        articulo2.setArticulo(new Articulo("Lápiz", "Stabilo", "2022", "Azul", "Unidades"));
        articulo2.setUnidadesCompradas(5);
        articulo2.setValorUnidad(50);
        articulo2.setIdCategoria(1);
        articulo2.setEstado(1);
        articulosCompraList.add(articulo2);

        compraArticulosDTO.setArticulosCompra(articulosCompraList);
        compraArticulosDTO.setIdUsuario(1);
        compraArticulosDTO.setIdProveedor(1);
        Mockito.doNothing().when(compraService).guardarCompraYRelaciones(any(CompraArticulosDTO.class));

        mockMvc.perform(post("/api/compras/registrarCompra")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(compraArticulosDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarDevolucion() throws Exception {
        DevoUpdateDTO devoUpdateDTO = new DevoUpdateDTO();
        devoUpdateDTO.setIdCompra(49);
        devoUpdateDTO.setDescripcion("precio");
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(6);
        devoUpdateDTO.setDevuelto(arr);

        Mockito.doNothing().when(compraService).actualizarDevolucion(
                devoUpdateDTO.getIdCompra(),
                devoUpdateDTO.getDescripcion(),
                devoUpdateDTO.getDevuelto()
        );

        mockMvc.perform(post("/api/compras/devolucionCompra")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(devoUpdateDTO)))
                .andExpect(status().isOk());
    }
}