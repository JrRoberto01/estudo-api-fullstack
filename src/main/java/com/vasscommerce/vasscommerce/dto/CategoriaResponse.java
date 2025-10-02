package com.vasscommerce.vasscommerce.dto;

public class CategoriaResponse {
    private Long id;
    private String nome;
    private String descricao;
    private String imagemSimboloUrl;

    public CategoriaResponse(long id, String nome, String descricao, String imagemSimboloUrl) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.imagemSimboloUrl = imagemSimboloUrl;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getImagemSimboloUrl() {
        return imagemSimboloUrl;
    }
}
