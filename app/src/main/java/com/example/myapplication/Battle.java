package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Battle extends AppCompatActivity {
    int quantity = 0;
    private ProgressDialog load;
    int roundCount = 0;
    Globals sharedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle);

        sharedData = Globals.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        try{
            sharedData = Globals.getInstance();
            TextView attributeTitle = (TextView) findViewById(R.id.attributeTitle);
            attributeTitle.setText(sharedData.getChoosenAttribute());

            BattleCards battle = sharedData.getBattle();
            Card user = battle.getUser();
            Card oponent = battle.getOponent();

            plotBattle(user, oponent, attributeTitle.getText().toString());

            verifyWhoWins(user, oponent, attributeTitle.getText().toString());
        } catch (Exception e){
            Exception a = e;
        }
    }

    private void verifyWhoWins(Card user, Card oponent, String attribute) {
        int attributeCode = Utils.AttributeStringToNumber(attribute);
        boolean won = false;

        switch (attributeCode){
            case 1: won = Integer.parseInt(user.getIntelligence()) > Integer.parseInt(oponent.getIntelligence());
                break;

            case 2: won = Integer.parseInt(user.getDurability()) > Integer.parseInt(oponent.getDurability());
                break;

            case 3: won = Integer.parseInt(user.getStrength()) > Integer.parseInt(oponent.getStrength());
                break;

            case 4: won = Integer.parseInt(user.getCombat()) > Integer.parseInt(oponent.getCombat());
                break;

            case 5: won = Integer.parseInt(user.getSpeed()) > Integer.parseInt(oponent.getSpeed());
                break;

            case 6: won = Integer.parseInt(user.getPower()) > Integer.parseInt(oponent.getPower());
                break;
        }

        winAnimation(won);


        win(won);
    }

    private void win(boolean win) {
        if(win){
            sharedData.roundWon();
        }

        Handler handler = new Handler();

        if(sharedData.getRoundQuantity() <= sharedData.getActualRound()) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            load.dismiss();
                            //joga pra tela de acabado o jogo
                            Intent finishedBattleScreen = new Intent(Battle.this, FinishedBattle.class);
                            startActivity(finishedBattleScreen);
                            return;
                        }
                    }, 2000);

                    load = ProgressDialog.show(Battle.this,
                            "Por favor Aguarde ...", "Finalizando partida...");
                }
            }, 6000);

            return;
        }

        handler.postDelayed(new Runnable() {
            public void run() {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        load.dismiss();
                        Intent chooseCardScreen = new Intent(Battle.this, ChooseCards.class);
                        startActivity(chooseCardScreen);
                    }
                }, 2000);

                load = ProgressDialog.show(Battle.this,
                        "Por favor Aguarde ...", "Avançando para próxima rodada...");
            }
        }, 10000);

    }

    private void winAnimation(boolean win) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                LinearLayout userArea = (LinearLayout) findViewById(R.id.userLayout);
                LinearLayout oponentArea = (LinearLayout) findViewById(R.id.oponentLayout);
                TextView attributeTitle = (TextView) findViewById(R.id.attributeTitle);

                if(win){
                    oponentArea.animate().alpha(0.0f).setDuration(2000);
                    oponentArea.setVisibility(View.GONE);
                    attributeTitle.setText("VOCÊ GANHOU");
                }
                else{
                    userArea.animate().alpha(0.0f).setDuration(2000);
                    userArea.setVisibility(View.GONE);
                    attributeTitle.setText("VOCÊ PERDEU");
                }
            }
        }, 4000);

        //criar animação pra mostrar que ganhou
    }

    private void plotBattle(Card user, Card oponent, String attribute) {

        TextView attributeUser = (TextView) findViewById(R.id.winLostTitle);
        TextView attributeOponent = (TextView) findViewById(R.id.oponentAttributeBattle);

        if(attribute.equals("Inteligência")){
            attributeUser.setText(user.getIntelligence());
            attributeOponent.setText(oponent.getIntelligence());
        } else if (attribute.equals("Resistência")) {
            attributeUser.setText(user.getDurability());
            attributeOponent.setText(oponent.getDurability());
        } else if (attribute.equals("Força")) {
            attributeUser.setText(user.getStrength());
            attributeOponent.setText(oponent.getStrength());
        } else if (attribute.equals("Combate")) {
            attributeUser.setText(user.getCombat());
            attributeOponent.setText(oponent.getCombat());
        } else if (attribute.equals("Velocidade")) {
            attributeUser.setText(user.getSpeed());
            attributeOponent.setText(oponent.getSpeed());
        } else if (attribute.equals("Poder")) {
            attributeUser.setText(user.getPower());
            attributeOponent.setText(oponent.getPower());
        }

        CardView userArea = (CardView) findViewById(R.id.userAreaCard);
        CardView oponentArea = (CardView) findViewById(R.id.oponentAreaCard);

        plotCard(user, userArea);
        plotCard(oponent, oponentArea);
    }

    private void plotCard(Card card, CardView cardView) {

        View content = cardView.getChildAt(0);
        ArrayList<View> listContent = getAllChildren(content);

        TextView heroName = (TextView) listContent.get(1);
        AppCompatImageView heroImage = (AppCompatImageView) listContent.get(4);
        TextView intelligence = (TextView) listContent.get(11);
        TextView durability = (TextView) listContent.get(16);
        TextView strength = (TextView) listContent.get(21);
        TextView combat = (TextView) listContent.get(26);
        TextView speed = (TextView) listContent.get(31);
        TextView power = (TextView) listContent.get(36);
        TextView occupation = (TextView) listContent.get(38);

        heroName.setText(card.getName());
        intelligence.setText(card.getIntelligence());
        durability.setText(card.getDurability());
        strength.setText(card.getStrength());
        combat.setText(card.getCombat());
        speed.setText(card.getSpeed());
        power.setText(card.getPower());
        occupation.setText(card.getOccupation());

        Picasso.get()
                .load(card.getImage())
                .placeholder(R.drawable.loading)
                .error(R.drawable.erro)
                .into(heroImage);
    }

    private ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {

            View child = vg.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }

}