package br.com.produtos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.produtos.model.Produto;
import br.com.produtos.service.ProdutoService;
import br.com.produtos.web.support.Response;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping
	@RequestMapping("/create")
	public ResponseEntity<Response<Produto>> create(HttpServletRequest request, @RequestBody Produto produto, BindingResult result){
		Response<Produto> response = new Response<Produto>();
		List<String> errors = new ArrayList<String>();
		try {
			/*userValidation.validadeSave(user, result);*/
			if(result.hasErrors()) {
				//result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				for(ObjectError error : result.getAllErrors()) {
					errors.add(error.getDefaultMessage());
				}
				response.setErrors(errors);
				return ResponseEntity.badRequest().body(response);
			}
			/*user.setPassword(passwordEncoder.encode(user.getPassword()));
			if(user.getProfiles() == null) {
				user.setProfiles(Profiles.ROLE_CUSTOMER);
			}*/
			Produto produtoPersisted = (Produto) produtoService.createOrUpdate(produto);
			response.setData(produtoPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	/*@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")*/
	public ResponseEntity<Response<Produto>> update(HttpServletRequest request, @RequestBody Produto produto, BindingResult result){
		Response<Produto> response = new Response<Produto>();
		try {
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			/*user.setPassword(passwordEncoder.encode(user.getPassword()));*/
			Produto produtoUpdate = (Produto) produtoService.createOrUpdate(produto);
			response.setData(produtoUpdate);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	/*@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")*/
	public ResponseEntity<Response<Produto>> findById(@PathVariable("id") String id){
		Response<Produto> response = new Response<Produto>();
		Produto produto = this.produtoService.findById(id);
		if(produto == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(produto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/findAll")
	/*@PreAuthorize("hasAnyRole('ADMIN')")*/
	public ResponseEntity<Response<List<Produto>>> findAll(){
		Response<List<Produto>> response =  new Response<List<Produto>>();
		List<Produto> produtos = this.produtoService.findAll();
		response.setData(produtos);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	/*@PreAuthorize("hasAnyRole('ADMIN')")*/
	public ResponseEntity<Response<Produto>> delete(@PathVariable("id") String id){
		Response<Produto> response = new Response<Produto>();
		Produto produto = this.produtoService.findById(id);
		if(produto == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.produtoService.delete(produto.getId());
		return ResponseEntity.ok(response);
	}

}
