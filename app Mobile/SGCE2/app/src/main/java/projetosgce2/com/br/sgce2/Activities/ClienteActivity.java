package projetosgce2.com.br.sgce2.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.*;

import java.util.List;

import projetosgce2.com.br.sgce2.Repositorio.ClienteRepositorio;
import projetosgce2.com.br.sgce2.Models.Cliente;
import projetosgce2.com.br.sgce2.R;



//teste
public class ClienteActivity extends ActionBarActivity implements View.OnClickListener{


    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtTelefone;
    private EditText txtCPF;
    private EditText txtEmail;
    private EditText txtCNPJ;
    private EditText txtInscricao_Estadual;
    private EditText txtEndereco;

    private Button btnEnviar;
    private Button btnCarregar;
    private Button btnExcluir;

    ClienteRepositorio clientedao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        //referencia os objetos
        txtNome = (EditText)findViewById(R.id.txtCliNome);
        txtSobrenome = (EditText)findViewById(R.id.txtCliSobrenome);
        txtTelefone = (EditText)findViewById(R.id.txtCliTelefone);
        txtCPF = (EditText)findViewById(R.id.txtCliCPF);
        txtCNPJ = (EditText)findViewById(R.id.txtCliCNPJ);
        txtInscricao_Estadual = (EditText)findViewById(R.id.txtCliInscricaoEstadual);
        txtEndereco = (EditText)findViewById(R.id.txtCliEndereco);
        txtEmail   = (EditText)findViewById(R.id.txtCliEmail);

        //referencia um objeto button
        btnEnviar = (Button)findViewById(R.id.btnCliAdicionar);
        btnCarregar = (Button)findViewById(R.id.btnCliCarregar);
        btnExcluir = (Button)findViewById(R.id.btnCliExcluir);

        //seleeciona a string n no valor de txtNome
        //String n = txtNome.getText().toString();

        //seta o botao enviar
        btnEnviar.setOnClickListener(this);
    }


    public void onClick(View v){
        // String nomePrinc, String nomeSec, String telefone, String cpf, String cnpj, String inscricao_estadual,   String endereco;


        //clientedao = new ClienteRepositorio(getBaseContext());
        clientedao = new ClienteRepositorio(getBaseContext());

        Button mButton = (Button) v;
        String buttonText = mButton.getText().toString();

        Cliente cliente = null;
        if(buttonText == btnEnviar.getText().toString()) {

            //String nomePrinc, String nomeSec, String telefone, String email, String cpf, String cnpj, String inscricao_estadual,   String endereco

            cliente = new Cliente(
                    txtNome.getText().toString(),
                    txtSobrenome.getText().toString(),
                    txtTelefone.getText().toString(),
                    txtEmail.getText().toString(),
                    txtCPF.getText().toString(),
                    txtCNPJ.getText().toString(),
                    txtInscricao_Estadual.getText().toString(),
                    txtEndereco.getText().toString()
            );

            clientedao.salvarN(cliente);
            ///teste android
        }

        else if(buttonText == btnCarregar.getText().toString()) {

            List<Cliente> clientes = clientedao.buscarCliente( txtTelefone.getText().toString());
            //List<Cliente> clientes = clientedao.buscar(getBaseContext(), txtTelefone.getText().toString());


            if (!clientes.isEmpty()) {

                cliente = clientes.get(0);


                txtNome.setText(cliente.getNomePrinc());


                txtEndereco.setText(cliente.getEndereco());

                txtEmail.setText(cliente.getEmail());

                txtInscricao_Estadual.setText(cliente.getInscricao_estadual());

                if (txtInscricao_Estadual.getText().toString().isEmpty()) {
                    txtInscricao_Estadual.setVisibility(View.INVISIBLE);
                    findViewById(R.id.tvCliInscricaoEstadual).setVisibility(View.INVISIBLE);
                } else {
                    txtInscricao_Estadual.setVisibility(View.VISIBLE);
                    findViewById(R.id.tvCliInscricaoEstadual).setVisibility(View.VISIBLE);
                }

                txtEmail.setText(cliente.getEmail());

                txtCNPJ.setText(cliente.getCnpj());

                if (txtCNPJ.getText().toString().isEmpty()) {
                    txtCNPJ.setVisibility(View.INVISIBLE);
                    findViewById(R.id.tvCliCNPJ).setVisibility(View.INVISIBLE);

                } else {
                    txtCNPJ.setVisibility(View.VISIBLE);
                    findViewById(R.id.tvCliCNPJ).setVisibility(View.VISIBLE);
                }

                txtCPF.setText(cliente.getCpf());
                if (txtCPF.getText().toString().isEmpty()) {
                    txtCPF.setVisibility(View.INVISIBLE);
                    findViewById(R.id.tvCliCPF).setVisibility(View.INVISIBLE);
                } else {
                    txtCPF.setVisibility(View.VISIBLE);
                    findViewById(R.id.tvCliCPF).setVisibility(View.VISIBLE);
                }


                txtSobrenome.setText(cliente.getNomeSec());

                txtTelefone.setText(cliente.getTelefone());


            }
        }
            else if(buttonText == btnExcluir.getText().toString()){

                cliente = new Cliente(
                        txtNome.getText().toString(),
                        txtSobrenome.getText().toString(),
                        txtTelefone.getText().toString(),
                        txtEmail.getText().toString(),
                        txtCPF.getText().toString(),
                        txtCNPJ.getText().toString(),
                        txtInscricao_Estadual.getText().toString(),
                        txtEndereco.getText().toString()
                );

                clientedao.excluir(cliente);
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
