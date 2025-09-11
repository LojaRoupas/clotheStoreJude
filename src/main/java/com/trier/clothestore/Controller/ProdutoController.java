package com.trier.clothestore.Controller;

import com.trier.clothestore.Dto.Produto.ProdutoRequestDto;
import com.trier.clothestore.Dto.Produto.ProdutoResponseDto;
import com.trier.clothestore.Model.Produto;
import com.trier.clothestore.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity adicionarProduto (@RequestBody ProdutoRequestDto produtoRequest){
        Produto novoProduto = new Produto(produtoRequest);
        this.produtoRepository.save(novoProduto);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getProdutos (){
        List<ProdutoResponseDto> listaProdutos = this.produtoRepository.findAll().stream().map(ProdutoResponseDto::new).toList();

        return ResponseEntity.ok(listaProdutos);
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity getProdutoById(@PathVariable("idProduto") Integer idProduto){
        Optional<Produto> produto = produtoRepository.findByIdProduto(idProduto);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produto.get());
    }

}
