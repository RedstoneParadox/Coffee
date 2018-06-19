package redstoneparadox.coffee;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redstoneparadox.coffee.proxy.CommonProxy;
import redstoneparadox.coffee.recipe.CoffeeRecipes;

@Mod(
        modid = Coffee.MOD_ID,
        name = Coffee.MOD_NAME,
        version = Coffee.VERSION
)
public class Coffee {

    public static final String MOD_ID = "coffee";
    public static final String MOD_NAME = "coffee";
    public static final String VERSION = "0.0.1";

    @SidedProxy(clientSide = "redstoneparadox.coffee.proxy.ClientProxy", serverSide = "redstoneparadox.coffee.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Coffee instance;

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        CoffeeRecipes.init();
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
