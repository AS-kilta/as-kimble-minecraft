package com.asdvek.MinecraftASKimble.logic;

/** Holds the state of a single Kimble game */
public class KimbleGame {
    private KimbleBoard boardState;
    private Popomatic diceState;

    // getters
    public KimbleBoard getBoardState() { return this.boardState; }
    public int getDiceState() { return diceState.getDiceValue(); }

    // FIXME: remove
    public int debugNaks() {
        return diceState.naks();
    }

    public KimbleGame() {
        this.boardState = new KimbleBoard();
        this.diceState = new Popomatic();
    }
}
