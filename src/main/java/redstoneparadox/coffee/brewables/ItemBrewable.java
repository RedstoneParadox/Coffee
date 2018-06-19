package redstoneparadox.coffee.brewables;

import redstoneparadox.coffee.items.CoffeeItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RedstoneParadox on 6/16/2018.
 */
public class ItemBrewable extends ItemFood {

    static List<PotionEffect> potionEffects = new ArrayList<>();

    public ItemBrewable(int amount, float saturation, boolean isWolfFood, ArrayList<PotionEffect> potionEffects) {
        super(amount, saturation, isWolfFood);
        this.setCreativeTab(CreativeTabs.BREWING);
        potionEffects.containsAll(potionEffects);

    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        return;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (!worldIn.isRemote) {
            applyPotionEffects(stack, worldIn, entityLiving);
        }
        if (entityLiving instanceof EntityPlayerMP)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)entityLiving;
            CriteriaTriggers.field_193138_y.func_193148_a(entityplayermp, stack);
            entityplayermp.addStat(StatList.getObjectUseStats(this));
        }

        if (entityLiving instanceof EntityPlayer && !((EntityPlayer)entityLiving).capabilities.isCreativeMode)
        {
            stack.shrink(1);
        }

        return stack.isEmpty() ? new ItemStack(CoffeeItems.MUG) : stack;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.DRINK;
    }

    /**
     * Called when the equipped item is right clicked.
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
    public void applyPotionEffects(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        for (int i = 0; i < potionEffects.size(); i++) {
            entityLiving.addPotionEffect(potionEffects.get(i));
        }
        return;
    }
}
