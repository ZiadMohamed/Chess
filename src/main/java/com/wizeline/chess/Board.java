package com.wizeline.chess;
import org.fusesource.jansi.Ansi;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;
import org.fusesource.jansi.AnsiConsole;
import java.util.Map;

public class Board {

    static final int ROWS = 8;
    static final int COLS = 8;
    static final String WHITE_PIECE = "w";
    static final String BLACK_PIECE = "b";
    static final String[] COLUMN_NAMES = new String[] {"a", "b", "c", "d", "e", "f", "g", "h"};

    public Board() {
        AnsiConsole.systemInstall();
    }

    public void draw(Map<String, String> pieces) {
        System.out.println( ansi().eraseScreen() );
        for (int row = 0; row < ROWS; row++) {
            System.out.print(ROWS - row);
            for (int col = 0; col < COLS; col++) {
                drawCell(row, col, pieces.get(getChessNotation(row, col)));
            }
            System.out.println();
        }
        System.out.print(" ");
        for (int col = 0; col < COLS; col++) {
            System.out.print(getColumnChessNotation(col));
        }
        System.out.println();
    }

    private void drawCell(int row, int col, String piece) {
        Ansi cell = ansi();
        
        if ((row + col) % 2 == 0) {
            cell = cell.bg(GREEN);
        } else {
            cell = cell.bg(WHITE);
        }


        if (WHITE_PIECE.equals(getPieceColor(piece))) {
            cell = cell.fg(MAGENTA);
        } else {
            cell = cell.fg(BLACK);
        }

        if (piece == null) {
            System.out.print(cell.a(" ").reset());
        } else {
            System.out.print(cell.bold().a(getPieceType(piece)).reset());
        }
    }

    private String getPieceColor(String piece) {
        if (piece == null) {
            return null;
        }
        return piece.substring(0, 1);
    }

    private String getPieceType(String piece) {
        if (piece == null) {
            return null;
        }
        return piece.substring(1);
    }

    private String getColumnChessNotation(int col) {
        return COLUMN_NAMES[col];
    }

    private String getChessNotation(int row, int col) {
        return getColumnChessNotation(col) + Integer.toString(ROWS - row);
    }

}
