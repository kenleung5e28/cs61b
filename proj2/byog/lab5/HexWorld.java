package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    /**
     *
     * @param world The world on which the hexagon is drawn
     * @param bottomLeftX The x-coordinate of the bottom left corner of the hexagon
     * @param bottomLeftY The x-coordinate of the bottom left corner of the hexagon
     * @param size The side length of the hexagon
     * @param tile The tile type used in drawing the hexagon
     */
    public static void addHexagon(TETile[][] world, int bottomLeftX, int bottomLeftY, int size, TETile tile) {
        for (int i = 0; i < 2 * size; i++) {
            int y = bottomLeftY + i;
            int xStart = rowStart(size, i, bottomLeftX);
            int xEnd = xStart + rowWidth(size, i);
            for (int x = xStart; x < xEnd; x++) {
                world[x][y] = tile;
            }
        }
    }

    // Calculate the x-coordinate of the leftmost point of the n-th row of a hexagon of given size
    public static int rowStart(int size, int n, int bottomLeftX) {
        if (size < 2) {
            throw new IllegalArgumentException("size must be at least 2");
        }
        if (n < 0 || n >= 2 * size) {
            throw new IllegalArgumentException("n must be between 0 and " + (2 * size - 1));
        }
        if (n < size) {
            return bottomLeftX - n;
        }
        return bottomLeftX - (2 * size - 1 - n);
    }

    // Calculate the width of the n-th row of a hexagon of given size
    public static int rowWidth(int size, int n) {
        if (size < 2) {
            throw new IllegalArgumentException("size must be at least 2");
        }
        if (n < 0 || n >= 2 * size) {
            throw new IllegalArgumentException("n must be between 0 and " + (2 * size - 1));
        }
        if (n < size) {
            return size + 2 * n;
        }
        return size + 2 * (2 * size - 1 - n);
    }

    public enum HexagonDirection {
        NORTHWEST,
        NORTH,
        NORTHEAST
    }

    // Get the coordinates of the bottom left corners of the northwest, northeast and north neighboring hexagon
    public static int neighborX(int size, int x, HexagonDirection dir) {
        if (size < 2) {
            throw new IllegalArgumentException("size must be at least 2");
        }
        return x + switch (dir) {
            case NORTHWEST -> - (2 * size - 1);
            case NORTH -> 0;
            case NORTHEAST -> 2 * size - 1;
        };
    }
    public static int neighborY(int size, int y, HexagonDirection dir) {
        if (size < 2) {
            throw new IllegalArgumentException("size must be at least 2");
        }
        return y + switch (dir) {
            case NORTHWEST, NORTHEAST -> size;
            case NORTH -> 2 * size;
        };
    }

    // place hexagons as a column growing north
    public static void placeHexagonsNorthbound(TETile[][] world, int firstX, int firstY, int size, TETile[] tiles) {
        int x = firstX;
        int y = firstY;
        for (TETile tile : tiles) {
            addHexagon(world, x, y, size, tile);
            x = neighborX(size, x, HexagonDirection.NORTH);
            y = neighborY(size, y, HexagonDirection.NORTH);
        }
    }

    // place north-growing columns of hexagons towards northeast/northwest
    public static void placeHexagonColumns(TETile[][] world, int originX, int originY, int size, boolean eastbound, TETile[][] tileColumns) {
        int x = originX;
        int y = originY;
        for (TETile[] column : tileColumns) {
            HexagonDirection dir = eastbound ? HexagonDirection.NORTHEAST : HexagonDirection.NORTHWEST;
            x = neighborX(size, x, dir);
            y = neighborY(size, y, dir);
            placeHexagonsNorthbound(world, x, y, size, column);
        }
    }

    public static void main(String[] args) {
        int width = 30;
        int height = 30;
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        TETile[][] world = new TETile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
        int x0 = 14;
        int y0 = 0;
        TETile[][] northeastColumns = new TETile[][]{
            new TETile[] {Tileset.MOUNTAIN, Tileset.TREE, Tileset.SAND, Tileset.FLOWER},
            new TETile[] {Tileset.SAND, Tileset.TREE, Tileset.FLOWER}
        };
        TETile[][] northwestColumns = new TETile[][]{
            new TETile[] {Tileset.FLOWER, Tileset.MOUNTAIN, Tileset.MOUNTAIN, Tileset.GRASS},
            new TETile[] {Tileset.GRASS, Tileset.GRASS, Tileset.MOUNTAIN}
        };
        TETile[] northColumn = new TETile[]{Tileset.MOUNTAIN, Tileset.MOUNTAIN, Tileset.MOUNTAIN, Tileset.MOUNTAIN, Tileset.TREE};
        placeHexagonColumns(world, x0, y0, 3, true, northeastColumns);
        placeHexagonsNorthbound(world, x0, y0, 3, northColumn);
        placeHexagonColumns(world, x0, y0, 3, false, northwestColumns);
        ter.renderFrame(world);
    }
}
