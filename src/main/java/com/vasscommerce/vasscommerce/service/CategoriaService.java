package com.vasscommerce.vasscommerce.service;

import com.vasscommerce.vasscommerce.model.Categoria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {
    private List<Categoria> categorias = new ArrayList<>();

    public CategoriaService(){
        categorias.add(new Categoria(1L, "https://vasscommerce.com.br/image/categoria/livros.webp", "Livros", "Categoria de Livros"));
    }

    //Metodos de busca
    public List<Categoria> listarTodas(){
        return categorias;
    }

    public List<Categoria> buscarCategoriaEspecifica(String nome){
        List<Categoria> resultadoBusca = new ArrayList<>();
        for(Categoria categoria: categorias){
            if (categoria.getNome().toLowerCase().contains(nome.toLowerCase())){
                resultadoBusca.add(categoria);
            }
        }
        return resultadoBusca;
    }

    public Categoria getCategoriaPorId(Long id){
        for(Categoria categoria : categorias){
            if(categoria.getId().equals(id)){
                return categoria;
            }
        }
        return null;
    }

    //Metodo de criação
    public Categoria criarCategoria(Categoria categoria){
        categoria.setId((long) (categorias.size() + 1));  //Passando ID automaticamente e incrementando
        categorias.add(categoria);
        return categoria;
    }
}
