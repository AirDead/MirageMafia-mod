package com.mirage.miragemafia.net

import net.minecraft.network.PacketByteBuf
import java.nio.charset.StandardCharsets

object NetUtil {
    fun readInt(buf: PacketByteBuf): Int = buf.readInt()
    fun readDouble(buf: PacketByteBuf): Double = buf.readDouble()
    fun readBoolean(buf: PacketByteBuf): Boolean = buf.readBoolean()
    fun readFloat(buf: PacketByteBuf): Float = buf.readFloat()
    fun readLong(buf: PacketByteBuf): Long = buf.readLong()
    fun readString(buf: PacketByteBuf): String {
        val length = buf.readInt()
        val strBytes = ByteArray(length)
        buf.readBytes(strBytes)
        return String(strBytes, StandardCharsets.UTF_8)
    }
}
