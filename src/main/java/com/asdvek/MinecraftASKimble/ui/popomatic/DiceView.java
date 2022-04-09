package com.asdvek.MinecraftASKimble.ui.popomatic;

import com.asdvek.MinecraftASKimble.WorldEditor;
import com.asdvek.MinecraftASKimble.logic.KimbleGame;
import com.asdvek.MinecraftASKimble.math.Mat3;
import com.asdvek.MinecraftASKimble.math.Vec3;
import com.asdvek.MinecraftASKimble.ui.Drawable;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Material;

class DiceView implements Drawable {
    // Represents a dice in its own coordinate space
    //  the 2d arrays here can be thought of slices of the volume for each value of z in the range [0, 6]
    //   x-axis points out of face 2
    //   y-axis points out of face 6
    //   z-axis points out of face 3
    // indexing: diceBlocks[z][y][x]
    private final Integer diceBlocks[][][] = {
            {
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 1, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 1, 0},
                    {0, 0, 0, 0, 0, 0, 0}
            },
            {
                    {0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 1, 0}
            },
            {
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0}
            },
            {
                    {0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 1, 0}
            },
            {
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0}
            },
            {
                    {0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 1},
                    {0, 1, 0, 0, 0, 1, 0}
            },
            {
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0}
            }

    };

    // Rotation matrices which orient the dice to have stored value displayed on the top face (+y)
    // index 0 correspond to having the number 1 facing up, index 1 to 2 facing up and so on
    private final Mat3[] rotationMatrices = {
            new Mat3(
                    new Vec3(1, 0, 0),
                    new Vec3(0, -1, 0),
                    new Vec3(0, 0, -1)
            ),
            new Mat3(
                    new Vec3(0, 1, 0),
                    new Vec3(-1, 0, 0),
                    new Vec3(0, 0, 1)
            ),
            new Mat3(
                    new Vec3(1, 0, 0),
                    new Vec3(0, 0, -1),
                    new Vec3(0, 1, 0)
            ),
            new Mat3(
                    new Vec3(1, 0, 0),
                    new Vec3(0, 0,1),
                    new Vec3(0, -1, 0)
            ),
            new Mat3(
                    new Vec3(0, -1, 0),
                    new Vec3(1, 0, 0),
                    new Vec3(0, 0, 1)
            ),
            new Mat3(
                    new Vec3(1, 0, 0),
                    new Vec3(0, 1, 0),
                    new Vec3(0, 0, 1)
            )
    };

    // blocks used for representing black and white pixels
    private final Material whitePixelBlock = Material.SNOW_BLOCK;
    private final Material blackPixelBlock = Material.BLACK_WOOL;

    private Vec3 popomaticOrigin; // parent origin for relative movement within the popomatic
    private Vec3 diceCenterLocation;
    private KimbleGame gameState; // reference to Kimble game instance to visualize

    /* constructors */
    public DiceView(Vec3 popomaticOrigin, KimbleGame gameState) {
        this.popomaticOrigin = popomaticOrigin;
        this.diceCenterLocation = Vec3.add(this.popomaticOrigin, new Vec3(0, 4, 0));
        this.gameState = gameState;
    }

    @Override
    public void draw() {
        int rotIndex = gameState.getDiceState()-1;
        for (int z = 0; z < 7; z++)
            for (int y = 0; y < 7; y++)
                for (int x = 0; x < 7; x++) {
                    Vec3 offsetPos = new Vec3(x-3, y-3, z-3);
                    Vec3 worldPos = Vec3.add(diceCenterLocation, Mat3.mult(rotationMatrices[rotIndex], offsetPos));

                    WorldEditor.replaceBlock(worldPos.x(), worldPos.y(), worldPos.z(),
                            diceBlocks[z][y][x] == 1 ? blackPixelBlock : whitePixelBlock
                    );
                }
    }

    @Override
    public void clean() {
        throw new NotImplementedException("TODO: Implement DiceView.clean()");
    }
}
