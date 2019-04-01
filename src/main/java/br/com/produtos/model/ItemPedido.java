package br.com.produtos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	private long precoUnitario;
	private int quantidade;
	private long precoTotal;
	@ManyToOne
	private Produto produto;
	@ManyToOne
	private Pedido pedido;

	public long getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(long precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public long getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(long precoTotal) {
		this.precoTotal = precoTotal;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}
