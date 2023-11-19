package com.project.Project.project.controller;

import com.project.Project.project.model.ProductoRevertidoDTO;
import com.project.Project.project.model.ReversionVentaDTO;
import com.project.Project.project.model.VentaArticuloDTO;
import com.project.Project.project.service.ErrorLoggingService;
import com.project.Project.project.service.VentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class VentaControllerTest {

    @Mock
    private VentaService ventaService;

    @Mock
    private ErrorLoggingService errorLoggingService;

    @InjectMocks
    private VentaController ventaController;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void createVenta_Success() {
        // Preparar datos de entrada y respuesta esperada
        VentaArticuloDTO ventaArticuloDTO = new VentaArticuloDTO();
        // Configurar datos de ventaArticuloDTO...

        // Simular comportamiento del servicio
        doNothing().when(ventaService).createVenta(any(VentaArticuloDTO.class));

        // Ejecutar el método a probar
        ResponseEntity<Object> response = ventaController.createVenta(ventaArticuloDTO);

        // Verificar resultados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Venta registrada exitosamente", response.getBody());

        // Verificar interacciones con mocks
        verify(ventaService).createVenta(ventaArticuloDTO);
    }

    @Test
    void revertirVenta_Success() {
        // Preparar datos de entrada
        ReversionVentaDTO reversionVentaDTO = new ReversionVentaDTO();
        reversionVentaDTO.setIdVenta(14);
        reversionVentaDTO.setMotivoReversion("a");
        ArrayList<ProductoRevertidoDTO> arr = new ArrayList<>();
        ProductoRevertidoDTO producto = new ProductoRevertidoDTO();
        producto.setCantidad(5);
        producto.setIdArticulo(6);
        arr.add(producto);
        reversionVentaDTO.setDevuelto(arr);
        // Configurar datos de reversionVentaDTO...
        when(ventaService.revertirVenta(anyInt(), anyString(), anyList(), anyBoolean())).thenReturn(true);

        // Ejecutar el método a probar
        ResponseEntity<String> response = ventaController.revertirVenta(reversionVentaDTO);

        // Verificar resultados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("Articulos devueltos exitosamente"));

        // Verificar interacciones con mocks
        verify(ventaService).revertirVenta(reversionVentaDTO.getIdVenta(), reversionVentaDTO.getMotivoReversion(), reversionVentaDTO.getDevuelto(), reversionVentaDTO.isConfirmacionUsuario());
    }

}
