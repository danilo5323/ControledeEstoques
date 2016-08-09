    package projetosgce2.com.br.sgce2.Activities;

    import android.support.v7.app.ActionBarActivity;
    import android.os.Bundle;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;

    import java.util.List;

    import projetosgce2.com.br.sgce2.Models.Produto;
    import projetosgce2.com.br.sgce2.R;
    import projetosgce2.com.br.sgce2.Repositorio.ProdutoRepositorio;

    public class ProdutoActivity extends ActionBarActivity implements View.OnClickListener{


        private EditText txtPrdNomePadrao;
        private EditText txtPrdNomeQualificador;
        private EditText txtPrdUnidadeMedida;
        private EditText txtPrdQuantidade;
        private EditText txtPrdMarca;
        private EditText txtCodBarras;

        private Button btnEnviar;
        private Button btnCarregar;
        private Button btnExcluir;

        ProdutoRepositorio produtodao;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_produto);

            //referencia os objetos
            txtPrdNomePadrao = (EditText)findViewById(R.id.txtPrdNomePadrao);
            txtPrdNomeQualificador = (EditText)findViewById(R.id.txtPrdNomeQualificador);
            txtPrdMarca = (EditText)findViewById(R.id.txtPrdMarca);
            txtPrdUnidadeMedida = (EditText)findViewById(R.id.txtPrdQuantidade);
            txtPrdQuantidade = (EditText)findViewById(R.id.txtPrdQuantidade);

            txtCodBarras = (EditText)findViewById(R.id.txtPrdCodBarras);

            //referencia um objeto button
            btnEnviar = (Button)findViewById(R.id.btnPrdAdicionar);
            btnCarregar = (Button)findViewById(R.id.btnPrdCarregar);
            btnExcluir = (Button)findViewById(R.id.btnPrdExcluir);

            //seleeciona a string n no valor de txtNome
            //String n = txtNome.getText().toString();

            //seta o botao enviar
            btnEnviar.setOnClickListener(this);
        }


        public void onClick(View v){
            // String nomePrinc, String nomeSec, String telefone, String cpf, String cnpj, String inscricao_estadual,   String endereco;


            produtodao = new ProdutoRepositorio(getBaseContext());

            Button mButton = (Button) v;
            String buttonText = mButton.getText().toString();




            Produto produto;

            if(buttonText == btnEnviar.getText().toString()) {

                produto = new Produto(
                        txtPrdNomePadrao.getText().toString(),
                        txtPrdNomeQualificador.getText().toString(),
                        txtPrdUnidadeMedida.getText().toString(),
                        txtPrdQuantidade.getText().toString(),
                        txtPrdMarca.getText().toString(),
                        txtCodBarras.getText().toString()
                );

                produtodao.salvar(produto);
            }

            else if(buttonText == btnCarregar.getText().toString()){
                List<Produto> produtos = produtodao.buscarProduto(txtCodBarras.getText().toString());

                if(!produtos.isEmpty()) {
                    produto = new Produto(produtos.get(0));

                    txtPrdNomePadrao.setText(produto.getNome_padrao());

                    txtPrdNomeQualificador.setText(produto.getNome_qualificador());

                    txtPrdUnidadeMedida.setText(produto.getUnidade_medida());

                    txtPrdQuantidade.setText(produto.getQuantia());

                    txtPrdMarca.setText(produto.getCod_barras());

                    txtCodBarras.setText(produto.getCod_barras());


                }
            }
            else if(buttonText == btnExcluir.getText().toString()){

                produto = new Produto(
                        txtPrdNomePadrao.getText().toString(),
                        txtPrdNomeQualificador.getText().toString(),
                        txtPrdUnidadeMedida.getText().toString(),
                        txtPrdQuantidade.getText().toString(),
                        txtPrdMarca.getText().toString(),
                        txtCodBarras.getText().toString()
                );

                produtodao.excluirProduto(produto);
            }
        }

        //instancia uma referencia para a proxima tela

        /*
        Intent intent = new Intent(this, ProdutoActivity.class);

        //seta o texto no inputbox referenciado em txtNome
        String tmpSTR = txtNome.getText().toString();
        if(tmpSTR.trim().isEmpty()){
            tmpSTR = "vazio";
        }

        intent.putExtra("MENSAGEM", tmpSTR);

        //executa a nova tela
        startActivity(intent);
        */




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
