package tlk.jorva.jorvasworkshop.network.data;

import tlk.jorva.jorvasworkshop.network.DataReader;
import tlk.jorva.jorvasworkshop.network.DataWriter;
import tlk.jorva.jorvasworkshop.network.IBitCount;
import tlk.jorva.jorvasworkshop.network.MaxCount;
import tlk.jorva.jorvasworkshop.tileentity.TileEntityTable;

public class DataFuel extends DataBase {
    private static IBitCount FUEL_BIT_COUNT = new MaxCount(TileEntityTable.MAX_POWER);

    @Override
    public void save(TileEntityTable table, DataWriter dw, int id) {
        dw.writeData(table.getPower(), FUEL_BIT_COUNT);
    }

    @Override
    public void load(TileEntityTable table, DataReader dr, int id) {
        table.setPower(dr.readData(FUEL_BIT_COUNT));
    }
}
