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
import projetosgce2.com.br.sgce2.Models.Administrador;
import projetosgce2.com.br.sgce2.Models.Administrador;
import projetosgce2.com.br.sgce2.Provider.AdministradorProvider;
import projetosgce2.com.br.sgce2.Repositorio.AdministradorRepositorio;

/**
 * Created by Danilo on 22/06/2016.
 */
public class AdministradorHttp {
    //public static final String LIVROS_URL_JSON = "https://raw.githubusercontent.com/nglauber/dominando_android/master/administradors_novatec.json";
    public static final String URL_COLETAR_USUARIO = "http://localhost:8084/ControledeEstoques/jspRetorno.jsp";
    public static final String LIVROS_URL_XML = "https://raw.githubusercontent.com/nglauber/dominando_android/master/administradors_novatec.xml";

    private static HttpURLConnection connectar(String urlArquivo) throws IOException{

        final int SEGUNDOS  = 1000;

        URL url = new URL(urlArquivo);

        HttpURLConnection conexao = (HttpURLConnection)url.openConnection();

        conexao.setReadTimeout(10 * SEGUNDOS);
        conexao.setConnectTimeout(15 * SEGUNDOS);
        conexao.setRequestMethod("GET");

        conexao.setDoInput(true);
        conexao.setDoOutput(false);;
        conexao.connect();
        return conexao;

    }

    public static boolean temConexao(Context ctx){
        ConnectivityManager cm = (ConnectivityManager)
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info!= null && info.isConnected());

    }


    public static List<Administrador> carregarAdministradorJson(){

        try{
            HttpURLConnection conexao = connectar(URL_COLETAR_USUARIO);
            int resposta = conexao.getResponseCode();
            if(resposta == HttpURLConnection.HTTP_OK){
                InputStream is = conexao.getInputStream();
                JSONObject json = new JSONObject((bytesParaString(is)));
                return lerJsonAdministradores(json);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    public static List<Administrador> lerJsonAdministradores(JSONObject json) throws JSONException{
        List<Administrador> listaDeAdministradors= new ArrayList<Administrador>();

        String categoriaAtual;

        JSONArray jsonNovatec = json.getJSONArray("novatec");

        for(int i = 0; i < jsonNovatec.length(); i++){

            JSONObject jsonCategoria = jsonNovatec.getJSONObject(i);
            //categoriaAtual = jsonCategoria.getString("categoria");

            JSONArray jsonAdministradors = jsonCategoria.getJSONArray("sgce");
            for(int j= 0; j < jsonAdministradors.length(); j++){

                JSONObject jsonAdministrador = jsonAdministradors.getJSONObject(j);

                Administrador adm;
                adm =
                        new Administrador (0l,
                                jsonAdministrador.getString("usuario"),
                                jsonAdministrador.getString("email"),
                                jsonAdministrador.getString("senha"));
                listaDeAdministradors.add(adm);
            }

        }
        return listaDeAdministradors;


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





















































    /*



    public static List<Administrador> carregarAdministradorsXml(){
        try{
            HttpURLConnection conexao = connectar(LIVROS_URL_XML);

            int resposta = conexao.getResponseCode();
            if(resposta == conexao.getResponseCode()){
                InputStream is = conexao.getInputStream();

                return lerXmlAdministradors(is);
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Administrador> lerXmlAdministradors(InputStream is) throws Exception{
        List<Administrador> listAdministradors = new ArrayList<Administrador>();
        Administrador administrador = null;
        String tagAtual = null;
        String categoria = null;

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(is, "UTF-8");

        int eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT){

            if(eventType != XmlPullParser.START_TAG){
                tagAtual = xpp.getName();
                if("administrador".equals(tagAtual)){
                    administrador = new Administrador();
                    administrador.categoria = categoria;
                }
            }
            else if (eventType == XmlPullParser.END_TAG){
                if("administrador".equals(xpp.getName())){
                    listAdministradors.add(administrador);
                }
            }else if(eventType == XmlPullParser.TEXT && !xpp.isWhitespace()){
                String text = xpp.getText();
                if("titulo".equals(tagAtual))
                    administrador.titulo = text;
                else if("paginas".equals(tagAtual))
                    administrador.paginas = Integer.parseInt(text);
                else if ("capa".equals(tagAtual))
                    administrador.capa = text;
                else if("autor".equals(tagAtual))
                    administrador.autor = text;
                else if("ano".equals(tagAtual))
                    administrador.ano = Integer.parseInt(tagAtual);
                else if("categoria".equals(tagAtual))
                    administrador.categoria = text;

            }
            eventType = xpp.next();


        }
        return listAdministradors;
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
    */
}
