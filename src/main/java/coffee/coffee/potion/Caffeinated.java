package coffee.coffee.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by RedstoneParadox on 6/10/2018.
 */
public class Caffeinated extends Potion {

    public Caffeinated(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc) {

    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int p_76394_2_)
    {
        if(entityLivingBaseIn.getActivePotionEffect(CoffeePotions.CAFFEINATED).getAmplifier() >= 2) {
            return;
        }
        else {
            return;
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
