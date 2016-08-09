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
public class Produto {
    private Long    id_produto = 0l;
    private String nome = "",
           nome_qualificador ="",
           unidade_medida = "",
           cod_barras = "", 
            marca = "";

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
 

    public Produto() {
    }

    public Produto(Long id_produto, String nome, String nome_qualificador, String unidade_medida, String cod_barras, String marca) {
        this.id_produto = id_produto;
        this.nome = nome;
        this.nome_qualificador = nome_qualificador;
        this.unidade_medida = unidade_medida;
        this.cod_barras = cod_barras;
        this.marca = marca;
    }
    
    public Produto(String nome, String nome_qualificador, String unidade_medida, String cod_barras, String marca) {
        this.nome = nome;
        this.nome_qualificador = nome_qualificador;
        this.unidade_medida = unidade_medida;
        this.cod_barras = cod_barras;
        this.marca = marca;
    }


    public Long getId_produto() {
        return id_produto;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome_qualificador() {
        return nome_qualificador;
    }

    public void setNome_qualificador(String nome_qualificador) {
        this.nome_qualificador = nome_qualificador;
    }

    public String getUnidade_medida() {
        return unidade_medida;
    }

    public void setUnidade_medida(String unidade_medida) {
        this.unidade_medida = unidade_medida;
    }

    public String getCod_barras() {
        return cod_barras;
    }

    public void setCod_barras(String cod_barras) {
        this.cod_barras = cod_barras;
    }

 
    
}
