package tlk.jorva.jorvasworkshop.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import tlk.jorva.jorvasworkshop.loaders.CreativeTabLoader;
import tlk.jorva.jorvasworkshop.main.JorVasWorkshop;
import tlk.jorva.jorvasworkshop.tileentity.TileEntityTable;

public class BlockWorkshopTable extends BaseBlock {

	public BlockWorkshopTable() {
		setHardness(3.5F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityTable();
	}

	// @SideOnly(Side.CLIENT)
	// @Override
	// public void registerBlockIcons(IIconRegister register) {
	// icons = new IIcon[] {
	// register.registerIcon("production:bot"),
	// register.registerIcon("production:top"),
	// register.registerIcon("production:back"),
	// register.registerIcon("production:front0"),
	// register.registerIcon("production:left"),
	// register.registerIcon("production:right"),
	// };
	// for (int i = 0; i < 8; i++) {
	// front = new IIcon[]{
	// register.registerIcon("production:front" + i),
	// };
	// }
	// }

	public static int getSideFromSideAndMeta(int side, int meta) {
		if (side <= 1) {
			return side;
		} else {
			int index = SIDES_INDICES[side - 2] - meta;
			if (index < 0) {
				index += SIDES.length;
			}
			return SIDES[index] + 2;
		}
	}

	public static int getSideFromSideAndMetaReversed(int side, int meta) {
		if (side <= 1) {
			return side;
		} else {
			int index = SIDES_INDICES[side - 2] + meta;
			index %= SIDES.length;

			return SIDES[index] + 2;
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			FMLNetworkHandler.openGui(playerIn, JorVasWorkshop.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}

	private static final int[] SIDES_INDICES = { 0, 2, 3, 1 };
	private static final int[] SIDES = { 0, 3, 1, 2 };

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		int rotation = MathHelper.floor_double((placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity te = world.getTileEntity(pos);

		if (te instanceof IInventory) {
			IInventory inventory = (IInventory) te;
			for (int i = 0; i < inventory.getSizeInventory(); ++i) {
				ItemStack item = ((TileEntityTable) inventory).getStackInSlotOnClosing(i);

				if (item != null) {
					float offsetX = world.rand.nextFloat() * 0.8F + 0.1F;
					float offsetY = world.rand.nextFloat() * 0.8F + 0.1F;
					float offsetZ = world.rand.nextFloat() * 0.8F + 0.1F;

					EntityItem entityItem = new EntityItem(world, pos.getX() + offsetX, pos.getY() + offsetY, pos.getY() + offsetZ, item.copy());
					entityItem.motionX = world.rand.nextGaussian() * 0.05F;
					entityItem.motionY = world.rand.nextGaussian() * 0.05F + 0.2F;
					entityItem.motionZ = world.rand.nextGaussian() * 0.05F;

					world.spawnEntityInWorld(entityItem);
				}
			}
		}
		super.breakBlock(world, pos,state);
	}
	
}
