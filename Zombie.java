package ZombieGame;

import com.googlecode.lanterna.terminal.Terminal;

import java.util.List;

public class Zombie {
    int zombieXPos;
    int zombieYPos;



    public Zombie(int zombieXPos, int zombieYPos) {
        this.zombieXPos = zombieXPos;
        this.zombieYPos = zombieYPos;

    }

    public int getZombieXPos() {
        return zombieXPos;
    }

    public void setZombieXPos(int zombieXPos) {
        this.zombieXPos = zombieXPos;
    }

    public int getZombieYPos() {
        return zombieYPos;
    }

    public void setZombieYPos(int zombieYPos) {
        this.zombieYPos = zombieYPos;
    }

    public static void moveZombies(List<Zombie> zombieList, List<Civilians> civilians, Terminal terminal) {
        for (Zombie zombie : zombieList) {
            Civilians closestYCivilian = civilians.get(0);
            Civilians closestXCivilian = civilians.get(0);
            for (Civilians civilian : civilians) {
                if ((zombie.getZombieYPos() - civilian.getCivilianYPos()) < (zombie.getZombieYPos() - closestYCivilian.getCivilianYPos()))
                    closestYCivilian = civilian;
                if ((zombie.getZombieXPos() - civilian.getCivilianXPos()) < (zombie.getZombieXPos() - closestXCivilian.getCivilianYPos()))

                    closestXCivilian = civilian;
            }

            if (zombie.getZombieYPos() != closestYCivilian.getCivilianYPos()) {

                terminal.moveCursor(zombie.getZombieXPos(), zombie.getZombieYPos());
                terminal.applyBackgroundColor(Terminal.Color.BLACK);
                terminal.putCharacter(' ');
                int dx = zombie.getZombieXPos() - closestYCivilian.getCivilianXPos();
                int dy = zombie.getZombieYPos() - closestYCivilian.getCivilianYPos();
                if (dy < 0)
                    zombie.setZombieYPos(zombie.getZombieYPos() + 1);
                else if (dy > 0)
                    zombie.setZombieYPos(zombie.getZombieYPos() - 1);

                else
                    zombie.setZombieYPos(zombie.getZombieYPos());
                terminal.moveCursor(zombie.getZombieXPos(), zombie.getZombieYPos());
                terminal.applyBackgroundColor(Terminal.Color.RED);
                terminal.putCharacter(' ');
            }


            if (zombie.getZombieXPos() != closestYCivilian.getCivilianXPos()) {
                terminal.moveCursor(zombie.getZombieXPos(), zombie.getZombieYPos());
                terminal.applyBackgroundColor(Terminal.Color.BLACK);
                terminal.putCharacter(' ');
                int dx = zombie.getZombieXPos() - closestYCivilian.getCivilianXPos();
                int dy = zombie.getZombieYPos() - closestYCivilian.getCivilianYPos();
                if (dx < 0)
                    zombie.setZombieXPos(zombie.getZombieXPos() + 1);
                else if (dx > 0)
                    zombie.setZombieXPos(zombie.getZombieXPos() - 1);
                else
                    zombie.setZombieXPos(zombie.getZombieXPos());
                terminal.applyBackgroundColor(Terminal.Color.RED);
                terminal.moveCursor(zombie.getZombieXPos(), zombie.getZombieYPos());
                terminal.putCharacter(' ');

            }

        }
    }

}