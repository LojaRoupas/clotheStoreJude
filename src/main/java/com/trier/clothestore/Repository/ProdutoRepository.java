package com.trier.clothestore.Repository;

import com.trier.clothestore.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    Optional<Produto> findByIdProduto(Integer idProduto);

}
