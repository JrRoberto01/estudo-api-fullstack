package com.vasscommerce.vasscommerce.model;

public class Categoria {
    private Long id;
    private String imagemSimboloUrl;
    private String nome;
    private String descricao;

    public Categoria(Long id, String imagemSimboloUrl, String nome, String descricao) {
        this.id = id;
        this.imagemSimboloUrl = imagemSimboloUrl;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getimagemSimboloUrl() {
        return imagemSimboloUrl;
    }

    public void setImagemSimboloUrl(String imagemSimboloUrl) {
        this.imagemSimboloUrl = imagemSimboloUrl;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
