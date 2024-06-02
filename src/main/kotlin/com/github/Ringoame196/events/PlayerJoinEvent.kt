package com.github.Ringoame196.events

import com.github.Ringoame196.managers.ResourcePackManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin

class PlayerJoinEvent(plugin: Plugin, dataFileName: String) : Listener {
    private val resourcePackManager = ResourcePackManager(plugin, dataFileName)
    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        resourcePackManager.id ?: return // 未設定なら実行しない
        val player = e.player
        // 新規参加者にリソースパック読み込みメッセージを送信する
        resourcePackManager.sendResourcePackLoadMessage(player)
    }
}
