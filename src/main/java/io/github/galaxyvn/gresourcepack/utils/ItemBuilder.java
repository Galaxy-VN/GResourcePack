package io.github.galaxyvn.gresourcepack.utils;

import io.github.galaxyvn.gresourcepack.GResourcePack;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemBuilder {
    public static ItemStack build(String path) {
        FileConfiguration config = GResourcePack.plugin.getConfig();

        ItemBuilder builder = new ItemBuilder();
        builder.material(MaterialUtils.matchMaterial(config.getString(path + ".ID")));
        if (config.contains(path + ".amount")) {
            builder.amount(config.getInt(path + ".amount"));
        }
        if (config.contains(path + ".custom-model-data")) {
            builder.customModelData(config.getInt(path + ".custom-model-data"));
        }
        if (config.contains(path + ".name")) {
            String name = HexUtils.colorify(config.getString(path + ".name"));
            builder.name(name);
        }
        if(config.contains(path + ".lore")) {
            List<String> lores = new ArrayList<String>();
            for (String lore : config.getStringList(path + ".lore"))
                lores.add(HexUtils.colorify(lore));
            builder.lore(lores);
        }
        return builder.build();
    }



    private Material material;
    private int amount;
    private String name;
    private int customModelData;
    private List<String> lores;
    private String skullowner;
    private boolean glow;

    public ItemBuilder() {
        this.material = Material.STONE;
        this.amount = 1;
        this.customModelData = 0;
        this.glow = false;
    }

    public ItemBuilder material(Material m) {
        this.material = m;
        return this;
    }

    public ItemBuilder amount(int a) {
        this.amount = a;
        return this;
    }

    public ItemBuilder name(String n) {
        this.name = n;
        return this;
    }

    public ItemBuilder customModelData(int customModelData) {
        this.customModelData = customModelData;
        return this;
    }

    public ItemBuilder lore(List<String> l) {
        this.lores = l;
        return this;
    }

    public ItemBuilder skull(String s) {
        this.skullowner = s;
        return this;
    }

    public ItemBuilder glow(boolean g) {
        this.glow = g;
        return this;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(this.material, this.amount);
        ItemMeta meta = item.getItemMeta();
        SkullMeta smeta = null;
        if (this.name != null) {
            meta.setDisplayName(this.name);
        }
        if (this.lores != null) {
            meta.setLore(this.lores);
        }
        if (this.customModelData > 0) {
            meta.setCustomModelData(this.customModelData);
        }
        if (this.glow) {
            meta.addEnchant(Enchantment.DURABILITY, 11, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);
        if (this.skullowner != null) {
            smeta = (SkullMeta) item.getItemMeta();
            smeta.setOwner(this.skullowner);
            item.setItemMeta(smeta);
        }
        return item;
    }
}
