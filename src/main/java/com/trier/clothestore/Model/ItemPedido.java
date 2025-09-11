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
    private Integer idItemPedido;
    private String nomeItem;
    private Integer quantidade;
    private Double precoUnitario;
    @ManyToOne
    @JoinColumn(name = "pedido_id") //nome da coluna da chave estrangeita do bd
    private Pedido pedido;

    public ItemPedido(ItemPedidoRequestDto itemPedidoRequest){
        this.nomeItem = itemPedidoRequest.nomeItem();
        this.quantidade = itemPedidoRequest.quantidade();
        this.precoUnitario = itemPedidoRequest.precoProduto();
    }
}
