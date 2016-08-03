package tlk.jorva.jorvasworkshop.gui.container.slot;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;


public class SlotUnitFurnaceInput extends tlk.jorva.jorvasworkshop.gui.container.slot.SlotUnit {
    public SlotUnitFurnaceInput(tlk.jorva.jorvasworkshop.tileentity.TileEntityTable table, tlk.jorva.jorvasworkshop.page.Page page, int id, int x, int y, tlk.jorva.jorvasworkshop.page.unit.Unit unit) {
        super(table, page, id, x, y, unit);
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return super.isItemValid(itemstack) && FurnaceRecipes.instance().getSmeltingResult(itemstack) != null;
    }

    @Override
    public boolean canShiftClickInto(ItemStack item) {
        return true;
    }
}
