package redstoneparadox.coffee;

import redstoneparadox.coffee.blocks.CoffeeBlocks;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by RedstoneParadox on 6/13/2018.
 */
public class CoffeeBeanWorldGen implements IWorldGenerator {

    //DecorateBiomeEvent.Post

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == 0) {
            generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
    }

    private void generateOverworld(Random random, int chunkX, int chunkY, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        generateCoffeeBeans( world, random, chunkX * 16, chunkY * 16, 62, 128, 16);
    }

    private void generateCoffeeBeans(World world, Random random, int x, int z, int minY, int maxY, int chances) {
        int deltaY = maxY - minY;

        for (int i = 0; i < chances; i++) {
            BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

            if (world.getBlockState(pos.add(0,1,0)) == Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE)) {
                world.setBlockState(pos, CoffeeBlocks.COFFEE_BEAN_POD.getDefaultState());
                return;
            }
        }
    }
}
