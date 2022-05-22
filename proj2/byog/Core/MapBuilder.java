package byog.Core;

import java.util.Random;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class MapBuilder {
    private final Random random;
    public final int width;
    public final int height;
    public final int minSideLength;

    public MapBuilder(long seed, int width, int height, int minSideLength) {
        if (width <= 0) {
            throw new IllegalArgumentException("width must be positive.");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("height must be positive.");
        }
        if (minSideLength <= 0 || minSideLength >= width || minSideLength >= height) {
            throw new IllegalArgumentException("minSideLength must be positive and less than width and height.");
        }
        this.width = width;
        this.height = height;
        this.minSideLength = minSideLength;
        random = new Random(seed);
    }

    public BinaryTreeNode<Region> buildBSPTree() {
        Region whole = new Region(0, 0, width, height);
        BinaryTreeNode<Region> root = new BinaryTreeNode<>(whole);
        splitBSPTreeNode(root);
        return root;
    }

    private void splitBSPTreeNode(BinaryTreeNode<Region> node) {
        Region leftRegion;
        Region rightRegion;
        Region region = node.value;
        boolean splitVertically = RandomUtils.uniform(random) > 0.5;
        if (splitVertically) {
            int leftWidth = (region.width - 1) / 2;
            int rightWidth = region.width - leftWidth;
            if (Math.min(leftWidth, rightWidth) < minSideLength) {
                return;
            }
            leftRegion = new Region(region.x, region.y, leftWidth, region.height);
            rightRegion = new Region(region.x + leftWidth + 1, region.y, rightWidth, region.height);
        } else {
            int leftHeight = (region.height - 1) / 2;
            int rightHeight = region.height - leftHeight;
            if (Math.min(leftHeight, rightHeight) < minSideLength) {
                return;
            }
            leftRegion = new Region(region.x, region.y, region.width, leftHeight);
            rightRegion = new Region(region.x, region.y + leftHeight + 1, region.width, rightHeight);
        }
        node.left = new BinaryTreeNode<>(leftRegion);
        node.right = new BinaryTreeNode<>(rightRegion);
        splitBSPTreeNode(node.left);
        splitBSPTreeNode(node.right);
    }

    private TETile[][] buildMapFromBSPTree(BinaryTreeNode<Region> bspTree) {

    }

    public static void main(String[] args) {
        MapBuilder builder = new MapBuilder(12345, 100, 50, 5);
        BinaryTreeNode<Region> bspTree = builder.buildBSPTree();
    }
}
