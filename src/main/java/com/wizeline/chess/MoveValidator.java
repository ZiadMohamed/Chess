package com.wizeline.chess;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Home on 10/24/16.
 */
public class MoveValidator {

    private Map<String, String> pieces;
    private String move;

    public MoveValidator() {
        pieces = new HashMap<String, String>();
        move = "";
    }

    public MoveValidator(String move, Map<String, String> pieces) {
        this.move = move;
        this.pieces = pieces;
    }



    public boolean validMove() {
        return true;
    }


    private boolean validKnightMove(int strtX, int strtY, int endX, int endY, boolean white) {
        int dirX[] = {2, 2, -2, -2, 1, -1, 1, -1};
        int dirY[] = {1, -1, 1, -1, 2, 2, -2, -2};

        for(int i = 0; i < 8; ++i) {
            if(strtX + dirX[i] == endX && strtY + dirY[i] == endY) {

                break;
            }
        }

        return false;
    }
}
