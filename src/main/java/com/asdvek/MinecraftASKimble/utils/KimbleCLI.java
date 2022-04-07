package com.asdvek.MinecraftASKimble.utils;

import com.asdvek.MinecraftASKimble.logic.KimbleBoard;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * CLI for testing the KimbleBoard class.
 */
public class KimbleCLI
{
    public static void main( String[] args )
    {
        Scanner stdin = new Scanner(System.in);

        KimbleBoard board = new KimbleBoard();

        boolean quit = false;
        while (!quit) {
            String cmd = stdin.nextLine();
            String[] parts = cmd.split(" ");

            switch (parts[0]) {
                case "to_play":
                    if (parts.length != 2) {
                        System.out.println("Usage: to_play TEAM");
                        break;
                    }
                    KimbleBoard.Team t = parse_team(parts[1]);
                    if (t == null) {
                        System.out.println("Team name parsing failed: " + parts[1]);
                        break;
                    }
                    if (!board.MovePieceIntoPlay(t)) {
                        System.out.println("Can't move piece into play for " + t.toString());
                    }
                    break;
                case "move":
                    if (parts.length != 3) {
                        System.out.println("Usage: move SPACE AMOUNT");
                        break;
                    }
                    try {
                        int from = Integer.parseInt(parts[1]);
                        int n = Integer.parseInt(parts[2]);
                        if (!board.MovePiece(from, n)) {
                            System.out.println("Invalid move!");
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Number parsing failed");
                    }
                    break;
                case "quit":
                    quit = true;
                    break;
                default:
                    System.out.println("Parsing the command failed");
            }

            draw_board(board);
        }
    }

    private static KimbleBoard.Team parse_team(String team) {
        team = team.toLowerCase();
        if (team.equals("red")) return KimbleBoard.Team.RED;
        if (team.equals("blue")) return KimbleBoard.Team.BLUE;
        if (team.equals("yellow")) return KimbleBoard.Team.YELLOW;
        if (team.equals("green")) return KimbleBoard.Team.GREEN;

        return null;
    }

    private static void draw_board(KimbleBoard board) {
        for (int row = 0; row < 4; row++) {
            StringBuilder row_str = new StringBuilder();
            for (int space = 0; space < 7; space++) {
                ArrayList<KimbleBoard.Piece> pieces = board.PiecesAtSpace(row * 7 + space);
                String piece_str = (pieces.isEmpty()) ? "#" :
                        pieces.get(0).team.toString().substring(0, 1);
                row_str.append(piece_str);
            }
            row_str.append("\t");
            for (int space = 0; space < 4; space++) {
                // Draw goal spaces
                ArrayList<KimbleBoard.Piece> pieces = board.PiecesAtSpace(28 + row * 4 + space);
                String piece_str = (pieces.isEmpty()) ? "#" :
                        pieces.get(0).team.toString().substring(0, 1);
                row_str.append(piece_str);
            }
            System.out.println(row_str);
        }
        print_penalties(board);
    }

    private static void print_penalties(KimbleBoard board) {
        for (KimbleBoard.Team t : KimbleBoard.Team.values()) {
            System.out.println(t.toString() + ": " + board.penalties.get(t) + " penalties, " + board.drank.get(t) + " drunk");
        }
    }
}
