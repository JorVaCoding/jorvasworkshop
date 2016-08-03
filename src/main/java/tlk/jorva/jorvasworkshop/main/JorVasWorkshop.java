package tlk.jorva.jorvasworkshop.main;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import tlk.jorva.jorvasworkshop.gui.GuiHandler;
import tlk.jorva.jorvasworkshop.loaders.BlockLoader;
import tlk.jorva.jorvasworkshop.loaders.ConfigLoader;
import tlk.jorva.jorvasworkshop.loaders.CreativeTabLoader;
import tlk.jorva.jorvasworkshop.loaders.ItemLoader;
import tlk.jorva.jorvasworkshop.loaders.RecipeLoader;
import tlk.jorva.jorvasworkshop.loaders.TileEntityLoader;
import tlk.jorva.jorvasworkshop.network.PacketHandler;
import tlk.jorva.jorvasworkshop.network.proxies.CommonProxy;
import tlk.jorva.jorvasworkshop.util.Logger;
import tlk.jorva.jorvasworkshop.util.StringMap;

@Mod(modid = StringMap.ID, name = StringMap.Name, version = StringMap.VersionBuildName)
public class JorVasWorkshop {

    public static FMLEventChannel packetHandler;

    @Mod.Instance(StringMap.ID)
    public static JorVasWorkshop instance;

    @SidedProxy(modId = StringMap.ID, clientSide = StringMap.clientProxyPath, serverSide = StringMap.commonProxyPath)
    public static CommonProxy proxy;

    private double launchTime;

    
    public boolean isIC2Loaded() {
        if (Loader.isModLoaded("ic2")) {
            Logger.info("IC2 found");
            if (ConfigLoader.EUSupport) {
                Logger.info("EU support activated");
                return true;
            }
        }
        return false;
    }
    
    public boolean isTeslaLoaded() {
        if (Loader.isModLoaded("tesla")) {
            Logger.info("Tesla found");
            if (ConfigLoader.TeslaSupport) {
                Logger.info("Tesla support activated");
                return true;
            }
        }
        return false;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Pre-Initialization started");
                packetHandler = NetworkRegistry.INSTANCE.newEventDrivenChannel(StringMap.Channel);
                ConfigLoader.init(event.getSuggestedConfigurationFile());
                new CreativeTabLoader();
                BlockLoader.loadBlocks();
                ItemLoader.loadItems();
                TileEntityLoader.loadTileEntities();
                FMLInterModComms.sendMessage("Waila", "register", StringMap.WailaPath);
                proxy.preInit();
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Pre-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        Logger.info("Pre-Initialization process successfully done");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Initialization started");
                NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
                packetHandler.register(new PacketHandler());
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        Logger.info("Initialization process successfully done");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Post-Initialization started");
                RecipeLoader.loadRecipes();
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Post-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        Logger.info("Post-Initialization process successfully done");
        Logger.info("Total launch time for " + StringMap.Name + " : " + launchTime + " ms");
    }
}
