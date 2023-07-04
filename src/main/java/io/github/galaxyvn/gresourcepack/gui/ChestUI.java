package io.github.galaxyvn.gresourcepack.gui;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import io.github.galaxyvn.gresourcepack.GResourcePack;
import io.github.galaxyvn.gresourcepack.utils.HexUtils;
import io.github.galaxyvn.gresourcepack.utils.ItemBuilder;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ChestUI {

    FileConfiguration config = GResourcePack.plugin.getConfig();
    ChestGui ui;

    public ChestGui create() {
        int rows = config.getInt("gui.rows");
        ui = new ChestGui(rows, HexUtils.colorify(config.getString("")));
        final StaticPane pane = new StaticPane(0, 0, 9, rows);
        for (String item : config.getConfigurationSection("gui.items").getKeys(false)) {
            final GuiItem guiItem = new GuiItem(new ItemBuilder().build("gui.items." + item), event -> {
                String url = config.getString("gui.itens." + item + ".resource-pack.url");
                int delay = config.getInt("gui.itens." + item + ".resource-pack.delay");

                Validate.notNull(url, "Could not load resource pack URL");

                Bukkit.getScheduler().scheduleSyncDelayedTask(GResourcePack.plugin, () -> ((Player) event.getWhoClicked()).setResourcePack(url), delay);
            });
            int i = config.getInt("gui.items." + item + ".slot");
            pane.addItem(guiItem, i % 9, i / 9);
        }

        ui.addPane(pane);
        ui.setOnTopClick(event -> event.setCancelled(true));
        return ui;
    }


}
