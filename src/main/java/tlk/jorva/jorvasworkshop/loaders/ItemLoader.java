package tlk.jorva.jorvasworkshop.loaders;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import net.minecraftforge.fml.common.registry.GameRegistry;
import tlk.jorva.jorvasworkshop.item.ItemUpgrade;
import tlk.jorva.jorvasworkshop.util.Logger;
import tlk.jorva.jorvasworkshop.util.StringMap;

@GameRegistry.ObjectHolder(StringMap.ID)
public final class ItemLoader {
    public static ItemUpgrade upgrade;
    public static void log(ItemUpgrade item) {Logger.info("  " + item.getUnlocalizedName() + " successfully loaded");}

    public static void loadItems() {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Loading items started");
                upgrade = new ItemUpgrade();
                    upgrade.setUnlocalizedName(StringMap.WorkshopTableUpgrade).setRegistryName(StringMap.WorkshopTableUpgrade);
                        GameRegistry.register(upgrade);
                    log(upgrade);
        Logger.info("Loading items finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }
    private ItemLoader() {}
}

