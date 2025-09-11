package com.trier.clothestore.Dto.Produto;

import com.trier.clothestore.Model.Produto;

public record ProdutoResponseDto(
        Integer idProduto, String nomeProduto, Double precoProduto
) {
    public ProdutoResponseDto(Produto produto){
        this(produto.getIdProduto(),
                produto.getNomeProduto(), produto.getPrecoProduto());
    }
}
