package tlk.jorva.jorvasworkshop.item;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tlk.jorva.jorvasworkshop.loaders.ItemLoader;
import tlk.jorva.jorvasworkshop.main.JorVasWorkshop;

public enum Upgrade {
    BLANK("Blank Upgrade", "Crafting component", new MaxCount(0), (ParentType)null, "blank"),
    AUTO_CRAFTER("Auto Crafter", "Convert a crafting table into an auto crafting table", new MaxCount(1), ParentType.CRAFTING, "auto_crafter"),
    STORAGE("Extra Storage", "Adds extra storage", new MaxCount(1), ParentType.CRAFTING, "storage"),
    CHARGED("Charger", "Let idle components charge up for later", new ConfigurableMax(8), "charged"),
    SPEED("Production Speed", "Increase the production speed", new ConfigurableMax(8), "speed"),
    QUEUE("Input Queue", "Adds an input queue", new MaxCount(3), ParentType.SMELTING, "queue"),
    EFFICIENCY("Fuel Efficiency", "Improves the fuel efficiency of solid fuel types", new ConfigurableMax(4), ParentType.GLOBAL, "efficiency"),
    LAVA("Lava Generator", "Allows lava to be used as fuel", new MaxCount(1), ParentType.GLOBAL, "lava"),
    TESLA("Tela Acceptor", "Allows tesla to be used as fuel", new MaxCount(1), ParentType.GLOBAL, "tesla"),
    SOLAR("Solar Generator", "Allows the table to be charged by solar power", new ConfigurableMax(1),  ParentType.GLOBAL, "solar"),
    EU("EU Acceptor", "Allows the table to be charged by EU", new ConfigurableMax(1),  ParentType.GLOBAL, "eu"),
    AUTO_TRANSFER("Auto Transfer", "Enables auto transfer to and from the table", new MaxCount(1), ParentType.GLOBAL, "auto_transfer"),
    FILTER("Filter", "Enables transfer filters", new MaxCount(1),  ParentType.GLOBAL, "filter"),
    TRANSFER("Transfer Capacity", "Increases the automatic transfer capacity", new ConfigurableMax(6, 20), ParentType.GLOBAL, "transfer");

    /**PATTERN("Pattern Crafting", "Remembers old recipes", 4, ParentType.CRAFTING),
    RESTOCK("Restock Control", "Only produce more items when there isn't enough of them", 1),*/

    private String unlocalizedName;
    private String name;
    private String description;
    private MaxCount maxCount;
    private EnumSet<ParentType> validParents;
    private String json;

    Upgrade(String name, String description, MaxCount maxCount, EnumSet<ParentType> validParents, String json) {
        this.name = name;
        this.validParents = validParents;
        this.unlocalizedName = toString().toLowerCase();
        this.description = description;
        this.maxCount = maxCount;
        this.json = json;
        maxCount.init(this);
    }

    Upgrade(String name, String description, MaxCount maxCount, ParentType type, String json) {
        this(name, description, maxCount, type == null ? EnumSet.noneOf(ParentType.class) : EnumSet.of(type), json);
    }

    Upgrade(String name, String description, MaxCount maxCount, String json) {
        this(name, description, maxCount, EnumSet.of(ParentType.CRAFTING, ParentType.SMELTING), json);
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }


    public boolean isEnabled() {
        return maxCount.getConfigurableMax() == 0 || maxCount.getMax() > 0;
    }

    public String getName() {
        return name;
    }

    public ItemStack getItemStack() {
        return new ItemStack(ItemLoader.upgrade, 1, ordinal());
    }

    public static ItemStack getInvalidItemStack() {
        return new ItemStack(ItemLoader.upgrade, 1, values().length);
    }

    public void addInfo(List<String> info) {
        JorVasWorkshop jworkshop = JorVasWorkshop.instance;
        info.add(TextFormatting.GRAY + description);
        if (GuiScreen.isShiftKeyDown()) {
            if (getMaxCount() == 1) {
                info.add(TextFormatting.YELLOW + "Doesn't stack well");
            }else if (getMaxCount() > 1) {
                info.add(TextFormatting.YELLOW + "Stacks well up to " + getMaxCount() + " items");
            }

            for (ParentType validParent : validParents) {
                info.add(TextFormatting.GOLD + validParent.description);
            }
        }
    }

    public boolean isValid(ItemStack parent) {
        for (ParentType validParent : validParents) {
            if (validParent.isValidParent(parent)) {
                return true;
            }
        }
        return false;
    }

    public int getMaxCount() {
        return maxCount.getMax();
    }

    public MaxCount getMaxCountObject() {
        return maxCount;
    }

    public enum ParentType {
        CRAFTING("Works with Crafting Tables") {
            @Override
            protected boolean isValidParent(ItemStack item) {
                return item != null && Item.getItemFromBlock(Blocks.CRAFTING_TABLE).equals(item.getItem());
            }
        },
        SMELTING("Works with Furnaces") {
            @Override
            protected boolean isValidParent(ItemStack item) {
                return item != null && Item.getItemFromBlock(Blocks.FURNACE).equals(item.getItem());
            }
        },
        GLOBAL("Upgrades the entire Production Table") {
            @Override
            protected boolean isValidParent(ItemStack item) {
                return item == null;
            }
        };

        private String description;

        ParentType(String description) {
            this.description = description;
        }

        protected abstract boolean isValidParent(ItemStack item);
    }

    public static class MaxCount {
        private int max;
        private int defaultMax;

        public MaxCount(int max) {
            this.max = max;
            this.defaultMax = max;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int value) {
            this.max = value;
        }

        public int getConfigurableMax() {
            return defaultMax;
        }

        public void init(Upgrade upgrade) {

        }
    }

    private static class ConfigurableMax extends MaxCount {
        private boolean isGlobal;
        private int configurableMax;
        private ConfigurableMax(int max, int configurableMax) {
            super(max);
            this.configurableMax = configurableMax;
        }
        private ConfigurableMax(int max) {
            this(max, -1);
        }

        private static final int GLOBAL_MAX_COUNT = 8 * 64;
        private static final int MAX_COUNT = 7 * 64;
        @Override
        public int getConfigurableMax() {
            return configurableMax != -1 ? configurableMax : isGlobal ? GLOBAL_MAX_COUNT : MAX_COUNT;
        }

        @Override
        public void init(Upgrade upgrade) {
            isGlobal = upgrade.validParents.contains(ParentType.GLOBAL);
        }
    }
    
    public String getJson(){
    	return json;
    }

 }
