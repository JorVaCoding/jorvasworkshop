package tlk.jorva.jorvasworkshop.loaders;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import net.minecraftforge.fml.common.registry.GameRegistry;
import tlk.jorva.jorvasworkshop.tileentity.TileEntityTable;
import tlk.jorva.jorvasworkshop.util.Logger;
import tlk.jorva.jorvasworkshop.util.StringMap;

@GameRegistry.ObjectHolder(StringMap.ID)
public class TileEntityLoader {

    public static void log(Class tileEntity) {Logger.info("  " + tileEntity + " successfully loaded");}

    public static void loadTileEntities() {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Loading tile entities started");
                GameRegistry.registerTileEntity(TileEntityTable.class, StringMap.WorkshopTable);
                    log(TileEntityTable.class);
        Logger.info("Loading tile entities finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }
}
