package io.github.nosequel.kitpvp.kits.impl;

import io.github.nosequel.kitpvp.kits.Kit;
import io.github.nosequel.kitpvp.kits.KitHandler;
import io.github.nosequel.kitpvp.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DefaultKit extends Kit {

    public DefaultKit(KitHandler kitHandler) {
        super(kitHandler);
    }

    /**
     * Get the name of the kit
     *
     * @return the name
     */
    @Override
    public String getKitName() {
        return "Default";
    }

    /**
     * Get the description of the kit
     *
     * @return the description
     */
    @Override
    public String[] getDescription() {
        return new String[]{
                "The default kit, a rather classic,",
                "yet trustworthy kit. Overall the mo-",
                "st balanced kit."
        };
    }

    /**
     * Get the sword to give to the player
     *
     * @return the sword to give
     */
    @Override
    public ItemStack getSword() {
        return new ItemBuilder(Material.DIAMOND_SWORD)
                .addEnchantment(Enchantment.DAMAGE_ALL, 1)
                .addEnchantment(Enchantment.DURABILITY, 5).get();
    }

    /**
     * Get the armor to equip to the player
     *
     * @return the armor to equip
     */
    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemStack(Material.IRON_BOOTS),
                new ItemStack(Material.IRON_LEGGINGS),
                new ItemStack(Material.IRON_CHESTPLATE),
                new ItemStack(Material.IRON_HELMET)
        };
    }

    /**
     * Get the type of the item for health
     *
     * @return the item
     */
    @Override
    public ItemStack getHealthType() {
        return new ItemStack(Material.MUSHROOM_SOUP);
    }

    /**
     * Get the icon to display inside of the kit selector
     *
     * @return the icon
     */
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.DIAMOND_SWORD);
    }

    /**
     * Get the effects to give to the player on equip
     *
     * @return the potion effects
     */
    @Override
    public PotionEffect[] getPotionEffects() {
        return new PotionEffect[] {
                new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1)
        };
    }
}