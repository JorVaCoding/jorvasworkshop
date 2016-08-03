package tlk.jorva.jorvasworkshop.gui.container.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tlk.jorva.jorvasworkshop.page.unit.UnitCrafting;


public class SlotUnitCraftingResult extends tlk.jorva.jorvasworkshop.gui.container.slot.SlotUnit {
    public SlotUnitCraftingResult(tlk.jorva.jorvasworkshop.tileentity.TileEntityTable table, tlk.jorva.jorvasworkshop.page.Page page, int id, int x, int y, tlk.jorva.jorvasworkshop.page.unit.UnitCrafting unit) {
        super(table, page, id, x, y, unit);
    }

    @Override
    public boolean isBig() {
         return true;
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return false;
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack item) {
        super.onPickupFromSlot(player, item);
        ((UnitCrafting)unit).onCrafting(player, item);
    }

    @Override
    public boolean canAcceptItems() {
        return false;
    }


    @Override
    public int getY() {
        int offset = 0;
        if (table.getUpgradePage().hasUpgrade(unit.getId(), tlk.jorva.jorvasworkshop.item.Upgrade.AUTO_CRAFTER)) {
            offset = tlk.jorva.jorvasworkshop.page.unit.UnitCrafting.RESULT_AUTO_OFFSET;
        }
        return super.getY() + offset;
    }

    @Override
    public boolean canPickUpOnDoubleClick() {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int count) {
        ItemStack itemstack = getStack();
        if (itemstack != null) {
            putStack(null);
        }
        return itemstack;
    }

    @Override
    public boolean shouldDropOnClosing() {
        return false;
    }
}
