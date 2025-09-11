package com.trier.clothestore.Dto.Pedido;

import com.trier.clothestore.Model.ItemPedido;

import java.util.List;

public record PedidoRequestDto(
        List<ItemPedido> itens
) {
}
