package io.github.galaxyvn.gresourcepack.listener;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import io.github.galaxyvn.gresourcepack.gui.ChestUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        ChestGui ui = new ChestUI().create();
        ui.show(event.getPlayer());
    }

}
