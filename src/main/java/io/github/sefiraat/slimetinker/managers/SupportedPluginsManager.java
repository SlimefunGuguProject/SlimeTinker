package io.github.sefiraat.slimetinker.managers;

import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.regex.Pattern;

@SuppressWarnings("SpellCheckingInspection")
public final class SupportedPluginsManager {

    private SupportedPluginsManager() {
        throw new IllegalStateException("Utility class");
    }

    public static final boolean INFINITY_EXPANSION;
    public static final boolean SLIMEFUN_WARFARE;
    public static final boolean DYNATECH;
    public static final boolean LITEXPANSION;
    public static final boolean TRANSCENDENCE;
    public static final boolean NETWORKS;

    public static Plugin TRANSCENDENCE_PLUGIN;
    public static Plugin INFINITY_EXPANSION_PLUGIN;
    public static boolean INFINITY_EXPANSION_VERSION = false;

    public static final String CORE_NOTE = ThemeUtils.MAIN + "粘液匠魂(SlimeTinker)";
    public static final String INFINITY_EXPANSION_NOTE = ThemeUtils.ADD_INFINITY + "无尽科技(InfinityExpansion)";
    public static final String SLIMEFUN_WARFARE_NOTE = ThemeUtils.ADD_SLIMEFUN_WARFARE + "粘液军事(Slimefun Warfare)";
    public static final String DYNATECH_NOTE = ThemeUtils.ADD_DYNATECH + "动力科技(DynaTech)";
    public static final String LITEXPANSION_NOTE = ThemeUtils.ADD_LITEXPANSION + "工业(LiteXpansion)";
    public static final String TRANSCENDENCE_NOTE = ThemeUtils.ADD_TRANSCENDENCE + "末地科技(TranscEndence)";
    public static final String NETWORKS_NOTE = ThemeUtils.ADD_NETWORKS + "网络(Networks)";

    static {
        PluginManager pluginManager = SlimeTinker.getInstance().getServer().getPluginManager();
        INFINITY_EXPANSION = pluginManager.isPluginEnabled("InfinityExpansion");
        SLIMEFUN_WARFARE = pluginManager.isPluginEnabled("SlimefunWarfare");
        DYNATECH = pluginManager.isPluginEnabled("DynaTech");
        LITEXPANSION = pluginManager.isPluginEnabled("LiteXpansion");
        TRANSCENDENCE = pluginManager.isPluginEnabled("TranscEndence");
        NETWORKS = pluginManager.isPluginEnabled("Networks");

        if (INFINITY_EXPANSION) {
            INFINITY_EXPANSION_PLUGIN = pluginManager.getPlugin("InfinityExpansion");

            String version = INFINITY_EXPANSION_PLUGIN.getDescription().getVersion();
            if (version.contains("baoad") || version.matches("\\\\d{1,6} zh-CN")) {
                INFINITY_EXPANSION_VERSION = true;
            }
        }

        if (TRANSCENDENCE) {
            TRANSCENDENCE_PLUGIN = pluginManager.getPlugin("TranscEndence");
        }
    }

    public static boolean isInfinityExpansion() {
        return INFINITY_EXPANSION;
    }

    public static boolean isSlimefunWarfare() {
        return SLIMEFUN_WARFARE;
    }

    public static boolean isDynatech() {
        return DYNATECH;
    }

    public static boolean isLitexpansion() {
        return LITEXPANSION;
    }

    public static boolean isTranscendence() {
        return TRANSCENDENCE;
    }

    public static boolean isNetworks() {
        return NETWORKS;
    }
}
