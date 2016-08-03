package tlk.jorva.jorvasworkshop.item;

import java.util.List;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import tlk.jorva.jorvasworkshop.loaders.CreativeTabLoader;
import tlk.jorva.jorvasworkshop.loaders.ItemLoader;
import tlk.jorva.jorvasworkshop.main.JorVasWorkshop;
import tlk.jorva.jorvasworkshop.util.StringMap;

public class ItemUpgrade extends Item {

    public ItemUpgrade() {
        setCreativeTab(CreativeTabLoader.JorVasWorkshopTab);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        Upgrade upgrade = getUpgrade(item);
        return StringMap.ItemPath + "." + (upgrade != null ? upgrade.getUnlocalizedName() : "unknown");
    }

    public Upgrade getUpgrade(int dmg) {
        if (dmg >= 0 && dmg < Upgrade.values().length) {
            return Upgrade.values()[dmg];
        }else{
            return null;
        }
    }

    public Upgrade getUpgrade(ItemStack item) {
        if (item != null && ItemLoader.upgrade.equals(item.getItem())) {
            return getUpgrade(item.getItemDamage());
        }else{
            return null;
        }
    }


    @Override
    public void getSubItems(Item item, CreativeTabs tab, List lst) {
        for (int i = 0; i < Upgrade.values().length; ++i) {
			Upgrade[] upgrades = Upgrade.values().clone();
			lst.add(upgrades[i].getItemStack());
		}
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack item, EntityPlayer player, List lst, boolean useExtraInfo) {
        Upgrade upgrade = getUpgrade(item);
        if (upgrade != null) {
            upgrade.addInfo(lst);
        }else{
            lst.add(TextFormatting.RED + "This is not a valid item");
        }
    }
    
    static void registerItemModel(Item i, int meta, String variant) {
		ResourceLocation loc = i.getRegistryName();
		ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "type=" + variant));
	}
}
