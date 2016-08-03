package tlk.jorva.jorvasworkshop.dependencies.waila;

import com.mojang.realmsclient.gui.ChatFormatting;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import tlk.jorva.jorvasworkshop.tileentity.TileEntityTable;

import java.util.List;

public class WailaWorkshop implements IWailaDataProvider {

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		NBTTagCompound tag = accessor.getNBTData();
		TileEntityTable table = (TileEntityTable) accessor.getTileEntity();
		int power = tag.getInteger("power");
		TextFormatting color;
		if (power <= ((table.MAX_POWER * 12.5) / 100)) {
			color = TextFormatting.DARK_RED;
		} else if (power <= ((table.MAX_POWER * 25) / 100)) {
			color = TextFormatting.RED;
		} else if (power <= ((table.MAX_POWER * 37.5) / 100)) {
			color = TextFormatting.GOLD;
		} else if (power <= ((table.MAX_POWER * 50) / 100)) {
			color = TextFormatting.YELLOW;
		} else if (power <= ((table.MAX_POWER * 62.5) / 100)) {
			color = TextFormatting.DARK_GREEN;
		} else if (power <= ((table.MAX_POWER * 75) / 100)) {
			color = TextFormatting.GREEN;
		} else if (power <= ((table.MAX_POWER * 87.5) / 100)) {
			color = TextFormatting.DARK_AQUA;
		} else {
			color = TextFormatting.AQUA;
		}
		
		
		currenttip.add(color + "Power: " + power + " / " + table.MAX_POWER);
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world,
			BlockPos pos) {
		TileEntityTable table = (TileEntityTable) te;
		tag.setInteger("power", table.getPower());
		return tag;
	}
}
