package io.github.nosequel.kitpvp.kits;

import io.github.nosequel.kitpvp.kits.ability.Ability;
import io.github.nosequel.kitpvp.kits.ability.ItemAbility;
import org.bukkit.inventory.ItemStack;

public abstract class AbilityKit extends Kit {

    /**
     * Constructor to make a new ability kit object
     *
     * @param kitHandler the handler to register it to
     */
    public AbilityKit(KitHandler kitHandler) {
        super(kitHandler);
    }

    /**
     * Get the contents to give to the player
     *
     * @return the contents
     */
    @Override
    public ItemStack[] getContents() {
        final ItemStack[] items = new ItemStack[36];

        items[0] = this.getSword();

        for(byte i = 0; i < this.getAbilities().length; i++) {
            final Ability ability = this.getAbilities()[i];

            if (ability instanceof ItemAbility) {
                items[i+1] = new ItemStack(((ItemAbility) ability).getMaterial());
            }
        }

        for (byte i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = this.getHealthType();
            }
        }

        return items;
    }

    /**
     * Get the abilities of the kit
     *
     * @return the abilities
     */
    public abstract Ability[] getAbilities();

}
