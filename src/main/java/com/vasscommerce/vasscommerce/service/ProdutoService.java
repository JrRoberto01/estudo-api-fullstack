package com.vasscommerce.vasscommerce.service;

import com.vasscommerce.vasscommerce.model.Produto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();

    public ProdutoService(){
        produtos.add(new Produto(1L, 1L, "Produto 1", "Descrição do produto 1", "Url da foto", LocalDateTime.now(), LocalDateTime.now(), 100.0f));
    }

    //Metodos de busca
    public List<Produto> listarTodos(){
        return produtos;
    }

    public List<Produto> buscarProdutoPorCategoriaEspecifica(Long categoriaId){
        List<Produto> resultadoBusca = new ArrayList<>();
        for (Produto produto: produtos){
            if (produto.getCategoriaId().equals(categoriaId)){
                resultadoBusca.add(produto);
            }
        }
        return  resultadoBusca;
    }

    public List<Produto> buscarProdutoPorNomeEspecifico(String nome){
        List<Produto> resultadoBusca = new ArrayList<>();
        for (Produto produto: produtos){
            if (produto.getNome().toLowerCase().contains(nome.toLowerCase())){
                resultadoBusca.add(produto);
            }
        }
        return  resultadoBusca;
    }

    public Produto buscarProdutoPorId(Long id){
        for (Produto produto: produtos){
            if (produto.getId().equals(id)){
                return produto;
            }
        }
        return null;
    }

    //Metodo criação
    public Produto criarProduto(Produto produto){
        produto.setId((long) (produtos.size() + 1));
        produto.setDataCadastro(LocalDateTime.now());
        produto.setDataUltimaAtualizacao(LocalDateTime.now());
        produtos.add(produto);
        return produto;
    }

    //Metodo de Delete
    public void deletarProduto(Long id) {
        Produto produto = buscarProdutoPorId(id);
        if (produto != null) {
            produtos.remove(produto);
        }
    }
}
