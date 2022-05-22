package byog.Core;

import java.util.Random;

import byog.TileEngine.TERenderer;
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
            if (region.width - minSideLength <= minSideLength) {
                return;
            }
            int leftWidth = RandomUtils.uniform(random, minSideLength, region.width - minSideLength);
            int rightWidth = region.width - 1 - leftWidth;
            leftRegion = new Region(region.x, region.y, leftWidth, region.height);
            rightRegion = new Region(region.x + 1 + leftWidth, region.y, rightWidth, region.height);
        } else {
            if (region.height - minSideLength <= minSideLength) {
                return;
            }
            int leftHeight = RandomUtils.uniform(random, minSideLength, region.height - minSideLength);
            int rightHeight = region.height - 1 - leftHeight;
            leftRegion = new Region(region.x, region.y, region.width, leftHeight);
            rightRegion = new Region(region.x, region.y + 1 + leftHeight, region.width, rightHeight);
        }
        node.left = new BinaryTreeNode<>(leftRegion);
        node.right = new BinaryTreeNode<>(rightRegion);
        splitBSPTreeNode(node.left);
        splitBSPTreeNode(node.right);
    }

    private TETile[][] createEmptyMap() {
        TETile[][] map = new TETile[width][];
        for (int i = 0; i < width; i++) {
            TETile[] row = new TETile[height];
            for (int j = 0; j < height; j++) {
                row[j] = Tileset.WALL;
            }
            map[i] = row;
        }
        return map;
    }

    private TETile[][] buildMapFromBSPTree(BinaryTreeNode<Region> bspTree) {
        TETile[][] map = createEmptyMap();
        renderBSPTreeNode(map, bspTree);
        return map;
    }

    private void renderBSPTreeNode(TETile[][] map, BinaryTreeNode<Region> node) {
        if (node == null) {
            return;
        }
        if (!node.isLeaf()) {
            renderBSPTreeNode(map, node.left);
            renderBSPTreeNode(map, node.right);
            return;
        }
        Region region = node.value;
        int x0 = region.x;
        int x1 = x0 + region.width;
        int y0 = region.y;
        int y1 = y0 + region.height;
        for (int i = x0; i < x1; i++) {
            for (int j = y0; j < y1; j++) {
                map[i][j] = Tileset.FLOOR;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer renderer = new TERenderer();
        MapBuilder builder = new MapBuilder(12345, 100, 50, 5);
        BinaryTreeNode<Region> bspTree = builder.buildBSPTree();
        TETile[][] map = builder.buildMapFromBSPTree(bspTree);
        // DebugGame.printMap(map);
        renderer.renderFrame(map);
    }
}
