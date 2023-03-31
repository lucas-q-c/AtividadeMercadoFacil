package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.Model.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoVolatilRepository implements ProdutoRepository<Produto, Long>{

    @Override
    public Produto save(Produto produto) {
        return null;
    }

    @Override
    public Produto find(Long aLong) {
        return null;
    }

    @Override
    public List<Produto> findAll() {
        return null;
    }

    @Override
    public Produto update(Produto lote) {
        return null;
    }

    @Override
    public void delete(Produto lote) {

    }

    @Override
    public void deleteAll() {

    }
}
