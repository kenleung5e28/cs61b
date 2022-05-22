package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class DebugGame {
    public static void printMap(TETile[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j].character());
            }
            System.out.println();
        }
    }
}
