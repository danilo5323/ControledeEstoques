package projetosgce2.com.br.sgce2.DataAccess;

/**
 * Created by danilo2 on 05/05/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MasterSQLHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "SGCE";
    private static final int VERSAO_BANCO = 1;


        //strings para criar a tabela cliente.
    public static final String TABELA_CLIENTE = "CLIENTE";
    //DADOS CLIENTE
    public static final String CLI_TELEFONE = "CLI_TELEFONE";
    public static final String CLI_CPF = "CLI_CPF";
    public static final String CLI_IE = "CLI_IE";
    public static final String CLI_CNPJ = "CLI_CNPJ";
    public static final String CLI_ENDERECO = "CLI_ENDERECO";
    public static final String CLI_NOME_PRINC = "CLI_NOME_PRINC";
    public static final String CLI_NOME_SEC = "CLI_NOME_SEC";
    public static final String CLI_COLUNA_STATUS = "CLI_COLUNA_STATUS";
    public static final String CLI_COLUNA_ID_SERVIDOR = "CLI_COLUNA_ID_SERVIDOR";


    public static final String CLI_EMAIL = "CLI_EMAIL";
    public static final String ID_CLIENTE = "ID_CLIENTE";



        //strings para criar a tabela de produto
    public static final String TABELA_PRODUTO = "PRODUTO";
    //DADOS PRODUTO
    public static final String PRD_UNIDADE_MEDIDA = "PRD_UNIDADE_MEDIDA";
    public static final String PRD_MEDIDA = "PRD_MEDIDA";
    public static final String PRD_NOME_QUALIFICADOR = "PRD_NOME_QUALIFICADOR";
    public static final String PRD_NOME_PADRAO = "PRD_NOME_PADRAO";
    public static final String PRD_COD_BARRAS = "PRD_COD_BARRAS";
    public static final String PRD_MARCA = "PRD_MARCA";
    public static final String ID_PRODUTO = "ID_PRODUTO";


        //dados para a tabela fornecedor
    public static final String TABELA_FORNECEDOR = "FORNECEDOR";
    //DADOS FORNECEDOR
    public static final String FORN_NOME_EMPRESA = "FORN_NOME_EMPRESA";
    public static final String FORN_NOME_FANTASIA = "FORN_NOME_FANTASIA";
    public static final String FORN_CNPJ = "FORN_CNPJ";
    public static final String FORN_INSCRICAO_ESTADUAL = "FORN_INSCRICAO_ESTADUAL";
    public static final String FORN_EMAIL = "FORN_EMAIL";
    public static final String FORN_TEL = "FORN_TEL";
    public static final String FORN_ENDERECO = "FORN_ENDERECO";
    public static final String ID_FORNECEDOR = "ID_FORNECEDOR";

    //strings para criar a tabela estoque.
    public static final String TABELA_ESTOQUE = "ESTOQUE";
    //DADOS ESTOQUE
    public static final String EST_PRD_DATA_COMPRA = "EST_PRD_DATA_COMPRA";
    public static final String EST_PRD_DATA_VENDA = "EST_PRD_DATA_VENDA";
    public static final String EST_DATA_VALIDADE = "EST_DATA_VALIDADE";
    public static final String EST_PRECO_VENDA = "EST_PRECO_VENDA";
    public static final String EST_PRECO_COMPRA = "EST_PRECO_COMPRA";
    public static final String EST_PRD_COD_BARRAS = "EST_PRD_COD_BARRAS";
    public static final String EST_QUANTIDADE = "EST_QUANTIDADE";
    public static final String EST_ID_ESTOQUE = "ID_ESTOQUE";

    //strings para criar a tabela administrador
    public static final String TABELA_ADMINISTRADOR = "TABELA_ADMINISTRADOR";
    //dados fornecedor
    public static final String ADM_USUARIO = "ADM_USUARIO";
    public static final String ADM_EMAIL = "ADM_EMAIL";
    public static final String ADM_SENHA = "ADM_SENHA";
    public static final String ADM_ID_ADMIN = "ADM_ID_ADMIN";
    public static final String ADM_COLUNA_STATUS = "ADM_COLUNA_STATUS";
    public static final String ADM_COLUNA_ID_SERVIDOR = "ADM_COLUNA_ID_SERVIDOR";




    private static final String criarSQLAdministrador = "CREATE TABLE " + TABELA_ADMINISTRADOR +
            "(" +
            ADM_USUARIO            +   " TEXT," +
            ADM_EMAIL              +   " TEXT," +
            ADM_SENHA              +   " TEXT," +
            ADM_COLUNA_STATUS      +   " TEXT," +
            ADM_COLUNA_ID_SERVIDOR     +   " INTEGET UNIQUE," +
            ADM_ID_ADMIN           +   " INTEGER PRIMARY KEY AUTOINCREMENT" +

            ");" ;

    private static final String criarSQLEstoque = "CREATE TABLE " + TABELA_ESTOQUE +
            "(" +
            EST_PRD_DATA_COMPRA    +   " TEXT," +
            EST_PRD_DATA_VENDA     +   " TEXT," +
            EST_DATA_VALIDADE      +   " TEXT," +
            EST_PRECO_VENDA        +   " TEXT," +
            EST_PRECO_COMPRA       +   " TEXT," +
            EST_PRD_COD_BARRAS         +   " TEXT," +
            EST_QUANTIDADE         +   " TEXT," +
            EST_ID_ESTOQUE         +   " INTEGER PRIMARY KEY AUTOINCREMENT" +
            ");" ;


    private static final String criarSQLProduto = "CREATE TABLE " + TABELA_PRODUTO +
            "(" +
            PRD_UNIDADE_MEDIDA    +   " TEXT," +
            PRD_NOME_QUALIFICADOR +   " TEXT," +
            PRD_NOME_PADRAO +         " TEXT," +
            PRD_MARCA       +         " TEXT," +
            PRD_MEDIDA     +          " TEXT," +
            PRD_COD_BARRAS  +         " TEXT," +
            ID_PRODUTO +              " INTEGER PRIMARY KEY AUTOINCREMENT" +
            ");" ;

    public static final String criarSQLFornecedor = " CREATE TABLE " + TABELA_FORNECEDOR +
            " ( " +
            FORN_NOME_EMPRESA +         " TEXT, " +
            FORN_NOME_FANTASIA +        " TEXT, " +
            FORN_CNPJ +                 " TEXT, " +
            FORN_INSCRICAO_ESTADUAL +   " TEXT, " +
            FORN_EMAIL +                " TEXT, " +
            FORN_TEL +                  " TEXT, " +
            FORN_ENDERECO +             " TEXT, " +
            ID_FORNECEDOR +             " INTEGER PRIMARY KEY AUTOINCREMENT" +
            " ); " ;


    public static final String criarSQLCliente = "CREATE TABLE " + TABELA_CLIENTE +
            "(" +
            CLI_TELEFONE +   " TEXT," +
            CLI_CPF +        " TEXT," +
            CLI_IE +         " TEXT," +
            CLI_CNPJ +       " TEXT," +
            CLI_ENDERECO +   " TEXT," +
            CLI_NOME_PRINC + " TEXT," +
            CLI_NOME_SEC +   " TEXT," +
            CLI_EMAIL   +    " TEXT," +
            CLI_COLUNA_ID_SERVIDOR   +    " TEXT," +
            CLI_COLUNA_STATUS   +    " TEXT," +
            ID_CLIENTE +     " INTEGER PRIMARY KEY AUTOINCREMENT" +
            ");" ;


    public MasterSQLHelper(Context context){
        super(context, NOME_BANCO, null, VERSAO_BANCO);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){

        sqLiteDatabase.execSQL(criarSQLCliente);
        sqLiteDatabase.execSQL(criarSQLFornecedor);
        sqLiteDatabase.execSQL(criarSQLProduto);
        sqLiteDatabase.execSQL(criarSQLEstoque);
        sqLiteDatabase.execSQL(criarSQLAdministrador);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
            //para as proximas versões

    }


}


