package redstoneparadox.coffee.recipe;

import redstoneparadox.coffee.items.CoffeeItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by RedstoneParadox on 6/11/2018.
 */
public class CoffeeRecipes {

    public static void init() {
        CoffeeSmeltingRecipes();
        CoffeeBrewingRecipes();
    }

    public static void CoffeeSmeltingRecipes() {
        GameRegistry.addSmelting(
                //Roasted Coffee Bean.
                new ItemStack(CoffeeItems.COFFEE_BEAN),
                new ItemStack(CoffeeItems.ROASTED_COFFEE_BEAN),
                0f
        );
    }

    public static void CoffeeBrewingRecipes() {
        BrewingRecipeRegistry.addRecipe(
                //Coffee.
                new ItemStack(CoffeeItems.MUG_WATER),
                new ItemStack(CoffeeItems.ROASTED_COFFEE_BEAN),
                new ItemStack(CoffeeItems.MUG_COFFEE)
        );
    }
}
