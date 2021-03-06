package net.glowstone.net;

import com.flowpowered.network.Message;
import net.glowstone.net.handler.play.player.UseItemMessage;
import net.glowstone.net.message.KickMessage;
import net.glowstone.net.message.play.entity.*;
import net.glowstone.net.message.play.game.*;
import net.glowstone.net.message.play.inv.*;
import net.glowstone.net.message.play.player.*;
import net.glowstone.net.protocol.AbstractPlayProtocol;
import net.glowstone.util.TextMessage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.UUID;

/**
 * Test cases for {@link net.glowstone.net.protocol.AbstractPlayProtocol.PlayProtocol}.
 */
public class PlayProtocolTest extends BaseProtocolTest {

    private static final Message[] TEST_MESSAGES = new Message[]{
            new PingMessage(1),
            new IncomingChatMessage("test"),
            new InteractEntityMessage(1, 1),
            new InteractEntityMessage(1, 2, 1f, 2f, 3f, 0),
            new PlayerUpdateMessage(true),
            new PlayerPositionMessage(true, 1.0, 2.0, 3.0),
            new PlayerLookMessage(3f, 2f, true),
            new PlayerPositionLookMessage(true, 1.0, 2.0, 3.0, 1f, 2f),
            new DiggingMessage(0, 1, 2, 3, 4),
            new BlockPlacementMessage(1, 2, 3, 4, 5, 6, 7, 8),
            new PlayerSwingArmMessage(1),
            //new HeldItemMessage(1), // asymmetric
            new PlayerActionMessage(1, 2, 3),
            new SteerVehicleMessage(1f, 2f, true, false),
            new CloseWindowMessage(1),
            new WindowClickMessage(1, 2, 3, 4, 5, new ItemStack(Material.APPLE, 1)),
            new TransactionMessage(1, 2, true),
            new CreativeItemMessage(1, new ItemStack(Material.APPLE, 1)),
            new EnchantItemMessage(1, 2),
            new UpdateSignMessage(1, 2, 3, new TextMessage[]{new TextMessage("hello"), new TextMessage("hi"), new TextMessage("third"), new TextMessage("fourth")}),
            UpdateSignMessage.fromPlainText(1, 2, 3, "hello", "hi", "third", "fourth"),
            new PlayerAbilitiesMessage(1, 2f, 3f),
            new TabCompleteMessage("text", false, null),
            new TabCompleteMessage("text", false, new BlockVector(1, 2, 3)),
            new ClientSettingsMessage("en-en", 16, 1, true, 2, 0),
            new ClientStatusMessage(1),
            new PluginMessage("glowstone", new byte[]{0x00, 0x11}),
            new SpectateMessage(UUID.randomUUID()),
            new ResourcePackStatusMessage("glowstonehash", 1),
            //new JoinGameMessage(1, 2, 3, 4, 5, "normal", true), //asymmetric
            new ChatMessage(ProtocolTestUtils.getTextMessage(), 2),
            new ChatMessage(ProtocolTestUtils.getTextMessage(), 1),
            new ChatMessage(ProtocolTestUtils.getJson()),
            new ChatMessage("glowstone"),
            new TimeMessage(1, 2),
            new EntityEquipmentMessage(1, 2, new ItemStack(Material.APPLE, 1)),
            new SpawnPositionMessage(1, 2, 3),
            new HealthMessage(1f, 2, 3f),
            new RespawnMessage(1, 2, 3, "world"),
            new PositionRotationMessage(1.0, 2.0, 3.0, 1f, 2f),
            new PositionRotationMessage(1.0, 2.0, 3.0, 4f, 5f, 6, 1),
            new PositionRotationMessage(new Location(null, 1.0, 2.0, 3.0, 4f, 5f)),
            new AnimateEntityMessage(1, 2),
            new SpawnPlayerMessage(1, UUID.randomUUID(), 2, 3, 4, 5, 6, ProtocolTestUtils.getMetadataEntry()),
            new CollectItemMessage(1, 2),
            new SpawnObjectMessage(1, UUID.randomUUID(),2, 3, 4, 5, 6, 7),
            new SpawnObjectMessage(1, UUID.randomUUID(), 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            new SpawnMobMessage(1, UUID.randomUUID(), 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ProtocolTestUtils.getMetadataEntry()),
            new SpawnPaintingMessage(1, "painting", 2, 3, 4, 5),
            new SpawnXpOrbMessage(1, 2, 3, 4, (short) 5),
            new EntityVelocityMessage(1, new Vector(1, 2, 3)),
            new EntityVelocityMessage(1, 2, 3, 4),
            new DestroyEntitiesMessage(Arrays.asList(1, 2, 3)),
            new RelativeEntityPositionMessage(1, (short) 2, (short) 3, (short) 4),
            new RelativeEntityPositionMessage(1, (short) 2, (short) 3, (short) 4, true),
            new KickMessage(ProtocolTestUtils.getTextMessage()),
            new AttachEntityMessage(1, 2, true),
            new EntityEffectMessage(1, (byte) 2, (byte) 3, 4, false),
            new EntityHeadRotationMessage(1, 2),
            new EntityMetadataMessage(1, ProtocolTestUtils.getMetadataEntry()),
            new EntityRemoveEffectMessage(1, (byte) 2),
            new EntityRotationMessage(1, 2, 3),
            new EntityRotationMessage(1, 2, 3, false),
            new EntityStatusMessage(1, 2),
            new EntityStatusMessage(1, EntityStatusMessage.ANIMAL_HEARTS),
            new EntityTeleportMessage(1, 2, 3, 4, 5, 6),
            new EntityTeleportMessage(1, 2, 3, 4, 5, 6, false),
            new RelativeEntityPositionRotationMessage(1, (short) 2, (short) 3, (short) 4, 5, 6),
            new RelativeEntityPositionRotationMessage(1, (short) 2, (short) 3, (short) 4, 5, 6, false),
            new SpawnLightningStrikeMessage(1, 2, 3, 4),
            new SpawnLightningStrikeMessage(1, 2, 3, 4, 5),
            new BlockActionMessage(1, 2, 3, 4, 5, 6),
            new BlockChangeMessage(1, 2, 3, 4),
            new BlockChangeMessage(1, 2, 3, 4, 5),
            //new ChunkBulkMessage(...),
            //new ChunkDataMessage(...),
            new ExperienceMessage(1f, 2, 3),
            //new MapDataMessage(...),
            //new MultiBlockChangeMessage(...),
            //PlayEffect
            //PlayParticle
            //PlaySound
            //SignEditor
            new StateChangeMessage(1, 2f),
            new StateChangeMessage(StateChangeMessage.Reason.GAMEMODE, 2f),
            //Statistic
            new TitleMessage(TitleMessage.Action.TITLE, new TextMessage("Title")),
            new TitleMessage(TitleMessage.Action.TIMES, 1, 3, 4),
            new TitleMessage(TitleMessage.Action.RESET),
            new UserListHeaderFooterMessage(new TextMessage("head"), new TextMessage("foot")),
            //UserListItem
            //WorldBorder
            //OpenWindow
            //SetWindowContents
            //SetWindowSlot
            //WindowProperty
            new CameraMessage(1),
            //CombatEvent
            new ResourcePackSendMessage("url", "hash"),
            new ServerDifficultyMessage(1),
            new UseBedMessage(1, 2, 3, 4),
            //TabCompleteResponse
            new UseItemMessage(0),
            new VehicleMoveMessage(1.0,2.0,3.0,4f,5f),
            new TeleportConfirmMessage(1),
    };

    public PlayProtocolTest() {
        super(new AbstractPlayProtocol.PlayProtocol(), TEST_MESSAGES);
    }
}
