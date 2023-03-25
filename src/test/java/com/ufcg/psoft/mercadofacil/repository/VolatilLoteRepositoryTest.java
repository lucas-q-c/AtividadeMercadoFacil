package com.ufcg.psoft.mercadofacil.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ufcg.psoft.mercadofacil.Model.Lote;
import com.ufcg.psoft.mercadofacil.Model.Produto;

@SpringBootTest
class VolatilLoteRepositoryTest {

   @Autowired
   LoteRepository<Lote, Long> driver;

   Lote lote;
   Lote resultado;
   Produto produto;

   @BeforeEach
   void setup() {
       produto = Produto.builder()
    		   .id(1L)
               .nome("Produto Base")
               .codigoBarra("123456789")
               .fabricante("Fabricante Base")
               .preco(125.36)
               .build();
       lote = Lote.builder()
               .id(1L)
               .numeroDeItens(100)
               .produto(produto)
               .build();
   }
   
   @AfterEach
   void tearDown() {
       produto = null;
       driver.deleteAll();
   }
   
   @Test
   @DisplayName("Não adiciona lote já existente")
   void addLoteExistente() {
	   driver.save(lote);
	   driver.save(lote);
	   assertEquals(driver.findAll().size(), 1);
   }

   @Test
   @DisplayName("Adicionar o primeiro Lote no repositorio de dados")
   void salvarPrimeiroLote() {
       resultado = driver.save(lote);

       assertEquals(driver.findAll().size(),1);
       assertEquals(resultado.getId().longValue(), lote.getId().longValue());
       assertEquals(resultado.getProduto(), produto);
   }

   @Test
   @DisplayName("Adicionar o segundo Lote (ou posterior) no repositorio de dados")
   void salvarSegundoLoteOuPosterior() {
       Produto produtoExtra = Produto.builder()
               .id(2L)
               .nome("Produto Extra")
               .codigoBarra("987654321")
               .fabricante("Fabricante Extra")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(2L)
               .numeroDeItens(100)
               .produto(produtoExtra)
               .build();
       driver.save(lote);

       resultado = driver.save(loteExtra);

       assertEquals(driver.findAll().size(),2);
       assertEquals(resultado.getId().longValue(), loteExtra.getId().longValue());
       assertEquals(resultado.getProduto(), produtoExtra);

   }
   
   @Test
   @DisplayName("Encontrar lote único existente no repositório")
   void encontrarLoteExistente() {
	   driver.save(lote);
	   resultado = driver.find(lote.getId());
	   
	   assertEquals(driver.findAll().size(),1);
	   assertEquals(resultado.getId().longValue(), lote.getId().longValue());
	   assertEquals(resultado.getProduto(), produto);
	   
   }
   
   @Test
   @DisplayName("Encontrar lote em repositório contendo mais de um lote")
   void encontrarLoteRepositorioMaisDeUmLote() {
	   Produto produtoExtra = Produto.builder()
               .id(2L)
               .nome("Produto Extra")
               .codigoBarra("987654321")
               .fabricante("Fabricante Extra")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(2L)
               .numeroDeItens(100)
               .produto(produtoExtra)
               .build();
       driver.save(lote);
       driver.save(loteExtra);
       resultado = driver.find(loteExtra.getId());
       
       assertEquals(driver.findAll().size(),2);
       assertEquals(resultado.getId().longValue(), loteExtra.getId().longValue());
       assertEquals(resultado.getProduto(), produtoExtra);
	   
   }
   
   @Test
   @DisplayName("Retornar lista com todos os lotes, repositório contendo um lote")
   void retornaListaLotesUmLote() {
	   driver.save(lote);
	   List<Lote> lista = driver.findAll();
	   
	   assertEquals(lista.size(), 1);
	   assertEquals(lista.get(0).getId().longValue(), lote.getId().longValue());
	   assertEquals(lista.get(0).getProduto(), produto);
   }
   
   @Test
   @DisplayName("Retornar lista com todos os lotes, repositório contendo mais de um lote")
   void retornaListaMultiLotes() {
	   Produto produtoExtra = Produto.builder()
               .id(2L)
               .nome("Produto Extra")
               .codigoBarra("987654321")
               .fabricante("Fabricante Extra")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(2L)
               .numeroDeItens(100)
               .produto(produtoExtra)
               .build();
       driver.save(lote);
       driver.save(loteExtra);
       List<Lote> lista = driver.findAll();
       List<Lote> listaEsperada = new ArrayList<>();
       listaEsperada.add(lote);
       listaEsperada.add(loteExtra);
       
       assertEquals(lista.size(), 2);
       for (int i = 0; i < lista.size(); i++) {
    	   assertEquals(lista.get(i).getId().longValue(), listaEsperada.get(i).getId().longValue());
    	   assertEquals(lista.get(i).getProduto(), listaEsperada.get(i).getProduto());
       }
   }
   
   @Test
   @DisplayName("Retornar lista vazia quando repositório não contém lotes")
   void retornaListaNenhumLote() {
	   List<Lote> lista = driver.findAll();
	   
	   assertTrue(lista.isEmpty());
   }
   
   @Test
   @DisplayName("Fazer update de um lote")
   void atualizaLote() {
	   driver.save(lote);
	   lote.setNumeroDeItens(200);
	   driver.update(lote);
	   assertEquals(driver.find(lote.getId()).getNumeroDeItens(), 200);
   }
   
   @Test
   @DisplayName("Update de um lote não interfere em outros lotes do repositório")
   void atualizaAfetaSomenteUmLote() {
	   Produto produtoExtra = Produto.builder()
               .id(2L)
               .nome("Produto Extra")
               .codigoBarra("987654321")
               .fabricante("Fabricante Extra")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(2L)
               .numeroDeItens(100)
               .produto(produtoExtra)
               .build();
       driver.save(lote);
       driver.save(loteExtra);
       lote.setNumeroDeItens(200);
	   driver.update(lote);
	   assertEquals(driver.findAll().size(), 2);
	   assertEquals(driver.find(loteExtra.getId().longValue()), loteExtra.getId().longValue());
   }
   
   @Test
   @DisplayName("Deleta um lote do repositório contendo somente este lote")
   void deletaLoteUnicoLote() {
	   driver.save(lote);
	   driver.delete(lote);
	   assertEquals(driver.findAll().size(), 0);
   }
   
   @Test
   @DisplayName("Deleta lote de repositório contendo mais de um lote")
   void deletaLoteMaisDeUmLote() {
	   Produto produtoExtra = Produto.builder()
               .id(2L)
               .nome("Produto Extra")
               .codigoBarra("987654321")
               .fabricante("Fabricante Extra")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(2L)
               .numeroDeItens(100)
               .produto(produtoExtra)
               .build();
       driver.save(lote);
       driver.save(loteExtra);
       driver.delete(lote);
       assertEquals(driver.findAll().size(), 1);
       assertEquals(driver.find(loteExtra.getId().longValue()).getId(), loteExtra.getId());
       assertEquals(driver.find(loteExtra.getId().longValue()).getProduto(), loteExtra.getProduto());
   }
   
   @Test
   @DisplayName("Deleta todos os lotes em repositório contendo somente um lote")
   void delteaLotesLoteUnico() {
	   driver.save(lote);
	   driver.deleteAll();
	   assertEquals(driver.findAll().size(), 0);
   }
   
   @Test
   @DisplayName("Deleta todos os lotes em ropositorio contendo mais de um lote")
   void DeletaTodos() {
	   Produto produtoExtra = Produto.builder()
               .id(2L)
               .nome("Produto Extra")
               .codigoBarra("987654321")
               .fabricante("Fabricante Extra")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(2L)
               .numeroDeItens(100)
               .produto(produtoExtra)
               .build();
       driver.save(lote);
       driver.save(loteExtra);
       driver.deleteAll();
       assertEquals(driver.findAll().size(), 0);
   }

}
