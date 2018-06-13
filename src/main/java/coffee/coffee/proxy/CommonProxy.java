package coffee.coffee.proxy;

import coffee.coffee.blocks.CoffeeBeanPod;
import coffee.coffee.items.*;
import coffee.coffee.items.beverages.MugCoffee;
import coffee.coffee.items.beverages.MugWater;
import coffee.coffee.potion.Caffeinated;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by RedstoneParadox on 6/10/2018.
 */
@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
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
    }

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event) {
        event.getRegistry().register(new Caffeinated(false, 0).setRegistryName("caffeinated").setPotionName("Caffeinated"));
    }
}