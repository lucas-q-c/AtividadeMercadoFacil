package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.Model.Produto;

public class ProdutoValidarCodigoDeBarrasImplService implements ProdutoValidarCodigoDeBarrasService {

    public boolean validarCodigoBarras(Produto produto) {
        String codigo = produto.getCodigoBarra();
        int soma = 0;

        for (int i = codigo.length() - 2; i >= 0; i = i - 2) {
            soma += Character.getNumericValue(codigo.charAt(i));
        }

        for (int i = codigo.length() - 3; i >= 0; i = i - 2) {

        }



        return true;
    }
}
