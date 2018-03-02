package ZombieGame;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;
import java.util.List;

public class Board {

    public void createBoard(Terminal terminal) {

        char[][] battleBoard = new char[100][30];
        for (int i = 0; i < battleBoard.length; i++) {
            for (int j = 0; j < battleBoard.length; j++) {
                terminal.moveCursor(i, j);
                if (j % 2 == 1) {
                    terminal.applyBackgroundColor(Terminal.Color.BLACK);
                } else if (j % 2 == 0) {
                    terminal.applyBackgroundColor(Terminal.Color.YELLOW);
                }
                if (i == 0 && !(j == 0))
                    terminal.putCharacter(' ');
                else if (j == 0 && !(i == 0))
                    terminal.putCharacter(' ');
                else if (j == battleBoard.length - 1 && !(i == 0))
                    terminal.putCharacter(' ');
                else if (i == battleBoard.length - 1 && !(j == 0))
                    terminal.putCharacter(' ');
            }
            terminal.setCursorVisible(false);
        }
    }


    private static void printText(int x, int y, String message, Terminal terminal) {
        for (int i = 0; i < message.length(); i++) {
            terminal.moveCursor(x, y);
            terminal.putCharacter(message.charAt(i));
            x += 1;
        }
    }

}