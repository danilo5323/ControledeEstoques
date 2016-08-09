package projetosgce2.com.br.sgce2.ConnectHttp;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import projetosgce2.com.br.sgce2.DataAccess.MasterSQLHelper;
import projetosgce2.com.br.sgce2.Models.Administrador;
import projetosgce2.com.br.sgce2.Provider.AdministradorProvider;
import projetosgce2.com.br.sgce2.Repositorio.AdministradorRepositorio;

/**
 * Created by Danilo on 22/06/2016.
 */
public class AdministradorHttp__backup {
    private static String BASE_URL = "http://192.168.56.1/hotel_service/webservice.php";

    private Context mContext;
    private AdministradorRepositorio mRepositorio;


    public AdministradorHttp__backup(Context ctx){
        mContext = ctx;
        mRepositorio = new AdministradorRepositorio(mContext);
    }

    public void sincronizar() throws Exception    {
        enviarDadosPendentes();
        List<Administrador> administradores = getAdministradores();
        ContentResolver cr = mContext.getContentResolver();
        for(Administrador adm : administradores){
            adm.setStatus(Administrador.Status.OK);
            mRepositorio.inserirLocal(adm, cr);
        }
    }


    private void enviarDadosPendentes() throws Exception{
        Cursor cursor = mContext.getContentResolver().query(
                AdministradorProvider.CONTENT_URI,
                null,
                MasterSQLHelper.ADM_COLUNA_STATUS + "=" + Administrador.Status.OK.ordinal(), null,
                MasterSQLHelper.ADM_COLUNA_ID_SERVIDOR + " DESC "
        );

        while(cursor.moveToNext()){

            try {
                Administrador administrador = AdministradorRepositorio.administradorFromCursor(cursor);
                if(administrador.getStatus() == Administrador.Status.INSERIR){
                    inserir(administrador);
                } else
                if(administrador.getStatus() == Administrador.Status.EXCLUIR){
                    excluir(administrador);

                }else
                if(administrador.getStatus() == Administrador.Status.ATUALIZAR){
                    if(administrador.getIdServidor() == 0){
                        inserir(administrador);
                    }
                    else{
                        atualizar(administrador);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void inserir(Administrador input){
        try {
            if (enviarAdministrador("POST", input)) {
                input.setStatus(Administrador.Status.OK);
                mRepositorio.atualizarLocal(input, mContext.getContentResolver());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void atualizar(Administrador input){
        try{
            if (enviarAdministrador("PUT", input)) {
                input.setStatus(Administrador.Status.OK);
                mRepositorio.atualizarLocal(input, mContext.getContentResolver());
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void excluir(Administrador input){
        boolean podeExcluir = true;

        if(input.getIdServidor() != 0){

            try{
                podeExcluir = enviarAdministrador("DELETE", input);

            }catch (Exception e ){
                podeExcluir = false;
                e.printStackTrace();
            }
        }
        if(podeExcluir){
            mRepositorio.excluirLocal(input, mContext.getContentResolver());
        }
    }

    private boolean enviarAdministrador(String metodoHttp, Administrador adm) throws Exception{
        boolean sucesso = false;
        boolean doOutput = !"DELETE".equals(metodoHttp);

        String url = BASE_URL;
        if(!doOutput){
            url += "/" + adm.getIdServidor();
        }

        HttpURLConnection conexao = abrirConexao(url, metodoHttp, doOutput);

        if(doOutput){
            OutputStream os = conexao.getOutputStream();
            os.write(administradorToJsonBytes(adm));
            os.flush();
            os.close();
        }

        int responseCode = conexao.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            InputStream is = conexao.getInputStream();
            String s = streamToString(is);
            is.close();

            JSONObject json = new JSONObject(s);

            int idServidor = json.getInt("id");
            adm.setIdServidor(idServidor);

            sucesso = true;
        }
        else
        {
            throw new RuntimeException("Erro ao realizar operação");
        }

        return sucesso;
    }
    //implementar
    private HttpURLConnection abrirConexao(String url, String metodo, boolean doOutput) throws Exception {

        URL urlCon = new URL(url);
        HttpURLConnection conexao = (HttpURLConnection)urlCon.openConnection();
        conexao.setConnectTimeout(15000);
        conexao.setConnectTimeout(15000);
        conexao.setRequestMethod(metodo);;
        conexao.setDoInput(true);
        conexao.setDoOutput(doOutput);

        if(doOutput){
            conexao.addRequestProperty("Content-Type", "application/json");
        }
        conexao.connect();
        return conexao;
    }


    //implementar
    private List<Administrador> getAdministradores() throws Exception{

        HttpURLConnection conexao = abrirConexao(BASE_URL, "GET", false);
        List<Administrador> list = new ArrayList<Administrador>();

        if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){
            String jsonString = streamToString(conexao.getInputStream());
            JSONArray json = new JSONArray(jsonString);

            for(int i = 0; i < json.length(); i++){
                JSONObject administradorJSON = json.getJSONObject(i);
                Administrador p = new Administrador(
                        0l,
                        administradorJSON.getString(""),
                        administradorJSON.getString(""),
                        administradorJSON.getString(""),
                        administradorJSON.getLong(""),
                        Administrador.Status.OK
                );

            }
        }

        return list;
    }

    //implementar
    private String streamToString(InputStream is) throws IOException{
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos ;
        while((lidos = is.read(bytes)) > 0){
            baos.write(bytes, 0, lidos);
        }
        return new String((baos.toByteArray()));
    }


    //implementar
    private byte[] administradorToJsonBytes(Administrador adm){

        try{
            JSONObject jsonPessoa = new JSONObject();
            jsonPessoa.put("id", adm.getIdServidor());
            jsonPessoa.put("usuario", adm.getUsuario());
            jsonPessoa.put("email", adm.getEmail());
            jsonPessoa.put("senha", adm.getSenha());

            String json = jsonPessoa.toString();

        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

}
