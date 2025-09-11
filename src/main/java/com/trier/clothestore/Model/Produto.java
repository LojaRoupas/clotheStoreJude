package com.trier.clothestore.Model;

import com.trier.clothestore.Dto.Produto.ProdutoRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduto;
    private String nomeProduto;
    private Double precoProduto;

    public Produto(ProdutoRequestDto produtoRequest) {
        this.nomeProduto = produtoRequest.nomeProduto();
        this.precoProduto = produtoRequest.precoProduto();
    }
}
