package hw4.puzzle;

public class Board implements WorldState {
    private int[][] tiles;
    private int N;

    @Override
    public int estimatedDistanceToGoal() {
        // TODO
        return 0;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        int x0;
        int y0;
        outermost:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) {
                    x0 = i;
                    y0 = j;
                    break outermost;
                }
            }
        }
        // TODO
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    /*public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }*/
}
