package io.github.nosequel.kitpvp.kits.impl;

import io.github.nosequel.kitpvp.kits.Kit;
import io.github.nosequel.kitpvp.kits.KitHandler;
import io.github.nosequel.kitpvp.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

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
                .addEnchantment(Enchantment.DAMAGE_ALL, 2)
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
                new ItemBuilder(Material.DIAMOND_BOOTS)
                        .addEnchantment(Enchantment.DURABILITY, 5)
                        .addEnchantment(Enchantment.PROTECTION_FALL, 3)
                        .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).get(),

                new ItemBuilder(Material.DIAMOND_LEGGINGS)
                        .addEnchantment(Enchantment.DURABILITY, 5)
                        .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).get(),

                new ItemBuilder(Material.DIAMOND_CHESTPLATE)
                        .addEnchantment(Enchantment.DURABILITY, 5)
                        .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).get(),

                new ItemBuilder(Material.DIAMOND_HELMET)
                        .addEnchantment(Enchantment.DURABILITY, 5)
                        .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).get(),
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
}