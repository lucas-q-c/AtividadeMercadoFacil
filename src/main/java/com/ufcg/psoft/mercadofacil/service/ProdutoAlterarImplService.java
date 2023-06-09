package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.Model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProdutoAlterarImplService implements ProdutoAlterarService {
    @Autowired
    ProdutoRepository<Produto, Long> produtoRepository;

    @Override
    public Produto alterar(Produto produtoAlterado) {
        if (produtoAlterado.getPreco() <= 0) {
            throw new RuntimeException("Preco invalido!");
        }
        return produtoRepository.update(produtoAlterado);
    }

}
