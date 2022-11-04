package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.graphics.Bitmap;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    private TextView nome;
    private TextView sobrenome;
    private TextView longitude;
    private TextView latitude;
    private GoogleMap mMap;


    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pessoa);

        nome = (TextView)findViewById(R.id.textView5);
        sobrenome = (TextView)findViewById(R.id.textView11);
        longitude = (TextView)findViewById(R.id.textView8);
        latitude = (TextView)findViewById(R.id.textView7);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }


    public void acionaRandom(View view){
        GetJson download = new GetJson();
        //Chama Async Task
        download.execute();
    }


    private class GetJson extends AsyncTask<Void, Void, PessoaObj> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(MainActivity.this,
                    "Por favor Aguarde ...", "Recuperando Informações do Servidor...");
        }

        @Override
        protected PessoaObj doInBackground(Void... params) {
            Utils util = new Utils();

            return util.getInformacao("https://randomuser.me/api/");
        }

        @Override
        protected void onPostExecute(PessoaObj pessoa){
            nome.setText(pessoa.getNome().substring(0,1).toUpperCase()+pessoa.getNome().substring(1));
            sobrenome.setText(pessoa.getSobrenome().substring(0,1).toUpperCase()+pessoa.getSobrenome().substring(1));
            latitude.setText((pessoa.getLatitude().toUpperCase(Locale.ROOT)));
            longitude.setText((pessoa.getLongitude().toUpperCase(Locale.ROOT)));

            LatLng location = new LatLng(Float.parseFloat(pessoa.getLatitude()), Float.parseFloat(pessoa.getLongitude()));
            mMap.addMarker(new MarkerOptions().position(location).title(pessoa.getNome().toUpperCase()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

            load.dismiss();
        }
    }

}
