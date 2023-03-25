package com.ufcg.psoft.mercadofacil.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Lote {

    private Long id;

    private Produto produto;

    private int numeroDeItens;
    
    public Long getId() {
    	return this.id;
    }
    
    public Produto getProduto() {
    	return this.produto;
    }

}