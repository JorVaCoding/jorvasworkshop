package tlk.jorva.jorvasworkshop.page;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Page {
    private String name;
    protected tlk.jorva.jorvasworkshop.tileentity.TileEntityTable table;
    private int id;


    public Page(tlk.jorva.jorvasworkshop.tileentity.TileEntityTable table, String name) {
        this.id = table.getPages().size();
        this.table = table;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int createSlots(int id);

    protected void addSlot(tlk.jorva.jorvasworkshop.gui.container.slot.SlotBase slot) {
        table.addSlot(slot);
    }

    @SideOnly(Side.CLIENT)
    public void draw(tlk.jorva.jorvasworkshop.gui.GuiBase gui, int mX, int mY) {
        gui.drawString(name, 8, 6, 0x404040);
    }
    @SideOnly(Side.CLIENT)
    public void onClick(tlk.jorva.jorvasworkshop.gui.GuiBase gui, int mX, int mY, int button) {}

    public int getId() {
        return id;
    }

    public void onUpdate() {}

    @SideOnly(Side.CLIENT)
    public void onRelease(tlk.jorva.jorvasworkshop.gui.GuiTable gui, int mX, int mY, int button) {}
}
