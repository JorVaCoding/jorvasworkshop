package tlk.jorva.jorvasworkshop.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockWorkshopTable extends ItemBlock{

	public ItemBlockWorkshopTable() {
		super(new BlockWorkshopTable());
		setRegistryName("itemBlockWorkshopTable");
	}

}
