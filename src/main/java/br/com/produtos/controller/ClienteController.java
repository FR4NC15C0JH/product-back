package br.com.produtos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.produtos.model.Cliente;
import br.com.produtos.service.ClienteService;
import br.com.produtos.web.support.Response;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	@RequestMapping("/create")
	public ResponseEntity<Response<Cliente>> create(HttpServletRequest request, @RequestBody Cliente cliente, BindingResult result){
		Response<Cliente> response = new Response<Cliente>();
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
			Cliente clientePersisted = (Cliente) clienteService.createOrUpdate(cliente);
			response.setData(clientePersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	/*@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")*/
	public ResponseEntity<Response<Cliente>> update(HttpServletRequest request, @RequestBody Cliente cliente, BindingResult result){
		Response<Cliente> response = new Response<Cliente>();
		try {
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			/*user.setPassword(passwordEncoder.encode(user.getPassword()));*/
			Cliente clienteUpdate = (Cliente) clienteService.createOrUpdate(cliente);
			response.setData(clienteUpdate);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	/*@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")*/
	public ResponseEntity<Response<Cliente>> findById(@PathVariable("id") String id){
		Response<Cliente> response = new Response<Cliente>();
		Cliente cliente = this.clienteService.findById(id);
		if(cliente == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(cliente);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/findAll")
	/*@PreAuthorize("hasAnyRole('ADMIN')")*/
	public ResponseEntity<List<Cliente>> findAll(){
		Response<Cliente> response =  new Response<Cliente>();
		List<Cliente> clientes = this.clienteService.findAll();
		response.setDatas(clientes);
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "{id}")
	/*@PreAuthorize("hasAnyRole('ADMIN')")*/
	public ResponseEntity<Response<Cliente>> delete(@PathVariable("id") String id){
		Response<Cliente> response = new Response<Cliente>();
		Cliente cliente = this.clienteService.findById(id);
		if(cliente == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.clienteService.delete(cliente.getId());
		return ResponseEntity.ok(response);
	}
}
