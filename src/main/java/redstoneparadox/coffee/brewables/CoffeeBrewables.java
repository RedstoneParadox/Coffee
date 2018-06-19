package redstoneparadox.coffee.brewables;

import redstoneparadox.coffee.Coffee;
import redstoneparadox.coffee.items.CoffeeItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by RedstoneParadox on 6/16/2018.
 */
public class CoffeeBrewables {

    public static final BrewableType COFFEE_COFFEE = new BrewableType(Coffee.MOD_ID, new ItemStack(CoffeeItems.MUG),new ItemStack(Items.REDSTONE), 2, new ItemStack(Items.GLOWSTONE_DUST),  0.5);
}
