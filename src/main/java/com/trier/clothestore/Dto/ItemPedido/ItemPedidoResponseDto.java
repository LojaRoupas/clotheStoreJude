package com.trier.clothestore.Dto.ItemPedido;

import com.trier.clothestore.Model.ItemPedido;

public record ItemPedidoResponseDto(
        Integer idItemProduto,
        Integer quantidade,
        Double precoProduto
) {
    public ItemPedidoResponseDto(ItemPedido itemPedido){
        this(itemPedido.getIdItemProduto(),
                itemPedido.getQuantidade(),
                itemPedido.getPrecoProduto());
    }
}
