package tlk.jorva.jorvasworkshop.page.setting;


import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class Transfer {
    private boolean enabled;
    private boolean isInput;
    private boolean auto;
    private tlk.jorva.jorvasworkshop.page.setting.ItemSetting[] items;
    private boolean useWhiteList;


    public Transfer(boolean isInput) {
        this.isInput = isInput;
        items = new tlk.jorva.jorvasworkshop.page.setting.ItemSetting[tlk.jorva.jorvasworkshop.page.setting.ItemSetting.ITEM_COUNT];
        for (int i = 0; i < items.length; i++) {
            items[i] = new tlk.jorva.jorvasworkshop.page.setting.ItemSetting(i);
        }
    }

    public boolean isInput() {
        return isInput;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public boolean hasWhiteList() {
        return useWhiteList;
    }

    public void setUseWhiteList(boolean useWhiteList) {
        this.useWhiteList = useWhiteList;
    }

    public tlk.jorva.jorvasworkshop.page.setting.ItemSetting getItem(int id) {
        return items[id];
    }

    public boolean isValid(tlk.jorva.jorvasworkshop.tileentity.TileEntityTable table, ItemStack item) {
        if (item == null || !table.getUpgradePage().hasGlobalUpgrade(tlk.jorva.jorvasworkshop.item.Upgrade.FILTER)) {
            return true;
        }

        for (tlk.jorva.jorvasworkshop.page.setting.ItemSetting itemSetting : items) {
            ItemStack filterItem = itemSetting.getItem();
            if (filterItem != null) {
                boolean match = itemSetting.getMode().isMatch(item, filterItem);

                if (match) {
                    return useWhiteList;
                }
            }
        }

        return !useWhiteList;
    }

    public boolean hasFilter(tlk.jorva.jorvasworkshop.tileentity.TileEntityTable table) {
        if (table.getUpgradePage().hasGlobalUpgrade(tlk.jorva.jorvasworkshop.item.Upgrade.FILTER)) {
            for (tlk.jorva.jorvasworkshop.page.setting.ItemSetting item : items) {
                if (item.getItem() != null) {
                    return true;
                }
            }
        }

        return false;
    }

    private static final String NBT_ENABLED = "Enabled";
    private static final String NBT_AUTO = "Auto";
    private static final String NBT_WHITE_LIST = "WhiteList";
    private static final String NBT_ITEMS = "Items";
    private static final String NBT_ID = "Slot";
    private static final String NBT_MODE = "MatchMode";
    private static final int COMPOUND_ID = 10;

    public void writeToNBT(NBTTagCompound compound) {
        compound.setBoolean(NBT_ENABLED, enabled);
        if (enabled) {
            compound.setBoolean(NBT_AUTO, auto);
            compound.setBoolean(NBT_WHITE_LIST, useWhiteList);

            boolean hasItem = false;
            NBTTagList itemList = new NBTTagList();
            for (int i = 0; i < items.length; i++) {
                tlk.jorva.jorvasworkshop.page.setting.ItemSetting item = items[i];
                if (item.getItem() != null) {
                    NBTTagCompound itemCompound = new NBTTagCompound();
                    itemCompound.setByte(NBT_ID, (byte)i);
                    itemCompound.setByte(NBT_MODE, (byte)item.getMode().ordinal());
                    item.getItem().writeToNBT(itemCompound);
                    itemList.appendTag(itemCompound);
                    hasItem = true;
                }
            }
            if (hasItem) {
                compound.setTag(NBT_ITEMS, itemList);
            }
        }
    }

    public void readFromNBT(NBTTagCompound compound) {
        for (tlk.jorva.jorvasworkshop.page.setting.ItemSetting item : items) {
            item.setItem(null);
            item.setMode(tlk.jorva.jorvasworkshop.page.setting.TransferMode.PRECISE);
        }

        enabled = compound.getBoolean(NBT_ENABLED);
        if (enabled) {
            auto = compound.getBoolean(NBT_AUTO);
            useWhiteList = compound.getBoolean(NBT_WHITE_LIST);

            if (compound.hasKey(NBT_ITEMS)) {
                NBTTagList itemList = compound.getTagList(NBT_ITEMS, COMPOUND_ID);
                for (int i = 0; i < itemList.tagCount(); i++) {
                    NBTTagCompound itemCompound = itemList.getCompoundTagAt(i);
                    int id = itemCompound.getByte(NBT_ID);
                    tlk.jorva.jorvasworkshop.page.setting.ItemSetting itemSetting = items[id];
                    itemSetting.setMode(tlk.jorva.jorvasworkshop.page.setting.TransferMode.values()[itemCompound.getByte(NBT_MODE)]);
                    itemSetting.setItem(ItemStack.loadItemStackFromNBT(itemCompound));
                }
            }
        }
    }
}
