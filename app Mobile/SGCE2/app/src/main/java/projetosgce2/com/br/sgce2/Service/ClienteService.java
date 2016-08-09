package projetosgce2.com.br.sgce2.Service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;

import projetosgce2.com.br.sgce2.ConnectHttp.ClienteHttp;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ClienteService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    //private static final String ACTION_FOO = "projetosgce2.com.br.sgce2.Service.action.FOO";
    //private static final String ACTION_BAZ = "projetosgce2.com.br.sgce2.Service.action.BAZ";

    // TODO: Rename parameters
    //private static final String EXTRA_PARAM1 = "projetosgce2.com.br.sgce2.Service.extra.PARAM1";
    //private static final String EXTRA_PARAM2 = "projetosgce2.com.br.sgce2.Service.extra.PARAM2";

    public static final String ACAO_SINCRONIZAR = "sincronizar";
    public static final String EXTRA_SUCESSO    = "sucesso";

    public ClienteService() {
        super("ClienteService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            ClienteHttp clienteHttp = new ClienteHttp(this);
            Intent it = new Intent(ACAO_SINCRONIZAR);
            LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);



            try{
                clienteHttp.sincronizar();
                it.putExtra(EXTRA_SUCESSO, false);

            }catch(Exception e){
                it.putExtra(EXTRA_SUCESSO, false);
                e.printStackTrace();
            }
            finally {
                lbm.sendBroadcast(it);
            }

        }
    }

}
