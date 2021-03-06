package tlk.jorva.jorvasworkshop.loaders;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tlk.jorva.jorvasworkshop.item.Upgrade;
import tlk.jorva.jorvasworkshop.main.JorVasWorkshop;
import tlk.jorva.jorvasworkshop.util.Logger;
import tlk.jorva.jorvasworkshop.util.StringMap;

@GameRegistry.ObjectHolder(StringMap.ID)
public class RecipeLoader {
	private static final String STONE = "stone";
	private static final String PLANKS = "plankWood";
	private static final String COBBLE = "cobblestone";
	private static final String LAPIS = "gemLapis";
	private static final String IRON = "ingotIron";
	private static final String REDSTONE = "dustRedstone";
	private static final String GLASS = "blockGlass";
	private static final String GLOW_STONE = "dustGlowstone";
	private static final String GOLD = "ingotGold";
	private static final String REDSTONE_BLOCK = "blockRedstone";
	private static final String DIAMOND = "gemDiamond";
	private static final String WOOD = "logWood";

	public static void loadRecipes() {
		Stopwatch watch = Stopwatch.createStarted();
		Logger.info("Loading recipes started");
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockLoader.workshopTable, "PPP", "CUC", "CCC", 'P', PLANKS, 'C',
				COBBLE, 'U', Upgrade.BLANK.getItemStack()));
		addRecipe(Upgrade.BLANK, "SP", "PS", 'S', STONE, 'P', PLANKS);
		addRecipe(Upgrade.STORAGE, "C", "U", 'C', Blocks.CHEST, 'U', Upgrade.BLANK.getItemStack());
		addRecipe(Upgrade.AUTO_CRAFTER, "PPP", "CTC", "CUC", 'P', PLANKS, 'C', COBBLE, 'T', Blocks.PISTON, 'U',
				Upgrade.BLANK.getItemStack());
		addRecipe(Upgrade.CHARGED, "IRI", "IUI", "IRI", 'I', IRON, 'R', REDSTONE, 'U', Upgrade.BLANK.getItemStack());
		addRecipe(Upgrade.SPEED, "IRI", "LUL", "IRI", 'I', IRON, 'R', REDSTONE, 'L', LAPIS, 'U',
				Upgrade.BLANK.getItemStack());
		addRecipe(Upgrade.QUEUE, "PPP", "IUI", "PPP", 'I', IRON, 'P', PLANKS, 'U', Upgrade.BLANK.getItemStack());
		addRecipe(Upgrade.LAVA, "NFN", "NUN", "NNN", 'N', Blocks.NETHERRACK, 'F', Blocks.FURNACE, 'U',
				Upgrade.BLANK.getItemStack());
		addRecipe(Upgrade.SOLAR, "CCC", "IGI", "DUD", 'I', IRON, 'G', GLOW_STONE, 'C', GLASS, 'U', DIAMOND, 'D',
				Upgrade.BLANK.getItemStack());
		addRecipe(Upgrade.EFFICIENCY, "III", "FPF", "RUR", 'I', IRON, 'R', REDSTONE, 'F', Blocks.FURNACE, 'P',
				Blocks.PISTON, 'U', Upgrade.BLANK.getItemStack());
		addRecipe(Upgrade.AUTO_TRANSFER, "GGG", "HUH", "GGG", 'G', GOLD, 'H', Blocks.HOPPER, 'U',
				Upgrade.BLANK.getItemStack());
		addRecipe(Upgrade.FILTER, "III", "GBG", "IUI", 'G', Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, 'I', IRON, 'B',
				Blocks.IRON_BARS, 'U', Upgrade.BLANK.getItemStack());
		addRecipe(Upgrade.TRANSFER, "III", "GRG", "GUG", 'G', GOLD, 'I', IRON, 'R', REDSTONE_BLOCK, 'U',
				Upgrade.BLANK.getItemStack());

		if(JorVasWorkshop.instance.isTeslaLoaded()){
            addRecipe(Upgrade.TESLA, "ORO", "RDR", "ORO", 'O', IRON, 'R', REDSTONE_BLOCK, 'D', Upgrade.BLANK.getItemStack());
		}
		
		if(JorVasWorkshop.instance.isIC2Loaded()){
            addRecipe(Upgrade.TESLA, "ORO", "RDR", "ORO", 'O', GOLD, 'R', REDSTONE_BLOCK, 'D', Upgrade.BLANK.getItemStack());
		}
		
		Logger.info("Loading recipes finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
	}

	private static void addRecipe(Upgrade upgrade, Object... recipe) {
		if (upgrade.isEnabled()) {
			GameRegistry.addRecipe(new ShapedOreRecipe(upgrade.getItemStack(), recipe));
			Logger.info("   " + upgrade + " recipe loaded");
		}
	}
}
