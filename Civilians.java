package ZombieGame;

import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Civilians {
    int civilianXPos;
    int civilianYPos;
    double speed;
    char civilianLook;

// TODO: set start value random x/y

    public Civilians(int civilianXPos, int civilianYPos, double speed, char civilianLook) {
        this.civilianXPos = civilianXPos;
        this.civilianYPos = civilianYPos;
        this.speed = speed;
        this.civilianLook = civilianLook;
    }

    public int getCivilianXPos() {
        return civilianXPos;
    }

    public void setCivilianXPos(int civilianXPos) {
        this.civilianXPos = civilianXPos;
    }

    public int getCivilianYPos() {
        return civilianYPos;
    }

    public void setCivilianYPos(int civilianYPos) {
        this.civilianYPos = civilianYPos;
    }

    public double getSpeed() {

        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public char getCivilianLook() {
        return civilianLook;
    }

    public void setCivilianLook(char civilianLook) {
        this.civilianLook = civilianLook;
    }
    public static void moveCivilians(List<Civilians> civiliansList, Terminal terminal) {
        List<Civilians> civiliansOnfield = new ArrayList<>();
        for (Civilians civilians : civiliansList) {
            terminal.moveCursor(civilians.getCivilianXPos(), civilians.getCivilianYPos());
            terminal.applyBackgroundColor(Terminal.Color.BLACK);
            terminal.putCharacter(' ');
            civilians.setCivilianXPos(civilians.getCivilianXPos() - 1);
            terminal.moveCursor(civilians.getCivilianXPos(), civilians.getCivilianYPos());
            terminal.applyBackgroundColor(Terminal.Color.GREEN);
            terminal.putCharacter(civilians.getCivilianLook());
        }
    }

}
