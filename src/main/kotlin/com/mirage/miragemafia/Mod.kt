package com.mirage.miragemafia

import com.mirage.miragemafia.net.MessageBus
import com.mirage.miragemafia.net.ModTransferClient
import com.mirage.miragemafia.net.NetUtil
import net.fabricmc.api.ClientModInitializer
import net.minecraft.util.Identifier
import ru.airdead.hudrenderer.HudEngine

class Mod : ClientModInitializer {
    val ADD_PLAYER_CHANNEL = Identifier("queues", "addplayer")
    override fun onInitializeClient() {
        HudEngine.initialize()
        MessageBus.registerListener(ADD_PLAYER_CHANNEL) {
            val string = NetUtil.readString(buf)
            println(string)
            ModTransferClient(string).send(ADD_PLAYER_CHANNEL)
        }
    }
}