package com.asdvek.MinecraftASKimble.ui;

import com.asdvek.MinecraftASKimble.WorldEditor;
import com.asdvek.MinecraftASKimble.logic.KimbleBoard;
import com.asdvek.MinecraftASKimble.logic.KimbleGame;
import com.asdvek.MinecraftASKimble.math.Vec3;
import com.asdvek.MinecraftASKimble.ui.popomatic.PopomaticView;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardView implements Drawable {
    private final int boardPlotSize = 128;  // chunk-aligned square area reserved for the kimble board
    private final int boardSize = 127;      // size of the board itself, the remaining plot is left as padding
    private final Vec3 boardOrigin;         // position of the board origin corner in world space
    private KimbleGame gameState;           // reference to game state that the view is visualizing

    private final HashMap<Integer, Vec3> studLocations = new HashMap<Integer, Vec3>() {{
        put(0,   new Vec3(110, 1, 26));
        put(1,   new Vec3(117, 1, 37));
        put(2,   new Vec3(122, 1, 50));
        put(3,   new Vec3(123, 1, 63));
        put(4,   new Vec3(122, 1, 77));
        put(5,   new Vec3(117, 1, 89));
        put(6,   new Vec3(110, 1, 100));
        put(7,   new Vec3(100, 1, 110));
        put(8,   new Vec3(89 , 1, 117));
        put(9,   new Vec3(76 , 1, 122));
        put(10,  new Vec3(63 , 1, 123));
        put(11,  new Vec3(49 , 1, 122));
        put(12,  new Vec3(37 , 1, 117));
        put(13,  new Vec3(26 , 1, 110));
        put(14,  new Vec3(16 , 1, 100));
        put(15,  new Vec3(9  , 1, 89));
        put(16,  new Vec3(4  , 1, 76));
        put(17,  new Vec3(3  , 1, 63));
        put(18,  new Vec3(4  , 1, 49));
        put(19,  new Vec3(9  , 1, 37));
        put(20,  new Vec3(16 , 1, 26));
        put(21,  new Vec3(26 , 1, 16));
        put(22,  new Vec3(37 , 1, 9));
        put(23,  new Vec3(50 , 1, 4));
        put(24,  new Vec3(63 , 1, 3));
        put(25,  new Vec3(77 , 1, 4));
        put(26,  new Vec3(89 , 1, 9));
        put(27,  new Vec3(100, 1, 16));
        put(28,  new Vec3(100, 1, 26));
        put(29,  new Vec3(93 , 1, 33));
        put(30,  new Vec3(86 , 1, 40));
        put(31,  new Vec3(79 , 1, 47));
        put(32,  new Vec3(100, 1, 100));
        put(33,  new Vec3(93 , 1, 93));
        put(34,  new Vec3(86 , 1, 86));
        put(35,  new Vec3(79 , 1, 79));
        put(36,  new Vec3(26 , 1, 100));
        put(37,  new Vec3(33 , 1, 93));
        put(38,  new Vec3(40 , 1, 86));
        put(39,  new Vec3(47 , 1, 79));
        put(40,  new Vec3(26 , 1, 26));
        put(41,  new Vec3(33 , 1, 33));
        put(42,  new Vec3(40 , 1, 40));
        put(43,  new Vec3(47 , 1, 47));
    }};

    // child drawables
    private final PopomaticView popomaticView;
    private final ArrayList<StudView> studs = new ArrayList<>();

    public BoardView(Vec3 boardOrigin, KimbleGame gameState) {
        this.boardOrigin = boardOrigin;
        this.gameState = gameState;
        this.popomaticView = new PopomaticView(
                Vec3.add(this.boardOrigin, new Vec3(63,1,63)),
                gameState
        );
        for (Vec3 studLocation : studLocations.values()) {
            studs.add(new StudView(Vec3.add(this.boardOrigin, studLocation)));
        }
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
        for (StudView stud : studs) {
            stud.draw();
        }
    }

    @Override
    public void clean() {
        final int boardCleanHeight = 200;
        Vec3 cleanStartCorner = boardOrigin;
        Vec3 cleanStopCorner = Vec3.add(cleanStartCorner, new Vec3(boardPlotSize, boardCleanHeight, boardPlotSize));
        WorldEditor.clearVolume(cleanStartCorner, cleanStopCorner);
    }
}
