package com.vasscommerce.vasscommerce.dto;

import java.time.LocalDateTime;

public class ProdutoResponse {
    private Long id;
    private Long categoriaId;
    private String nome;
    private String descricao;
    private String fotoUrl;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataUltimaAtualizacao;
    private float valorUnitario;

    public ProdutoResponse(Long id, Long categoriaId, String nome, String descricao, String fotoUrl, LocalDateTime dataCadastro, LocalDateTime dataUltimaAtualizacao, float valorUnitario) {
        this.id = id;
        this.categoriaId = categoriaId;
        this.nome = nome;
        this.descricao = descricao;
        this.fotoUrl = fotoUrl;
        this.dataCadastro = dataCadastro;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
        this.valorUnitario = valorUnitario;
    }

    public Long getId() {
        return id;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public LocalDateTime getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }
}
