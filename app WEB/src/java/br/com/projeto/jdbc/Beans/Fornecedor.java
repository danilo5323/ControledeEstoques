/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc.Beans;

/**
 *
 * @author Danilo
 */
public class Fornecedor {

    private Long      id_fornecedor = 0l;
    private String   forn_nome_empresa = "",
                     forn_nome_fantasia = "",
                     forn_email = "";
    private Long     forn_cnpj = 0l,
                     forn_ie = 0l,
                     forn_tel = 0l;
    private Endereco forn_endereco;
    
    public Fornecedor(Long id_fornecedor, String forn_nome_empresa, String forn_nome_fantasia, 
            String forn_email, Long forn_cnpj, Long forn_ie, Long forn_tel, 
            Endereco forn_endereco ) {
        this.id_fornecedor = id_fornecedor;
        this.forn_nome_empresa = forn_nome_empresa;
        this.forn_nome_fantasia = forn_nome_fantasia;
        this.forn_email = forn_email;
        this.forn_cnpj = forn_cnpj;
        this.forn_ie = forn_ie;
        this.forn_tel = forn_tel;
        this.forn_endereco = forn_endereco;
    }
    
    public Fornecedor(Long id_fornecedor, String forn_nome_empresa, String forn_nome_fantasia, 
            String forn_email, Long forn_cnpj, Long forn_ie, Long forn_tel) {
        this.id_fornecedor = id_fornecedor;
        this.forn_nome_empresa = forn_nome_empresa;
        this.forn_nome_fantasia = forn_nome_fantasia;
        this.forn_email = forn_email;
        this.forn_cnpj = forn_cnpj;
        this.forn_ie = forn_ie;
        this.forn_tel = forn_tel;
        this.forn_endereco = null;
    }
    
    public Fornecedor(String forn_nome_empresa, String forn_nome_fantasia, 
            String forn_email, String forn_cnpj, String forn_ie, String forn_tel) {
        this.id_fornecedor = id_fornecedor;
        this.forn_nome_empresa = forn_nome_empresa;
        this.forn_nome_fantasia = forn_nome_fantasia;
        this.forn_email = forn_email;
        this.forn_cnpj = Long.parseLong(forn_cnpj);
        this.forn_ie = Long.parseLong(forn_ie);
        this.forn_tel = Long.parseLong(forn_tel);
        this.forn_endereco = null;
    }
    
    
    public Fornecedor(String forn_nome_empresa, String forn_nome_fantasia, 
            String forn_email, Long forn_cnpj, Long forn_ie, Long forn_tel) {
        this.forn_nome_empresa = forn_nome_empresa;
        this.forn_nome_fantasia = forn_nome_fantasia;
        this.forn_email = forn_email;
        this.forn_cnpj = (forn_cnpj);
        this.forn_ie = (forn_ie);
        this.forn_tel = (forn_tel);
        this.forn_endereco = null;
    }

    public Fornecedor() {
    }

    public Long getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(Long id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public String getForn_nome_empresa() {
        return forn_nome_empresa;
    }

    public void setForn_nome_empresa(String forn_nome_empresa) {
        this.forn_nome_empresa = forn_nome_empresa;
    }

    public String getForn_nome_fantasia() {
        return forn_nome_fantasia;
    }

    public void setForn_nome_fantasia(String forn_nome_fantasia) {
        this.forn_nome_fantasia = forn_nome_fantasia;
    }

    public String getForn_email() {
        return forn_email;
    }

    public void setForn_email(String forn_email) {
        this.forn_email = forn_email;
    }

    public Long getForn_cnpj() {
        return forn_cnpj;
    }

    public void setForn_cnpj(Long forn_cnpj) {
        this.forn_cnpj = forn_cnpj;
    }

    public Long getForn_ie() {
        return forn_ie;
    }

    public void setForn_ie(Long forn_ie) {
        this.forn_ie = forn_ie;
    }

    public Long getForn_tel() {
        return forn_tel;
    }

    public void setForn_tel(Long forn_tel) {
        this.forn_tel = forn_tel;
    }

    public Endereco getForn_endereco() {
        return forn_endereco;
    }

    public void setForn_endereco(Endereco forn_endereco) {
        this.forn_endereco = forn_endereco;
    }
    
    
}
