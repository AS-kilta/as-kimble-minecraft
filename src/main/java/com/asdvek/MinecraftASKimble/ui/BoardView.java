package com.asdvek.MinecraftASKimble.ui;

import com.asdvek.MinecraftASKimble.WorldEditor;
import com.asdvek.MinecraftASKimble.logic.KimbleBoard;
import com.asdvek.MinecraftASKimble.logic.KimbleGame;
import com.asdvek.MinecraftASKimble.math.Vec3;
import com.asdvek.MinecraftASKimble.ui.popomatic.PopomaticView;
import org.bukkit.Material;

public class BoardView implements Drawable {
    private final int boardPlotSize = 128;  // chunk-aligned square area reserved for the kimble board
    private final int boardSize = 127;      // size of the board itself, the remaining plot is left as padding
    private final Vec3 boardOrigin;         // position of the board origin corner in world space
    private KimbleGame gameState;           // reference to game state that the view is visualizing

    // child drawables
    private final PopomaticView popomaticView;

    public BoardView(Vec3 boardOrigin, KimbleGame gameState) {
        this.boardOrigin = boardOrigin;
        this.gameState = gameState;
        this.popomaticView = new PopomaticView(
                Vec3.add(this.boardOrigin, new Vec3(63,1,63)),
                gameState
        );
    }

    /** Draw Kimble board floor as a grid pattern */
    private void drawFloor() {
        final Material gridColorOne = Material.MAGENTA_WOOL;
        final Material gridColorTwo = Material.LIGHT_GRAY_WOOL;
        for (int dx = 0; dx < boardSize; dx++) {
            for (int dz = 0; dz < boardSize; dz++) {
                Vec3 offset = new Vec3(dx, 0, dz);
                Vec3 worldPosition = Vec3.add(boardOrigin, offset);
                if ((dx + dz) % 2 == 0) {
                    WorldEditor.replaceBlock(worldPosition, gridColorOne);
                } else {
                    WorldEditor.replaceBlock(worldPosition, gridColorTwo);
                }
            }
        }
    }

    @Override
    public void draw() {
        drawFloor();
        this.popomaticView.draw();
    }

    @Override
    public void clean() {
        final int boardCleanHeight = 20;
        Vec3 cleanStartCorner = boardOrigin;
        Vec3 cleanStopCorner = Vec3.add(cleanStartCorner, new Vec3(boardPlotSize, boardCleanHeight, boardPlotSize));
        WorldEditor.clearVolume(cleanStartCorner, cleanStopCorner);
    }
}
