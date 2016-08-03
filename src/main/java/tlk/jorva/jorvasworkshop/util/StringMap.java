package tlk.jorva.jorvasworkshop.util;

public class StringMap {

    //Channels
    public static final String Channel = "JWorkshop";

    //Mod info
    public static final String ID = "jorvasworkshop";
    public static final String Name = "JorVa's Workshop";
    public static final String MinecraftVersion = "1.10.2";
    public static final String VersionMajor = "1";
    public static final String VersionMinor = "1";
    public static final String VersionPatch = "0";
    public static final String VersionBuildName = Name + "-" + MinecraftVersion + "-" + VersionMajor + "." + VersionMinor + "." + VersionPatch;

    //Proxies
    public static final String clientProxyPath = "tlk.jorva.jorvasworkshop.network.proxies.ClientProxy";
    public static final String commonProxyPath = "tlk.jorva.jorvasworkshop.network.proxies.CommonProxy";

    //Blocks
    public static final String WorkshopTable = "WorkshopTable";

    //Items
    public static final String ItemPath = "jorvasworkshop:item";
    public static final String WorkshopTableUpgrade = "WorkshopTableUpgrade";

    //Configs
    public static final String ConfigCategoryTweaks = "Tweaks";
    public static final String ConfigCategoryTogglable = "Togglables";
    public static final String ConfigCategoryUpgrades = "Upgrades";

    //NBT
    public static final String NBTDirection = "forgeDirection";

    //Pages
    public static final String PageMain = "main";
    public static final String PageTransfer = "transfer";
    public static final String PageUpgrade = "upgrade";

    //Waila
    public static final String WailaPath = "tlk.jorva.jorvasworkshop.dependencies.waila.Waila.onWailaCall";
}
