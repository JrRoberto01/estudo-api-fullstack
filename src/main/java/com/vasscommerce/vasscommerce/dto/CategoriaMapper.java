package com.vasscommerce.vasscommerce.dto;

import com.vasscommerce.vasscommerce.model.Categoria;

public class CategoriaMapper {
    public static Categoria toEntity(CategoriaRequest dto, Long id){
        return new Categoria(
                id,
                dto.getImagemSimboloUrl(),
                dto.getNome(),
                dto.getDescricao()
        );
    }

    public static CategoriaResponse toResponse(Categoria categoria){
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getimagemSimboloUrl()
        );
    }
}
