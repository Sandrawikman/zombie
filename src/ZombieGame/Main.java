package ZombieGame;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import javax.swing.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) throws Exception {
        Player player = new Player(95, 15, 'O');
        List<Civilians> civiliansList = new ArrayList<>();
        civiliansList.add(new Civilians(98, 15, -0.5, 'C'));
        List<Zombie> zombieList = new ArrayList<>(10);
        //OVER
        zombieList.add(new Zombie(98, -1));
        zombieList.add(new Zombie(95, -5));
        zombieList.add(new Zombie(85, -10));
        zombieList.add(new Zombie(70, -15));
        zombieList.add(new Zombie(77, -17));
        zombieList.add(new Zombie(70, -29));
        zombieList.add(new Zombie(69, -27));
        zombieList.add(new Zombie(58, -40));
        zombieList.add(new Zombie(48, -50));
        zombieList.add(new Zombie(45, -60));
        zombieList.add(new Zombie(42, -62));
        zombieList.add(new Zombie(30, -66));
        zombieList.add(new Zombie(24, -70));
        zombieList.add(new Zombie(21, -73));
        zombieList.add(new Zombie(19, -77));
        zombieList.add(new Zombie(15, -80));
        zombieList.add(new Zombie(10, -83));
        zombieList.add(new Zombie(45, -87));
        zombieList.add(new Zombie(42, -91));
        zombieList.add(new Zombie(30, -95));
        zombieList.add(new Zombie(24, -97));
        zombieList.add(new Zombie(21, -99));
        //LOWER
        zombieList.add(new Zombie(100, 30));
        zombieList.add(new Zombie(95, 32));
        zombieList.add(new Zombie(90, 36));
        zombieList.add(new Zombie(85, 40));
        zombieList.add(new Zombie(80, 42));
        zombieList.add(new Zombie(75, 47));
        zombieList.add(new Zombie(70, 49));
        zombieList.add(new Zombie(65, 55));
        zombieList.add(new Zombie(60, 58));
        zombieList.add(new Zombie(55, 62));
        zombieList.add(new Zombie(50, 65));
        zombieList.add(new Zombie(45, 70));
        zombieList.add(new Zombie(40, 75));
        zombieList.add(new Zombie(35, 78));
        zombieList.add(new Zombie(30, 81));
        zombieList.add(new Zombie(25, 85));
        zombieList.add(new Zombie(20, 88));
        zombieList.add(new Zombie(15, 90));
        zombieList.add(new Zombie(10, 92));
        zombieList.add(new Zombie(5, 93));
        zombieList.add(new Zombie(1, 96));
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        terminal.applyBackgroundColor(51, 0, 0);
        terminal.applyForegroundColor(180, 180, 180);
        terminal.setCursorVisible(false);
        System.out.println(terminal.getTerminalSize());
        Board board = new Board();
        board.createBoard(terminal);
        String startMessage = "******** ZOMBIE SLAUGTHER ********";
        String instructions = "Escort the Civilian to the Safe Zone on the left side. Kill the zombies!";
        String start = "Press any key to Start the game";
        terminal.moveCursor(30, 10);
        for (int i = 0; i < startMessage.length(); i++) {
            terminal.putCharacter(startMessage.charAt(i));
        }
        terminal.moveCursor(10, 15);
        for (int i = 0; i < instructions.length(); i++) {
            terminal.putCharacter(instructions.charAt(i));
        }
        terminal.moveCursor(30, 20);
        for (int i = 0; i < start.length(); i++) {
            terminal.putCharacter(start.charAt(i));
        }
        Key key=null;
        while (key==null){
            key=terminal.readInput();
        }
        terminal.clearScreen();
        board.createBoard(terminal);
        int counter = 0;
        while (!gameOver(zombieList, civiliansList)) {
            readInputAndMovePlayer(player, terminal);
            Player.playerKills(zombieList, player);
            if (counter % 130 == 0) {
                Civilians.moveCivilians(civiliansList, terminal);
                Zombie.moveZombies(zombieList, civiliansList, terminal);
                System.out.println("before player kills " + zombieList.size());
                System.out.println("after player kills " + zombieList.size());
                System.out.println();
                counter = 0;
            }
            Thread.sleep(5);
            counter++;
        }
    }
    private static void readInputAndMovePlayer(Player player, Terminal terminal) {
        player.removePlayerTail();
        Key key = terminal.readInput();
        if (key == null) {
            return;
        }
        switch (key.getKind()) {
            case ArrowDown:
                player.setPlayerYPos(player.getPlayerYPos() + player.playerSpeed);
                if (player.isPositionIncorrect()) {
                    player.setPlayerYPos(player.getPlayerYPos() - player.playerSpeed);
                }
                Player.moveChar(player, terminal);
                break;
            case ArrowUp:
                player.setPlayerYPos(player.getPlayerYPos() - player.playerSpeed);
                if (player.isPositionIncorrect()) {
                    player.setPlayerYPos(player.getPlayerYPos() + player.playerSpeed);
                }
                Player.moveChar(player, terminal);
                break;
            case ArrowLeft:
                player.setPlayerXPos(player.getPlayerXPos() - player.playerSpeed);
                if (player.isPositionIncorrect()) {
                    player.setPlayerXPos(player.getPlayerXPos() + player.playerSpeed);
                }
                Player.moveChar(player, terminal);
                break;
            case ArrowRight:
                player.setPlayerXPos(player.getPlayerXPos() + player.playerSpeed);
                if (player.isPositionIncorrect()) {
                    player.setPlayerXPos(player.getPlayerXPos() - player.playerSpeed);
                }
                Player.moveChar(player, terminal);
                break;
        }
    }
    public static boolean gameOver(List<Zombie> zombieList, List<Civilians> civilians) {
        if (civilians.get(0).civilianXPos == 0) {
            JOptionPane.showMessageDialog(null,"Congratulations, the civilian made it to the safe-zone. You have won!");
            return true;
        }
        for (Zombie zombie : zombieList) {
            if (zombie.getZombieXPos() == civilians.get(0).getCivilianXPos() && zombie.getZombieYPos() == civilians.get(0).getCivilianYPos()) {
                JOptionPane.showMessageDialog(null, "The civilian has been infected. Game over!");
                return true;
            }
        }
        return false;
    }
}