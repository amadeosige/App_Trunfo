package com.example.myapplication;

public class Globals {

    private static Globals instance = new Globals();

    // Getter-Setters
    public static Globals getInstance() {
        return instance;
    }

    public static void setInstance(Globals instance) {
        Globals.instance = instance;
    }

    private String userName;
    private String userImage;
    private GameCards game;
    private String choosenAttribute;
    private int roundQuantity;
    private int actualRound = 0;
    private int roundsWon = 0;
    private BattleCards battle;

    private Globals() {

    }

    public void resetGameGlobals(){
        this.game = null;
        this.roundQuantity = 0;
        this.actualRound = 0;
        this.roundsWon = 0;
        this.battle = null;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public GameCards getGame() {
        return game;
    }

    public void setGame(GameCards game) {
        this.game = game;
    }

    public String getChoosenAttribute() {
        return choosenAttribute;
    }

    public void setChoosenAttribute(String choosenAttribute) {
        this.choosenAttribute = choosenAttribute;
    }

    public int getActualRound() {
        return actualRound;
    }

    public int getRoundQuantity() {
        return roundQuantity;
    }

    public void setRoundQuantity(int roundQuantity) {
        this.roundQuantity = roundQuantity;
    }

    public void updateRoundTurn() {
        this.actualRound++;
    }

    public BattleCards getBattle() {
        return battle;
    }

    public Card getUserBattleCard() {
        return battle.getUser();
    }

    public Card getOponentBattleCard() {
        return battle.getOponent();
    }

    public void setBattle(Card oponent, Card user) {
        this.battle = new BattleCards(oponent, user);
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public void roundWon() {
        this.roundsWon++;
    }
}
