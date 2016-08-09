/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc.Beans;

import java.sql.Date;

/**
 *
 * @author Danilo
 */
public class ProdutoEstoque {
    private Produto produto;
    private Date data_compra;
    
    private Date data_venda;
   
    private Date data_validade;
    private Double preco_venda;
    
    
    private Double preco_compra;
    
    private Double medida;

    public ProdutoEstoque(Produto produto, Date data_compra, Date data_venda, Date data_validade, Double preco_venda, Double preco_compra, Double medida) {
        this.produto = produto;
        this.data_compra = data_compra;
        this.data_venda = data_venda;
        this.data_validade = data_validade;
        this.preco_venda = preco_venda;
        this.preco_compra = preco_compra;
        this.medida = medida;
    }

    public ProdutoEstoque() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Date getData_compra() {
        return data_compra;
    }

    public void setData_compra(Date data_compra) {
        this.data_compra = data_compra;
    }

    public Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }

    public Date getData_validade() {
        return data_validade;
    }
    
    public void setData_validade(Date data_validade) {
        this.data_validade = data_validade;
    }

    public Double getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(Double preco_venda) {
        this.preco_venda = preco_venda;
    }

    public Double getPreco_compra() {
        return preco_compra;
    }

    public void setPreco_compra(Double preco_compra) {
        this.preco_compra = preco_compra;
    }

    public Double getMedida() {
        return medida;
    }

    public void setMedida(Double medida) {
        this.medida = medida;
    }
}
