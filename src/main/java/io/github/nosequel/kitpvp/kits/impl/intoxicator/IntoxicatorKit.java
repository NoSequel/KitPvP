package io.github.nosequel.kitpvp.kits.impl.intoxicator;

import io.github.nosequel.kitpvp.kits.AbilityKit;
import io.github.nosequel.kitpvp.kits.KitHandler;
import io.github.nosequel.kitpvp.kits.ability.Ability;
import io.github.nosequel.kitpvp.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class IntoxicatorKit extends AbilityKit {

    /**
     * Constructor to make a new ability kit object
     *
     * @param kitHandler the handler to register it to
     */
    public IntoxicatorKit(KitHandler kitHandler) {
        super(kitHandler);
    }

    /**
     * Get the abilities of the kit
     *
     * @return the abilities
     */
    @Override
    public Ability[] getAbilities() {
        return new Ability[]{
                new IntoxicatorAbility(getKitHandler())
        };
    }

    /**
     * Get the name of the kit
     *
     * @return the name
     */
    @Override
    public String getKitName() {
        return "Intoxicator";
    }

    /**
     * Get the description of the kit
     *
     * @return the description
     */
    @Override
    public String[] getDescription() {
        return new String[]{
                "The intoxicator kit gives",
                "everyone in a range of 15",
                "blocks nausea and hunger."
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
                .addEnchantment(Enchantment.DURABILITY, 2).get();
    }

    /**
     * Get the armor to equip to the player
     *
     * @return the armor to equip
     */
    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemStack(Material.DIAMOND_BOOTS),
                new ItemStack(Material.CHAINMAIL_LEGGINGS),
                new ItemStack(Material.CHAINMAIL_CHESTPLATE),
                new ItemStack(Material.DIAMOND_HELMET)
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
        return new ItemStack(Material.COOKED_BEEF);
    }

    /**
     * Get the effects to give to the player on equip
     *
     * @return the potion effects
     */
    @Override
    public PotionEffect[] getPotionEffects() {
        return new PotionEffect[]{
                new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1)
        };
    }

    /**
     * Get the level required to access the kit
     *
     * @return the required level
     */
    @Override
    public int getRequiredLevel() {
        return 2;
    }
}