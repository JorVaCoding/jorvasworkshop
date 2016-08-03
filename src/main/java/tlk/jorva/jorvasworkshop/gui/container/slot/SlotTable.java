package tlk.jorva.jorvasworkshop.gui.container.slot;

import tlk.jorva.jorvasworkshop.gui.GuiBase;
import tlk.jorva.jorvasworkshop.page.Page;
import tlk.jorva.jorvasworkshop.tileentity.TileEntityTable;

public class SlotTable extends tlk.jorva.jorvasworkshop.gui.container.slot.SlotBase {
    private tlk.jorva.jorvasworkshop.page.Page page;

    public SlotTable(TileEntityTable table, Page page, int id, int x, int y) {
        super(table, table, id, x, y);

        this.page = page;
    }

    @Override
    public boolean isVisible() {
        return super.isVisible() && (page == null || page.equals(table.getSelectedPage()));
    }

    @Override
    public int getTextureIndex(GuiBase gui) {
        return shouldSlotHighlightSelf() && shouldHighlight(this, gui.getSelectedSlot()) && gui.getSelectedSlot() instanceof SlotPlayer ? 3 : super.getTextureIndex(gui);
    }
}
