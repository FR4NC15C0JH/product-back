package br.com.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.produtos.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String>{
	
	@Query("select p from Produto p where p.id = :id")
	public Produto findByIdProduto(@Param("id") String id);
}
