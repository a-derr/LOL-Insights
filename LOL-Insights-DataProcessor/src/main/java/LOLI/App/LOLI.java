package LOLI.App;

import Model.Game;
import Model.PlayerData.Player;

public class LOLI {
    public static Game currGame;
    public static Player activePlayer;
    public static API_Client apiClient;

    public static void main(String[] args) {

    }

    public LOLI(){
        activePlayer = new Player();
        currGame = new Game();

    }

    public Player getActivePlayer() {
        return activePlayer;
    }
    private void setActivePlayerData() {

    }
}
