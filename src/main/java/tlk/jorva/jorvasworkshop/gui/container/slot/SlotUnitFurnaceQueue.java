package tlk.jorva.jorvasworkshop.gui.container.slot;


import net.minecraft.item.ItemStack;
import tlk.jorva.jorvasworkshop.item.Upgrade;
import tlk.jorva.jorvasworkshop.page.Page;
import tlk.jorva.jorvasworkshop.page.unit.Unit;
import tlk.jorva.jorvasworkshop.tileentity.TileEntityTable;

public class SlotUnitFurnaceQueue extends SlotUnitFurnaceInput {
    private int queueId;
    public SlotUnitFurnaceQueue(TileEntityTable table, Page page, int id, int x, int y, Unit unit, int queueId) {
        super(table, page, id, x, y, unit);
        this.queueId = queueId;
    }

    @Override
    public boolean isVisible() {
        return isUsed() && super.isVisible();
    }

    @Override
    public boolean isEnabled() {
        return isUsed() && super.isEnabled();
    }

    private boolean isUsed() {
        return queueId < table.getUpgradePage().getUpgradeCount(unit.getId(), Upgrade.QUEUE);
    }

    @Override
    public boolean canShiftClickInto(ItemStack item) {
        return true;
    }
}
