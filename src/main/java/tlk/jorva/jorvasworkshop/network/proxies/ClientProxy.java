package tlk.jorva.jorvasworkshop.network.proxies;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit() {
		super.preInit();
	}
	
	@Override
	public boolean isClient() {
		return true;
	}
}
