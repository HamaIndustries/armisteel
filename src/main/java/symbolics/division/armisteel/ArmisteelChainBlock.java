package symbolics.division.armisteel;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArmisteelChainBlock extends PillarBlock implements Waterloggable {
	public static final MapCodec<ArmisteelChainBlock> CODEC = createCodec(ArmisteelChainBlock::new);

	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	protected static final float AABB_MIN = 3.5F;
	protected static final float AABB_MAX = 12.5F;

	protected static final VoxelShape Y_AXIS_AABB = createCuboidShape(AABB_MIN, 0.0, AABB_MIN, AABB_MAX, 16.0, AABB_MAX);
	protected static final VoxelShape Z_AXIS_AABB = createCuboidShape(AABB_MIN, AABB_MIN, 0.0, AABB_MAX, AABB_MAX, 16.0);
	protected static final VoxelShape X_AXIS_AABB = createCuboidShape(0.0, AABB_MIN, AABB_MIN, 16.0, AABB_MAX, AABB_MAX);

	@NotNull
	@Override
	public MapCodec<ArmisteelChainBlock> getCodec() {
		return CODEC;
	}

	public ArmisteelChainBlock(AbstractBlock.Settings properties) {
		super(properties);
		this.setDefaultState(getDefaultState()
			.with(WATERLOGGED, false)
			.with(AXIS, Direction.Axis.Y));
	}

	@Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return switch (state.get(AXIS)) {
			case X -> X_AXIS_AABB;
			case Z -> Z_AXIS_AABB;
			case Y -> Y_AXIS_AABB;
		};
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockState defaultValue = super.getPlacementState(ctx);
		if (defaultValue == null) return null;

		return defaultValue.with(
				WATERLOGGED,
				ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER)
		);
	}


	@Override
	protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (state.get(WATERLOGGED)) world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(WATERLOGGED);
	}

	@NotNull
	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	@Override
	protected boolean canPathfindThrough(BlockState state, NavigationType type) {
		return false;
	}
}
