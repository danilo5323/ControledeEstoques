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
public class NotaFiscalFornecedor {
    private Fornecedor fornecedor;
    private Long id_nf_fornecedor;
    private List<Produto> produtos;
    private Date data_emissao;

    public NotaFiscalFornecedor() {
    }

    public NotaFiscalFornecedor(Fornecedor fornecedor, Long id_nf_fornecedor, List<Produto> produtos, Date data_emissao) {
        this.fornecedor = fornecedor;
        this.id_nf_fornecedor = id_nf_fornecedor;
        this.produtos = produtos;
        this.data_emissao = data_emissao;
    }

    
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Long getId_nf_fornecedor() {
        return id_nf_fornecedor;
    }

    public void setId_nf_fornecedor(Long id_nf_fornecedor) {
        this.id_nf_fornecedor = id_nf_fornecedor;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Date getData_emissao() {
        return data_emissao;
    }

    public void setData_emissao(Date data_emissao) {
        this.data_emissao = data_emissao;
    }
    
    
}
