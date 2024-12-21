/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.neoforge.datagen.model;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.neoforge.datagen.BOPBlockFamilies;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.Direction;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BOPBlockModelGenerators extends BlockModelGenerators
{
    final Map<Block, TexturedModel> texturedModels = ImmutableMap.<Block, TexturedModel>builder()
            .put(BOPBlocks.WHITE_SANDSTONE, TexturedModel.TOP_BOTTOM_WITH_WALL.get(BOPBlocks.WHITE_SANDSTONE))
            .put(BOPBlocks.SMOOTH_WHITE_SANDSTONE, TexturedModel.createAllSame(TextureMapping.getBlockTexture(BOPBlocks.WHITE_SANDSTONE, "_top")))
            .put(BOPBlocks.CUT_WHITE_SANDSTONE, TexturedModel.COLUMN.get(BOPBlocks.CUT_WHITE_SANDSTONE).updateTextures(p_386968_ -> {
                p_386968_.put(TextureSlot.END, TextureMapping.getBlockTexture(BOPBlocks.WHITE_SANDSTONE, "_top"));
                p_386968_.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BOPBlocks.CUT_WHITE_SANDSTONE));
            }))
            .put(BOPBlocks.CHISELED_WHITE_SANDSTONE, TexturedModel.COLUMN.get(BOPBlocks.CHISELED_WHITE_SANDSTONE).updateTextures(p_386968_ -> {
                p_386968_.put(TextureSlot.END, TextureMapping.getBlockTexture(BOPBlocks.WHITE_SANDSTONE, "_top"));
                p_386968_.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BOPBlocks.CHISELED_WHITE_SANDSTONE));
            }))
            .put(BOPBlocks.ORANGE_SANDSTONE, TexturedModel.TOP_BOTTOM_WITH_WALL.get(BOPBlocks.ORANGE_SANDSTONE))
            .put(BOPBlocks.SMOOTH_ORANGE_SANDSTONE, TexturedModel.createAllSame(TextureMapping.getBlockTexture(BOPBlocks.ORANGE_SANDSTONE, "_top")))
            .put(BOPBlocks.CUT_ORANGE_SANDSTONE, TexturedModel.COLUMN.get(BOPBlocks.CUT_ORANGE_SANDSTONE).updateTextures(p_386968_ -> {
                p_386968_.put(TextureSlot.END, TextureMapping.getBlockTexture(BOPBlocks.ORANGE_SANDSTONE, "_top"));
                p_386968_.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BOPBlocks.CUT_ORANGE_SANDSTONE));
            }))
            .put(BOPBlocks.CHISELED_ORANGE_SANDSTONE, TexturedModel.COLUMN.get(BOPBlocks.CHISELED_ORANGE_SANDSTONE).updateTextures(p_386968_ -> {
                p_386968_.put(TextureSlot.END, TextureMapping.getBlockTexture(BOPBlocks.ORANGE_SANDSTONE, "_top"));
                p_386968_.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BOPBlocks.CHISELED_ORANGE_SANDSTONE));
            }))
            .put(BOPBlocks.BLACK_SANDSTONE, TexturedModel.TOP_BOTTOM_WITH_WALL.get(BOPBlocks.BLACK_SANDSTONE))
            .put(BOPBlocks.SMOOTH_BLACK_SANDSTONE, TexturedModel.createAllSame(TextureMapping.getBlockTexture(BOPBlocks.BLACK_SANDSTONE, "_top")))
            .put(BOPBlocks.CUT_BLACK_SANDSTONE, TexturedModel.COLUMN.get(BOPBlocks.CUT_BLACK_SANDSTONE).updateTextures(p_386968_ -> {
                p_386968_.put(TextureSlot.END, TextureMapping.getBlockTexture(BOPBlocks.BLACK_SANDSTONE, "_top"));
                p_386968_.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BOPBlocks.CUT_BLACK_SANDSTONE));
            }))
            .put(BOPBlocks.CHISELED_BLACK_SANDSTONE, TexturedModel.COLUMN.get(BOPBlocks.CHISELED_BLACK_SANDSTONE).updateTextures(p_386968_ -> {
                p_386968_.put(TextureSlot.END, TextureMapping.getBlockTexture(BOPBlocks.BLACK_SANDSTONE, "_top"));
                p_386968_.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BOPBlocks.CHISELED_BLACK_SANDSTONE));
            }))
            .build();

    final Consumer<BlockStateGenerator> blockStateOutput;
    final BiConsumer<ResourceLocation, ModelInstance> modelOutput;

    public BOPBlockModelGenerators(Consumer<BlockStateGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput)
    {
        super(blockStateOutput, itemModelOutput, modelOutput);
        this.blockStateOutput = blockStateOutput;
        this.modelOutput = modelOutput;
    }

    @Override
    public void run()
    {
        BOPBlockFamilies.getAllFamilies()
                .filter(BlockFamily::shouldGenerateModel)
                .forEach(p_386718_ -> this.family(p_386718_.getBaseBlock()).generateFor(p_386718_));

        // Fir
        this.woodProvider(BOPBlocks.FIR_LOG).logWithHorizontal(BOPBlocks.FIR_LOG).wood(BOPBlocks.FIR_WOOD);
        this.woodProvider(BOPBlocks.STRIPPED_FIR_LOG).logWithHorizontal(BOPBlocks.STRIPPED_FIR_LOG).wood(BOPBlocks.STRIPPED_FIR_WOOD);
        this.createHangingSign(BOPBlocks.STRIPPED_FIR_LOG, BOPBlocks.FIR_HANGING_SIGN, BOPBlocks.FIR_WALL_HANGING_SIGN);
        this.createTrivialBlock(BOPBlocks.FIR_LEAVES, TexturedModel.LEAVES);
        this.createPlantWithDefaultItem(BOPBlocks.FIR_SAPLING, BOPBlocks.POTTED_FIR_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        // Pine
        this.woodProvider(BOPBlocks.PINE_LOG).wood(BOPBlocks.PINE_WOOD);
        this.logWithKnot(BOPBlocks.PINE_LOG);
        this.woodProvider(BOPBlocks.STRIPPED_PINE_LOG).logWithHorizontal(BOPBlocks.STRIPPED_PINE_LOG).wood(BOPBlocks.STRIPPED_PINE_WOOD);
        this.createHangingSign(BOPBlocks.STRIPPED_PINE_LOG, BOPBlocks.PINE_HANGING_SIGN, BOPBlocks.PINE_WALL_HANGING_SIGN);
        this.createLeavesOverlay(BOPBlocks.PINE_LEAVES, FoliageColor.FOLIAGE_DEFAULT);
        this.createPlantWithDefaultItem(BOPBlocks.PINE_SAPLING, BOPBlocks.POTTED_PINE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        // Maple
        this.woodProvider(BOPBlocks.MAPLE_LOG).logWithHorizontal(BOPBlocks.MAPLE_LOG).wood(BOPBlocks.MAPLE_WOOD);
        this.woodProvider(BOPBlocks.STRIPPED_MAPLE_LOG).logWithHorizontal(BOPBlocks.STRIPPED_MAPLE_LOG).wood(BOPBlocks.STRIPPED_MAPLE_WOOD);
        this.createHangingSign(BOPBlocks.STRIPPED_MAPLE_LOG, BOPBlocks.MAPLE_HANGING_SIGN, BOPBlocks.MAPLE_WALL_HANGING_SIGN);
        this.createTrivialBlock(BOPBlocks.ORANGE_MAPLE_LEAVES, TexturedModel.LEAVES);
        this.createTrivialBlock(BOPBlocks.RED_MAPLE_LEAVES, TexturedModel.LEAVES);
        this.createTrivialBlock(BOPBlocks.YELLOW_MAPLE_LEAVES, TexturedModel.LEAVES);
        this.createPlantWithDefaultItem(BOPBlocks.ORANGE_MAPLE_SAPLING, BOPBlocks.POTTED_ORANGE_MAPLE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(BOPBlocks.RED_MAPLE_SAPLING, BOPBlocks.POTTED_RED_MAPLE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(BOPBlocks.YELLOW_MAPLE_SAPLING, BOPBlocks.POTTED_YELLOW_MAPLE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        // Redwood
        this.woodProvider(BOPBlocks.REDWOOD_LOG).logWithHorizontal(BOPBlocks.REDWOOD_LOG).wood(BOPBlocks.REDWOOD_WOOD);
        this.woodProvider(BOPBlocks.STRIPPED_REDWOOD_LOG).logWithHorizontal(BOPBlocks.STRIPPED_REDWOOD_LOG).wood(BOPBlocks.STRIPPED_REDWOOD_WOOD);
        this.createHangingSign(BOPBlocks.STRIPPED_REDWOOD_LOG, BOPBlocks.REDWOOD_HANGING_SIGN, BOPBlocks.REDWOOD_WALL_HANGING_SIGN);
        this.createTrivialBlock(BOPBlocks.REDWOOD_LEAVES, TexturedModel.LEAVES);
        this.createPlantWithDefaultItem(BOPBlocks.REDWOOD_SAPLING, BOPBlocks.POTTED_REDWOOD_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        // Mahogany
        this.woodProvider(BOPBlocks.MAHOGANY_LOG).logWithHorizontal(BOPBlocks.MAHOGANY_LOG).wood(BOPBlocks.MAHOGANY_WOOD);
        this.woodProvider(BOPBlocks.STRIPPED_MAHOGANY_LOG).logWithHorizontal(BOPBlocks.STRIPPED_MAHOGANY_LOG).wood(BOPBlocks.STRIPPED_MAHOGANY_WOOD);
        this.createHangingSign(BOPBlocks.STRIPPED_MAHOGANY_LOG, BOPBlocks.MAHOGANY_HANGING_SIGN, BOPBlocks.MAHOGANY_WALL_HANGING_SIGN);
        this.createTintedLeaves(BOPBlocks.MAHOGANY_LEAVES, TexturedModel.LEAVES, FoliageColor.FOLIAGE_DEFAULT);
        this.createPlantWithDefaultItem(BOPBlocks.MAHOGANY_SAPLING, BOPBlocks.POTTED_MAHOGANY_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        // Jacaranda
        this.woodProvider(BOPBlocks.JACARANDA_LOG).logWithHorizontal(BOPBlocks.JACARANDA_LOG).wood(BOPBlocks.JACARANDA_WOOD);
        this.woodProvider(BOPBlocks.STRIPPED_JACARANDA_LOG).logWithHorizontal(BOPBlocks.STRIPPED_JACARANDA_LOG).wood(BOPBlocks.STRIPPED_JACARANDA_WOOD);
        this.createHangingSign(BOPBlocks.STRIPPED_JACARANDA_LOG, BOPBlocks.JACARANDA_HANGING_SIGN, BOPBlocks.JACARANDA_WALL_HANGING_SIGN);
        this.createTrivialBlock(BOPBlocks.JACARANDA_LEAVES, TexturedModel.LEAVES);
        this.createPlantWithDefaultItem(BOPBlocks.JACARANDA_SAPLING, BOPBlocks.POTTED_JACARANDA_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        // Palm
        this.woodProvider(BOPBlocks.PALM_LOG).logWithHorizontal(BOPBlocks.PALM_LOG).wood(BOPBlocks.PALM_WOOD);
        this.woodProvider(BOPBlocks.STRIPPED_PALM_LOG).logWithHorizontal(BOPBlocks.STRIPPED_PALM_LOG).wood(BOPBlocks.STRIPPED_PALM_WOOD);
        this.createHangingSign(BOPBlocks.STRIPPED_PALM_LOG, BOPBlocks.PALM_HANGING_SIGN, BOPBlocks.PALM_WALL_HANGING_SIGN);
        this.createTintedLeaves(BOPBlocks.PALM_LEAVES, TexturedModel.LEAVES, FoliageColor.FOLIAGE_DEFAULT);
        this.createPlantWithDefaultItem(BOPBlocks.PALM_SAPLING, BOPBlocks.POTTED_PALM_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        // Willow
        this.woodProvider(BOPBlocks.WILLOW_LOG).logWithHorizontal(BOPBlocks.WILLOW_LOG).wood(BOPBlocks.WILLOW_WOOD);
        this.woodProvider(BOPBlocks.STRIPPED_WILLOW_LOG).logWithHorizontal(BOPBlocks.STRIPPED_WILLOW_LOG).wood(BOPBlocks.STRIPPED_WILLOW_WOOD);
        this.createHangingSign(BOPBlocks.STRIPPED_WILLOW_LOG, BOPBlocks.WILLOW_HANGING_SIGN, BOPBlocks.WILLOW_WALL_HANGING_SIGN);
        this.createTintedLeaves(BOPBlocks.WILLOW_LEAVES, TexturedModel.LEAVES, FoliageColor.FOLIAGE_DEFAULT);
        this.createPlantWithDefaultItem(BOPBlocks.WILLOW_SAPLING, BOPBlocks.POTTED_WILLOW_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        // Dead
        this.woodProvider(BOPBlocks.DEAD_LOG).logWithHorizontal(BOPBlocks.DEAD_LOG).wood(BOPBlocks.DEAD_WOOD);
        this.woodProvider(BOPBlocks.STRIPPED_DEAD_LOG).logWithHorizontal(BOPBlocks.STRIPPED_DEAD_LOG).wood(BOPBlocks.STRIPPED_DEAD_WOOD);
        this.createHangingSign(BOPBlocks.STRIPPED_DEAD_LOG, BOPBlocks.DEAD_HANGING_SIGN, BOPBlocks.DEAD_WALL_HANGING_SIGN);
        this.createTrivialBlock(BOPBlocks.DEAD_LEAVES, TexturedModel.LEAVES);
        this.createPlantWithDefaultItem(BOPBlocks.DEAD_SAPLING, BOPBlocks.POTTED_DEAD_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        // Magic
        this.woodProvider(BOPBlocks.MAGIC_LOG).logWithHorizontal(BOPBlocks.MAGIC_LOG).wood(BOPBlocks.MAGIC_WOOD);
        this.woodProvider(BOPBlocks.STRIPPED_MAGIC_LOG).logWithHorizontal(BOPBlocks.STRIPPED_MAGIC_LOG).wood(BOPBlocks.STRIPPED_MAGIC_WOOD);
        this.createHangingSign(BOPBlocks.STRIPPED_MAGIC_LOG, BOPBlocks.MAGIC_HANGING_SIGN, BOPBlocks.MAGIC_WALL_HANGING_SIGN);
        this.createTrivialBlock(BOPBlocks.MAGIC_LEAVES, TexturedModel.LEAVES);
        this.createPlantWithDefaultItem(BOPBlocks.MAGIC_SAPLING, BOPBlocks.POTTED_MAGIC_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        // Umbran
        this.woodProvider(BOPBlocks.UMBRAN_LOG).wood(BOPBlocks.UMBRAN_WOOD);
        this.logWithKnot(BOPBlocks.UMBRAN_LOG);
        this.woodProvider(BOPBlocks.STRIPPED_UMBRAN_LOG).logWithHorizontal(BOPBlocks.STRIPPED_UMBRAN_LOG).wood(BOPBlocks.STRIPPED_UMBRAN_WOOD);
        this.createHangingSign(BOPBlocks.STRIPPED_UMBRAN_LOG, BOPBlocks.UMBRAN_HANGING_SIGN, BOPBlocks.UMBRAN_WALL_HANGING_SIGN);
        this.createTrivialBlock(BOPBlocks.UMBRAN_LEAVES, TexturedModel.LEAVES);
        this.createPlantWithDefaultItem(BOPBlocks.UMBRAN_SAPLING, BOPBlocks.POTTED_UMBRAN_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        // Hellbark
        this.woodProvider(BOPBlocks.HELLBARK_LOG).logWithHorizontal(BOPBlocks.HELLBARK_LOG).wood(BOPBlocks.HELLBARK_WOOD);
        this.woodProvider(BOPBlocks.STRIPPED_HELLBARK_LOG).logWithHorizontal(BOPBlocks.STRIPPED_HELLBARK_LOG).wood(BOPBlocks.STRIPPED_HELLBARK_WOOD);
        this.createHangingSign(BOPBlocks.STRIPPED_HELLBARK_LOG, BOPBlocks.HELLBARK_HANGING_SIGN, BOPBlocks.HELLBARK_WALL_HANGING_SIGN);
        this.createTrivialBlock(BOPBlocks.HELLBARK_LEAVES, TexturedModel.LEAVES);
        this.createPlantWithDefaultItem(BOPBlocks.HELLBARK_SAPLING, BOPBlocks.POTTED_HELLBARK_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        // Empyreal
        this.woodProvider(BOPBlocks.EMPYREAL_LOG).logWithHorizontal(BOPBlocks.EMPYREAL_LOG).wood(BOPBlocks.EMPYREAL_WOOD);
        this.woodProvider(BOPBlocks.STRIPPED_EMPYREAL_LOG).logWithHorizontal(BOPBlocks.STRIPPED_EMPYREAL_LOG).wood(BOPBlocks.STRIPPED_EMPYREAL_WOOD);
        this.createHangingSign(BOPBlocks.STRIPPED_EMPYREAL_LOG, BOPBlocks.EMPYREAL_HANGING_SIGN, BOPBlocks.EMPYREAL_WALL_HANGING_SIGN);
        this.createTrivialBlock(BOPBlocks.EMPYREAL_LEAVES, TexturedModel.LEAVES);
        this.createPlantWithDefaultItem(BOPBlocks.EMPYREAL_SAPLING, BOPBlocks.POTTED_EMPYREAL_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        // Other trees
        this.registerSimpleItemModel(BOPBlocks.NULL_LEAVES, ModelLocationUtils.getModelLocation(BOPBlocks.NULL_LEAVES, "_alt"));

        this.createTrivialBlock(BOPBlocks.ORIGIN_LEAVES, TexturedModel.LEAVES);
        this.createPlantWithDefaultItem(BOPBlocks.ORIGIN_SAPLING, BOPBlocks.POTTED_ORIGIN_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createLeavesOverlay(BOPBlocks.FLOWERING_OAK_LEAVES, FoliageColor.FOLIAGE_DEFAULT);
        this.createPlantWithDefaultItem(BOPBlocks.FLOWERING_OAK_SAPLING, BOPBlocks.POTTED_FLOWERING_OAK_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createTrivialBlock(BOPBlocks.CYPRESS_LEAVES, TexturedModel.LEAVES);
        this.createPlantWithDefaultItem(BOPBlocks.CYPRESS_SAPLING, BOPBlocks.POTTED_CYPRESS_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createTrivialBlock(BOPBlocks.SNOWBLOSSOM_LEAVES, TexturedModel.LEAVES);
        this.createPlantWithDefaultItem(BOPBlocks.SNOWBLOSSOM_SAPLING, BOPBlocks.POTTED_SNOWBLOSSOM_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        this.registerSimpleItemModel(BOPBlocks.RAINBOW_BIRCH_LEAVES, TexturedModel.LEAVES.createWithSuffix(BOPBlocks.RAINBOW_BIRCH_LEAVES, "_inventory", this.modelOutput));
        this.createPlantWithDefaultItem(BOPBlocks.RAINBOW_BIRCH_SAPLING, BOPBlocks.POTTED_RAINBOW_BIRCH_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        this.createRotatedVariantBlock(BOPBlocks.WHITE_SAND);
        this.createRotatedVariantBlock(BOPBlocks.ORANGE_SAND);
        this.createRotatedVariantBlock(BOPBlocks.BLACK_SAND);

        this.createRotatedVariantBlock(BOPBlocks.BRIMSTONE);
        this.createTrivialCube(BOPBlocks.ROSE_QUARTZ_BLOCK);
        this.createTrivialCube(BOPBlocks.WISPJELLY);
        this.createRotatedVariantBlock(BOPBlocks.DRIED_SALT);
        this.registerSimpleItemModel(BOPBlocks.THERMAL_CALCITE, ModelLocationUtils.getModelLocation(BOPBlocks.THERMAL_CALCITE, "_inventory"));
        this.registerSimpleItemModel(BOPBlocks.THERMAL_CALCITE_VENT, ModelLocationUtils.getModelLocation(BOPBlocks.THERMAL_CALCITE_VENT, "_inventory"));

        this.createMushroomBlock(BOPBlocks.TOADSTOOL_BLOCK);
        this.createGlowshroomBlock(BOPBlocks.GLOWSHROOM_BLOCK);
        this.createFullAndCarpetBlocks(BOPBlocks.GLOWING_MOSS_BLOCK, BOPBlocks.GLOWING_MOSS_CARPET);

        this.registerSimpleTintedItemModel(BOPBlocks.MOSSY_BLACK_SAND, ModelLocationUtils.getModelLocation(BOPBlocks.MOSSY_BLACK_SAND), new GrassColorSource());

        this.createWillowVine();

        //
        // Items. Ideally the block models should be generated too, but we'll just do the items for now for simplicity's sake
        //

        // Double plants
        this.registerSimpleFlatItemModel(BOPBlocks.TALL_LAVENDER, "_top");
        this.registerSimpleFlatItemModel(BOPBlocks.TALL_WHITE_LAVENDER, "_top");
        this.registerSimpleFlatItemModel(BOPBlocks.GOLDENROD, "_top");
        this.registerSimpleFlatItemModel(BOPBlocks.BLUE_HYDRANGEA, "_top");
        this.registerSimpleFlatItemModel(BOPBlocks.ICY_IRIS, "_top");
        this.registerSimpleFlatItemModel(BOPBlocks.BRIMSTONE_CLUSTER, "_bottom");
        this.registerSimpleFlatItemModel(BOPBlocks.LUMALOOP, "_plant_lit");

        // Hanging plants
        this.registerSimpleFlatItemModel(BOPBlocks.SPANISH_MOSS);
        this.registerSimpleFlatItemModel(BOPBlocks.FLESH_TENDONS);
        this.registerSimpleFlatItemModel(BOPBlocks.HANGING_COBWEB);

        this.registerSimpleFlatItemModel(BOPBlocks.WEBBING);

        this.registerSimpleFlatItemModel(BOPBlocks.TOADSTOOL);
        this.registerSimpleFlatItemModel(BOPBlocks.GLOWSHROOM);

        this.registerSimpleFlatItemModel(BOPBlocks.BRIMSTONE_BUD);
        this.registerSimpleFlatItemModel(BOPBlocks.BLACKSTONE_SPINES);
        this.registerSimpleFlatItemModel(BOPBlocks.BLACKSTONE_BULB);
        this.registerSimpleFlatItemModel(BOPBlocks.ROSE_QUARTZ_CLUSTER);
        this.registerSimpleFlatItemModel(BOPBlocks.LARGE_ROSE_QUARTZ_BUD);
        this.registerSimpleFlatItemModel(BOPBlocks.MEDIUM_ROSE_QUARTZ_BUD);
        this.registerSimpleFlatItemModel(BOPBlocks.SMALL_ROSE_QUARTZ_BUD);
        this.registerSimpleFlatItemModel(BOPBlocks.NULL_PLANT);

        // Flowers
        this.registerSimpleFlatItemModel(BOPBlocks.ROSE);
        this.registerSimpleFlatItemModel(BOPBlocks.VIOLET);
        this.registerSimpleFlatItemModel(BOPBlocks.LAVENDER);
        this.registerSimpleFlatItemModel(BOPBlocks.WHITE_LAVENDER);
        this.registerSimpleFlatItemModel(BOPBlocks.ORANGE_COSMOS);
        this.registerSimpleFlatItemModel(BOPBlocks.PINK_HIBISCUS);
        this.registerSimpleFlatItemModel(BOPBlocks.GLOWFLOWER);
        this.registerSimpleFlatItemModel(BOPBlocks.WILTED_LILY);
        this.registerSimpleFlatItemModel(BOPBlocks.BURNING_BLOSSOM);
        this.registerSimpleFlatItemModel(BOPBlocks.ENDBLOOM);

        // Foliage
        this.createTintedItemModel(BOPBlocks.SPROUT, new GrassColorSource());
        this.createTintedItemModel(BOPBlocks.BUSH, ItemModelUtils.constantTint(-12012264));
        this.createTintedItemModel(BOPBlocks.BRAMBLE_LEAVES, ItemModelUtils.constantTint(-12012264));
        this.registerSimpleFlatItemModel(BOPBlocks.DUNE_GRASS);
        this.registerSimpleFlatItemModel(BOPBlocks.DESERT_GRASS);
        this.registerSimpleFlatItemModel(BOPBlocks.DEAD_GRASS);
        this.registerSimpleFlatItemModel(BOPBlocks.TUNDRA_SHRUB);
        this.registerSimpleFlatItemModel(BOPBlocks.ENDERPHYTE);
        this.registerSimpleFlatItemModel(BOPBlocks.TINY_CACTUS);

        // Items that use a separate item texture from their block
        this.createBlockItemModel(BOPBlocks.RED_MAPLE_LEAF_PILE);
        this.createBlockItemModel(BOPBlocks.ORANGE_MAPLE_LEAF_PILE);
        this.createBlockItemModel(BOPBlocks.YELLOW_MAPLE_LEAF_PILE);
        this.createBlockItemModel(BOPBlocks.BRAMBLE);
        this.createBlockItemModel(BOPBlocks.WILDFLOWER);
        this.createBlockItemModel(BOPBlocks.WHITE_PETALS);
        this.createBlockItemModel(BOPBlocks.WATERGRASS);
        this.createBlockItemModel(BOPBlocks.CATTAIL);
        this.createBlockItemModel(BOPBlocks.BARLEY);
        this.createBlockItemModel(BOPBlocks.SEA_OATS);
        this.createBlockItemModel(BOPBlocks.REED);
        this.createBlockItemModel(BOPBlocks.SPIDER_EGG);
        this.createBlockItemModel(BOPBlocks.STRINGY_COBWEB);
        this.createBlockItemModel(BOPBlocks.PUS_BUBBLE);
        this.createBlockItemModel(BOPBlocks.EYEBULB);
        this.createBlockItemModel(BOPBlocks.HAIR);
        this.createBlockItemModel(BOPBlocks.GLOWWORM_SILK);
        this.createBlockItemModel(BOPBlocks.BARNACLES);
        this.createBlockItemModel(BOPBlocks.DEAD_BRANCH);
        this.createBlockItemModel(BOPBlocks.WATERLILY);
        this.createBlockItemModel(BOPBlocks.PINK_DAFFODIL);
        this.createTintedBlockItemModel(BOPBlocks.CLOVER, new GrassColorSource());
        this.createTintedBlockItemModel(BOPBlocks.HUGE_CLOVER_PETAL, new GrassColorSource());
        this.createTintedBlockItemModel(BOPBlocks.HIGH_GRASS, new GrassColorSource());
        this.createTintedBlockItemModel(BOPBlocks.HUGE_LILY_PAD, ItemModelUtils.constantTint(-9321636));
    }

    public void createLeavesOverlay(Block block, int tint)
    {
        TextureMapping textureMapping = BOPTextureMapping.leavesOverlay(block);
        ResourceLocation model = BOPModelTemplates.LEAVES_OVERLAY.create(block, textureMapping, this.modelOutput);
        this.blockStateOutput.accept(createSimpleBlock(block, model));
        this.registerSimpleTintedItemModel(block, model, ItemModelUtils.constantTint(tint));
    }

    @Override
    public BlockModelGenerators.BlockFamilyProvider family(Block block)
    {
        TexturedModel texturedmodel = this.texturedModels.getOrDefault(block, TexturedModel.CUBE.get(block));
        return new BOPBlockFamilyProvider(texturedmodel.getMapping()).fullBlock(block, texturedmodel.getTemplate());
    }

    public void createWillowVine()
    {
        this.createMultifaceBlockStates(BOPBlocks.WILLOW_VINE);
        ResourceLocation resourcelocation = this.createFlatItemModelWithBlockTexture(BOPItems.WILLOW_VINE, BOPBlocks.WILLOW_VINE);
        this.registerSimpleTintedItemModel(BOPBlocks.WILLOW_VINE, resourcelocation, ItemModelUtils.constantTint(FoliageColor.FOLIAGE_DEFAULT));
    }

    public void createBlockItemModel(Block block)
    {
        ResourceLocation resourcelocation = this.createFlatItemModel(block.asItem());
        this.registerSimpleItemModel(block, resourcelocation);
    }

    public void createTintedBlockItemModel(Block block, ItemTintSource tint)
    {
        ResourceLocation resourcelocation = this.createFlatItemModel(block.asItem());
        this.registerSimpleTintedItemModel(block, resourcelocation, tint);
    }

    public void createTintedItemModel(Block block, ItemTintSource tint)
    {
        ResourceLocation resourcelocation = this.createFlatItemModelWithBlockTexture(block.asItem(), block);
        this.registerSimpleTintedItemModel(block, resourcelocation, tint);
    }

    public void logWithKnot(Block block)
    {
        var logMapping = TextureMapping.logColumn(block);
        var logKnotMapping = BOPTextureMapping.logColumnKnot(block);
        ResourceLocation columnModel = ModelTemplates.CUBE_COLUMN.create(block, logMapping, this.modelOutput);
        ResourceLocation horizontalModel = ModelTemplates.CUBE_COLUMN_HORIZONTAL.create(block, logMapping, this.modelOutput);
        ResourceLocation columnKnotModel = ModelTemplates.CUBE_COLUMN.createWithSuffix(block, "_knot", logKnotMapping, this.modelOutput);
        ResourceLocation horizontalKnotModel = ModelTemplates.CUBE_COLUMN_HORIZONTAL.createWithSuffix(block, "_knot", logKnotMapping, this.modelOutput);
        this.blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(block)
                .with(
                        PropertyDispatch.property(BlockStateProperties.AXIS)
                                .select(Direction.Axis.Y, List.of(
                                        Variant.variant().with(VariantProperties.MODEL, columnModel),
                                        Variant.variant().with(VariantProperties.MODEL, columnKnotModel)
                                ))
                                .select(
                                        Direction.Axis.Z,
                                        List.of(
                                                Variant.variant().with(VariantProperties.MODEL, horizontalModel).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90),
                                                Variant.variant().with(VariantProperties.MODEL, horizontalKnotModel).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                                        )
                                )
                                .select(
                                        Direction.Axis.X,
                                        List.of(Variant.variant()
                                                .with(VariantProperties.MODEL, horizontalModel)
                                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90),
                                                Variant.variant()
                                                        .with(VariantProperties.MODEL, horizontalKnotModel)
                                                        .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                        )
                                )
                )
        );
    }

    public void createGlowshroomBlock(Block p_388752_)
    {
        ResourceLocation resourcelocation = ModelTemplates.SINGLE_FACE.create(p_388752_, TextureMapping.defaultTexture(p_388752_), this.modelOutput);
        ResourceLocation resourcelocation1 = ModelLocationUtils.getModelLocation(p_388752_, "_inside");
        this.blockStateOutput
                .accept(
                        MultiPartGenerator.multiPart(p_388752_)
                                .with(Condition.condition().term(BlockStateProperties.NORTH, true), Variant.variant().with(VariantProperties.MODEL, resourcelocation))
                                .with(
                                        Condition.condition().term(BlockStateProperties.EAST, true),
                                        Variant.variant()
                                                .with(VariantProperties.MODEL, resourcelocation)
                                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                                .with(VariantProperties.UV_LOCK, true)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.SOUTH, true),
                                        Variant.variant()
                                                .with(VariantProperties.MODEL, resourcelocation)
                                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                                .with(VariantProperties.UV_LOCK, true)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.WEST, true),
                                        Variant.variant()
                                                .with(VariantProperties.MODEL, resourcelocation)
                                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                                .with(VariantProperties.UV_LOCK, true)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.UP, true),
                                        Variant.variant()
                                                .with(VariantProperties.MODEL, resourcelocation)
                                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R270)
                                                .with(VariantProperties.UV_LOCK, true)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.DOWN, true),
                                        Variant.variant()
                                                .with(VariantProperties.MODEL, resourcelocation)
                                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                                                .with(VariantProperties.UV_LOCK, true)
                                )
                                .with(Condition.condition().term(BlockStateProperties.NORTH, false), Variant.variant().with(VariantProperties.MODEL, resourcelocation1))
                                .with(
                                        Condition.condition().term(BlockStateProperties.EAST, false),
                                        Variant.variant()
                                                .with(VariantProperties.MODEL, resourcelocation1)
                                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                                .with(VariantProperties.UV_LOCK, false)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.SOUTH, false),
                                        Variant.variant()
                                                .with(VariantProperties.MODEL, resourcelocation1)
                                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                                .with(VariantProperties.UV_LOCK, false)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.WEST, false),
                                        Variant.variant()
                                                .with(VariantProperties.MODEL, resourcelocation1)
                                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                                .with(VariantProperties.UV_LOCK, false)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.UP, false),
                                        Variant.variant()
                                                .with(VariantProperties.MODEL, resourcelocation1)
                                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R270)
                                                .with(VariantProperties.UV_LOCK, false)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.DOWN, false),
                                        Variant.variant()
                                                .with(VariantProperties.MODEL, resourcelocation1)
                                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                                                .with(VariantProperties.UV_LOCK, false)
                                )
                );
        this.registerSimpleItemModel(p_388752_, TexturedModel.CUBE.createWithSuffix(p_388752_, "_inventory", this.modelOutput));
    }

    public class BOPBlockFamilyProvider extends BlockFamilyProvider
    {
        public BOPBlockFamilyProvider(TextureMapping p_388151_)
        {
            super(p_388151_);
        }

        @Override
        public BlockModelGenerators.BlockFamilyProvider fullBlockVariant(Block block)
        {
            TexturedModel texturedmodel = BOPBlockModelGenerators.this.texturedModels.getOrDefault(block, TexturedModel.CUBE.get(block));
            ResourceLocation resourcelocation = texturedmodel.create(block, BOPBlockModelGenerators.this.modelOutput);
            BOPBlockModelGenerators.this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, resourcelocation));
            return this;
        }
    }
}
