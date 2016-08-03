package tlk.jorva.jorvasworkshop.gui.container.slot;

import net.minecraft.inventory.IInventory;
import tlk.jorva.jorvasworkshop.gui.GuiBase;
import tlk.jorva.jorvasworkshop.tileentity.TileEntityTable;

public class SlotPlayer extends SlotBase {
    public SlotPlayer(IInventory inventory, TileEntityTable table, int id, int x, int y) {
        super(inventory, table, id, x, y);
    }

    @Override
    public int getTextureIndex(GuiBase gui) {
        return shouldHighlight(gui.getSelectedSlot(), this) && gui.getSelectedSlot().shouldSlotHighlightItems() ? 3 : super.getTextureIndex(gui);
    }

    @Override
    public boolean shouldSlotHighlightItems() {
        return false;
    }
}
