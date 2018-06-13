package coffee.coffee.items.beverages;

import coffee.coffee.Coffee;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by RedstoneParadox on 6/10/2018.
 */
public class MugWater extends CoffeeBase {

    public MugWater(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        setCreativeTab(CreativeTabs.BREWING);
        setUnlocalizedName(Coffee.MOD_ID + ".mug_water");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public void caffeinate(ItemStack stack, EntityLivingBase entityLiving) {
        return;
    }
}
