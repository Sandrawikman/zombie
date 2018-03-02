package ZombieGame;

import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;

public class Player {

    public int playerXPos;
    public int playerYPos;
    public int playerOldXPos;
    public int playerOldYPos;
    public char playerLook = 'S';
    public int playerSpeed = 1;

    //   private int spawnPlayer = playerXPos + playerYPos;

    //Constructor
    public Player(int playerXPos, int playerYPos, char playerLook) {
        this.playerXPos = playerXPos;
        this.playerYPos = playerYPos;
        this.playerLook = playerLook;
    }

    public void removePlayerTail() {
        playerOldXPos = playerXPos;
        playerOldYPos = playerYPos;
    }

    //Getters
    public int getPlayerXPos() {
        return playerXPos;
    }

    public int getPlayerYPos() {
        return playerYPos;
    }

    public char getPlayerLook() {
        return playerLook;
    }

    //Setters
    public void setPlayerXPos(int playerXPos) {
        this.playerXPos = playerXPos;
    }

    public void setPlayerYPos(int playerYPos) {
        this.playerYPos = playerYPos;
    }

    public void setPlayerLook(char playerLook) {
        this.playerLook = playerLook;
    }

    public boolean isPositionIncorrect() {
        if (getPlayerYPos() <= 0 || getPlayerYPos() >= 29 || getPlayerXPos() <= 0 || getPlayerXPos() >= 99) {
            return true;
        }
        return false;
    }

    public static void moveChar(Player player, Terminal terminal) {
        terminal.applyBackgroundColor(Terminal.Color.BLACK);
        terminal.moveCursor(player.playerOldXPos, player.playerOldYPos);
        terminal.putCharacter(' ');
        terminal.moveCursor(player.getPlayerXPos(), player.getPlayerYPos());
        terminal.applyBackgroundColor(Terminal.Color.BLUE);
        terminal.putCharacter('\u2639');

    }

    public static void playerKills(List<Zombie> zombieList, Player player) {
        List<Zombie> deadZombies = new ArrayList<>();
        List<Zombie> newSpawned = new ArrayList<>();
        int zombieSpawnXpos=50;
        for (Zombie zombie : zombieList) {
            if (zombie.getZombieXPos() == player.getPlayerXPos() && zombie.getZombieYPos() == player.getPlayerYPos()) {
                deadZombies.add(zombie);

            }


        }
        zombieList.removeAll(deadZombies);

    }
}