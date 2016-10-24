package com.wizeline.chess;

import java.util.HashMap;

/**
 * Created by Home on 10/24/16.
 */
public class MoveSimulator {

    static final int ROWS = 8;
    static final int COLS = 8;

    public static HashMap<String, String> simulateMove(HashMap<String, String> pieces, String startCell, String endCell) {

        HashMap<String,String> newPieces = new HashMap<String, String>(pieces);


        String piece = pieces.get(startCell);

        newPieces.remove(startCell);

        newPieces.put(endCell, piece);

        return newPieces;
    }

    private static String getKingPosition(HashMap<String, String> pieces, char kingColor) {
        String king = "";
        king += kingColor;
        king += 'K';

        for(HashMap.Entry<String, String> piece : pieces.entrySet()) {
            if(king.equals(piece.getValue()))
                return piece.getKey();
        }

        return null;
    }


    public static boolean isKingChecked(HashMap<String, String> pieces, char kingColor) {
        String kingPosition = getKingPosition(pieces, kingColor);

        for(HashMap.Entry<String, String> piece : pieces.entrySet()) {
            if(piece.getValue().charAt(0) != kingColor) {

                String move = piece.getKey() + " " + kingPosition;

                if(new MoveValidator(move, pieces).validMove(piece.getValue().charAt(0), false))
                    return true;
            }
        }

        return false;
    }


    private static boolean willPreventCheck(HashMap<String, String> pieces, char kingColor, String position) {
        for(HashMap.Entry<String, String> piece : pieces.entrySet()) {
            if(piece.getValue().charAt(0) == kingColor) {

                String move = piece.getKey() + " " + position;

                if(new MoveValidator(move, pieces).validMove(kingColor, true))
                    return true;
            }
        }
        return false;
    }

    public static boolean checkMate(HashMap<String, String> pieces, char kingColor) {
        if(!isKingChecked(pieces, kingColor))
            return false;

        String kingPosition = getKingPosition(pieces, kingColor);

        for(int i = 0; i < ROWS; ++i) {
            for(int j = 0; j < COLS; ++j) {
                String chessNotation = NotationConverter.convertMatrixToChess(new int[]{i, j});

                if(willPreventCheck(pieces, kingColor, chessNotation))
                    return false;
            }
        }

        return true;

    }
}
