package tlk.jorva.jorvasworkshop.page.setting;

import net.minecraft.item.ItemStack;

public class ItemSetting {
    public static final int ITEM_COUNT = 10;

    private int id;
    private ItemStack item;
    private tlk.jorva.jorvasworkshop.page.setting.TransferMode mode = tlk.jorva.jorvasworkshop.page.setting.TransferMode.PRECISE;


    public ItemSetting(int id) {
        this.id = id;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public tlk.jorva.jorvasworkshop.page.setting.TransferMode getMode() {
        return mode;
    }

    public void setMode(tlk.jorva.jorvasworkshop.page.setting.TransferMode mode) {
        this.mode = mode;
    }
}
