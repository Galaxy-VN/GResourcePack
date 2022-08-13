package io.github.galaxyvn.gresourcepack.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;

import java.util.HashMap;

public class MaterialUtils {

    private static boolean LEGACY = true;

    public static boolean isLegacy() {
        if (NMSUtil.getVersionNumber() >= 19)
            return LEGACY = false;
        return LEGACY;
    }

    public static Material matchMaterial(String mat) {
        if (NMSUtil.getVersionNumber() >= 19)
            LEGACY = false;
        return LEGACY ? Material.matchMaterial(mat) : Material.matchMaterial(mat, LEGACY);
    }

}
