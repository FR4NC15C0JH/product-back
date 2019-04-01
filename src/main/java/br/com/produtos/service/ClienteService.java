package br.com.produtos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.produtos.model.Cliente;
import br.com.produtos.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente findByEmail(String email) {
		return this.clienteRepository.findByEmail(email);
	}
	
	public Cliente createOrUpdate(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
	
	public Cliente findById(String id) {
		return this.clienteRepository.findByIdCliente(id);
	}
	
	public Page<Cliente> findAll(int page, int count){
		Pageable pages = new PageRequest(page, count, null);
		return this.clienteRepository.findAll(pages);
	}
	
	public List<Cliente> findAll() {
		return this.clienteRepository.listClientes();
	}
	
	public void delete(String id) {
		this.clienteRepository.deleteById(id);
	}
}
