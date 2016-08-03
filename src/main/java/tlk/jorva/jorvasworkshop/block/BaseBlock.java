package tlk.jorva.jorvasworkshop.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import tlk.jorva.jorvasworkshop.loaders.ConfigLoader;
import tlk.jorva.jorvasworkshop.loaders.CreativeTabLoader;
import tlk.jorva.jorvasworkshop.tileentity.BaseTileEntity;
import tlk.jorva.jorvasworkshop.util.Logger;

public class BaseBlock extends BlockContainer {

    public BaseBlock() {
        this(Material.ROCK);
    }

    public BaseBlock(Material material) {
        super(material);
        setCreativeTab(CreativeTabLoader.JorVasWorkshopTab);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    	this.dropInventory(worldIn, pos.getX(), pos.getY(), pos.getZ());
    	super.breakBlock(worldIn, pos, state);
    }

    public static int blockFacing;

    public static int getBlockFacing() {
        return blockFacing;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
    		ItemStack stack) {
    	super.onBlockPlacedBy(world, pos, state, placer, stack);
        if (world.getTileEntity(pos) instanceof BaseTileEntity || world.getBlockState(pos).getBlock() instanceof Block) {
            EnumFacing direction = null;
            int facing = MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            switch (facing) {
                case 0: direction = EnumFacing.NORTH; break;
                case 1: direction = EnumFacing.EAST; break;
                case 2: direction = EnumFacing.SOUTH; break;
                case 3: direction = EnumFacing.WEST; break;
                default: direction = null;
            }
            ((BaseTileEntity) world.getTileEntity(pos)).setOrientation(direction);
            blockFacing = direction.ordinal();
            if (ConfigLoader.debugMode) {Logger.info(direction.name());}
        }
    }

    protected void dropInventory(World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if(tileEntity instanceof IInventory) {
            IInventory inventory = (IInventory) tileEntity;
            for(int i = 0; i < inventory.getSizeInventory();i++) {
                ItemStack itemStack = inventory.getStackInSlot(i);
                if (itemStack != null && itemStack.stackSize > 0) {
                    Random random = new Random();
                    float dX = random.nextFloat() * 0.8F + 0.1F;
                    float dY = random.nextFloat() * 0.8F + 0.1F;
                    float dZ = random.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityItem = new EntityItem(world, (double)((float)x + dX), (double)((float)y + dY), (double)((float)z + dZ), itemStack.copy());
                    if (itemStack.hasTagCompound()) {
                        entityItem.getEntityItem().setTagCompound((NBTTagCompound)itemStack.getTagCompound().copy());
                    }
                    float factor = 0.05F;
                    entityItem.motionX = random.nextGaussian() * (double)factor;
                    entityItem.motionX = random.nextGaussian() * (double)factor + 0.20000000298023224D;
                    entityItem.motionX = random.nextGaussian() * (double)factor;
                    world.spawnEntityInWorld(entityItem);
                    itemStack.stackSize = 0;
                }

            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return null;
    }
}
