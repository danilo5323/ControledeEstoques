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
public class Produto implements Serializable{
    private long    id_produto = 0l;
    private String nome_padrao = "";
    private String nome_qualificador = "";
    private String unidade_medida = "";
    private String quantia = "";
    private String marca = "";
    private String cod_barras = "";

    public Produto() {
    }

    public Produto(Produto input) {
        id_produto = input.getId_produto();
        nome_padrao = input.getNome_padrao();
        nome_qualificador = input.getNome_qualificador();
        unidade_medida = input.getUnidade_medida();
        quantia = input.getQuantia();
        marca = input.getMarca();
        cod_barras = input.getCod_barras();

    }

    public Produto(long id_produto, String nome_padrao, String nome_qualificador, String unidade_medida, String quantia, String marca, String cod_barras) {
        this.id_produto = id_produto;
        this.nome_padrao = nome_padrao;
        this.nome_qualificador = nome_qualificador;
        this.unidade_medida = unidade_medida;
        this.quantia = quantia;
        this.marca = marca;
        this.cod_barras = cod_barras;
    }

    public Produto(String nome_padrao, String nome_qualificador, String unidade_medida, String quantia, String marca, String cod_barras) {
        this.nome_padrao = nome_padrao;
        this.nome_qualificador = nome_qualificador;
        this.unidade_medida = unidade_medida;
        this.quantia = quantia;
        this.marca = marca;
        this.cod_barras = cod_barras;
    }



    @Override
    public String toString() {
        super.toString();
        return nome_padrao + " " + nome_qualificador;// + .;
    }

    public long getId_produto() {
        return id_produto;
    }

    public void setId_produto(long id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome_padrao() {
        return nome_padrao;
    }

    public void setNome_padrao(String nome_padrao) {
        this.nome_padrao = nome_padrao;
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

    public String getQuantia() {
        return quantia;
    }

    public void setQuantia(String quantia) {
        this.quantia = quantia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCod_barras() {
        return cod_barras;
    }

    public void setCod_barras(String cod_barras) {
        this.cod_barras = cod_barras;
    }
}
