package uk.co.proxying.cubecraft.objects;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import uk.co.proxying.cubecraft.CubeCraftTest;
import uk.co.proxying.cubecraft.wrappers.WrapperPlayClientUpdateSign;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Consumer;

/**
 * Created by Kieran Quigley (Proxying) on 25-Aug-16.
 */
public class EditableSign {
    private static ProtocolManager protocolManager;
    private static Map<UUID, Consumer<List<String>>> consumers = new HashMap<>();
    private static Map<UUID, BlockPosition> signLocations = new HashMap<>();

    private static EditableSign instance = null;

    public static EditableSign getInstance() {
        if (instance == null) {
            instance = new EditableSign();
        }
        return instance;
    }

    public void runTasks() {
        protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new PacketAdapter(CubeCraftTest.getInstance(), PacketType.Play.Client.UPDATE_SIGN) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                final WrapperPlayClientUpdateSign packet = new WrapperPlayClientUpdateSign(event.getPacket());
                final BlockPosition blockPosition = packet.getLocation();
                final BlockPosition playerBlockPos = signLocations.remove(event.getPlayer().getUniqueId());
                final Consumer<List<String>> action = consumers.remove(event.getPlayer().getUniqueId());
                if (playerBlockPos == null || blockPosition.getX() != playerBlockPos.getX() || blockPosition.getY() != playerBlockPos.getY() || blockPosition.getZ() != playerBlockPos.getZ()) return;
                List<String> lines = new ArrayList<>();
                Collections.addAll(lines, packet.getLines());
                if (action != null) {
                    event.setCancelled(true);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> action.accept(lines));
                }
            }}
        );
    }

    /**
     * @param player The player you wish to open the GUI for.
     * @param action The consumer you wish to return the users typed lines to.
     */
    public void createSignGUI(Player player, Consumer<List<String>> action) {
        int x = player.getLocation().getBlockX(), y = 0, z = player.getLocation().getBlockZ();
        BlockPosition blockPosition = new BlockPosition(x, y, z);
        PacketContainer packetContainer = protocolManager.createPacket(PacketType.Play.Server.OPEN_SIGN_EDITOR);
        packetContainer.getBlockPositionModifier().write(0, blockPosition);
        try {
            protocolManager.sendServerPacket(player, packetContainer);
            signLocations.put(player.getUniqueId(), blockPosition);
            consumers.put(player.getUniqueId(), action);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
