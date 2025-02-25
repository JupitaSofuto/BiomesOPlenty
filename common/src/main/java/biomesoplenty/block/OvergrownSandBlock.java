/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.worldgen.placement.BOPVegetationPlacements;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.lighting.LightEngine;

import java.util.Optional;

public class OvergrownSandBlock extends ColoredFallingBlock implements BonemealableBlock
{
    public OvergrownSandBlock(ColorRGBA dustColor, Properties properties)
    {
        super(dustColor, properties);
    }

    private static boolean canBeGrass(BlockState state, LevelReader p_56825_, BlockPos p_56826_)
    {
        BlockPos abovePos = p_56826_.above();
        BlockState aboveState = p_56825_.getBlockState(abovePos);
        if (aboveState.getFluidState().getAmount() == 8)
        {
            return false;
        }
        else
        {
            int i = LightEngine.getLightBlockInto(state, aboveState, Direction.UP, aboveState.getLightBlock());
            return i < 15;
        }
    }

    @Override
    public void randomTick(BlockState p_222508_, ServerLevel p_222509_, BlockPos p_222510_, RandomSource p_222511_)
    {
        if (!canBeGrass(p_222508_, p_222509_, p_222510_))
        {
            p_222509_.setBlockAndUpdate(p_222510_, BOPBlocks.BLACK_SAND.defaultBlockState());
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_153797_, BlockPos p_153798_, BlockState p_153799_)
    {
        return p_153797_.getBlockState(p_153798_.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level p_153802_, RandomSource p_153803_, BlockPos p_153804_, BlockState p_153805_)
    {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel p_221270_, RandomSource p_221271_, BlockPos p_221272_, BlockState p_221273_)
    {
        BlockPos blockpos = p_221272_.above();
        BlockState blockstate = Blocks.SHORT_GRASS.defaultBlockState();
        Optional<Holder.Reference<PlacedFeature>> optional = p_221270_.registryAccess().lookupOrThrow(Registries.PLACED_FEATURE).get(BOPVegetationPlacements.SPROUT_BONEMEAL);

        label49:
        for(int i = 0; i < 128; ++i)
        {
            BlockPos blockpos1 = blockpos;

            for(int j = 0; j < i / 16; ++j)
            {
                blockpos1 = blockpos1.offset(p_221271_.nextInt(3) - 1, (p_221271_.nextInt(3) - 1) * p_221271_.nextInt(3) / 2, p_221271_.nextInt(3) - 1);
                if (!p_221270_.getBlockState(blockpos1.below()).is(this) || p_221270_.getBlockState(blockpos1).isCollisionShapeFullBlock(p_221270_, blockpos1)) {
                    continue label49;
                }
            }

            BlockState blockstate1 = p_221270_.getBlockState(blockpos1);
            if (blockstate1.is(blockstate.getBlock()) && p_221271_.nextInt(10) == 0)
            {
                ((BonemealableBlock)blockstate.getBlock()).performBonemeal(p_221270_, p_221271_, blockpos1, blockstate1);
            }

            if (blockstate1.isAir())
            {
                Holder<PlacedFeature> holder;
                if (!optional.isPresent()) {
                    continue;
                }

                holder = optional.get();

                holder.value().place(p_221270_, p_221270_.getChunkSource().getGenerator(), p_221271_, blockpos1);
            }
        }

    }
}
