package kr.kro.narileein05.pack;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static JavaPlugin pl;
    public static String prefix = "§f[§9Plugin§f]";

    @Override
    public void onEnable() {
        pl = this;
        getCommand("cmd").setExecutor(new CMD());
    }

    @Override
    public void onDisable() {
    }
}
