package com.mirage.miragemafia.net

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.minecraft.client.MinecraftClient
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.Identifier

class ModTransferClient(vararg params: Any) {
    private val buffer: PacketByteBuf = PacketByteBufs.create()

    init {
        params.forEach { param ->
            when (param) {
                is Int -> buffer.writeInt(param)
                is Double -> buffer.writeDouble(param)
                is Boolean -> buffer.writeBoolean(param)
                is Float -> buffer.writeFloat(param)
                is Long -> buffer.writeLong(param)
                is String -> {
                    val strBytes = param.toByteArray(Charsets.UTF_8)
                    buffer.writeInt(strBytes.size)
                    buffer.writeBytes(strBytes)
                }
                else -> throw IllegalArgumentException("Unsupported type: ${param::class.simpleName}")
            }
        }
    }

    fun send(channel: Identifier) {
        val client = MinecraftClient.getInstance()
        if (client.networkHandler != null) {
            ClientPlayNetworking.send(channel, buffer)
        }
    }
}