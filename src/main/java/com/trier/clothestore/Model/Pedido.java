package com.trier.clothestore.Model;

import com.trier.clothestore.Dto.Pedido.PedidoRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    public Pedido(PedidoRequestDto pedidoRequest) {
        this.itens = pedidoRequest.itens();
    }
}
