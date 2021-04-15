package io.github.nosequel.kitpvp.kits.impl.launcher;

import io.github.nosequel.kitpvp.kits.AbilityKit;
import io.github.nosequel.kitpvp.kits.KitHandler;
import io.github.nosequel.kitpvp.kits.ability.Ability;
import io.github.nosequel.kitpvp.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LauncherKit extends AbilityKit {

    public LauncherKit(KitHandler kitHandler) {
        super(kitHandler);
    }

    /**
     * Get the abilities of the kit
     *
     * @return the abilities
     */
    @Override
    public Ability[] getAbilities() {
        return new Ability[] {
                new LauncherAbilityItem(this.getKitHandler())
        };
    }

    /**
     * Get the name of the kit
     *
     * @return the name
     */
    @Override
    public String getKitName() {
        return "Launcher";
    }

    /**
     * Get the description of the kit
     *
     * @return the description
     */
    @Override
    public String[] getDescription() {
        return new String[]{
                "The launcher kit is a kit which",
                "launches everyone within a 8-",
                "block radius up into the air.",
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
                new ItemBuilder(Material.CHAINMAIL_BOOTS)
                        .addEnchantment(Enchantment.PROTECTION_FALL, 2)
                        .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                        .addEnchantment(Enchantment.DURABILITY, 5).get(),

                new ItemBuilder(Material.GOLD_LEGGINGS)
                        .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                        .addEnchantment(Enchantment.DURABILITY, 5).get(),

                new ItemBuilder(Material.LEATHER_CHESTPLATE)
                        .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                        .addEnchantment(Enchantment.DURABILITY, 5).get(),

                new ItemBuilder(Material.LEATHER_HELMET)
                        .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                        .addEnchantment(Enchantment.DURABILITY, 5).get(),
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
        return new ItemStack(Material.GLOWSTONE_DUST);
    }

    /**
     * Get the effects to give to the player on equip
     *
     * @return the potion effects
     */
    @Override
    public PotionEffect[] getPotionEffects() {
        return new PotionEffect[]{
                new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2),
                new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1),
                new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1)
        };
    }
}