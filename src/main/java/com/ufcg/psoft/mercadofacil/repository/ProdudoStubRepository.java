package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.Model.Produto;

public class ProdudoStubRepository {

    public Produto update(Produto produto) {
        if (produto.getId() == 10L) {
            return Produto.builder()
                    .id(10L)
                    .codigoBarra("7899137500104")
                    .nome("Chiclete")
                    .fabricante("Fabricante Dez")
                    .preco()
                    .build();
        }
    }
}
