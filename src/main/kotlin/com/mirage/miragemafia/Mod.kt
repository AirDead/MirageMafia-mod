package com.mirage.miragemafia

import com.mirage.miragemafia.menu.MenuNetworking
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.text.Text
import ru.airdead.hudrenderer.HudEngine
import ru.airdead.hudrenderer.HudManager
import ru.airdead.hudrenderer.utility.*


class Mod : ClientModInitializer {
    override fun onInitializeClient() {
        HudEngine.initialize()
        MenuNetworking.initialize()

        val buttonColor = BLACK
        val defaultColor = WHITE

        val mainElement = menu {
            +rectangle {
                size = V3(402.0, 202.0)
                align = CENTER
                origin = CENTER
                color = Color(176, 165, 126, 1.0)
                +rectangle {
                    size = V3(398.0, 198.0)
                    align = CENTER
                    origin = CENTER
                    color = Color(244, 233, 194, 1.0)
                    +rectangle {
                        size = V3(392.0, 7.0)
                        align = TOP
                        origin = BOTTOM
                        color = Color(176, 165, 126, 1.0)
                        +rectangle {
                            size = V3(388.0, 5.0)
                            align = BOTTOM
                            origin = BOTTOM
                            color = Color(244, 233, 194, 1.0)
                            +rectangle {
                                size = V3(174.0, 22.0)
                                align = TOP
                                origin = CENTER
                                color = Color(172, 58, 58, 1.0)
                                +rectangle {
                                    size = V3(170.0, 18.0)
                                    align = CENTER
                                    origin = CENTER
                                    color = Color(229, 77, 77, 1.0)
                                    +text {
                                        content = "Лобби"
                                        offset = V3(-14.0, -1.0)
                                        align = CENTER
                                        origin = CENTER
                                        color = WHITE
                                    }
                                }
                            }
                        }
                    }
                    +rectangle {
                        size = V3(392.0, 7.0)
                        align = BOTTOM
                        origin = TOP
                        color = Color(176, 165, 126, 1.0)
                        +rectangle {
                            size = V3(388.0, 5.0)
                            align = TOP
                            origin = TOP
                            color = Color(244, 233, 194, 1.0)
                            +rectangle {
                                val rect1 = this
                                size = V3(140.0, 22.0)
                                align = BOTTOM
                                origin = CENTER
                                color = Color(191, 191, 191, 1.0)
                                +rectangle {
                                    val rect2 = this
                                    size = V3(136.0, 18.0)
                                    align = CENTER
                                    origin = CENTER
                                    color = Color(255, 255, 255, 1.0)
                                    +text {
                                        val text = this
                                        align = CENTER
                                        origin = CENTER
                                        offset = V3(-15.0, -2.0)
                                        content = "Начать"
                                        color = Color(73, 73, 73, 1.0)
                                        rect1.onHover {
                                            if (isHovered) {
                                                rect1.color = Color(255, 255, 255, 1.0)
                                                rect2.color = Color(191, 191, 191, 1.0)
                                                text.color = WHITE
                                            } else {
                                                rect1.color = Color(191, 191, 191, 1.0)
                                                rect2.color = Color(255, 255, 255, 1.0)
                                                text.color = Color(73, 73, 73, 1.0)
                                            }
                                        }
                                        rect1.onLeftClick {
                                            println("test")
                                        }
                                    }
                                }
                            }
                        }
                    }
                    +rectangle {
                        size = V3(350.0, 145.0)
                        offset = V3(0.0, -5.0)
                        align = CENTER
                        origin = CENTER
                        color = Color(250, 243, 221, 1.0)

                        val button1 = rectangle {
                            size = V3(5.0, 5.0)
                            align = BOTTOM
                            origin = CENTER
                            color = defaultColor
                        }

                        val button2 = rectangle {
                            size = V3(5.0, 5.0)
                            offset = V3(-10.0, 0.0)
                            align = BOTTOM
                            origin = CENTER
                            color = defaultColor
                        }

                        val button3 = rectangle {
                            size = V3(4.0, 4.0)
                            offset = V3(10.0, 0.0)
                            align = BOTTOM
                            origin = CENTER
                            color = defaultColor
                        }

                        button1.onLeftClick {
                            button1.color = buttonColor
                            button2.color = defaultColor
                            button3.color = defaultColor
                        }

                        button2.onLeftClick {
                            button1.color = buttonColor
                            button2.color = defaultColor
                            button3.color = defaultColor
                        }

                        button3.onLeftClick {
                            button1.color = buttonColor
                            button2.color = defaultColor
                            button3.color = defaultColor
                        }

                        +button1
                        +button2
                        +button3
                    }
                }
            }
        }

        CommandRegistrationCallback.EVENT.register { dispatcher, registryAccess, environment ->
            dispatcher.register(literal("foo")
                .executes { context ->
                    mainElement.show()
                    context.source.sendFeedback({ Text.literal("Called /foo with no arguments") }, false)
                    1
                })
        }


        HudManager.addElement(mainElement)
    }
}
