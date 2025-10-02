package com.vasscommerce.vasscommerce.controller;

import com.vasscommerce.vasscommerce.dto.*;
import com.vasscommerce.vasscommerce.model.Produto;
import com.vasscommerce.vasscommerce.service.ProdutoService;
import com.vasscommerce.vasscommerce.dto.ProdutoResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/produtos", produces = "application/json")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @GetMapping()
    public ResponseEntity<List<ProdutoResponse>> getProdutos(@RequestParam(name = "nome", required = false) String nome){
        List<Produto> produtos;
        if (nome == null || nome.isBlank()){
            produtos = produtoService.listarTodos();
        }else{
            produtos = produtoService.buscarProdutoPorNomeEspecifico(nome);
        }

        List<ProdutoResponse> response = new ArrayList<>();
        for (Produto produto : produtos){
            response.add(ProdutoMapper.toResponse(produto));
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> getProdutoPorId(@PathVariable Long id){
        Produto produtoEncontrado = produtoService.buscarProdutoPorId(id);

        if(produtoEncontrado == null){
            return ResponseEntity.notFound().build();
        }
        ProdutoResponse response = ProdutoMapper.toResponse(produtoEncontrado);
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ProdutoResponse> postProduto(@Valid @RequestBody ProdutoRequest produto){
        Produto novoProduto = ProdutoMapper.toEntity(produto, null);
        Produto produtoCriado = produtoService.criarProduto(novoProduto);

        if(produtoCriado == null){
            return ResponseEntity.status(500).build();
        }else{
            URI location = URI.create("/produtos/" + produtoCriado.getId());
            ProdutoResponse response= ProdutoMapper.toResponse(produtoCriado);
            return ResponseEntity.created(location).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoRequest dto) {

        Produto produto = produtoService.buscarProdutoPorId(id);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        // atualizar campos
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setFotoUrl(dto.getFotoUrl());
        produto.setCategoriaId(dto.getCategoriaId());
        produto.setValorUnitario(dto.getValorUnitario());
        produto.setDataUltimaAtualizacao(LocalDateTime.now());

        return ResponseEntity.ok(ProdutoMapper.toResponse(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        Produto produto = produtoService.buscarProdutoPorId(id);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
