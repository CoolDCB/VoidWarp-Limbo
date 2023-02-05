package me.dave.voidwarplimbo;

import com.loohp.limbo.events.EventHandler;
import com.loohp.limbo.events.Listener;
import com.loohp.limbo.events.player.PlayerMoveEvent;
import com.loohp.limbo.player.Player;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onPlayerMove(@NotNull PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("voidwarp.admin.bypass")) return;
        double currYHeight = player.getLocation().getY();
        if (currYHeight <= VoidwarpLimbo.configManager.getYMin()) return;
        if (currYHeight >= VoidwarpLimbo.configManager.getYMax()) return;

        player.teleport(VoidwarpLimbo.configManager.getSpawnLocation());

        String teleportMessage = VoidwarpLimbo.configManager.getMessage();
        teleportMessage = teleportMessage.replaceAll("%location%", "Spawn");
        player.sendActionBar(Component.text(ChatColor.translateAlternateColorCodes('&', teleportMessage)));
    }
}
