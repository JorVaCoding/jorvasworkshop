package tlk.jorva.jorvasworkshop.loaders;

import com.google.common.base.Stopwatch;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tlk.jorva.jorvasworkshop.block.BlockWorkshopTable;
import tlk.jorva.jorvasworkshop.util.Logger;
import tlk.jorva.jorvasworkshop.util.StringMap;

import java.util.concurrent.TimeUnit;

@GameRegistry.ObjectHolder(StringMap.ID)
public final class BlockLoader {

    public static Block workshopTable, barrel;
    public static void log(Block block) {Logger.info("  " + block.getUnlocalizedName() + " successfully loaded");}

    public static void loadBlocks() {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Loading blocks started");
                workshopTable = new BlockWorkshopTable().setUnlocalizedName(StringMap.WorkshopTable).setRegistryName(StringMap.WorkshopTable);
                    GameRegistry.registerWithItem(workshopTable);
                        log(workshopTable);
        Logger.info("Loading blocks finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }
    private BlockLoader(){}
}
