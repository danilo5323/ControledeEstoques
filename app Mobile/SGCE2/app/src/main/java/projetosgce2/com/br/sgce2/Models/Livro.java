package projetosgce2.com.br.sgce2.Models;

import java.io.Serializable;

/**
 * Created by Danilo on 21/06/2016.
 */
public class Livro implements Serializable {
    public String titulo, categoria, autor, capa;
    public int ano, paginas;

    //public Livro(){}
    //Livro(jsonLivro.getString("titulo"), categoriaAtual, jsonLivro.getString("autor"), jsonLivro.getInt("ano"), jsonLivro.getInt("paginas"), jsonLivro.getString("capa"));

    public Livro(String titulo, String categoria, String autor, int ano, int paginas, String capa) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.autor = autor;
        this.capa = capa;
        this.ano = ano;
        this.paginas = paginas;
    }

    public Livro() {

    }

    @Override
    public String toString(){
        return this.titulo;

    }

}
