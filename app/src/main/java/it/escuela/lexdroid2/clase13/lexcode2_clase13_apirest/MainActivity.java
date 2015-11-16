package it.escuela.lexdroid2.clase13.lexcode2_clase13_apirest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONObject;

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

            return result;
        }


    }

}
