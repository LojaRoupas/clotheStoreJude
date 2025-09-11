package com.trier.clothestore.Controller;

import com.trier.clothestore.Dto.ItemPedido.ItemPedidoRequestDto;
import com.trier.clothestore.Dto.ItemPedido.ItemPedidoResponseDto;
import com.trier.clothestore.Dto.Produto.ProdutoResponseDto;
import com.trier.clothestore.Model.ItemPedido;
import com.trier.clothestore.Repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itenspedido")
public class ItemPedidoController {
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @GetMapping
    public ResponseEntity listarItens (){
        List<ItemPedidoResponseDto> listaDeItens = this.itemPedidoRepository.findAll().stream().
                map(ItemPedidoResponseDto::new).toList();
        return ResponseEntity.ok(listaDeItens);
    }

    @GetMapping("/{idItemPedido}")
    public ResponseEntity getItemPedido(@PathVariable("idItemPedido") Integer idItemPedido){
        Optional<ItemPedido> itemPedido = itemPedidoRepository.findByIdItemPedido(idItemPedido);

        if (itemPedido.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(itemPedido.get());
    }
}
