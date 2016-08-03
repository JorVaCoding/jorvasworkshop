package tlk.jorva.jorvasworkshop.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import tlk.jorva.jorvasworkshop.util.StringMap;

public class BaseTileEntity extends TileEntity {

	public BaseTileEntity() {
		orientation = getOrientation();
	}

	protected EnumFacing orientation;

	public EnumFacing getOrientation() {
		return orientation;
	}

	public void setOrientation(EnumFacing orientation) {
		this.orientation = orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = EnumFacing.byName(orientation);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		if (nbtTagCompound.hasKey(StringMap.NBTDirection)) {
			this.orientation = EnumFacing.byName(nbtTagCompound.getString(StringMap.NBTDirection));
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setString(StringMap.NBTDirection, orientation.name());
		return nbtTagCompound;
	}
}
