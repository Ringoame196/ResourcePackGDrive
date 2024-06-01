package com.github.Ringoame196.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class TabExecutor : TabCompleter {
    override fun onTabComplete(sender: CommandSender, commands: Command, label: String, args: Array<out String>): MutableList<String>? {
        return when (args.size) {
            1 -> mutableListOf("set", "reset", "sendLoadMessage")
            2 -> mutableListOf("[GoogleDrive共有リンク]")
            else -> mutableListOf()
        }
    }
}
