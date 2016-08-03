package tlk.jorva.jorvasworkshop.network.proxies;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import tlk.jorva.jorvasworkshop.item.Upgrade;
import tlk.jorva.jorvasworkshop.loaders.BlockLoader;
import tlk.jorva.jorvasworkshop.loaders.ItemLoader;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit() {
		super.preInit();
		for (int i = 0; i < Upgrade.values().length; ++i) {
			Upgrade[] upgrades = Upgrade.values().clone();
				ModelLoader.setCustomModelResourceLocation(ItemLoader.upgrade, i, new ModelResourceLocation("jorvasworkshop:" + upgrades[i].getJson()));
		}
		
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BlockLoader.workshopTable), 0, new ModelResourceLocation(BlockLoader.workshopTable.getRegistryName(), "inventory"));
	}
	
	@Override
	public boolean isClient() {
		return true;
	}
}
