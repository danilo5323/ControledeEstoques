package projetosgce2.com.br.sgce2.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import projetosgce2.com.br.sgce2.Models.Fornecedor;
import projetosgce2.com.br.sgce2.R;
import projetosgce2.com.br.sgce2.Repositorio.FornecedorRepositorio;

public class FornecedorActivity extends ActionBarActivity implements View.OnClickListener{


    private EditText txtFornNomeEmpresa;
    private EditText txtFornNomeFantasia;
    private EditText txtFornCNPJ;
    private EditText txtFornInscricaoEstadual;
    private EditText txtFornEmail;
    private EditText txtFornTel;
    private EditText txtFornEndereco;

    private Button btnEnviar;
    private Button btnCarregar;
    private Button btnExcluir;

    FornecedorRepositorio fornecedordao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedor);

        //referencia os objetos
        txtFornNomeEmpresa = (EditText)findViewById(R.id.txtFornNomeEmpresa);
        txtFornNomeFantasia = (EditText)findViewById(R.id.txtFornNomeFantasiaEmpresa);
        txtFornCNPJ = (EditText)findViewById(R.id.txtFornCNPJ);
        txtFornInscricaoEstadual = (EditText)findViewById(R.id.txtFornInscricaoEstadual);
        txtFornEmail = (EditText)findViewById(R.id.txtFornEmail);
        txtFornTel = (EditText)findViewById(R.id.txtFornTelefone);
        txtFornEndereco = (EditText)findViewById(R.id.txtFornEndereco);

        //referencia um objeto button
        btnEnviar = (Button)findViewById(R.id.btnFornAdicionar);
        btnCarregar = (Button)findViewById(R.id.btnFornCarregar);
        btnExcluir = (Button)findViewById(R.id.btnFornExcluir);

        //seleeciona a string n no valor de txtNome
        //String n = txtNome.getText().toString();

        //seta o botao enviar
        btnEnviar.setOnClickListener(this);
    }


    public void onClick(View v){
        // String nomePrinc, String nomeSec, String telefone, String cpf, String cnpj, String inscricao_estadual,   String endereco;
        //clientedao = new ClienteRepositorio(MainActivity.contexto);

        //fornecedordao = new FornecedorRepositorio(getBaseContext());
        fornecedordao = new FornecedorRepositorio(getBaseContext());

        Button mButton = (Button) v;
        String buttonText = mButton.getText().toString();




        Fornecedor fornecedor;

        if(buttonText == btnEnviar.getText().toString()) {

            fornecedor = new Fornecedor(
                    txtFornNomeEmpresa.getText().toString(),
                    txtFornNomeFantasia.getText().toString(),
                    txtFornEmail.getText().toString(),

                    txtFornCNPJ.getText().toString(),

                    txtFornInscricaoEstadual.getText().toString(),
                    txtFornTel.getText().toString(),
                    txtFornEndereco.getText().toString()
            );

            fornecedordao.salvar(fornecedor);
        }

        else if(buttonText == btnCarregar.getText().toString()){
            List<Fornecedor> fornecedores = fornecedordao.buscarFornecedorCNPJ(txtFornCNPJ.getText().toString());

            if(!fornecedores.isEmpty()) {
                fornecedor = new Fornecedor(fornecedores.get(0));

                txtFornNomeEmpresa.setText(fornecedor.getForn_nome_empresa());

                txtFornEndereco.setText(fornecedor.getForn_endereco());

                txtFornInscricaoEstadual.setText(fornecedor.getForn_ie());

                txtFornNomeFantasia.setText(fornecedor.getForn_nome_fantasia());

                txtFornCNPJ.setText(fornecedor.getForn_cnpj());

                txtFornEmail.setText(fornecedor.getForn_email());

                txtFornTel.setText(fornecedor.getForn_tel());

            }
        }
            else if(buttonText == btnExcluir.getText().toString()){

                fornecedor = new Fornecedor(
                        txtFornNomeEmpresa.getText().toString(),
                        txtFornNomeFantasia.getText().toString(),
                        txtFornEmail.getText().toString(),

                        txtFornCNPJ.getText().toString(),

                        txtFornInscricaoEstadual.getText().toString(),
                        txtFornTel.getText().toString(),
                        txtFornEndereco.getText().toString()
                );

                fornecedordao.excluirFornecedor(fornecedor);
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cliente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
