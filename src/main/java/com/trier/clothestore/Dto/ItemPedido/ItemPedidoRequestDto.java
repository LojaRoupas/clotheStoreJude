package com.trier.clothestore.Dto.ItemPedido;

public record ItemPedidoRequestDto(
        String nomeItem,
        Integer quantidade,
        Double precoProduto
) {
}
