package com.wizeline.chess;

/**
 * Created by Home on 10/24/16.
 */
public class NotationConverter {

    static final int ROWS = 8;
    static final int COLS = 8;

    public static int[] convertChessToMatrix(String position) {
        int row = ROWS - Integer.parseInt(position.substring(1));
        int col = Integer.parseInt(position.substring(0, 1)) - 97;

        return new int[]{row, col};
    }


    public static String convertMatrixToChess(int[] position) {
        String ret = "";
        ret += (char)(ROWS - position[0] + '0');
        ret += (char)(position[1] + 'a');

        return ret;
    }
}
