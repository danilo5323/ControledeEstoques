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
public class Cliente {
    Long id_cliente = 0l;
    Long telefone = 0l;
    long cpf = 0;
    long inscricao_estadual = 0;
    long cnpj = 0;
    String nomePrinc = "", nomeSec= "";

    public String getNomePrinc() {
        return nomePrinc;
    }

    public Cliente(Long id_cliente, Long telefone, long cpf, long inscricao_estadual, long cnpj, String nomePrinc, String nomeSec, Endereco endereco) {
        this.id_cliente = id_cliente;
        this.telefone = telefone;
        this.cpf = cpf;
        this.inscricao_estadual = inscricao_estadual;
        this.cnpj = cnpj;
        this.nomePrinc = nomePrinc;
        this.nomeSec = nomeSec;
        this.endereco = endereco;
    }
    
    public Cliente(Long id_cliente, Long telefone, long cpf, long inscricao_estadual, long cnpj, String nomePrinc, String nomeSec) {
        this.id_cliente = id_cliente;
        this.telefone = telefone;
        this.cpf = cpf;
        this.inscricao_estadual = inscricao_estadual;
        this.cnpj = cnpj;
        this.nomePrinc = nomePrinc;
        this.nomeSec = nomeSec;

    }

    public void setNomePrinc(String nomePrinc) {
        this.nomePrinc = nomePrinc;
    }

    public String getNomeSec() {
        return nomeSec;
    }

    public void setNomeSec(String nomeSec) {
        this.nomeSec = nomeSec;
    }
    
    Endereco endereco = new Endereco();
    
    public Cliente() {
    }

    public Cliente(Long id_cliente, Long telefone, long cpf, long inscricao_estadual, long cnpj, Endereco endereco) {
        this.id_cliente = id_cliente;
        this.telefone = telefone;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.inscricao_estadual = inscricao_estadual;
        this.endereco = endereco;
    }

    
    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }
    
    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getInscricao_estadual() {
        return inscricao_estadual;
    }

    public void setInscricao_estadual(long inscricao_estadual) {
        this.inscricao_estadual = inscricao_estadual;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
}
