package com.asdvek.MinecraftASKimble.ui;

import com.asdvek.MinecraftASKimble.WorldEditor;
import com.asdvek.MinecraftASKimble.math.Vec3;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Material;
import java.lang.Math;
import java.util.HashMap;

// Visualization of a board stud where players can slot in
class StudView implements Drawable {
    private final Vec3 centerLocation;
    private final int radius = 2;
    private final int height = 3;
    private final double tolerance = 0.5;

    public StudView(Vec3 studOrigin) {
        centerLocation = studOrigin;
    }

    @Override
    public void draw() {
        for (int dz = -radius; dz <= radius; dz++)
            for (int dy = 0; dy < height; dy++)
                for (int dx = -radius; dx <= radius; dx++) {
                    Vec3 xzOffset = new Vec3(dx, 0, dz);
                    Vec3 offsetPos = new Vec3(dx, dy, dz);
                    if (Math.abs(xzOffset.length() - radius) < StudView.this.tolerance) {
                        Vec3 worldPos = Vec3.add(centerLocation, offsetPos);
                        WorldEditor.replaceBlock(worldPos, Material.GLASS);
                    }
                }
    }

    @Override
    public void clean() {
        throw new NotImplementedException("TODO: Implement StudView.clean()");
    }
}
