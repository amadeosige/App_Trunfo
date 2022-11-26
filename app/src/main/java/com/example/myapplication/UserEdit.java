package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;

public class UserEdit extends AppCompatActivity {
    private ProgressDialog load;
    private String originalUserName;
    private String ranking;
    private final int GALLERY_REQ_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit);

        UserEdit.GetUser download = new UserEdit.GetUser();
        //Chama Async Task
        download.execute();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_stats:
                        Intent statsScreen = new Intent(UserEdit.this, UserStats.class);
                        startActivity(statsScreen);
                        break;

                    case R.id.action_edit:
                        break;

                    case R.id.action_play:
                        Intent playScreen = new Intent(UserEdit.this, StartPlay.class);
                        startActivity(playScreen);
                        break;
                }
                return true;
            }
        });

    }


    private class GetUser extends AsyncTask<Void, Void, UserModel> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(UserEdit.this,
                    "Por favor Aguarde ...", "Editando usuário...");
        }

        @Override
        protected UserModel doInBackground(Void... params) {
            Utils util = new Utils();
            Globals sharedData = Globals.getInstance();
            String userName = sharedData.getUsername();

            return util.getUserJson("http://10.0.2.2:3000/user/getUser?userName=" + userName);
            //esse ip é o que o android identifica como o localhost do meu pc
        }

        @Override
        protected void onPostExecute(UserModel user){

            originalUserName = user.getUserName();
            ranking = user.getRanking();

            ImageView userImage = (ImageView) findViewById(R.id.imageView);

            String base64Decoded = UrlDecodeBase64(user.getImage());
            byte[] decodedString = Base64.decode(base64Decoded, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            userImage.setImageBitmap(decodedByte);

            TextView userName = (TextView) findViewById(R.id.editTextTextPassword);
            userName.setText(user.getPassword()); // set the text

            TextView password = (TextView) findViewById(R.id.editTextTextPersonName);
            password.setText(user.getUserName()); // set the text

            load.dismiss();
        }
    }

    private class EditUser extends AsyncTask<Void, Void, UserModel> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(UserEdit.this,
                    "Por favor Aguarde ...", "Editando usuário...");
        }

        @Override
        protected UserModel doInBackground(Void... params) {
            Utils util = new Utils();

            TextView user = (TextView) findViewById(R.id.editTextTextPersonName);
            TextView password = (TextView) findViewById(R.id.editTextTextPassword);
            String newUserName = user.getText().toString();
            String newPassword = password.getText().toString();

            ImageView userImage = (ImageView) findViewById(R.id.imageView);
            BitmapDrawable userDrawable = (BitmapDrawable) userImage.getDrawable();
            Bitmap bMap = userDrawable.getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bMap .compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            String encodedString = Base64.encodeToString(b, Base64.NO_WRAP + Base64.URL_SAFE);

            //nao consegui enviar o base64 pela queryString

            return util.getUserJson("http://10.0.2.2:3000/user/editUser?" +
                    "originalUserName=" + originalUserName +
                    "&userName=" + newUserName +

                    "&password=" + newPassword +
                    "&ranking=" + ranking);
        }

        @Override
        protected void onPostExecute(UserModel user){

            if(user == null){
                showToast("Erro ao editar usuário");
                load.dismiss();
                return;
            }

            originalUserName = user.getUserName();
            Globals sharedData = Globals.getInstance();
            sharedData.setUsername(originalUserName);

            ranking = user.getRanking();

            TextView userName = (TextView) findViewById(R.id.editTextTextPassword);
            userName.setText(user.getPassword()); // set the text

            TextView password = (TextView) findViewById(R.id.editTextTextPersonName);
            password.setText(user.getUserName()); // set the text

            showToast("Usuário editado com sucesso");

            load.dismiss();
        }
    }

    public static String UrlEncodeBase64(String base64Input)
    {
        return base64Input.replace('+', '.').replace('/', '_').replace('=', '-');
    }

    public static String UrlDecodeBase64(String encodedBase64Input)
    {
        return encodedBase64Input.replace('.', '+').replace('_', '/').replace('-', '=');
    }

    public void editUser (View v){
        UserEdit.EditUser download = new UserEdit.EditUser();
        //Chama Async Task
        download.execute();
    }

    public void changeImage (View v){
        Intent iGalery = new Intent(Intent.ACTION_PICK);
        iGalery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(iGalery, GALLERY_REQ_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == GALLERY_REQ_CODE){
                ImageView userImage = (ImageView) findViewById(R.id.imageView);
                userImage.setImageURI(data.getData());
            }
        }
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();
    }
}