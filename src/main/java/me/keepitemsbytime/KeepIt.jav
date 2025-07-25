package me.keepitemsbytime;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class KeepItemsByTime extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("KeepItemsByTime enabled!");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        World world = player.getWorld();
        long time = world.getTime(); // 0 - 24000

        if (time >= 0 && time < 12300) {
            //  Ban ngày → Giữ đồ
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            event.getDrops().clear();
        } else {
            //  Ban đêm → Mất đồ
            event.setKeepInventory(false); //  Rất quan trọng
            event.setKeepLevel(false);

            // Gửi thông báo
            player.sendTitle("§cTrời Tối Rồi!", "§eTìm chỗ trốn đi các cháu!", 10, 60, 10);
            player.sendMessage("§cTỐI RỒI TÌM CHỖ TRỐN ĐI CÁC CHÁU");
        }
    }
}