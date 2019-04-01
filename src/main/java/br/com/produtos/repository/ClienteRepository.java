package br.com.produtos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.produtos.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{
	
	public Cliente findByEmail(String email);
	
	@Query("select c from Cliente c where c.id = :id")
	public Cliente findByIdCliente(@Param("id") String id);
	
	@Query("select c from Cliente c")
	public List<Cliente> listClientes();
}
