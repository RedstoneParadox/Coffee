package coffee.coffee.brewables;

import coffee.coffee.potion.CoffeePotions;
import jline.internal.Nullable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RedstoneParadox on 6/16/2018.
 */
public class BrewableType {

    static List<PotionEffect> someList = new ArrayList<PotionEffect>();

    static {
        someList.add(new PotionEffect(CoffeePotions.CAFFEINATED, 4000, 1));
    }

    public final String extendedPrefix;
    public final String enhancedPrefix;
    public final ItemStack extendedIngredient;
    public final ItemStack enhancedIngredient;

    public BrewableType(@Nullable ItemStack extendedIngredient, @Nullable ItemStack enhancedIngredient, @Nullable String extendedPrefix, @Nullable String enhancedPrefix) {

        this.extendedPrefix = extendedPrefix;
        this.enhancedPrefix = enhancedPrefix;
        this.extendedIngredient = extendedIngredient;
        this.enhancedIngredient = enhancedIngredient;
    }

    public void registerBrewable(RegistryEvent.Register<Item> event, String modID, String name, String input, String ingredient, ArrayList potions) {
        Item itemName = ForgeRegistries.ITEMS.getValue(new ResourceLocation(name));
        Item itemInput = ForgeRegistries.ITEMS.getValue(new ResourceLocation(input));
        Item itemIngredient = ForgeRegistries.ITEMS.getValue(new ResourceLocation(ingredient));
        event.getRegistry().register(new BrewableBase(1, 1, false).setRegistryName(name).setUnlocalizedName(modID + "." + name));

        BrewingRecipeRegistry.addRecipe(
                new ItemStack(itemInput),
                new ItemStack(itemIngredient),
                new ItemStack(itemName)
        );

        if (extendedIngredient != null) {
            ResourceLocation extendedLocation = new ResourceLocation(extendedPrefix + "_" + name);
            Item itemExtended = ForgeRegistries.ITEMS.getValue(extendedLocation);
            event.getRegistry().register(new BrewableBase(1, 1, false).setRegistryName(extendedPrefix + "_" + name).setUnlocalizedName(modID + "." + extendedPrefix + "_" + name));

            BrewingRecipeRegistry.addRecipe(
                    new ItemStack(itemName),
                    extendedIngredient,
                    new ItemStack(itemExtended)
            );

        }
        if (enhancedIngredient != null) {
            ResourceLocation enhancedLocation = new ResourceLocation(extendedPrefix + "_" + name);
            Item itemEnhanced = ForgeRegistries.ITEMS.getValue(enhancedLocation);
            event.getRegistry().register(new BrewableBase(1, 1, false).setRegistryName(enhancedPrefix + "_" + name).setUnlocalizedName(modID + "." + enhancedPrefix + "_" + name));

            BrewingRecipeRegistry.addRecipe(
                    new ItemStack(itemName),
                    enhancedIngredient,
                    new ItemStack(itemEnhanced)
            );

        }
        if (extendedIngredient != null && enhancedIngredient != null) {
            ResourceLocation extendedLocation = new ResourceLocation(extendedPrefix + "_" + name);
            ResourceLocation enhancedLocation = new ResourceLocation(extendedPrefix + "_" + name);
            Item itemExtended = ForgeRegistries.ITEMS.getValue(extendedLocation);
            Item itemEnhanced = ForgeRegistries.ITEMS.getValue(enhancedLocation);

            BrewingRecipeRegistry.addRecipe(
                    new ItemStack(itemEnhanced),
                    extendedIngredient,
                    new ItemStack(itemExtended)
            );
            BrewingRecipeRegistry.addRecipe(
                    new ItemStack(itemName),
                    enhancedIngredient,
                    new ItemStack(itemExtended)
            );

        }
        return;
    }
}
