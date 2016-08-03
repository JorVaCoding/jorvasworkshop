package tlk.jorva.jorvasworkshop.network.data;

import tlk.jorva.jorvasworkshop.network.BasicCount;
import tlk.jorva.jorvasworkshop.network.DataReader;
import tlk.jorva.jorvasworkshop.network.DataWriter;
import tlk.jorva.jorvasworkshop.network.IBitCount;
import tlk.jorva.jorvasworkshop.tileentity.TileEntityTable;

public class DataPage extends DataBase {
    private static final IBitCount BITS = new BasicCount(2);

    @Override
    public void save(TileEntityTable table, DataWriter dw, int id) {
        dw.writeData(table.getSelectedPage().getId(), BITS);
    }

    @Override
    public void load(TileEntityTable table, DataReader dr, int id) {
        table.setSelectedPage(table.getPages().get(dr.readData(BITS)));
    }
}
