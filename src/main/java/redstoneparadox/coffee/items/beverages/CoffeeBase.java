package redstoneparadox.coffee.items.beverages;

import redstoneparadox.coffee.items.CoffeeItems;
import redstoneparadox.coffee.potion.CoffeePotions;
import net.minecraft.advancements.CriteriaTriggers;
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

/**
 * Created by RedstoneParadox on 6/11/2018.
 */
public class CoffeeBase extends ItemFood {

    public CoffeeBase(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        return;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (!worldIn.isRemote) {
            if (entityLiving.getActivePotionEffect(CoffeePotions.CAFFEINATED) != null) {
                if (entityLiving.getActivePotionEffect(CoffeePotions.CAFFEINATED).getDuration() > 1200) {
                    entityLiving.addPotionEffect(new PotionEffect(CoffeePotions.CAFFEINATED,6000,2));
                }
                else {
                    caffeinate(stack, entityLiving);
                }
            }
            else {
                caffeinate(stack, entityLiving);
            }
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

    public void caffeinate(ItemStack stack, EntityLivingBase entityLiving) {
        entityLiving.curePotionEffects(stack);
        return;
    }
}
