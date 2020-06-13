package com.asdvek.MinecraftASKimble;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

/**
 * World editing helpers implemented without math libraries in a naive way.
 */

public class WorldEditor {

    /**
     * functions with scalar arguments
     */

    // replace block at location (x,y,z) with material given by `blockMaterial`
    public static void replaceBlock(double x, double y, double z, Material blockMaterial) {
        World world = Bukkit.getServer().getWorld(Const.WORLD_NAME);
        Location loc = new Location(world, x, y, z);
        Block block = world.getBlockAt(loc);
        block.setType(blockMaterial);
    }

    // replace block at location (x,y,z) with air
    public static void clearBlock(double x, double y, double z) {
        replaceBlock(x, y, z, Material.AIR);
    }

    // replace all blocks enclosed in a volume with the given material
    // the volume is specified by two opposite corners of a cuboid
    // both starting and ending point are inclusive
    public static void replaceVolume(double sx, double sy, double sz, double ex, double ey, double ez, Material blockMaterial) {
        // TODO: add sanity checks for bounds

        // make sure that end indices are greater than or equal than start indices
        if (ex < sx) {
            double tmp = ex;
            ex = sx;
            sx = tmp;
        }
        if (ey < sy) {
            double tmp = ey;
            ey = sy;
            sy = tmp;
        }
        if (ez < sz) {
            double tmp = ez;
            ez = sz;
            sz = tmp;
        }

        World world = Bukkit.getServer().getWorld(Const.WORLD_NAME);
        for (int y = (int)Math.floor(sy); y <= (int)Math.floor(ey); y++) {
            for (int z = (int)Math.floor(sz); z <= (int)Math.floor(ez); z++) {
                for (int x = (int)Math.floor(sx); x <= (int)Math.floor(ex); x++) {
                    replaceBlock(x, y, z, blockMaterial);
                }
            }
        }
    }

    // replace given volume with air
    public static void clearVolume(double sx, double sy, double sz, double ex, double ey, double ez) {
        replaceVolume(sx, sy, sz, ex, ey, ez, Material.AIR);
    }


    /**
     * functions with vector arguments
     */
    // replace block at location (x,y,z) with material given by `blockMaterial`
    public static void replaceBlock(Vec3 v, Material blockMaterial) {
        replaceBlock(v.x(), v.y(), v.z(), blockMaterial);
    }

    // replace block at location (x,y,z) with air
    public static void clearBlock(Vec3 v) {
        replaceBlock(v, Material.AIR);
    }

    // replace all blocks enclosed in a volume with the given material
    // the volume is specified by two opposite corners of a cuboid
    // both starting and ending point are inclusive
    public static void replaceVolume(Vec3 vStart, Vec3 vEnd, Material blockMaterial) {
        // extract scalar index ending points from vectors
        double sx = vStart.x();
        double sy = vStart.y();
        double sz = vStart.z();
        double ex = vEnd.x();
        double ey = vEnd.y();
        double ez = vEnd.z();

        // call scalar variant of the function
        replaceVolume(sx, sy, sz, ex, ey, ez, blockMaterial);
    }

    // replace given volume with air
    public static void clearVolume(Vec3 vStart, Vec3 vEnd) {
        replaceVolume(vStart, vEnd, Material.AIR);
    }

}
