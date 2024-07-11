package com.mirage.miragemafia.menu

import com.mirage.miragemafia.net.MessageBus
import com.mirage.miragemafia.net.NetUtil
import com.mirage.miragemafia.net.NetworkingConstants.ADD_PLAYER
import com.mirage.miragemafia.net.NetworkingConstants.CREATE_MENU
import com.mirage.miragemafia.net.NetworkingConstants.REMOVE_PLAYER
import com.mirage.miragemafia.player.Player
import io.netty.buffer.Unpooled
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.network.PacketByteBuf
import java.nio.charset.StandardCharsets
import java.util.*

object MenuNetworking {
    private val menus = Collections.synchronizedList(LinkedList<Menu>())

    fun initialize() {
        MessageBus.registerListener(CREATE_MENU) {
            menus.add(Menu(NetUtil.readInt(it), Player(NetUtil.readString(it))))
            val buf = PacketByteBuf(Unpooled.buffer())
            buf.writeInt(10)
            val stringValue = "Hello"
            buf.writeInt(stringValue.length)
            buf.writeCharSequence(stringValue, StandardCharsets.UTF_8)
            ClientPlayNetworking.send(REMOVE_PLAYER, buf)
        }

        MessageBus.registerListener(ADD_PLAYER) {
            val id = NetUtil.readInt(it)
            val playerName = NetUtil.readString(it)
            menus.find { it.id == id }?.let { menu ->
                if (menu.players.size < 24) {
                    menu.players.add(Player(playerName))
                } else {
                    println("Cannot add player: Maximum number of players reached.")
                }
            } ?: println("Menu with ID $id not found.")
        }

        MessageBus.registerListener(REMOVE_PLAYER) {
            val id = NetUtil.readInt(it)
            val playerName = NetUtil.readString(it)
            menus.find { it.id == id }?.players?.removeIf { it.name == playerName }
                ?: println("Menu with ID $id not found.")
        }
    }
}