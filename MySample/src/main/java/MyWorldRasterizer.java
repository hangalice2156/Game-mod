import org.terasology.math.ChunkMath;
import org.terasology.math.geom.BaseVector2i;
import org.terasology.math.geom.Rect2i;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
import org.terasology.world.viewer.layers.engine.SurfaceHeightFacetLayer;

public class MyWorldRasterizer implements WorldRasterizer
{
    private Block dirt;
    @Override
    public void initialize()
    {
        dirt = CoreRegistry.get(BlockManager.class).getBlock("Core:Dirt");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion)
    {
        SurfaceHeightFacet surfaceHeightFacet = chunkRegion.getFacet(SurfaceHeightFacet.class);
        for(Vector3i position : chunkRegion.getRegion())
        {
            float surfaceHeight = surfaceHeightFacet.getWorld(position.x, position.z);
            if(position.y < surfaceHeight)
            {
                chunk.setBlock(ChunkMath.calcBlockPos(position), dirt);
            }
        }
    }
}
