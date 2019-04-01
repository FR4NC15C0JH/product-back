package br.com.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.produtos.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String>{

}
