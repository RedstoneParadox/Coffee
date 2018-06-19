package redstoneparadox.coffee.blocks;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by RedstoneParadox on 6/10/2018.
 */
@GameRegistry.ObjectHolder("coffee")
public class CoffeeBlocks {

    public static final CoffeeBeanPod COFFEE_BEAN_POD = null;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        COFFEE_BEAN_POD.initModel();
    }
}
