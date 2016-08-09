package projetosgce2.com.br.sgce2.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.*;
import android.content.*;

import projetosgce2.com.br.sgce2.R;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private Button btnCliente;
    private Button btnFornecedor;
    private Button btnProduto;
    private Button btnComprar;
    private Button btnVender;
    private Button btnRelatorios;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

/*
        Intent intent = null;
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        */
        //referencia os objetos
        //referencia um objeto button
        btnCliente = (Button)findViewById(R.id.btnClienteMain);
        btnFornecedor = (Button)findViewById(R.id.btnFornecedorMain);
        btnProduto =  (Button)findViewById(R.id.btnProdutoMain);
        btnComprar = (Button)findViewById(R.id.btnComprarMain);
        btnVender = (Button)findViewById(R.id.btnVenderMain);
        btnRelatorios = (Button)findViewById(R.id.btnRelatoriosMain);



        //help.close();
        //seleeciona a string n no valor de txtNome
        //String n = txtNome.getText().toString();

        //seta o botao enviar
        btnCliente.setOnClickListener(this);
    }


    public void onClick(View v){
       // String nomePrinc, String nomeSec, String telefone, String cpf, String cnpj, String inscricao_estadual,   String endereco;
        //instancia uma referencia para a proxima tela
        Button mButton = (Button) v;
        String buttonText = mButton.getText().toString();
        Intent intent = null;

        if(buttonText == btnCliente.getText().toString()) {
            intent = new Intent(this, ClienteActivity.class);

        }
        else if(buttonText == btnFornecedor.getText().toString()){
            intent = new Intent(this, FornecedorActivity.class);

        }
        else if(buttonText == btnProduto.getText().toString()){
            intent = new Intent(this, ProdutoActivity.class);

        }
        else if(buttonText == btnComprar.getText().toString()){
            intent = new Intent(this, ComprarActivity.class);
        }
        else if(buttonText == btnVender.getText().toString()){
            intent = new Intent(this, VenderActivity.class);
        }
        else if(buttonText == btnRelatorios.getText().toString()){
            intent = new Intent(this, RelatoriosActivity.class);
        }

        startActivity(intent);

        //seta o texto no inputbox referenciado em txtNome
        //String tmpSTR = txtNome.getText().toString();
        //if(tmpSTR.trim().isEmpty()){
        //    tmpSTR = "vazio";
        // }

        //intent.putExtra("MENSAGEM", tmpSTR);

        //executa a nova tela


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }


}

