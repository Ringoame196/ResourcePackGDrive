package com.github.Ringoame196

import com.github.Ringoame196.commands.TabExecutor
import com.github.Ringoame196.events.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        val plugin = this
        val dataFileName = "data.yml"
        val command = "resourcepackgd"
        saveResource(dataFileName, false)
        server.pluginManager.registerEvents(PlayerJoinEvent(plugin, dataFileName), plugin)
        getCommand(command)!!.setExecutor(ResourcePackGD(plugin, dataFileName))
        getCommand(command)!!.setTabCompleter(TabExecutor())
    }
}
