package com.ufcg.psoft.mercadofacil.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ufcg.psoft.mercadofacil.Model.Produto;
import com.ufcg.psoft.mercadofacil.service.ProdutoAlterarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes do Controlador de Produtos")
public class ProdutoV1ControllerTest {
    @Autowired
    ProdutoAlterarService produtoAlterarService;

    @PutMapping("/{id}")
    public Produto updateProduct(
            @PathVariable Long id,
            @RequestBody
            )

    @Autowired
    MockMvc mockMvc;

    Produto produto;

    @BeforeEach
    void setup() {
        produto = Produto.builder()
                .id(10L)
                .nome("Produto Dez")
                .codigoBarra("123456789")
                .fabricante("Fabricante Dez")
                .preco(125.36)
                .build();
    }

    @Test
    @DisplayName("Quando altero produto com nome válido")
    void alteroProdutoComNomeValido() throws JsonProcessingException {
        //Arrange
        produto.setNome("Chiclete");
        //Act
        String produtoModificadoJSONString = mockMvc.perform(
                        put("/produto/" + 10)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        Produto produtoModificado = objectMapper.readValue(produtoModificadoJSONString, Produto);
        assertEquals("Chiclete", produtoModificado.getNome());
    }
}
