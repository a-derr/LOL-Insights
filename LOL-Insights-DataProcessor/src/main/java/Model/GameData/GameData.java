package Model.GameData;

public class GameData {
    private String gameMode;
    private float gameTime;
    private String mapName;

    public String getGameMode() {
        return gameMode;
    }
    private void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public float getGameTime() {
        return gameTime;
    }
    private void setGameTime(float gameTime) {
        this.gameTime = gameTime;
    }

    public String getMapName() {
        return mapName;
    }
    private void setMapName(String mapName) {
        this.mapName = mapName;
    }
}
