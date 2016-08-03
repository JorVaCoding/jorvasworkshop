package tlk.jorva.jorvasworkshop.network.data;

public abstract class DataBase {

    public abstract void save(tlk.jorva.jorvasworkshop.tileentity.TileEntityTable table, tlk.jorva.jorvasworkshop.network.DataWriter dw, int id);
    public abstract void load(tlk.jorva.jorvasworkshop.tileentity.TileEntityTable table, tlk.jorva.jorvasworkshop.network.DataReader dr, int id);
    public boolean shouldBounce(tlk.jorva.jorvasworkshop.tileentity.TileEntityTable table) {
        return true;
    }
    public boolean shouldBounceToAll(tlk.jorva.jorvasworkshop.tileentity.TileEntityTable table) {
        return false;
    }
}
