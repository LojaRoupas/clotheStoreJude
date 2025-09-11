package com.trier.clothestore.Model;

import com.trier.clothestore.Dto.ItemPedido.ItemPedidoRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItemProduto;
    private Integer quantidade;
    private Double precoUnitario;

    public ItemPedido(ItemPedidoRequestDto itemPedidoRequest){
        this.quantidade = itemPedidoRequest.quantidade();
        this.precoUnitario = itemPedidoRequest.precoProduto();
    }
}
