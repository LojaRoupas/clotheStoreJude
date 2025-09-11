package com.trier.clothestore.Repository;

import com.trier.clothestore.Model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
    Optional<ItemPedido> findByIdItemPedido(Integer idItemPedido);

}
