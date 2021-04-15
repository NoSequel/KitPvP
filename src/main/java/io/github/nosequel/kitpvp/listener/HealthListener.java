package io.github.nosequel.kitpvp.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class HealthListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack stack = player.getItemInHand();

        if (!event.getAction().name().contains("RIGHT") || stack == null || !stack.getType().equals(Material.MUSHROOM_SOUP)) {
            return;
        }

        if (player.getHealth() != player.getMaxHealth() || player.getFoodLevel() != 20) {
            player.getItemInHand().setType(Material.BOWL);
            player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + 6.5F));
            player.setFoodLevel(20);
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        final Player player = (Player) event.getEntity();

        if (event.getFoodLevel() < player.getFoodLevel() && !player.hasPotionEffect(PotionEffectType.HUNGER)) {
            event.setFoodLevel(20);
        }
    }
}