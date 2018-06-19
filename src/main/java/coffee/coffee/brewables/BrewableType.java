package coffee.coffee.brewables;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;

/**
 * Created by RedstoneParadox on 6/16/2018.
 */
public class BrewableType {

    static ArrayList<PotionEffect> longPotionEffectsList = new ArrayList<>();
    static ArrayList<PotionEffect> strongPotionEffectsList = new ArrayList<>();

    // Mod ID used by the potions when registered.
    public final String modID;
    //Ingredient for brewing the potion into the long variant.
    public final ItemStack longIngredient;
    //Ingredient for brewing the potion into the strong variant.
    public final ItemStack strongIngredient;
    //The amount the duration of potion effects is multiplied by for the long variant.
    public final double longMultiplier;
    //The amount the duration of potion effects is multiplied by for the strong variant.
    public final double strongMultiplier;

    public BrewableType(String modID, ItemStack emptyContainer, @Nullable ItemStack longIngredient, @Nullable double longMultiplier, @Nullable ItemStack strongIngredient, @Nullable double strongMultiplier) {

        this.modID = modID;
        this.longIngredient = longIngredient;
        this.strongIngredient = strongIngredient;
        this.longMultiplier = longMultiplier;
        this.strongMultiplier = strongMultiplier;
    }

    /*Calling this method will register a new potion using as a new instance of the ItemBrewable class,
    extended and level 2 versions if their ingredients weren't null when constructing the BrewableType,
    and all associated recipes.
     */
    public void registerBrewable(RegistryEvent.Register<Item> event, String name, ResourceLocation input, ResourceLocation ingredient, int amount, int saturation, ArrayList<PotionEffect> potionEffects) {
        ResourceLocation extendedLocation = new ResourceLocation(modID + ":" + "long_" + name);
        ResourceLocation strongLocation = new ResourceLocation(modID + ":" + "strong_" + name);
        Item itemInput = ForgeRegistries.ITEMS.getValue(input);
        Item itemIngredient = ForgeRegistries.ITEMS.getValue(ingredient);
        event.getRegistry().register(new ItemBrewable(amount, saturation, false, potionEffects).setRegistryName(name).setUnlocalizedName(modID + "." + name));
        Item itemName = ForgeRegistries.ITEMS.getValue(new ResourceLocation(modID + ":" + name));

        BrewingRecipeRegistry.addRecipe(
                new ItemStack(itemInput),
                new ItemStack(itemIngredient),
                new ItemStack(itemName)
        );

        if (longIngredient != null) {
            buildPotionEffectList(potionEffects, longPotionEffectsList,longMultiplier, 0);
            event.getRegistry().register(new ItemBrewable(amount, saturation, false, longPotionEffectsList).setRegistryName("long_" + name).setUnlocalizedName(modID + "." + "extended_" + name));
            Item itemExtended = ForgeRegistries.ITEMS.getValue(extendedLocation);

            BrewingRecipeRegistry.addRecipe(
                    new ItemStack(itemName),
                    longIngredient,
                    new ItemStack(itemExtended)
            );

        }
        if (strongIngredient != null) {
            buildPotionEffectList(potionEffects, strongPotionEffectsList,strongMultiplier, 1);
            event.getRegistry().register(new ItemBrewable(amount, saturation, false, potionEffects).setRegistryName("strong_" + name).setUnlocalizedName(modID + "." + "strong_" + name));
            Item itemEnhanced = ForgeRegistries.ITEMS.getValue(strongLocation);

            BrewingRecipeRegistry.addRecipe(
                    new ItemStack(itemName),
                    strongIngredient,
                    new ItemStack(itemEnhanced)
            );

        }
        if (longIngredient != null && strongIngredient != null) {
            Item itemExtended = ForgeRegistries.ITEMS.getValue(extendedLocation);
            Item itemEnhanced = ForgeRegistries.ITEMS.getValue(strongLocation);

            BrewingRecipeRegistry.addRecipe(
                    new ItemStack(itemEnhanced),
                    longIngredient,
                    new ItemStack(itemExtended)
            );
            BrewingRecipeRegistry.addRecipe(
                    new ItemStack(itemName),
                    strongIngredient,
                    new ItemStack(itemExtended)
            );

        }
        return;
    }

    /* Used to create potion lists for the variants with modified durations and amplifiers.*/
    public void buildPotionEffectList(ArrayList<PotionEffect> potionEffects, ArrayList<PotionEffect> editList, double durationMultiplier, int levelBonus) {
        editList.clear();

        for (int i=0; i < potionEffects.size(); i++) {
            Potion potionEffect = potionEffects.get(i).getPotion();
            int duration = potionEffects.get(i).getDuration();
            int amplifier = potionEffects.get(i).getAmplifier();

            editList.add(new PotionEffect(potionEffect, Math.toIntExact(Math.round(duration * durationMultiplier)), amplifier + levelBonus));
        }
        return;
    }
}
