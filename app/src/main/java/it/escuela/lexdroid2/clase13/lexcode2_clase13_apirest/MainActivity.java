package it.escuela.lexdroid2.clase13.lexcode2_clase13_apirest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private class SyncService extends AsyncTask<String, String, Boolean> {
        JSONObject responseJSON;
        String urlCompose;
        private ProgressDialog dialog;

        public void validateSyc(Context context){
            dialog = new ProgressDialog(context);

        }

        protected Boolean doInBackground(String... params){
            boolean result = true;
            HttpClient httpClient = new DefaultHttpClient();
            urlCompose="http://gs500.mx/AppGeolocalizacion/GetStations.php?token=3b120d6dd087042cc46bfc0b94b07382";
            HttpGet get= new HttpGet(urlCompose);
            get.setHeader("content-type", "application/json");



            try {
                Log.d("Conectaados a : ", urlCompose);
                HttpResponse response = httpClient.execute(get);
                String respStr= EntityUtils.toString(response.getEntity());
                JSONObject respJSON = new JSONObject(respStr);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e){
                e.printStackTrace();
                //varias formas de manejar excepciones , subirla a la clase de arriba o manejarla aqui como ahora
            }


            return result;
        }

        protected void onPreExcute(){
            this.dialog.setMessage("Sincronizando .. espere");
            this.dialog.show();

        }
        protected void onPostExecute(){
            //recibir respuesta

        }

    }

}
