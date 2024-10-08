package io.github.sefiraat.slimetinker;

import java.text.MessageFormat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.sefiraat.slimetinker.itemgroups.ItemGroups;
import io.github.sefiraat.slimetinker.items.Casts;
import io.github.sefiraat.slimetinker.items.Dies;
import io.github.sefiraat.slimetinker.items.Guide;
import io.github.sefiraat.slimetinker.items.Materials;
import io.github.sefiraat.slimetinker.items.Mods;
import io.github.sefiraat.slimetinker.items.Parts;
import io.github.sefiraat.slimetinker.items.Workstations;
import io.github.sefiraat.slimetinker.items.tinkermaterials.TinkerMaterialManager;
import io.github.sefiraat.slimetinker.items.workstations.workbench.Workbench;
import io.github.sefiraat.slimetinker.listeners.ListenerManager;
import io.github.sefiraat.slimetinker.managers.DispatchManager;
import io.github.sefiraat.slimetinker.managers.MemoryManager;
import io.github.sefiraat.slimetinker.managers.TraitManager;
import io.github.sefiraat.slimetinker.runnables.RunnableManager;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import net.guizhanss.guizhanlibplugin.updater.GuizhanUpdater;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class SlimeTinker extends JavaPlugin implements SlimefunAddon {

    public static final int RUNNABLE_TICK_RATE = 40;

    private static SlimeTinker instance;

    private final String username;
    private final String repo;
    private final String branch;

    private RunnableManager runnableManager;
    private ListenerManager listenerManager;
    private TinkerMaterialManager tinkerMaterialManager;
    private DispatchManager dispatchManager;
    private Workbench workbench;
    private TraitManager traitManager;
    private MemoryManager memoryManager;

    public SlimeTinker() {
        this.username = "SlimefunGuguProject";
        this.repo = "SlimeTinker";
        this.branch = "master";
    }

    @Override
    public void onEnable() {

        if (!getServer().getPluginManager().isPluginEnabled("GuizhanLibPlugin")) {
            getLogger().log(Level.SEVERE, "本插件需要 鬼斩前置库插件(GuizhanLibPlugin) 才能运行!");
            getLogger().log(Level.SEVERE, "从此处下载: https://50l.cc/gzlib");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        new Metrics(this, 11748);

        instance = this;

        getLogger().info("########################################");
        getLogger().info("         Slime Tinker  粘液匠魂          ");
        getLogger().info("       作者: Sefiraat 汉化: ybw0014      ");
        getLogger().info("########################################");

        ItemGroups.set(this);
        Materials.set(this);
        Dies.set(this);
        Casts.set(this);
        Parts.set(this);
        Guide.set(this);
        Mods.set(this);
        Workstations.set(this);

        traitManager = new TraitManager();
        tinkerMaterialManager = new TinkerMaterialManager();
        runnableManager = new RunnableManager();
        dispatchManager = new DispatchManager();
        memoryManager = new MemoryManager();

        this.listenerManager = new ListenerManager(this, this.getServer().getPluginManager());

        if (getConfig().getBoolean("auto-update") && getDescription().getVersion().startsWith("Build")) {
            GuizhanUpdater.start(this, getFile(), username, repo, branch);
        }
    }

    @Override
    public void onDisable() {
        saveConfig();
        instance = null;
    }

    public RunnableManager getRunnableManager() {
        return runnableManager;
    }

    public ListenerManager getListenerManager() {
        return listenerManager;
    }

    public TinkerMaterialManager getCmManager() {
        return tinkerMaterialManager;
    }

    public DispatchManager getDispatchManager() {
        return dispatchManager;
    }

    public Workbench getWorkbench() {
        return workbench;
    }

    public void setWorkbench(Workbench workbench) {
        this.workbench = workbench;
    }

    public TraitManager getTraitManager() {
        return traitManager;
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nullable
    @Override
    public String getBugTrackerURL() {
        return MessageFormat.format("https://github.com/{0}/{1}/issues/", this.username, this.repo);
    }

    public static SlimeTinker getInstance() {
        return instance;
    }
}
