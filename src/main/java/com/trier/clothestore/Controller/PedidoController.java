package com.trier.clothestore.Controller;

import com.trier.clothestore.Dto.ItemPedido.ItemPedidoRequestDto;
import com.trier.clothestore.Dto.Pedido.PedidoRequestDto;
import com.trier.clothestore.Dto.Pedido.PedidoResponseDto;
import com.trier.clothestore.Model.ItemPedido;
import com.trier.clothestore.Model.Pedido;
import com.trier.clothestore.Model.Produto;
import com.trier.clothestore.Repository.PedidoRepository;
import com.trier.clothestore.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestBody PedidoRequestDto pedidoRequest){
        Pedido pedido = new Pedido();              // << não passa mais DTO no construtor
        List<ItemPedido> itens = new ArrayList<>();

        if (pedidoRequest.itens() != null) {
            for (ItemPedidoRequestDto dtoItem : pedidoRequest.itens()) {
                Produto produto = produtoRepository.findById(dtoItem.produtoId())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Produto não encontrado: " + dtoItem.produtoId()));

                ItemPedido item = new ItemPedido();
                item.setPedido(pedido);
                item.setProduto(produto);
                item.setQuantidade(dtoItem.quantidade());
                // snapshots (nome/preço no momento da compra)
                item.setNomeItem(produto.getNomeProduto());
                item.setPrecoUnitario(produto.getPrecoProduto());

                itens.add(item);
            }
        }

        pedido.setItens(itens);
        pedidoRepository.save(pedido); // Cascade ALL salva itens

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    public ResponseEntity listarPedidos (){
        List<PedidoResponseDto> listaPedidos = pedidoRepository.findAll().stream().map(PedidoResponseDto::new).toList();

        return ResponseEntity.ok(listaPedidos);
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity getPedido (@PathVariable("idPedido") Integer idPedido){
        Optional<Pedido> pedido = pedidoRepository.findByIdPedido(idPedido);

        if (pedido.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pedido.get());
    }
}
