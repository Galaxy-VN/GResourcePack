package io.github.galaxyvn.gresourcepack.commands;

import io.github.galaxyvn.gresourcepack.GResourcePack;
import io.github.galaxyvn.gresourcepack.utils.HexUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args[0].equalsIgnoreCase("reload")) {

            if (!sender.hasPermission("gresourcepack.admin")) {
                HexUtils.sendMessage(sender, "&cBạn không có quyền!");
                return false;
            }

            GResourcePack.plugin.reloadConfig();
            return true;
        }

        return true;
    }
}
