package com.trier.clothestore.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    // snapshots (opcional, mas útil para manter o histórico do pedido)
    private String nomeItem;
    private Integer quantidade;
    private Double precoUnitario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false) // FK para pedido
    @JsonBackReference // evita loop na serialização (lado "filho")
    private Pedido pedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false) //FK para produto
    private Produto produto;

    public ItemPedido(Produto produto, Integer quantidade, Pedido pedido) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.pedido = pedido;

        this.nomeItem = produto.getNomeProduto();
        this.precoUnitario = produto.getPrecoProduto();
    }

    public ItemPedido(ItemPedidoRequestDto itemPedidoRequest, Produto produto, Pedido pedido) {
        this(produto, itemPedidoRequest.quantidade(), pedido);
    }
}
