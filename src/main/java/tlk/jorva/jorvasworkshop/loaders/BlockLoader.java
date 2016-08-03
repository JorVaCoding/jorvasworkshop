package tlk.jorva.jorvasworkshop.loaders;

import com.google.common.base.Stopwatch;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tlk.jorva.jorvasworkshop.block.BlockWorkshopTable;
import tlk.jorva.jorvasworkshop.block.ItemBlockWorkshopTable;
import tlk.jorva.jorvasworkshop.util.Logger;
import tlk.jorva.jorvasworkshop.util.StringMap;

import java.util.concurrent.TimeUnit;

@GameRegistry.ObjectHolder(StringMap.ID)
public final class BlockLoader {

    public static Block workshopTable;
    public static void log(Block block) {Logger.info("  " + block.getUnlocalizedName() + " successfully loaded");}

    public static void loadBlocks() {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Loading blocks started");
                workshopTable = new BlockWorkshopTable().setUnlocalizedName(StringMap.WorkshopTable).setRegistryName(StringMap.WorkshopTable);
                    GameRegistry.register(workshopTable);
                    GameRegistry.register(new ItemBlock(workshopTable), workshopTable.getRegistryName());
                        log(workshopTable);
        Logger.info("Loading blocks finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }
    private BlockLoader(){}
}
