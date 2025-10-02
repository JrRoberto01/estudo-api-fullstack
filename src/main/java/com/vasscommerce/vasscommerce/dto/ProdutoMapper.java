package com.vasscommerce.vasscommerce.dto;

import com.vasscommerce.vasscommerce.model.Produto;

public class ProdutoMapper {
    public static Produto toEntity(ProdutoRequest dto, Long id){
        return new Produto(
                id,
                dto.getCategoriaId(),
                dto.getNome(),
                dto.getDescricao(),
                dto.getFotoUrl(),
                dto.getDataCadastro(),
                dto.getDataUltimaAtualizacao(),
                dto.getValorUnitario()
        );
    }

    public static ProdutoResponse toResponse(Produto produto){
        return new ProdutoResponse(
                produto.getId(),
                produto.getCategoriaId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getFotoUrl(),
                produto.getDataCadastro(),
                produto.getDataCadastro(),
                produto.getValorUnitario()
        );
    }
}
