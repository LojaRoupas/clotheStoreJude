package com.trier.clothestore.Controller;

import com.trier.clothestore.Dto.Pedido.PedidoRequestDto;
import com.trier.clothestore.Dto.Pedido.PedidoResponseDto;
import com.trier.clothestore.Model.ItemPedido;
import com.trier.clothestore.Model.Pedido;
import com.trier.clothestore.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public ResponseEntity criarPedido(@RequestBody PedidoRequestDto pedidoRequest){
        Pedido novoPedido = new Pedido(pedidoRequest);

        if (novoPedido.getItens() != null) {
            for (ItemPedido item : novoPedido.getItens()) {
                item.setPedido(novoPedido);
            }
        }
        this.pedidoRepository.save(novoPedido);

        return ResponseEntity.ok().build();
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
