package it.escuela.lexdroid2.clase13.lexcode2_clase13_apirest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
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

        // MANDAMOS LLAMAR A LOS METODOS.
        SyncService service = new SyncService();
        service.validateSyc(this);
        service.execute(); //aqui ponemos usuario o resto de argumentos.
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
                Log.d("Conectados a : ", urlCompose);
                HttpResponse response = httpClient.execute(get);
                String respStr= EntityUtils.toString(response.getEntity());
                JSONObject respJSON = new JSONObject(respStr);
                // este objeto solo vive aqui ...
                // miramos el objeto JSON que nos viene. siempre hay que conocer la estructura
                if(respJSON.getBoolean("success")){
                    Log.d("paso por","datos");
                    responseJSON=respJSON;

                }else{
                    result= false;
                }



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
        protected void onPostExecute(Boolean result){
            //recibir respuesta
            if(dialog.isShowing()){
                dialog.dismiss();
            }
            if(result){
                try {
                    JSONArray data = responseJSON.getJSONArray("arrayResults");
                    // ahora hay que sacar los datos ....
                    //json array no permiten foreach que seria mejor
                    for (int i=0; i<data.length(); i++){
                        JSONObject gasJSON= (JSONObject) data.get(i);
                        Log.d("Estacion: ", gasJSON.getString("BUSINESS_NAME"));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
