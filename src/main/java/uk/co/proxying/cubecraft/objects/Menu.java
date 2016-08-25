package uk.co.proxying.cubecraft.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import uk.co.proxying.cubecraft.CubeCraftTest;

/**
 * Created by Kieran Quigley (Proxying) on 25-Aug-16.
 */
public abstract class Menu implements Listener {

    private Player target;
    private String title;
    private int size;
    private Inventory inventory;

    public Menu(Player player, String title, int size) {
        this.target = player;
        this.title = title;
        this.size = size;
        this.inventory = Bukkit.createInventory(target, size, title);
        Bukkit.getServer().getPluginManager().registerEvents(this, CubeCraftTest.getInstance());
    }

    /**
     * @param itemStack The ItemStack to set.
     * @param slot      The slot the item's being set to.
     * @return This object.
     */
    public Menu setItemInSlot(ItemStack itemStack, int slot) {
        this.inventory.setItem(slot, itemStack);
        return this;
    }

    @EventHandler
    public void onInventoryClicked(InventoryClickEvent event) {
        if (!event.getInventory().getTitle().equals(getTitle())) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        if (event.getRawSlot() > event.getInventory().getSize() || event.getRawSlot() < 0) return;
        if (!event.getWhoClicked().getName().equalsIgnoreCase(getTarget().getName())) return;
        event.setCancelled(true);
        onClick(event, event.getRawSlot());
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!event.getInventory().getTitle().equals(getTitle())) return;
        if (!event.getPlayer().getName().equalsIgnoreCase(getTarget().getName())) return;
        event.getHandlers();
        HandlerList.unregisterAll(this);
    }

    @EventHandler
    public abstract void onClick(InventoryClickEvent event, int slot);

    /**
     * @param player The player
     */
    public void openFor(Player player) {
        player.openInventory(getInventory());
    }

    public Player getTarget() {
        return target;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public Inventory getInventory() {
        return inventory;
    }


}
