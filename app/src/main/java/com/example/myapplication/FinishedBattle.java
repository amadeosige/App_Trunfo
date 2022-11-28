package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class FinishedBattle extends AppCompatActivity {
    int quantity = 0;
    private ProgressDialog load;
    int roundCount = 0;
    Globals sharedData;
    int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_finish);
        try {
            boolean win = false;
            sharedData = Globals.getInstance();

            TextView winLostTitle = (TextView) findViewById(R.id.winLostTitle);
            if(sharedData.getRoundsWon() > sharedData.getRoundQuantity() / 2){
                winLostTitle.setText("VOCÊ VENCEU");
                win = true;
            } else {
                winLostTitle.setText("VOCÊ PERDEU");
            }

            ImageView userImage = (ImageView) findViewById(R.id.userImage);
            String base64Decoded = Utils.UrlDecodeBase64(sharedData.getUserImage());
            byte[] decodedString = Base64.decode(base64Decoded, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            userImage.setImageBitmap(decodedByte);

            TextView roundsWon = (TextView) findViewById(R.id.roundsWon);
            TextView roundsLost = (TextView) findViewById(R.id.roundsLost);

            int rounds = sharedData.getRoundsWon();
            int losts = sharedData.getRoundQuantity() - sharedData.getRoundsWon();

            roundsWon.setText(String.valueOf(rounds));
            roundsLost.setText(String.valueOf(losts));


            points = calculatePoints(win);

        } catch (Exception e){
            Exception a = e;
        }
    }

    private int calculatePoints(boolean win){
        try{
            int gamePoints = 0;
            int userPoints = 0;

            switch (sharedData.getRoundQuantity()){
                case 1: gamePoints = 50;
                    break;
                case 3: gamePoints = 100;
                    break;
                case 5: gamePoints = 200;
                    break;
            }

            if(win){
                userPoints = gamePoints * sharedData.getRoundsWon();
            } else{
                userPoints = userPoints - gamePoints * (sharedData.getRoundQuantity() - sharedData.getRoundsWon());
            }

            String userPointsString = userPoints > 0 ? "+" : "";
            TextView pointsDoneView = (TextView) findViewById(R.id.points);
            pointsDoneView.setText(userPointsString + String.valueOf(userPoints) + " PONTOS");

            return userPoints;

        } catch (Exception e){
            Exception a = e;
        }
        return 0;
    }

    public void finalizeGame(View v){
        Handler handler = new Handler();

        load = ProgressDialog.show(FinishedBattle.this,
                "Por favor Aguarde ...", "Finalizando a partida...");


        FinishedBattle.UpdateUserRanking download = new FinishedBattle.UpdateUserRanking();
        //Chama Async Task
        download.execute();

        handler.postDelayed(new Runnable() {
            public void run() {
                //joga pra tela de acabado o jogo
                Intent listRankingScreen = new Intent(FinishedBattle.this, UserStats.class);
                startActivity(listRankingScreen);
                load.dismiss();
            }
        }, 1000);
    }

    private class UpdateUserRanking extends AsyncTask<Void, Void, UserModel> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(FinishedBattle.this,
                    "Por favor Aguarde ...", "Atualizando ranking...");
        }

        @Override
        protected UserModel doInBackground(Void... params) {
            Utils util = new Utils();


            return util.getUserJson("http://10.0.2.2:3000/user/updateRanking?" +
                    "userName=" + sharedData.getUsername() +
                    "&ranking=" + points);
        }

        @Override
        protected void onPostExecute(UserModel user){
            showToast("Ranking atualizado com sucesso");
            load.dismiss();
        }
    }


    private void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();
    }
}