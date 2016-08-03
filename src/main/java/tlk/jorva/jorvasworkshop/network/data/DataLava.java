package tlk.jorva.jorvasworkshop.network.data;

import tlk.jorva.jorvasworkshop.network.DataReader;
import tlk.jorva.jorvasworkshop.network.DataWriter;
import tlk.jorva.jorvasworkshop.network.IBitCount;
import tlk.jorva.jorvasworkshop.network.MaxCount;
import tlk.jorva.jorvasworkshop.tileentity.TileEntityTable;

public class DataLava extends DataBase {
    private static IBitCount LAVA_BIT_COUNT = new MaxCount(TileEntityTable.MAX_LAVA);

    @Override
    public void save(TileEntityTable table, DataWriter dw, int id) {
        dw.writeData(table.getLava(), LAVA_BIT_COUNT);
    }

    @Override
    public void load(TileEntityTable table, DataReader dr, int id) {
        table.setLava(dr.readData(LAVA_BIT_COUNT));
    }
}
