package com.trier.clothestore.Dto.Pedido;

import com.trier.clothestore.Dto.ItemPedido.ItemPedidoRequestDto;
import com.trier.clothestore.Model.ItemPedido;

import java.util.List;

public record PedidoRequestDto(List<ItemPedidoRequestDto> itens) {}