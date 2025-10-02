package com.vasscommerce.vasscommerce.controller;

import com.vasscommerce.vasscommerce.dto.CategoriaMapper;
import com.vasscommerce.vasscommerce.dto.CategoriaRequest;
import com.vasscommerce.vasscommerce.dto.CategoriaResponse;
import com.vasscommerce.vasscommerce.dto.ProdutoResponse;
import com.vasscommerce.vasscommerce.model.Categoria;
import com.vasscommerce.vasscommerce.model.Produto;
import com.vasscommerce.vasscommerce.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vasscommerce.vasscommerce.service.CategoriaService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/categorias", produces = "application/json")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final ProdutoService produtoService;

    public CategoriaController(CategoriaService categoriaService, ProdutoService produtoService){
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
    }

    @GetMapping()
    public ResponseEntity<List<CategoriaResponse>> getCategorias(@RequestParam(name = "nome", required = false) String nome){

        List<Categoria> categorias;
        if (nome == null || nome.isBlank()){
            categorias = categoriaService.listarTodas();
        }else {
            categorias = categoriaService.buscarCategoriaEspecifica(nome);
        }

        List<CategoriaResponse> response = new ArrayList<>();
        for (Categoria categoria : categorias){
            response.add(CategoriaMapper.toResponse(categoria));
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> getCategoriasPorId(@PathVariable Long id){
        Categoria categoriaEncontrada = categoriaService.getCategoriaPorId(id);

        if(categoriaEncontrada == null){
            return ResponseEntity.notFound().build();
        }
        CategoriaResponse response = CategoriaMapper.toResponse(categoriaEncontrada);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{categoriaId}/produtos")
    public ResponseEntity<List<ProdutoResponse>> getProdutosPorCategoria(@PathVariable Long categoriaId){
        Categoria categoria = categoriaService.getCategoriaPorId(categoriaId);
        if (categoria == null){
            return ResponseEntity.notFound().build();
        }

        List<Produto> produtos = produtoService.buscarProdutoPorCategoriaEspecifica(categoriaId);
        if (produtos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<ProdutoResponse> response = new ArrayList<>();
        for(Produto produto : produtos){
            response.add(new ProdutoResponse(
                    produto.getId(),
                    produto.getCategoriaId(),
                    produto.getNome(),
                    produto.getDescricao(),
                    produto.getFotoUrl(),
                    produto.getDataCadastro(),
                    produto.getDataUltimaAtualizacao(),
                    produto.getValorUnitario()
            ));
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CategoriaResponse> postCategoria(@Valid @RequestBody CategoriaRequest categoria){
        Categoria novaCategoria = CategoriaMapper.toEntity(categoria, null);
        Categoria categoriaCriada = categoriaService.criarCategoria(novaCategoria);

        if(categoriaCriada == null){
            return ResponseEntity.status(500).build();
        }else{
            URI location = URI.create("/categorias" + categoriaCriada.getId());
            CategoriaResponse response = CategoriaMapper.toResponse(categoriaCriada);
            return ResponseEntity.created(location).body(response);
        }
    }
}