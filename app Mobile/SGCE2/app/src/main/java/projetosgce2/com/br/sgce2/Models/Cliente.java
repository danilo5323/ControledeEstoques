/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosgce2.com.br.sgce2.Models;

import java.io.Serializable;

/**
 *
 * @author Danilo
 */
public class Cliente implements Serializable {
    public long id_cliente = 0l;
    public String telefone = "0";
    public String cpf = "0";
    public String inscricao_estadual = "0";
    public String email = "";
    public String cnpj = "0";
    public String nomePrinc = "";
    public String nomeSec= "";
    public String endereco = "";

    public Status status;

    public long idServidor;


    public Cliente(long id, String nomePrinc, String nomeSec, String telefone, String email, String cpf,
                   String cnpj, String inscricao_estadual, String endereco, Status status, long idServidor) {
        this.id_cliente = id;
        this.nomePrinc = nomePrinc;
        this.nomeSec= nomeSec;
        this.telefone = telefone;
        this.email= email;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.inscricao_estadual= inscricao_estadual;
        this.endereco = endereco;
        this.status = status;
        this.idServidor = idServidor;


    }

    public enum Status{
        OK, INSERIR, ATUALIZAR, EXCLUIR;
    }

    public Cliente(Cliente cliente){
        this.id_cliente = cliente.getId_cliente();
        this.telefone = cliente.getTelefone();
        this.cpf = cliente.getCpf();
        this.inscricao_estadual = cliente.getInscricao_estadual();
        this.cnpj = cliente.getCnpj();
        this.nomePrinc = cliente.getNomePrinc();
        this.nomeSec = cliente.getNomeSec();
        this.endereco = cliente.getEndereco();

    }

    public Cliente(String nomePrinc, String nomeSec, String telefone, String email, String cpf, String cnpj, String inscricao_estadual,   String endereco) {
        this.telefone = telefone;
        this.cpf = cpf;
        this.inscricao_estadual = inscricao_estadual;
        this.email = email;
        this.cnpj = cnpj;
        this.nomePrinc = nomePrinc;
        this.nomeSec = nomeSec;
        this.endereco = endereco;
    }

    public Cliente(String nomePrinc, String nomeSec, String telefone, String cpf, String cnpj, String inscricao_estadual,   String endereco) {
        this.telefone = telefone;
        this.cpf = cpf;
        this.inscricao_estadual = inscricao_estadual;
        this.cnpj = cnpj;
        this.nomePrinc = nomePrinc;
        this.nomeSec = nomeSec;
        this.endereco = endereco;
    }


    public Cliente(long id_cliente, String nomePrinc, String nomeSec, String telefone, String email, String cpf, String cnpj, String inscricao_estadual,   String endereco) {
        this.id_cliente = id_cliente;
        this.telefone = telefone;
        this.cpf = cpf;
        this.inscricao_estadual = inscricao_estadual;
        this.cnpj = cnpj;
        this.nomePrinc = nomePrinc;
        this.nomeSec = nomeSec;
        this.endereco = endereco;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getInscricao_estadual() {
        return inscricao_estadual;
    }

    public void setInscricao_estadual(String inscricao_estadual) {
        this.inscricao_estadual = inscricao_estadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomePrinc() {
        return nomePrinc;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


    public Cliente() {
    }

}
