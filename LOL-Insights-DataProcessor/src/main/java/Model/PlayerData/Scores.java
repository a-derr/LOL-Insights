package Model.PlayerData;

public class Scores {
    int assists;
    int creepScore;
    int deaths;
    int kills;
    float wardScore;

    public int getAssists() {
        return assists;
    }
    private void setAssists(int assists) {
        this.assists = assists;
    }

    public int getCreepScore() {
        return creepScore;
    }
    private void setCreepScore(int creepScore) {
        this.creepScore = creepScore;
    }

    public int getDeaths() {
        return deaths;
    }
    private void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getKills() {
        return kills;
    }
    private void setKills(int kills) {
        this.kills = kills;
    }

    public float getWardScore() {
        return wardScore;
    }
    private void setWardScore(float wardScore) {
        this.wardScore = wardScore;
    }


}
