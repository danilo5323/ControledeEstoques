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
public class Fornecedor implements Serializable{

    private long      id_fornecedor = 0;
    private String   forn_nome_empresa = "",
                     forn_nome_fantasia = "",
                     forn_email = "";
    private String     forn_cnpj = "0",
                     forn_ie = "0",
                     forn_tel = "0";
    private String forn_endereco = "";

    public Fornecedor(Fornecedor input){

        this.setId_fornecedor(input.getId_fornecedor());
        this.setForn_cnpj(input.getForn_cnpj());
        this.setForn_email(input.getForn_email());
        this.setForn_endereco(input.getForn_endereco());
        this.setForn_ie(input.getForn_ie());
        this.setForn_nome_empresa(input.getForn_nome_empresa());
        this.setForn_nome_fantasia(input.getForn_nome_fantasia());
        this.setForn_tel(input.getForn_tel());
    }

    public Fornecedor(long id_fornecedor, String forn_nome_empresa, String forn_nome_fantasia,
            String forn_email, String forn_cnpj, String forn_ie, String forn_tel,
                      String forn_endereco ) {
        this.id_fornecedor = id_fornecedor;
        this.forn_nome_empresa = forn_nome_empresa;
        this.forn_nome_fantasia = forn_nome_fantasia;
        this.forn_email = forn_email;
        this.forn_cnpj = forn_cnpj;
        this.forn_ie = forn_ie;
        this.forn_tel = forn_tel;
        this.forn_endereco = forn_endereco;
    }
    
    public Fornecedor(long id_fornecedor,
                      String forn_nome_empresa,
                      String forn_nome_fantasia,
                      String forn_email,
                      String forn_cnpj,
                      String forn_ie,
                      String forn_tel) {
        this.id_fornecedor = id_fornecedor;
        this.forn_nome_empresa = forn_nome_empresa;
        this.forn_nome_fantasia = forn_nome_fantasia;
        this.forn_email = forn_email;
        this.forn_cnpj = forn_cnpj;
        this.forn_ie = forn_ie;
        this.forn_tel = forn_tel;
        this.forn_endereco = null;
    }
    
    public Fornecedor(String forn_nome_empresa,
                      String forn_nome_fantasia,
                      String forn_email,
                      String forn_cnpj,
                      String forn_ie,
                      String forn_tel) {
        this.id_fornecedor = id_fornecedor;
        this.forn_nome_empresa = forn_nome_empresa;
        this.forn_nome_fantasia = forn_nome_fantasia;
        this.forn_email = forn_email;
        this.forn_cnpj = (forn_cnpj);
        this.forn_ie = (forn_ie);
        this.forn_tel = (forn_tel);
        this.forn_endereco = null;
    }

    public Fornecedor(String forn_nome_empresa,
                      String forn_nome_fantasia,
                      String forn_email,
                      String forn_cnpj,
                      String forn_ie,
                      String forn_tel,
                      String forn_endereco) {

        this.forn_nome_empresa = forn_nome_empresa;
        this.forn_nome_fantasia = forn_nome_fantasia;
        this.forn_email = forn_email;
        this.forn_cnpj = (forn_cnpj);
        this.forn_ie = (forn_ie);
        this.forn_tel = (forn_tel);
        this.forn_endereco = forn_endereco;
    }
    


    public Fornecedor() {
    }

    public long getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(long id_fornecedor) {
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

    public String getForn_cnpj() {
        return forn_cnpj;
    }

    public void setForn_cnpj(String forn_cnpj) {
        this.forn_cnpj = forn_cnpj;
    }

    public String getForn_ie() {
        return forn_ie;
    }

    public void setForn_ie(String forn_ie) {
        this.forn_ie = forn_ie;
    }

    public String getForn_tel() {
        return forn_tel;
    }

    public void setForn_tel(String forn_tel) {
        this.forn_tel = forn_tel;
    }

    public String getForn_endereco() {
        return forn_endereco;
    }

    public void setForn_endereco(String forn_endereco) {
        this.forn_endereco = forn_endereco;
    }
    
    
}
