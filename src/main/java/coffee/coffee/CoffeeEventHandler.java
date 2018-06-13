package coffee.coffee;

import coffee.coffee.potion.CoffeePotions;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by RedstoneParadox on 6/12/2018.
 */
public class CoffeeEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void rightClickBed(PlayerInteractEvent.RightClickBlock event) {
        event.setUseBlock(null);
        double xpos = event.getHitVec().x;
        double ypos = event.getHitVec().y;
        double zpos = event.getHitVec().z;
        BlockPos blockPos = new BlockPos(xpos, ypos, zpos);
        if (event.getWorld().getBlockState(blockPos).getBlock() == Blocks.BED) {
            if (event.getWorld().getWorldTime() >= 12541 && event.getWorld().getWorldTime() <= 23458) {
                if (event.getEntityPlayer().getActivePotionEffect(CoffeePotions.CAFFEINATED) != null) {
                    if (event.getEntityPlayer().getActivePotionEffect(CoffeePotions.CAFFEINATED).getDuration() >= 1200) {
                        event.setUseBlock(null);
                    }
                    else {
                        event.setResult(Event.Result.DEFAULT);
                    }
                }
                else {
                    event.setResult(Event.Result.DEFAULT);
                }
            }
            else {
                event.setResult(Event.Result.DEFAULT);
            }
        }
    }
}
