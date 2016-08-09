/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc.Beans;

import java.io.Serializable;

/**
 *
 * @author danilo2
 */

public class Administrador implements Serializable{

    private String usuario;
    private String email;
    private String senha;
    private Long id;
    public Status status;
    private long idServidor;

    public enum Status{
        OK, INSERIR, ATUALIZAR, EXCLUIR
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(long idServidor) {
        this.idServidor = idServidor;
    }
    
    public Administrador(){}

    public Administrador(Administrador input) {
        this.usuario = input.getUsuario();
        this.email = input.getEmail();
        this.senha = input.getSenha();
        this.id = input.getId();
        this.idServidor = input.getIdServidor();
        this.status = input.getStatus();
    }

    public Administrador(Long id, String usuario, String email, String senha, long idServidor, Status status) {
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
        this.id = id;
        this.idServidor = idServidor;
        this.status =status;
    }

    public Administrador(Long id, String usuario, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    @Override
    public String toString(){
        return usuario;
    }
}
