package com.example.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;


public class ChooseCards extends AppCompatActivity {
    int quantity = 0;
    private ProgressDialog load;
    int roundCount = 0;
    Globals sharedData = Globals.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_card);

        Bundle extras = getIntent().getExtras();
        if (extras != null || (sharedData != null && sharedData.getRoundQuantity() > 0)) {
            if(extras != null)
                quantity = extras.getInt("quantity");

            if(sharedData.getRoundQuantity() > 0)
                quantity = sharedData.getRoundQuantity();

            sharedData.updateRoundTurn();
            GameCards game = sharedData.getGame();

            randomAttribute();
            TextView attribute = (TextView) findViewById(R.id.attribute);
            attribute.setText(sharedData.getChoosenAttribute());

            sharedData.setRoundQuantity(quantity);

            TextView rounds = (TextView) findViewById(R.id.round);
            rounds.setText("RODADA " + sharedData.getActualRound() + "/" + sharedData.getRoundQuantity());

            int counter = 1;
            for (Card card: game.user) {
                plotCard(card, counter);
                counter++;
            }
        }



    }

    private void randomAttribute() {
        Random rand = new Random();
        int randomNum = rand.nextInt((6 - 1) + 1) + 1;

        if(randomNum == 1) sharedData.setChoosenAttribute("Inteligência");
        if(randomNum == 2) sharedData.setChoosenAttribute("Resistência");
        if(randomNum == 3) sharedData.setChoosenAttribute("Força");
        if(randomNum == 4) sharedData.setChoosenAttribute("Combate");
        if(randomNum == 5) sharedData.setChoosenAttribute("Velocidade");
        if(randomNum == 6) sharedData.setChoosenAttribute("Poder");
    }


    private void plotCard(Card card, int cardPosition) {
        CardView cardView;
        switch (cardPosition){
            case 1: cardView = (CardView) findViewById(R.id.card1);
                break;
            case 2: cardView = (CardView) findViewById(R.id.card2);
                break;

            case 3: cardView = (CardView) findViewById(R.id.card3);
                break;

            case 4: cardView = (CardView) findViewById(R.id.card4);
                break;
            case 5: cardView = (CardView) findViewById(R.id.card5);
                break;
            default:
                return;
        }

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
        return;
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

    public void alert(View v){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Deseja jogar esta carta?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        playCard(v);
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Não",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void transferCard(View view) {
        int id = view.getId();
        CardView cardView = (CardView) findViewById(id);
        ((LinearLayout) cardView.getParent()).removeView(cardView);

        CardView subsituibleArea = (CardView) findViewById(R.id.SubsituteArea);

        View subsituibleAreaChild = subsituibleArea.getChildAt(0);
        ((CardView) subsituibleAreaChild.getParent()).removeView(subsituibleAreaChild);


        cardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        subsituibleArea.addView(cardView);
    }

    private Card getRandomOponentCard() {
        Random rand = new Random();
        int randomNum = rand.nextInt((4 - 0) + 1) + 0;

        return sharedData.getGame().oponent.get(randomNum);
    }

    private Card FindCardByName(String name, ArrayList<Card> cards){
        for(int i=0; i < cards.size(); i++) {
            String s = cards.get(i).getName();
            //search the string
            if(name.equals(s)) {
                return cards.get(i);
            }
        }
        return null;
    }

    private Card getUserCard(View view) {
        int id = view.getId();
        CardView cardView = (CardView) findViewById(id);

        View content = cardView.getChildAt(0);
        ArrayList<View> listContent = getAllChildren(content);

        TextView heroNameView = (TextView) listContent.get(1);
        String heroName = heroNameView.getText().toString();

        return FindCardByName(heroName, sharedData.getGame().user);
    }

    private void playCard(View view) {
        Card user = getUserCard(view);
        Card oponent = getRandomOponentCard();
        transferCard(view);

        sharedData.setBattle(oponent, user);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        load.dismiss();
                        Intent battle = new Intent(ChooseCards.this, Battle.class);
                        startActivity(battle);
                    }
                }, 2000);

                load = ProgressDialog.show(ChooseCards.this,
                        "Por favor Aguarde ...", "Iniciando a batalha...");
            }
        }, 3000);
    }

}