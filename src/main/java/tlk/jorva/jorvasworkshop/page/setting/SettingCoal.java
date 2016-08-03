package tlk.jorva.jorvasworkshop.page.setting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;


public class SettingCoal extends tlk.jorva.jorvasworkshop.page.setting.Setting {
    private ItemStack itemStack;

    public SettingCoal(tlk.jorva.jorvasworkshop.tileentity.TileEntityTable table, int id, int x, int y) {
        super(table, id, x, y);
        itemStack = new ItemStack(Items.COAL);
    }

    @Override
    public ItemStack getItem() {
        return itemStack;
    }

    @Override
    public List<tlk.jorva.jorvasworkshop.gui.container.slot.SlotBase> getSlots() {
        List<tlk.jorva.jorvasworkshop.gui.container.slot.SlotBase> slots = new ArrayList<tlk.jorva.jorvasworkshop.gui.container.slot.SlotBase>();
        slots.add(table.getSlots().get(0));
        return slots;
    }

    @Override
    public String getName() {
        return "Fuel";
    }
}
