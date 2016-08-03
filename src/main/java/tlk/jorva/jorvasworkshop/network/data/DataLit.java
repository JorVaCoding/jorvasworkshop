package tlk.jorva.jorvasworkshop.network.data;

public class DataLit extends tlk.jorva.jorvasworkshop.network.data.DataBase {
    @Override
    public void save(tlk.jorva.jorvasworkshop.tileentity.TileEntityTable table, tlk.jorva.jorvasworkshop.network.DataWriter dw, int id) {
        dw.writeBoolean(table.isLit());
    }

    @Override
    public void load(tlk.jorva.jorvasworkshop.tileentity.TileEntityTable table, tlk.jorva.jorvasworkshop.network.DataReader dr, int id) {
        table.setLit(dr.readBoolean());
    }
}
