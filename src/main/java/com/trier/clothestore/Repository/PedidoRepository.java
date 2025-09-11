package com.trier.clothestore.Repository;

import com.trier.clothestore.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    Optional<Pedido> findByIdPedido (Integer idProduto);
}
