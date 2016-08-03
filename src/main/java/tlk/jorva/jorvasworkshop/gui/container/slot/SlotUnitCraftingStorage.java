package tlk.jorva.jorvasworkshop.gui.container.slot;

public class SlotUnitCraftingStorage extends tlk.jorva.jorvasworkshop.gui.container.slot.SlotUnit {
    public SlotUnitCraftingStorage(tlk.jorva.jorvasworkshop.tileentity.TileEntityTable table, tlk.jorva.jorvasworkshop.page.Page page, int id, int x, int y, tlk.jorva.jorvasworkshop.page.unit.Unit unit) {
        super(table, page, id, x, y, unit);
    }

    @Override
    public boolean isVisible() {
        return isAvailable() && super.isVisible();
    }

    @Override
    public boolean isEnabled() {
        return isAvailable() && super.isEnabled();
    }

    private boolean isAvailable() {
        return table.getUpgradePage().hasUpgrade(unit.getId(), tlk.jorva.jorvasworkshop.item.Upgrade.STORAGE);
    }

    @Override
    public boolean canAcceptItems() {
        return true;
    }

    @Override
    public boolean shouldSlotHighlightItems() {
        return false;
    }

    @Override
    public boolean shouldSlotHighlightSelf() {
        return false;
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        ((tlk.jorva.jorvasworkshop.page.unit.UnitCrafting)unit).onGridChanged();
    }
}
