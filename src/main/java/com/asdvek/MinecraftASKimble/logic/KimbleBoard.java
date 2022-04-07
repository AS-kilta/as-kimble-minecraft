package com.asdvek.MinecraftASKimble.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class KimbleBoard {

    public enum Team {
        RED, BLUE, YELLOW, GREEN
    }

    /**
     * Helper class to internally refer to a single Kimble piece
     * identified by the Team and the piece index (0..3)
     */
    public static final class Piece {
        public final Team team;
        public final int i;
        public Piece(Team _team, int _i) {team=_team; i=_i;}
    }

    /**
     * Penalties for each teams.
     */
    public HashMap<Team, Integer> penalties = new HashMap<Team, Integer>() {{
        put(Team.RED,       0);
        put(Team.BLUE,      0);
        put(Team.YELLOW,    0);
        put(Team.GREEN,     0);
    }};

    /**
     * Penalties taken care of for each team.
     */
    public HashMap<Team, Integer> drank = new HashMap<Team, Integer>() {{
        put(Team.RED,       0);
        put(Team.BLUE,      0);
        put(Team.YELLOW,    0);
        put(Team.GREEN,     0);
    }};

    /**
     * The spaces where each player moves his piece into play.
     */
    static HashMap<Team, Integer> mine_space = new HashMap<Team, Integer>() {{
        put(Team.RED,       0);
        put(Team.BLUE,      7);
        put(Team.YELLOW,    14);
        put(Team.GREEN,     21);
    }};

    /**
     * The last space before moving to goal spaces for each team.
     */
    static HashMap<Team, Integer> last_space = new HashMap<Team, Integer>() {{
        put(Team.RED,       27);
        put(Team.BLUE,      6);
        put(Team.YELLOW,    13);
        put(Team.GREEN,     20);
    }};

    /**
     * The first space of each team's goal spaces.
     */
    static HashMap<Team, Integer> goal_start = new HashMap<Team, Integer>() {{
        put(Team.RED,       28);
        put(Team.BLUE,      32);
        put(Team.YELLOW,    36);
        put(Team.GREEN,     40);
    }};

    /**
     * The pseudo-space that is used to refer to each player's home space.
     */
    static HashMap<Team, Integer> home_space = new HashMap<Team, Integer>() {{
        put(Team.RED,       -1);
        put(Team.BLUE,      -2);
        put(Team.YELLOW,    -3);
        put(Team.GREEN,     -4);
    }};

    private final HashMap<Team, int[]> piece_positions = new HashMap<Team, int[]>();

    /**
     * KimbleBoard constructor. Creates a board in the starting state.
     */
    public KimbleBoard() {
        for (Team team : Team.values()) {
            Integer home = home_space.get(team);
            piece_positions.put(team, new int[] {home, home, home, home});
        }
    }

    /**
     * @param current_pos the current space of the piece(s) to be moved
     * @param amount the amount of spaces to move the piece(s)
     * @return true if the move was valid and was executed. false otherwise
     */
    public boolean MovePiece(int current_pos, int amount) {
        ArrayList<Piece> pieces = PiecesAtSpace(current_pos);
        if (pieces.isEmpty()) return false;  // No piece at given position

        int new_space = current_pos + amount;

        Team team = pieces.get(0).team;

        if (current_pos <= last_space.get(team) && new_space > last_space.get(team)) {
            // Move to goal space
            new_space = goal_start.get(team) + new_space - last_space.get(team) - 1;
        }
        else if (current_pos >= goal_start.get(team)) {
            // Move in the goal spaces
            new_space = new_space;
        }
        else {
            // Move normally on the board, not in goal spaces
            new_space = new_space % 28;
        }

        if (!LegalMove(pieces.get(0), new_space)) return false;  // Not a legal move

        ArrayList<Piece> captured_pieces = PiecesAtSpace(new_space);
        if (!captured_pieces.isEmpty() && mine_space.get(captured_pieces.get(0).team) == new_space) {
            // Mine hit
            int mine_count = captured_pieces.size();
            pieces.forEach(p -> {
                UpdatePiecePosition(p, home_space.get(p.team));
                penalties.computeIfPresent(p.team, (_team, i) -> i + mine_count);
            });
            return true;
        }

        captured_pieces.forEach(captured -> {
            UpdatePiecePosition(captured, home_space.get(captured.team));
            penalties.computeIfPresent(captured.team, (_team, penalties) -> penalties + pieces.size());
        });

        int to = new_space;
        pieces.forEach(p -> UpdatePiecePosition(p, to));

        if (goal_start.get(team) < to && pieces.size() > 1) {
            pieces.subList(1, pieces.size()).forEach(p -> {
                OptionalInt stack_to = IntStream.iterate(to-1, i->i-1).limit(goal_start.get(team)).
                        filter(i -> PiecesAtSpace(i).isEmpty()).findFirst();
                stack_to.ifPresent(i -> UpdatePiecePosition(p, i));
            });
        }

        return true;
    }

    public boolean MovePieceIntoPlay(Team team) {
        ArrayList<Piece> pieces = PiecesAtSpace(home_space.get(team));
        if (pieces.isEmpty()) return false;  // No piece that can be moved into play

        PiecesAtSpace(mine_space.get(team)).forEach(captured -> {
            if (captured.team != team) {
                UpdatePiecePosition(captured, home_space.get(captured.team));
                penalties.computeIfPresent(captured.team, (_team, penalties) -> penalties + 1);
            }
        });

        Piece p = pieces.get(0);
        UpdatePiecePosition(p, mine_space.get(team));
        return true;
    }

    public ArrayList<Piece> PiecesAtSpace(int space) {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (Team team : Team.values()) {
            for (int i = 0; i < 4; i++) {
                if (piece_positions.get(team)[i] == space) {
                    pieces.add(new Piece(team, i));
                }
            }
        }
        return pieces;
    }

    private void UpdatePiecePosition(Piece piece, final int new_space) {
        int[] pos = piece_positions.get(piece.team);
        pos[piece.i] = new_space;
    }

    private boolean LegalMove(Piece piece, int new_space) {
        Team t = piece.team;
        if (new_space > goal_start.get(t) + 3) return false;
        ArrayList<Piece> pieces = PiecesAtSpace(new_space);
        return pieces.isEmpty() || pieces.get(0).team != t;
    }

    /**
     * Gets the positions of all pieces.
     * @return All piece positions. Hashmap with Team as key and a 4-array of ints
     *         that correspond to board spaces.
     */
    public HashMap<Team, int[]> GetPiecePositions() {return piece_positions;}
}
