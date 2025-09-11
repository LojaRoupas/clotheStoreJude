package com.trier.clothestore.Dto.Produto;

import com.trier.clothestore.Model.Produto;

public record ProdutoResponseDto(
        Integer id, String nome, Double preco
) {
    public ProdutoResponseDto(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getPreco());
    }
}
