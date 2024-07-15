package com.mirage.miragemafia.net

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.Identifier

object MessageBus {
    fun registerListener(channel: Identifier, listener: BufferHandler) {
        ClientPlayNetworking.registerGlobalReceiver(channel) { client: MinecraftClient?, handler: ClientPlayNetworkHandler?, buf: PacketByteBuf, sender: PacketSender? ->
            client?.let {
                listener.invoke(BufferContext(buf, client))
            }
        }
    }

}

typealias BufferHandler = BufferContext.() -> Unit
data class BufferContext(val buf: PacketByteBuf, val client: MinecraftClient)