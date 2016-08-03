package tlk.jorva.jorvasworkshop.loaders;

import java.io.File;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tlk.jorva.jorvasworkshop.item.Upgrade;
import tlk.jorva.jorvasworkshop.util.Logger;
import tlk.jorva.jorvasworkshop.util.StringMap;

@GameRegistry.ObjectHolder(StringMap.ID)
public final class ConfigLoader {

	private static final String MAX_COUNT_SUFFIX = " maximum amount";
	public static boolean doRenderSpinningEntity, debugMode;
	public static int MAX_POWER, MAX_LAVA, MAX_LAVA_DRAIN, LAVA_EFFICIENCY, SOLAR_GENERATION, FUEL_DELAY;
	
	//IC2
	public static int EU_BUFFER, EU_CONVERSION_RATE, EU_CONVERSION_RATIO;
	public static boolean EUSupport;
	
	//TESLA
	public static int TESLA_BUFFER, TESLA_CONVERSION_RATE, TESLA_CONVERSION_RATIO;
	public static boolean TeslaSupport;
	
	public static void init(File file) {
		String spacing = "  ";
		Stopwatch watch = Stopwatch.createStarted();
		Logger.info("Loading configs started");
		Configuration config = new Configuration(file);
		Logger.info(spacing + "Reading " + config);
		config.load();
		MAX_POWER = config.getInt("Max Energy", StringMap.ConfigCategoryTweaks, 30000, 0, Integer.MAX_VALUE,
				"Sets the max number of energy storage in the workshop table");
		Logger.info(spacing + "Max Power = " + MAX_POWER);
		MAX_LAVA = config.getInt("Max Lava", StringMap.ConfigCategoryTweaks, 1000, 0, Integer.MAX_VALUE,
				"Sets the max number of lava energy storage in the workshop table");
		Logger.info(spacing + "Max Lava = " + MAX_LAVA);
		MAX_LAVA_DRAIN = config.getInt("Max Lava Drain", StringMap.ConfigCategoryTweaks, 30, 0, Integer.MAX_VALUE,
				"Sets the max number of lava drained from the workshop table");
		Logger.info(spacing + "Max Lava Drain = " + MAX_LAVA_DRAIN);
		LAVA_EFFICIENCY = config.getInt("Max Efficiency", StringMap.ConfigCategoryTweaks, 12, 0, Integer.MAX_VALUE,
				"Sets the lava efficacy in the workshop table");
		Logger.info(spacing + "Max Efficiency = " + LAVA_EFFICIENCY);
		SOLAR_GENERATION = config.getInt("Solar Generation", StringMap.ConfigCategoryTweaks, 4, 0, Integer.MAX_VALUE,
				"Sets the amount of energy generated per tick with solar panel upgrade in the workshop table");
		Logger.info(spacing + "Solar Generation = " + SOLAR_GENERATION);
		FUEL_DELAY = config.getInt("Fuel Delay", StringMap.ConfigCategoryTweaks, 15, 0, Integer.MAX_VALUE,
				"Sets the amount of ticks between each time the worktable consumes a fuel resource");
		Logger.info(spacing + "Fuel Delay = " + FUEL_DELAY);
		debugMode = config.getBoolean("Debug Mode", StringMap.ConfigCategoryTogglable, false,
				"Set true to turn on developer debug mode for debugging info in console");
		Logger.info(spacing + "Debug Mode = " + debugMode);

		//EU
		EUSupport = config.getBoolean("EU Support", StringMap.ConfigCategoryTogglable, true, "Set to false to disable EU support if IC2 is added");
        Logger.info(spacing + "EU Support = " + EUSupport);
        
        EU_BUFFER = config.getInt("EU Buffer Capacity", StringMap.ConfigCategoryTweaks, 5000, 100, Integer.MAX_VALUE,
				"Sets the internal EU buffer capacity if CoFH Core is added");
		Logger.info(spacing + "EU Buffer Capacity = " + EU_BUFFER);
		EU_CONVERSION_RATE = config.getInt("EU Conversion Rate", StringMap.ConfigCategoryTweaks, 50, 1,
				Integer.MAX_VALUE,
				"Sets the amount of EU converted to power on each fuel reload session (EU -> Power for each (Fuel Delay) tick)");
		Logger.info(spacing + "EU Conversion Rate = " + EU_CONVERSION_RATE);
		EU_CONVERSION_RATIO = config.getInt("EU Conversion Ratio", StringMap.ConfigCategoryTweaks, 1, 1,
				Integer.MAX_VALUE,
				"Sets the ratio EU gets converted to power (EU Conversion Ratio for each Power (Default 10:1 ratio)) THIS CAN NOT BE LOWER OR EQUAL TO THE CONVERSION RATE!");
		Logger.info(spacing + "EU Conversion Ratio = " + EU_CONVERSION_RATIO + ":1 ratio");
		
		//TESLA
        TeslaSupport = config.getBoolean("Tesla Support", StringMap.ConfigCategoryTogglable, true, "Set to false to disable Tesla support if IC2 is added");
        Logger.info(spacing + "Tesla Support = " + TeslaSupport);
        
        TESLA_BUFFER = config.getInt("Tesla Buffer Capacity", StringMap.ConfigCategoryTweaks, 5000, 100, Integer.MAX_VALUE,
                "Sets the internal Tesla buffer capacity if CoFH Core is added");
        Logger.info(spacing + "Tesla Buffer Capacity = " + TESLA_BUFFER);
        TESLA_CONVERSION_RATE = config.getInt("Tesla Conversion Rate", StringMap.ConfigCategoryTweaks, 50, 1,
                Integer.MAX_VALUE,
                "Sets the amount of Tesla converted to power on each fuel reload session (Tesla -> Power for each (Fuel Delay) tick)");
        Logger.info(spacing + "Tesla Conversion Rate = " + TESLA_CONVERSION_RATE);
        TESLA_CONVERSION_RATIO = config.getInt("Tesla Conversion Ratio", StringMap.ConfigCategoryTweaks, 1, 1,
                Integer.MAX_VALUE,
                "Sets the ratio Tesla gets converted to power (Tesla Conversion Ratio for each Power (Default 10:1 ratio)) THIS CAN NOT BE LOWER OR EQUAL TO THE CONVERSION RATE!");
        Logger.info(spacing + "Tesla Conversion Ratio = " + TESLA_CONVERSION_RATIO + ":1 ratio");
        
		
		doRenderSpinningEntity = config.getBoolean("Do Render Spinning Entity", StringMap.ConfigCategoryTogglable, true,
				"Set to false to disable spinning entity animation in the barrel display");
		Logger.info(spacing + "Do Render Spinning Entity = " + doRenderSpinningEntity);
		for (Upgrade upgrade : Upgrade.values()) {
			Upgrade.MaxCount max = upgrade.getMaxCountObject();
			if (max.getConfigurableMax() > 0) {
				upgrade.getMaxCountObject()
						.setMax(config.getInt(upgrade.getName() + MAX_COUNT_SUFFIX, StringMap.ConfigCategoryUpgrades,
								max.getMax(), 0, max.getConfigurableMax(),
								"Max amount of the " + upgrade.getName() + " upgrade"));
				Logger.info(spacing + upgrade.getName() + " = " + max.getMax());
			}
		}
		config.save();
		Logger.info(spacing + "Saving " + config);
		Logger.info("Loading configs finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
	}

	private ConfigLoader() {
	}
}
