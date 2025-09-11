package com.trier.clothestore.Dto.Pedido;

import com.trier.clothestore.Model.ItemPedido;
import com.trier.clothestore.Model.Pedido;

import java.util.List;

public record PedidoResponseDto(
        Integer idPedido, List<ItemPedido> itens
) {
    public PedidoResponseDto(Pedido pedido){
        this(pedido.getIdPedido(),
                pedido.getItens());
    }
}
