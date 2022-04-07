package com.asdvek.MinecraftASKimble.ui;

// Interface for objects that can "draw UI" by updating the blocks in the kimble arena
public interface Drawable {
    // draw the UI state by creating/destroying blocks in the minecraft world
    public void draw();
}
