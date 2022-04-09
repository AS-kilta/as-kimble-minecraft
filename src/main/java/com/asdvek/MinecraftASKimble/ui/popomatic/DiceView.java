package com.asdvek.MinecraftASKimble.ui.popomatic;

import com.asdvek.MinecraftASKimble.WorldEditor;
import com.asdvek.MinecraftASKimble.math.Mat3;
import com.asdvek.MinecraftASKimble.math.Vec3;
import com.asdvek.MinecraftASKimble.ui.Drawable;
import org.bukkit.Material;

public class DiceView implements Drawable {
    // value of the face to be shown on top
    private Integer value;

    // blocks used for representing black and white pixels
    private final Material whitePixelBlock = Material.SNOW_BLOCK;
    private final Material blackPixelBlock = Material.BLACK_WOOL;

    // dice central block location
    private Vec3 diceCenterLocation = new Vec3(0.5, -56.5, 0.5);

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


    /* constructors */
    public DiceView(Integer initialValue) {
        if (initialValue < 1 || initialValue > 6) {
            throw new IllegalArgumentException("initialValue needs to be in the range [1, 6].");
        }
        value = initialValue;
    }
    public DiceView(Integer initialValue, Vec3 diceCenter) {
        if (initialValue < 1 || initialValue > 6) {
            throw new IllegalArgumentException("initialValue needs to be in the range [1, 6].");
        }
        value = initialValue;
        diceCenterLocation = diceCenter;
    }


    /* getters */

    // Sets the center block of the DiceView by storing a copy of the given vector
    public void setCenterLocation(Vec3 v) {
        diceCenterLocation = new Vec3(v);
    }

    public Integer getValue() { return value; }


    /* setters */

    // return a copy of center block's location in the DiceView
    public Vec3 getCenterLocation() {
        return new Vec3(diceCenterLocation);
    }

    // set the value which should be shown in the top face of the dice
    public void setValue(int newValue) {
        if (newValue < 1 || newValue > 6) {
            throw new IllegalArgumentException("newValue needs to be in the range [1, 6].");
        }
        value = newValue;
    }


    @Override
    public void draw() {
        int rotIndex = value-1;
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
}
