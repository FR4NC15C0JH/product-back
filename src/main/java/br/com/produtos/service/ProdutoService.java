package br.com.produtos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.produtos.model.Produto;
import br.com.produtos.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto createOrUpdate(Produto produto) {
		return this.produtoRepository.save(produto);
	}
	
	public Produto findById(String id){
		return this.produtoRepository.findByIdProduto(id);
	}
	
	public List<Produto> findAll(){
		return this.produtoRepository.findAll();
	}
	
	public void delete(String id) {
		this.produtoRepository.deleteById(id);
	}
}
