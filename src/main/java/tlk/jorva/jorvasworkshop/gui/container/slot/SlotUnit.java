package tlk.jorva.jorvasworkshop.gui.container.slot;

import net.minecraft.item.ItemStack;
import tlk.jorva.jorvasworkshop.page.Page;
import tlk.jorva.jorvasworkshop.page.unit.Unit;
import tlk.jorva.jorvasworkshop.tileentity.TileEntityTable;

public class SlotUnit extends SlotTable {
    protected Unit unit;
    public SlotUnit(TileEntityTable table, Page page, int id, int x, int y, Unit unit) {
        super(table, page, id, x, y);

        this.unit = unit;
    }

    @Override
    public boolean isVisible() {
        return super.isVisible() && isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return unit.isEnabled();
    }


    @Override
    public boolean canSupplyItems() {
        return false;
    }

    @Override
    public boolean canShiftClickInto(ItemStack item) {
        return false;
    }
}
