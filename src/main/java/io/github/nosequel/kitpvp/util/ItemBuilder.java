package io.github.nosequel.kitpvp.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemBuilder {

    private final Map<Enchantment, Integer> enchantments = new HashMap<>();

    private String displayName;
    private String[] lore;

    private Material type;

    /**
     * Constructor to make a new item builder object
     *
     * @param material the type of the item
     */
    public ItemBuilder(Material material) {
        this.type = material;
        this.displayName = material.name();
    }

    /**
     * Set the display name of the item
     *
     * @param displayName the new display name
     * @return the current item builder instance
     */
    public ItemBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Set the lore of the item
     *
     * @param lore the new lore
     * @return the current item builder instance
     */
    public ItemBuilder setLore(String... lore) {
        this.lore = lore;
        return this;
    }

    /**
     * Set the material type of the item
     *
     * @param type the new material type
     * @return the current item builder instance
     */
    public ItemBuilder setType(Material type) {
        this.type = type;
        return this;
    }

    /**
     * Add a new enchantment to the item
     *
     * @param enchantment the enchantment to add
     * @param level       the level of the enchantment
     * @return the current item builder instance
     */
    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        this.enchantments.put(enchantment, level);
        return this;
    }

    /**
     * Get the item stack
     *
     * @return the item stack
     */
    public ItemStack get() {
        final ItemStack item = new ItemStack(this.type);
        final ItemMeta meta = item.getItemMeta();

        if (this.displayName != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        }

        if (this.lore != null) {
            meta.setLore(Arrays.stream(lore)
                    .map(lore -> ChatColor.translateAlternateColorCodes('&', lore))
                    .collect(Collectors.toList())
            );
        }

        if(!this.enchantments.isEmpty()) {
            for (Map.Entry<Enchantment, Integer> entry : this.enchantments.entrySet()) {
                item.addUnsafeEnchantment(entry.getKey(), entry.getValue());
            }
        }

        return item;
    }
}
