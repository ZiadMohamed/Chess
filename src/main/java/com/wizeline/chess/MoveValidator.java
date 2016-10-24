package com.wizeline.chess;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Home on 10/24/16.
 */
public class MoveValidator {

    static final int ROWS = 8;
    static final int COLS = 8;
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
        if(!validMoveCommand())
            return false;

        String cell1 = move.substring(0, 2), cell2 = move.substring(3, 5);

        if(!pieces.containsKey(cell1))
            return false;

        int[] strt = NotationConverter.convertChessToMatrix(cell1);
        int[] target = NotationConverter.convertChessToMatrix(cell2);


        if(!insideBoard(strt[0], strt[1]) || !insideBoard(target[0], target[1]))
            return false;


        String pieceToMove = pieces.get(cell1);

        char pieceType = pieceToMove.charAt(1);
        char pieceColor = pieceToMove.charAt(0);


        boolean validMovement = false;
        if(pieceType == 'K') {
            validMovement = validKingMove(strt[0], strt[1], target[0], target[1], pieceColor);
        }
        else if(pieceType == 'Q') {
            validMovement = validQueenMove(strt[0], strt[1], target[0], target[1], pieceColor);
        }
        else if(pieceType == 'N') {
            validMovement = validKnightMove(strt[0], strt[1], target[0], target[1], pieceColor);
        }
        else if(pieceType == 'B') {
            validMovement = validBishopMove(strt[0], strt[1], target[0], target[1], pieceColor);
        }
        else if(pieceType == 'R') {
            validMovement = validRookMove(strt[0], strt[1], target[0], target[1], pieceColor);
        }
        else {
            validMovement = validPawnMove(strt[0], strt[1], target[0], target[1], pieceColor);
        }

        return validMovement;
    }

    public boolean validMoveCommand() {
        if(move.length() != 5)
            return false;

        return (Character.isLetter(move.charAt(0)) &&
        Character.isDigit(move.charAt(1)) &&
        Character.isLetter(move.charAt(3)) &&
        Character.isDigit(move.charAt(4)));
    }

    private boolean insideBoard(int x, int y) {
        return (x >= 0 && x < ROWS && y >= 0 && y < COLS);
    }

    private boolean canStandOn(int x, int y, char myColor) {
        String position = NotationConverter.convertMatrixToChess(new int[]{x, y});

        return (!pieces.containsKey(position) || pieces.get(position).charAt(0) != myColor);
    }

    private boolean validKnightMove(int strtX, int strtY, int endX, int endY, char color) {
        int dirX[] = {2, 2, -2, -2, 1, -1, 1, -1};
        int dirY[] = {1, -1, 1, -1, 2, 2, -2, -2};

        for(int i = 0; i < 8; ++i) {
            if(strtX + dirX[i] == endX && strtY + dirY[i] == endY) {

                String position = NotationConverter.convertMatrixToChess(new int[]{endX, endY});

                if(canStandOn(endX, endY, color))
                    return true;

                break;
            }
        }

        return false;
    }


    private boolean validRookMove(int strtX, int strtY, int endX, int endY, char color) {
        int dirX[] = {1, 0, 0, -1};
        int dirY[] = {0, 1, -1, 0};

        for(int i = 0; i < 4; ++i) {
            int curX = strtX + dirX[i], curY = strtY + dirY[i];

            while(insideBoard(curX, curY)) {

                if(curX == endX && curY == endY)
                    return canStandOn(endX, endY, color);


                else {
                    String position = NotationConverter.convertMatrixToChess(new int[]{curX, curY});
                    if(pieces.containsKey(position))
                        break;

                }

                curX += dirX[i];
                curY += dirY[i];
            }
        }

        return false;
    }

    private boolean validBishopMove(int strtX, int strtY, int endX, int endY, char color) {
        int dirX[] = {1, 1, -1, -1};
        int dirY[] = {1, -1, 1, -1};

        for(int i = 0; i < 4; ++i) {
            int curX = strtX + dirX[i], curY = strtY + dirY[i];

            while(insideBoard(curX, curY)) {

                if(curX == endX && curY == endY)
                    return canStandOn(endX, endY, color);


                else {
                    String position = NotationConverter.convertMatrixToChess(new int[]{curX, curY});
                    if(pieces.containsKey(position))
                        break;

                }

                curX += dirX[i];
                curY += dirY[i];
            }
        }

        return false;
    }

    private boolean validQueenMove(int strtX, int strtY, int endX, int endY, char color) {
        int dirX[] = {1, 1, -1, -1, 1, 0, 0, -1};
        int dirY[] = {1, -1, 1, -1, 0, 1, -1, 0};

        for(int i = 0; i < 8; ++i) {
            int curX = strtX + dirX[i], curY = strtY + dirY[i];

            while(insideBoard(curX, curY)) {

                if(curX == endX && curY == endY)
                    return canStandOn(endX, endY, color);


                else {
                    String position = NotationConverter.convertMatrixToChess(new int[]{curX, curY});
                    if(pieces.containsKey(position))
                        break;

                }

                curX += dirX[i];
                curY += dirY[i];
            }
        }

        return false;
    }

    private boolean validKingMove(int strtX, int strtY, int endX, int endY, char color) {
        int dirX[] = {1, 1, -1, -1, 1, 0, 0, -1};
        int dirY[] = {1, -1, 1, -1, 0, 1, -1, 0};

        for(int i = 0; i < 8; ++i) {
            if(strtX + dirX[i] == endX && strtY + dirY[i] == endY) {
                String position = NotationConverter.convertMatrixToChess(new int[]{endX, endY});

                if(canStandOn(endX, endY, color))
                    return true;
            }
        }

        return false;
    }

    private boolean PawnsFirstMove(int x, char color) {
        return ( (color == 'w' && x == 6) || (color == 'b' && x == 1) );
    }

    /*
    0 for empty
    1 for white
    2 for black
     */
    private int getPieceOnCell(int x, int y) {
        String position = NotationConverter.convertMatrixToChess(new int[]{x, y});
        if(!pieces.containsKey(position))
            return 0;

        if(pieces.get(position).charAt(0) == 'w')
            return 1;

        return 2;
    }

    private boolean validPawnMove(int strtX, int strtY, int endX, int endY, char color) {
        int dirX[] = {1, 2, 1, 1};
        int dirY[] = {0, 0, 1, -1};

        if(color == 'w') {
            for(int i = 0; i < 4; ++i)
                dirX[i] = - dirX[i];
        }

        int targetColor = getPieceOnCell(endX, endY);

        if(strtX + dirX[0] == endX && strtY + dirY[0] == endY)
            return (targetColor == 0);

        else if(strtX + dirX[1] == endX && strtY + dirY[1] == endY)
            return (PawnsFirstMove(strtX, color) && targetColor == 0);


        for(int i = 2; i < 4; ++i)
            if(strtX + dirX[i] == endX && strtY + dirY[i] == endY)
                return (targetColor != 0 && targetColor != color);

        return false;
    }
}
