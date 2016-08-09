/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc.Beans;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class NotaFiscalCliente {
    private Cliente cliente;
    private Long id_nf_cliente;
    private List<Produto> produtos;
    private Date data_emissao;
    
    public NotaFiscalCliente() {
    }

    public NotaFiscalCliente(Cliente cliente, Long id_nf_cliente, List<Produto> produtos, Date data_emissao) {
        this.cliente = cliente;
        this.id_nf_cliente = id_nf_cliente;
        this.produtos = produtos;
        this.data_emissao = data_emissao;
    }

    public Date getData_emissao() {
        return data_emissao;
    }

    public void setData_emissao(Date data_emissao) {
        this.data_emissao = data_emissao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId_nf_cliente() {
        return id_nf_cliente;
    }

    public void setId_nf_cliente(Long id_nf_cliente) {
        this.id_nf_cliente = id_nf_cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    
}
