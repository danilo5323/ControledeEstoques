package projetosgce2.com.br.sgce2.ConnectHttp;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import projetosgce2.com.br.sgce2.DataAccess.MasterSQLHelper;
import projetosgce2.com.br.sgce2.Models.Cliente;
import projetosgce2.com.br.sgce2.Models.Livro;
import projetosgce2.com.br.sgce2.Provider.ClienteProvider;
import projetosgce2.com.br.sgce2.Repositorio.ClienteRepositorio;

/**
 * Created by Danilo on 28/06/2016.
 */
public class ClienteHttp {

    public static final String LIVROS_URL_JSON = "http://192.168.0.176:8084/ControledeEstoques/ManipulaCliente";
    public static final String BASE_URL = "http://192.168.0.176:8084/ControledeEstoques/ManipulaCliente";

    private Context mContext;
    private ClienteRepositorio mRepositorio;

    //construtor
    public ClienteHttp(Context ctx){
        mContext = ctx;
        mRepositorio = new ClienteRepositorio(mContext);
    }

    public void sincronizar() throws Exception{
        enviarDadosPendentes();
        List<Cliente> clientes = getClientes();
        ContentResolver cr = mContext.getContentResolver();
        for(Cliente cliente : clientes){
            cliente.status = Cliente.Status.OK;
            mRepositorio.inserirLocal(cliente, cr);
        }
    }

    private void enviarDadosPendentes() {
        Cursor cursor = mContext.getContentResolver().query(
                ClienteProvider.CONTENT_URI, null,
                MasterSQLHelper.CLI_COLUNA_STATUS + " != " + Cliente.Status.OK.ordinal(), null,
                MasterSQLHelper.CLI_COLUNA_ID_SERVIDOR + " DESC ");

        while (cursor.moveToNext()){
            Cliente cliente = ClienteRepositorio.clienteFromCursor(cursor);
            if(cliente.status == Cliente.Status.EXCLUIR){
                excluir(cliente);
            } else
            if(cliente.status == Cliente.Status.INSERIR){
                inserir(cliente);
            } else
            if(cliente.status == Cliente.Status.ATUALIZAR){
                if(cliente.idServidor == 0) {
                    inserir(cliente);
                }else{
                    atualizar(cliente);
                }
            }
        }
    }

    private void inserir(Cliente cliente){
        try{
            //condicao para envio
            if(enviarCliente("POST", cliente)){
                cliente.status = Cliente.Status.OK;
                mRepositorio.atualizarLocal(cliente, mContext.getContentResolver());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void atualizar(Cliente cliente){
        try{
            if(enviarCliente("PUT", cliente)){
                cliente.status = Cliente.Status.OK;
                mRepositorio.atualizarLocal(cliente, mContext.getContentResolver());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void excluir(Cliente cliente){
        boolean podeExcluir = true;

        if(cliente.idServidor != 0 ){
            try{
                podeExcluir = enviarCliente("DELETE", cliente);
                mRepositorio.atualizarLocal(cliente, mContext.getContentResolver());
            }catch(Exception e){
                podeExcluir = false;
                e.printStackTrace();
            }
        }
        if(podeExcluir) {
            mRepositorio.excluirLocal(cliente, mContext.getContentResolver());
        }
    }


    private boolean enviarCliente(String metodoHttp, Cliente cliente) throws Exception{
        boolean sucesso = false;
        boolean doOutput = !"DELETE".equals(metodoHttp);
        String url = BASE_URL;
        if(!doOutput){
            url += "/"+cliente.idServidor;
        }
        HttpURLConnection conexao = abrirConexao(url, metodoHttp, doOutput);

        if(doOutput){
            OutputStream os = conexao.getOutputStream();
            os.write((clienteJsonToBytes(cliente)));
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
            cliente.idServidor = idServidor;
            sucesso = true;

        }
        else{
            throw new RuntimeException("Erro ao realizar operaçao ");
        }

        conexao.disconnect();
        return sucesso;
    }


    private HttpURLConnection abrirConexao(String url, String metodo, boolean doOutput) throws Exception {

        URL urlCon = new URL(url);

        HttpURLConnection conexao = (HttpURLConnection) urlCon.openConnection();

        final int SEGUNDO = 1000;

        conexao.setReadTimeout(SEGUNDO * 15);
        conexao.setConnectTimeout(SEGUNDO * 15);
        conexao.setDoInput(true);
        conexao.setDoOutput(doOutput);
        if(doOutput){
            conexao.addRequestProperty("Content-Type", "application/json");
        }

        conexao.connect();
        return conexao;
    }

    /**
     * @return
     * @throws Exception
     */
    private List<Cliente> getClientes() throws Exception{
        HttpURLConnection conexao = abrirConexao(BASE_URL, "GET", false);

        List<Cliente> lista = new ArrayList<Cliente>();

        if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){

            String jsonString = streamToString(conexao.getInputStream());

            JSONArray json = new JSONArray(jsonString);

            for(int i = 0; i < json.length(); i++){
                //Cliente(long id, String nomePrinc, String nomeSec, String telefone, String email, String cpf,
                //        String cnpj, String inscricao_estadual, String endereco, Status status, long idServidor)
                JSONObject clienteJson = json.getJSONObject(i);



                //public Cliente(long id, String nomePrinc, String nomeSec, String telefone, String email, String cpf,
                //        String cnpj, String inscricao_estadual, String endereco, Status status, long idServidor) {

                    Cliente p = new Cliente(
                        ///retorno do servidor
                        clienteJson.getLong("id"),
                        clienteJson.getString("nome_princ"),
                        clienteJson.getString("nome_sec"),
                        clienteJson.getString("telefone"),
                        clienteJson.getString("email"),
                        clienteJson.getString("cpf"),
                        clienteJson.getString("cnpj"),
                        clienteJson.getString("inscricao_estadual"),
                        clienteJson.getString("endereco"),
                        Cliente.Status.values()[clienteJson.getInt("status")],
                        clienteJson.getLong("id_servidor")
                );

                lista.add(p);

            }


        }


        return lista;
    }

private String streamToString(InputStream is) throws IOException {
    byte[] bytes = new byte[1024];
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    int lidos ;

    while((lidos = is.read(bytes)) >0 ){
        baos.write(bytes, 0, lidos);
    }
    return new String(baos.toByteArray());
}

    private byte[] clienteJsonToBytes(Cliente cliente){
        try{
            JSONObject jsonCliente =new JSONObject();
            jsonCliente.put("id", cliente.id_cliente);
            jsonCliente.put("nome_princ", cliente.nomePrinc);
            jsonCliente.put("nome_sec", cliente.nomeSec);
            jsonCliente.put("telefone", cliente.telefone);
            jsonCliente.put("email", cliente.email);
            jsonCliente.put("cpf", cliente.cpf);
            jsonCliente.put("cnpj", cliente.cnpj);
            jsonCliente.put("inscricao_estadual", cliente.inscricao_estadual);
            jsonCliente.put("endereco", cliente.endereco);
            jsonCliente.put("status", cliente.status);
            jsonCliente.put("id_servidor", cliente.idServidor);


            String json = jsonCliente.toString();
            return json.getBytes();

        }catch (JSONException  e){
            e.printStackTrace();
        }
        return null;
    }



    private static HttpURLConnection connectar(String urlArquivo) throws IOException {

        final int SEGUNDOS = 1000;

        URL url = new URL(urlArquivo);

        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

        conexao.setReadTimeout(10 * SEGUNDOS);
        conexao.setConnectTimeout(15 * SEGUNDOS);
        conexao.setRequestMethod("PUT");

        conexao.setDoInput(true);
        conexao.setDoOutput(false);

        conexao.connect();
        return conexao;

    }

    public static boolean temConexao(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager)
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected());

    }


    public static List<Livro> carregarLivroJson() {

        try {
            HttpURLConnection conexao = connectar(LIVROS_URL_JSON);
            int resposta = conexao.getResponseCode();
            if (resposta == HttpURLConnection.HTTP_OK) {
                InputStream is = conexao.getInputStream();
                JSONObject json = new JSONObject((bytesParaString(is)));
                return lerJsonLivros(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<Cliente> carregarClienteJsonComParametro(String parametro) {

        try {
            String strConexao = LIVROS_URL_JSON + parametro;

            //strConexao = LIVROS_URL_JSON;

            HttpURLConnection conexao = connectar(strConexao);
            int resposta = conexao.getResponseCode();
            if (resposta == HttpURLConnection.HTTP_OK) {
                InputStream is = conexao.getInputStream();
                JSONObject json = new JSONObject((bytesParaString(is)));
                return lerJsonCliente(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Cliente> carregarClienteJson() {

        try {
            HttpURLConnection conexao = connectar(LIVROS_URL_JSON);
            int resposta = conexao.getResponseCode();
            if (resposta == HttpURLConnection.HTTP_OK) {
                InputStream is = conexao.getInputStream();
                JSONObject json = new JSONObject((bytesParaString(is)));
                return lerJsonCliente(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<Cliente> lerJsonCliente(JSONObject json) throws JSONException {

        List<Cliente> listaDeClientes = new ArrayList<Cliente>();

        String usuario, email, senha;

        JSONArray jsonAdm = json.getJSONArray("sgce");

        for(
                int i = 0;
                i<jsonAdm.length();i++)

        {

            JSONObject jsonCategoria = jsonAdm.getJSONObject(i);

            usuario = jsonCategoria.getString("usuario");
            email = jsonCategoria.getString("email");
            senha = jsonCategoria.getString("senha");

            Cliente adm;
            adm = new Cliente(//parametros
             );

            listaDeClientes.add(adm);


        }

        return listaDeClientes;
    }

    public static List<Livro> lerJsonLivros(JSONObject json) throws JSONException{
        List<Livro> listaDeLivros= new ArrayList<Livro>();


        String categoriaAtual;

        JSONArray jsonNovatec = json.getJSONArray("novatec");

        for(int i = 0; i < jsonNovatec.length(); i++){

            JSONObject jsonCategoria = jsonNovatec.getJSONObject(i);
            categoriaAtual = jsonCategoria.getString("categoria");

            JSONArray jsonLivros = jsonCategoria.getJSONArray("livros");
            for(int j= 0; j < jsonLivros.length(); j++){

                JSONObject jsonLivro = jsonLivros.getJSONObject(j);

                Livro livro = new Livro(jsonLivro.getString("titulo"), categoriaAtual, jsonLivro.getString("autor"), jsonLivro.getInt("ano"), jsonLivro.getInt("paginas"), jsonLivro.getString("capa"));
                listaDeLivros.add(livro);

            }

        }
        return listaDeLivros;



    }

    private static String bytesParaString(InputStream is) throws IOException{

        byte[] buffer = new byte[
                1024];
        //o bufferzao vai armazenar todos os bytes lidos
        ByteArrayOutputStream bufferzao = new ByteArrayOutputStream();
        //necessario saber quantos bytes foram lidos
        int bytesLidos;
        //1kb por vez
        while((bytesLidos = is.read(buffer))!= -1){
            //copiando a quantidade de bytes lidos dobuffer para o bufferzao
            bufferzao.write(buffer, 0, bytesLidos);

        }
        return new String(bufferzao.toByteArray(), "UTF-8");
    }

}
