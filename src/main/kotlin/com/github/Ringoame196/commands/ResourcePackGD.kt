
package com.github.Ringoame196

import com.github.Ringoame196.managers.GoogleDriveManager
import com.github.Ringoame196.managers.ResourcePackManager
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class ResourcePackGD(private val plugin: Plugin, private val dataFileName: String) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        // /resourcepackgd単体で実行した場合は読み込ませる処理を実行する
        if (args.isEmpty()) {
            // リソースパックを読みこませる
            if (sender !is Player) return false
            val resourcePackManager = ResourcePackManager(plugin, dataFileName)
            resourcePackManager.loadResourcePack(sender)
            return true
        }

        if (!sender.isOp) { // OPのみ実行可能にする
            sender.sendMessage("${ChatColor.RED}権限がありません")
            return true
        }
        val subCommand = args[0]

        when (args.size) {
            1 -> {
                // サブコマンドだけで実行するコマンド
                when (subCommand) {
                    "reset" -> resetResourcePackURL(sender)
                    "sendLoadMessage" -> sendResourcePackLoadMessageAllPlayer()
                }
                return true
            }
            2 -> {
                val url = args[1]
                when (subCommand) {
                    "set" -> setResourcePackURL(url, sender)
                }
                return true
            }
            else -> return false
        }
    }
    private fun resetResourcePackURL(sender: CommandSender) {
        // リソースパックをリセットする処理
        val resourcePackManager = ResourcePackManager(plugin, dataFileName)
        resourcePackManager.setResourcePack(null)
        sender.sendMessage("${ChatColor.RED}リソースパックをリセットしました")
    }
    private fun setResourcePackURL(url: String, sender: CommandSender) {
        // リソースパックを設定する
        val googleDriveManager = GoogleDriveManager()
        if (!googleDriveManager.checkGoogleDriveURL(url)) {
            sender.sendMessage("${ChatColor.RED}このURLはGoogleドライブの共有リンクではありません")
            return
        }
        val resourcePackManager = ResourcePackManager(plugin, dataFileName)
        val id = googleDriveManager.cutID(url)
        resourcePackManager.setResourcePack(id)
        sendResourcePackLoadMessageAllPlayer()
    }
    private fun sendResourcePackLoadMessageAllPlayer() {
        val resourcePackManager = ResourcePackManager(plugin, dataFileName)
        // リソースパック読み込ませるメッセージを全員に送る
        for (player in Bukkit.getOnlinePlayers()) {
            resourcePackManager.sendResourcePackLoadMessage(player)
        }
    }
}
