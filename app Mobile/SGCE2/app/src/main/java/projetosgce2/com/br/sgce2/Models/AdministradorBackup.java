package projetosgce2.com.br.sgce2.Models;

import java.io.Serializable;

/**
 * Created by Danilo on 21/06/2016.
 */
public class AdministradorBackup implements Serializable{

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

    public AdministradorBackup(AdministradorBackup input) {
        this.usuario = input.getUsuario();
        this.email = input.getEmail();
        this.senha = input.getSenha();
        this.id = input.getId();
        this.idServidor = input.getIdServidor();
        this.status = input.getStatus();
    }

    public AdministradorBackup(Long id, String usuario, String email, String senha, long idServidor, Status status) {
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
        this.id = id;
        this.idServidor = idServidor;
        this.status =status;
    }

    public AdministradorBackup(Long id, String usuario, String email, String senha) {
        this(id, usuario, email, senha, 0, Status.INSERIR);
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
