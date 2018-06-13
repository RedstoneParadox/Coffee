package coffee.coffee.items;

import coffee.coffee.items.beverages.MugCoffee;
import coffee.coffee.items.beverages.MugWater;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by RedstoneParadox on 6/10/2018.
 */
@GameRegistry.ObjectHolder("coffee")
public class CoffeeItems {
    
    public static final CoffeeBean COFFEE_BEAN = null;
    public static final RoastedBean ROASTED_COFFEE_BEAN = null;
    public static final Mug MUG = null;
    public static final MugWater MUG_WATER = null;
    public static final MugCoffee MUG_COFFEE = null;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        COFFEE_BEAN.initModel();
        ROASTED_COFFEE_BEAN.initModel();
        MUG.initModel();
        MUG_WATER.initModel();
        MUG_COFFEE.initModel();
    }
}
