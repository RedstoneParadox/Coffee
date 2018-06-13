package coffee.coffee.items;

import coffee.coffee.Coffee;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by RedstoneParadox on 6/11/2018.
 */
public class RoastedBean extends Item{

    public RoastedBean() {
        setCreativeTab(CreativeTabs.MATERIALS);
        setUnlocalizedName(Coffee.MOD_ID + ".roasted_coffee_bean");
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
