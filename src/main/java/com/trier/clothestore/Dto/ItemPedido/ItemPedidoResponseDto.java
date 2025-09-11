package com.trier.clothestore.Dto.ItemPedido;

import com.trier.clothestore.Model.ItemPedido;

public record ItemPedidoResponseDto(
        Integer idItemPedido,
        Integer quantidade,
        Double precoUnitario
) {
    public ItemPedidoResponseDto(ItemPedido itemPedido){
        this(itemPedido.getIdItemPedido(),
                itemPedido.getQuantidade(),
                itemPedido.getPrecoUnitario());
    }
}
