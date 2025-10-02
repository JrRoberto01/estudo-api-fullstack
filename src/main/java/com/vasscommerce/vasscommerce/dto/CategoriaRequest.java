package com.vasscommerce.vasscommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaRequest {
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve conter no máximo 100 caracteres")
    private String nome;

    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    private String descricao;

    @NotBlank(message = "A URL da imagem é obrigatória")
    private String imagemSimboloUrl;

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

    public String getImagemSimboloUrl() {
        return imagemSimboloUrl;
    }

    public void setImagemSimboloUrl(String imagemSimboloUrl) {
        this.imagemSimboloUrl = imagemSimboloUrl;
    }
}
