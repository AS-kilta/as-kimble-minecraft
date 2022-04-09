package com.asdvek.MinecraftASKimble.ui.popomatic;

import com.asdvek.MinecraftASKimble.WorldEditor;
import com.asdvek.MinecraftASKimble.math.Mat3;
import com.asdvek.MinecraftASKimble.math.Vec3;
import com.asdvek.MinecraftASKimble.ui.Drawable;
import org.bukkit.Material;
import java.lang.Math;

// Visualization of the popomatic dome
public class Dome implements Drawable {
    private Vec3 centerLocation = new Vec3(0.5, -56.5, 0.5);
    private final int domeRadius = 15;
    private final double domeTolerance = 0.5;

    public Dome(Vec3 location) {
        centerLocation = location;
    }

    private void drawDome() {
        for (int dz = -domeRadius; dz <= domeRadius; dz++)
            for (int dy = 0; dy <= domeRadius; dy++)
                for (int dx = -domeRadius; dx <= domeRadius; dx++) {
                    Vec3 offsetPos = new Vec3(dx, dy, dz);
                    if (Math.abs(offsetPos.length() - domeRadius) < Dome.this.domeTolerance) {
                        Vec3 worldPos = Vec3.add(centerLocation, offsetPos);
                        WorldEditor.replaceBlock(worldPos.x(), worldPos.y()-3, worldPos.z(), Material.GLASS);
                    }
                }
    }

    private void drawFloor() {
        for (int dz = -domeRadius; dz <= domeRadius; dz++)
            for (int dx = -domeRadius; dx <= domeRadius; dx++) {
                Vec3 offsetPos = new Vec3(dx, 0, dz);
                if (offsetPos.length() <= domeRadius) {
                    Vec3 worldPos = Vec3.add(centerLocation, offsetPos);
                    WorldEditor.replaceBlock(worldPos.x(), worldPos.y()-3, worldPos.z(), Material.IRON_BLOCK);
                }
            }
    }

    @Override
    public void draw() {
        drawFloor();
        drawDome();
    }
}