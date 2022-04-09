package com.asdvek.MinecraftASKimble.ui.popomatic;

import com.asdvek.MinecraftASKimble.WorldEditor;
import com.asdvek.MinecraftASKimble.math.Vec3;
import com.asdvek.MinecraftASKimble.ui.Drawable;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Material;
import java.lang.Math;

// Visualization of the popomatic dome
class DomeView implements Drawable {
    private final Vec3 centerLocation;
    private final int domeRadius = 15;
    private final double domeTolerance = 0.5;

    public DomeView(Vec3 domeOrigin) {
        centerLocation = domeOrigin;
    }

    private void drawDome() {
        for (int dz = -domeRadius; dz <= domeRadius; dz++)
            for (int dy = 0; dy <= domeRadius; dy++)
                for (int dx = -domeRadius; dx <= domeRadius; dx++) {
                    Vec3 offsetPos = new Vec3(dx, dy, dz);
                    if (Math.abs(offsetPos.length() - domeRadius) < DomeView.this.domeTolerance) {
                        Vec3 worldPos = Vec3.add(centerLocation, offsetPos);
                        WorldEditor.replaceBlock(worldPos, Material.GLASS);
                    }
                }
    }

    private void drawFloor() {
        for (int dz = -domeRadius; dz <= domeRadius; dz++)
            for (int dx = -domeRadius; dx <= domeRadius; dx++) {
                Vec3 offsetPos = new Vec3(dx, 0, dz);
                if (offsetPos.length() <= domeRadius) {
                    Vec3 worldPos = Vec3.add(centerLocation, offsetPos);
                    WorldEditor.replaceBlock(worldPos, Material.IRON_BLOCK);
                }
            }
    }

    @Override
    public void draw() {
        drawFloor();
        drawDome();
    }

    @Override
    public void clean() {
        throw new NotImplementedException("TODO: Implement DiceView.clean()");
    }
}