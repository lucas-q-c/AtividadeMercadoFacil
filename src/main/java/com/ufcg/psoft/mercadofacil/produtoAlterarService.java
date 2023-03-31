package com.ufcg.psoft.mercadofacil;

import com.ufcg.psoft.mercadofacil.Model.Produto;

@FunctionalInterface
public interface produtoAlterarService {

    Produto alterar(Produto produtoAlterado);
}
