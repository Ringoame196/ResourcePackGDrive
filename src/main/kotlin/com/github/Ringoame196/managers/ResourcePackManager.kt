package com.github.Ringoame196.managers

import com.github.Ringoame196.FileManager.YmlManager
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Text
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.io.File

class ResourcePackManager(plugin: Plugin, dataFileName: String) {
    private val pluginName = plugin.name
    private val key = "resorcePackURL"
    private val dataFile = File(plugin.dataFolder, dataFileName)
    private val yml = YmlManager(dataFile)
    val resourcePackURL = yml.acquisitionStringValue(key)
    fun setResourcePack(url: String?) {
        yml.setValue(key, url)
    }
    fun loadResourcePack(player: Player) {
        if (resourcePackURL == null) player.sendMessage("${ChatColor.RED}[$pluginName] リソースパックが未設定です")
        else {
            player.setResourcePack(resourcePackURL)
            player.sendMessage("${ChatColor.GOLD}[$pluginName] リソースパックを読み込みました\n${ChatColor.RED}サーバーリソースパック無効 または BEの場合は反映されません")
        }
    }
    fun sendResourcePackLoadMessage(sender: Player) {
        // メインメッセージ部分
        val prefix = TextComponent("${ChatColor.AQUA}[$pluginName] このサーバーはリソースパックが設定されています ")

        // クリック可能な部分
        val clickMessage = TextComponent("[クリックで読み込み]")
        clickMessage.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/resourcepackgd")
        clickMessage.color = net.md_5.bungee.api.ChatColor.YELLOW
        clickMessage.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(resourcePackURL))

        // サフィックスメッセージ部分
        val suffix = TextComponent(" または /resourcepackgd")

        // メッセージを構築
        val finalMessage = TextComponent()
        finalMessage.addExtra(prefix)
        finalMessage.addExtra(clickMessage)
        finalMessage.addExtra(suffix)

        // メッセージをプレイヤーに送信
        sender.sendMessage("--------------------------------------")
        sender.spigot().sendMessage(finalMessage)
        sender.sendMessage("--------------------------------------")
    }
}
