package Model.PlayerData;

import Model.RuneData.Runes;

public class Player {
    private String championName;
    private Boolean isBot;
    private Boolean isDead;
    private int level;
    private String position;
    private float respawnTimer;

    // Runes
    private Runes runes;
    // Items
    private Item[] items;
    // Scores
    private Scores scores;
    // Team
    private TEAM team;

    protected enum TEAM{
        CHAOS,
        ORDER
    };

    public String get_championName(){
        return championName;
    }
    private void set_championName(String championName){
        this.championName = championName;
    }

    public Boolean get_isBot(){
        return isBot;
    }
    private void set_isBot(Boolean isBot){
        this.isBot = isBot;
    }

    public Boolean get_isDead(){
        return isDead;
    }
    private void set_isDead(Boolean isDead){
        this.isDead = isDead;
    }

    public int get_level(){
        return level;
    }
    private void set_level(int level){
        this.level = level;
    }

    public String get_position(){
        return position;
    }
    private void set_position(String position){
        this.position = position;
    }

    public float get_respawnTimer(){
        return respawnTimer;
    }
    private void set_respawnTimer(float respawnTimer){
        this.respawnTimer = respawnTimer;
    }

    public Runes get_runes(){
        return runes;
    }
    private void set_runes(Runes runes){
        this.runes = runes;
    }

    public Item[] get_items(){
        return items;
    }
    private void set_items(Item[] items){
        this.items = items;
    }

    public Scores get_scores(){
        return scores;
    }
    private void set_scores(Scores scores){
        this.scores = scores;
    }

    public TEAM get_team(){
        return team;
    }
    private void set_team(TEAM team){
        this.team = team;
    }


}
