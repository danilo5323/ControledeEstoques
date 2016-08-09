package projetosgce2.com.br.sgce2.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import projetosgce2.com.br.sgce2.Models.Produto;
import projetosgce2.com.br.sgce2.Models.ProdutoEstoque;
import projetosgce2.com.br.sgce2.R;
import projetosgce2.com.br.sgce2.Repositorio.EstoqueRepositorio;
import projetosgce2.com.br.sgce2.Repositorio.ProdutoRepositorio;

public class ComprarActivity extends ActionBarActivity {

    List<String> produtos;
    List<ProdutoEstoque> produto_estoque;


    TextView prd_cod_barras;
    TextView data_compra;
    TextView data_validade;
    TextView preco_venda;
    TextView preco_compra;
    TextView quantidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar);

        produtos = new ArrayList<String>();

        data_compra = (TextView) findViewById(R.id.txtCompraDataCompra);
        data_validade = (TextView) findViewById(R.id.txtCompraDataValidade);
        preco_venda = (TextView) findViewById(R.id.txtCompraPrecoVenda);
        preco_compra = (TextView) findViewById(R.id.txtCompraPrecoCompra);
        quantidade = (TextView) findViewById(R.id.txtCompraQuantidade);
        codigoBarras = ((TextView) findViewById(R.id.txtCompraCodigoBarras));

        Total = (TextView) findViewById(R.id.tvCompraTotal);
        produtos = new ArrayList<String>();
        produto_estoque = new ArrayList<ProdutoEstoque>();
    }

    TextView codigoBarras;
    TextView Total;

    static int i = 0;
    public void onClick(View v){}

    public void ClickAdicionar(View v) {

        String prd_cod_barras = codigoBarras.getText().toString();
        String data_compra =  this.data_compra.getText().toString();
        //String data_venda = this.data_validade.getText().toString();
        String data_validade = this.data_validade.getText().toString();
        String preco_venda = this.preco_venda.getText().toString();
        String preco_compra = this.preco_compra.getText().toString();
        String quantidade  = this.quantidade.getText().toString();
        String data_venda = "";
        //instancia para salvar a classe;
        EstoqueRepositorio estRep = new EstoqueRepositorio(getBaseContext());

        // instanciando a classe para salvar o novo produto
        ProdutoEstoque prdEstoque = new ProdutoEstoque(prd_cod_barras,data_compra,data_venda, data_validade, preco_venda, preco_compra, quantidade);

        //crtiando a lista de produtos para busca pelo codigo de barras;
        List<Produto> produto = new ArrayList<Produto>();

        // pegando autorização apra consultar o produto repositorio
        ProdutoRepositorio prdRep = new ProdutoRepositorio(getBaseContext());


        //tentando verificando se o produto está cadastrado
        try {
            produto = prdRep.buscarProduto(prd_cod_barras);
        }catch (Exception e){
            Total.setText(e.getMessage() + "\n" + e.getCause());
        }


        //se a busca trouxer algum produto, entao
        if(!produto.isEmpty()){

            Long contador = Long.parseLong(quantidade);

            estRep.adicionaProdutosEstoque(prdEstoque, "");


           prdEstoque = new ProdutoEstoque();

        }
        else{}

        //codigo para a busca de produtos e adição na lista

//        Produto produto;

        /*
        if(produtoemestoque) {
            prdEstoque = estRep.retiraProdutosEstoque(strCodigoBarras);


            produto =  new ProdutoRepositorio().buscarProdutoUnico(strCodigoBarras);

            produtos.add((i++) + " : " + produto.getNome_padrao() + " " + produto.getNome_qualificador() );

            ArrayAdapter produtoAdapter = new ArrayAdapter(this, R.layout.spinner, produtos);
            produtoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner produtoSpinner = (Spinner) findViewById(R.id.produtos);

            produtoSpinner.setAdapter(produtoAdapter);

            Total.setText("R$: " + precototal);

            Total.setText("R$: " + calculaValorTotal( Double.parseDouble(prdEstoque.getPreco_venda()) ) );
        }
        else{


        }
        */
    }

    Double precototal = 0d;

    public void ClickFinalizarVenda(View v){
            this.finish();

    }

    ///soma opreço total da venda
    public Double calculaValorTotal(Double preco){
        precototal += Float.parseFloat("" + preco);
        return precototal;
    }


    ///soma opreço total da venda
    public Double calculaValorTotal(Integer preco){
        precototal += Float.parseFloat("" + preco);
        return precototal;
    }
    public void ClickProcurarCliente(View v){}

}
