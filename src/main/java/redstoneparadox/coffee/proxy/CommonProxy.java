package redstoneparadox.coffee.proxy;

import redstoneparadox.coffee.CoffeeBeanWorldGen;
import redstoneparadox.coffee.CoffeeEventHandler;
import redstoneparadox.coffee.brewables.CoffeeBrewables;
import redstoneparadox.coffee.items.CoffeeBean;
import redstoneparadox.coffee.items.beverages.MugWater;
import net.minecraft.block.Block;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import redstoneparadox.coffee.blocks.CoffeeBeanPod;
import redstoneparadox.coffee.items.Mug;
import redstoneparadox.coffee.items.RoastedBean;
import redstoneparadox.coffee.items.beverages.MugCoffee;
import redstoneparadox.coffee.potion.Caffeinated;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

/**
 * Created by RedstoneParadox on 6/10/2018.
 */
@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        new CoffeeBrewables();
        MinecraftForge.EVENT_BUS.register(new CoffeeEventHandler());
        GameRegistry.registerWorldGenerator(new CoffeeBeanWorldGen(), 3);
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new CoffeeBeanPod().setRegistryName("coffee_bean_pod"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new CoffeeBean().setRegistryName("coffee_bean"));
        event.getRegistry().register(new RoastedBean().setRegistryName("roasted_coffee_bean"));
        event.getRegistry().register(new Mug().setRegistryName("mug"));
        event.getRegistry().register(new MugWater(1,1,false).setRegistryName("mug_water"));
        event.getRegistry().register(new MugCoffee(1,1,false).setRegistryName("mug_coffee"));
        ArrayList<PotionEffect> PotionEffectsList = new ArrayList<>();
        PotionEffectsList.add(new PotionEffect(MobEffects.SPEED, 1000,1));
        CoffeeBrewables.COFFEE_COFFEE.registerBrewable(
                event,
                "example_potion",
                new ResourceLocation("coffee:mug_water"),
                new ResourceLocation("coffee:coffee_bean"),
                1,
                1,
                PotionEffectsList
        );
    }

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event) {
        event.getRegistry().register(new Caffeinated(false, 0).setRegistryName("caffeinated").setPotionName("Caffeinated"));
    }
}