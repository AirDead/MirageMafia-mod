package com.mirage.miragemafia.menu

import com.mirage.miragemafia.player.Player
import java.util.*

class Menu(val id: Int, val owner: Player) {
    val players: MutableList<Player> = LinkedList()
}