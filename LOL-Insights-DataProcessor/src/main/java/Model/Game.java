package Model;

import Model.GameData.GameEvent;
import Model.GameData.GameData;
import Model.PlayerData.Player;

public class Game {
    private GameEvent[] events;
    private GameData gameData;
    private Player[] allPlayers;


    public GameEvent[] getEvents() {
        return events;
    }
    private void setEvents(GameEvent[] events) {
        this.events = events;
    }

    public GameData getGameData() {
        return gameData;
    }
    private void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public Player[] getPlayers() {
        return allPlayers;
    }
    private void setPlayers(Player[] players) {
        this.allPlayers = players;
    }


}
