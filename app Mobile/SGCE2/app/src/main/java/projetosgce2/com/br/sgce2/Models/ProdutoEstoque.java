/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosgce2.com.br.sgce2.Models;



/**
 *
 * @author Danilo
 */
public class ProdutoEstoque {

    private String prd_cod_barras;

    private String data_compra;
    private String data_venda;
    private String data_validade;
    private String preco_venda;
    private String preco_compra;
    private String quantidade;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrd_cod_barras() {
        return prd_cod_barras;
    }

    public void setPrd_cod_barras(String prd_cod_barras) {
        this.prd_cod_barras = prd_cod_barras;
    }

    public String getData_compra() {
        return data_compra;
    }

    public void setData_compra(String data_compra) {
        this.data_compra = data_compra;
    }

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    public String getData_validade() {
        return data_validade;
    }

    public void setData_validade(String data_validade) {
        this.data_validade = data_validade;
    }

    public String getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(String preco_venda) {
        this.preco_venda = preco_venda;
    }

    public String getPreco_compra() {
        return preco_compra;
    }

    public void setPreco_compra(String preco_compra) {
        this.preco_compra = preco_compra;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public ProdutoEstoque() {
    }

    public ProdutoEstoque(String prd_cod_barras, String data_compra, String data_venda, String data_validade, String preco_venda, String preco_compra, String quantidade) {
        this.prd_cod_barras = prd_cod_barras;
        this.data_compra = data_compra;
        this.data_venda = data_venda;
        this.data_validade = data_validade;
        this.preco_venda = preco_venda;
        this.preco_compra = preco_compra;
        this.quantidade = quantidade;
    }

    public ProdutoEstoque(Long id, String prd_cod_barras, String data_compra, String data_venda, String data_validade, String preco_venda, String preco_compra, String quantidade) {
        this.prd_cod_barras = prd_cod_barras;
        this.data_compra = data_compra;
        this.data_venda = data_venda;
        this.data_validade = data_validade;
        this.preco_venda = preco_venda;
        this.preco_compra = preco_compra;
        this.quantidade = quantidade;
        this.id = id;
    }
}
