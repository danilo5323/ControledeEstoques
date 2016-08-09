package projetosgce2.com.br.sgce2.Activities;

import android.accounts.AccountManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.util.List;
import java.util.ArrayList;
import projetosgce2.com.br.sgce2.Models.Produto;
import projetosgce2.com.br.sgce2.Models.ProdutoEstoque;
import projetosgce2.com.br.sgce2.R;
import projetosgce2.com.br.sgce2.Repositorio.EstoqueRepositorio;
import projetosgce2.com.br.sgce2.Repositorio.ProdutoRepositorio;

public class VenderActivity extends ActionBarActivity {


    List<String> produtos;
    List<ProdutoEstoque> produto_estoque;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender);
        produtos = new ArrayList<String>();

        Total = (TextView) findViewById(R.id.tvVendeTotal);

        produtos = new ArrayList<String>();
        produto_estoque = new ArrayList<ProdutoEstoque>();

     }

    TextView codigoBarras;
    TextView Total;

    static int i = 0;
    public void onClick(View v){}

    public void ClickAdicionar(View v) throws Exception {

        codigoBarras = ((TextView) findViewById(R.id.txtVendaCodigoBarras));
        String strCodigoBarras = codigoBarras.getText().toString();
        Boolean produtoemestoque = false;
        EstoqueRepositorio estRep = new EstoqueRepositorio(getBaseContext());
        ProdutoEstoque prdEstoque ;



        try {
            produtoemestoque = estRep.quantidadeEmEstoque(strCodigoBarras) > 0;
        }catch (Exception e){
            Total.setText(e.getMessage() + "\n" + e.getCause());
        }

        //codigo para a busca de produtos e adição na lista

        Produto produto = null;

        if(produtoemestoque) {
            prdEstoque = estRep.retiraProdutosEstoque(strCodigoBarras);


            try {
                produto = new ProdutoRepositorio(getBaseContext()).buscarProdutoUnico(strCodigoBarras);

                Integer quantidadeEmEstoque =  new EstoqueRepositorio(getBaseContext()).quantidadeEmEstoque(strCodigoBarras);

                produtos.add((i++) + " : " + produto.getNome_padrao() + " " + produto.getNome_qualificador() + "\t R$ " + prdEstoque.getPreco_venda());

                new EstoqueRepositorio(getBaseContext()).retiraProdutosEstoque(strCodigoBarras);

                ArrayAdapter produtoAdapter = new ArrayAdapter(this, R.layout.spinner, produtos);
                produtoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner produtoSpinner = (Spinner) findViewById(R.id.produtos);

                produtoSpinner.setAdapter(produtoAdapter);

                //Total.setText("Total: R$ " + precototal);

                Total.setText("Total: R$ " + calculaValorTotal( Double.parseDouble(prdEstoque.getPreco_venda()) ) );
            }
            catch (Exception e) {
                Total.setText(e.getMessage() + "\n" + e.getCause() );
            }
        }
        else{


        }
    }

    Double precototal = 0d;

    public void ClickFinalizarVenda(View v)  {
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
