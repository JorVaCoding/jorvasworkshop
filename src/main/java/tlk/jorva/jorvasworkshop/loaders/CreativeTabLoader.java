package tlk.jorva.jorvasworkshop.loaders;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tlk.jorva.jorvasworkshop.util.StringMap;

@GameRegistry.ObjectHolder(StringMap.ID)
public class CreativeTabLoader {
    public static CreativeTabs JorVasWorkshopTab = new CreativeTabs(StringMap.Name) {
        public ItemStack getIconItemStack(){return new ItemStack(BlockLoader.workshopTable);}

        @Override
        public Item getTabIconItem() {
            return null;
        }
    };
}
