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

    public BinaryTreeNode<Region> buildBSPTree(int minSideLength) {
        if (minSideLength <= 0 || minSideLength >= width || minSideLength >= height) {
            throw new IllegalArgumentException("minSideLength must be a positive integer less than width and height.");
        }
        Region whole = new Region(0, 0, width, height);
        BinaryTreeNode<Region> root = new BinaryTreeNode<>(whole);
        
        return null;
    }
}
