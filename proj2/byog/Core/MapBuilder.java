package byog.Core;

import java.util.Random;

public class MapBuilder {
    private final Random random;
    public final int width;
    public final int height;

    public MapBuilder(long seed, int width, int height) {
        this.width = width;
        this.height = height;
        random = new Random(seed);
    }

    public BinaryTree<Region> buildBSPTree() {
        // TODO
        return null;
    }
}
