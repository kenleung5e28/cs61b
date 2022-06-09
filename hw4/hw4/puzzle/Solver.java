package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;

public class Solver {
    private class SearchNode {
        private WorldState state;
        private int moves;
        private SearchNode previous;

        public SearchNode(WorldState state, int moves, SearchNode previous) {
            this.state = state;
            this.moves = moves;
            this.previous = previous;
        }

        public WorldState state() {
            return this.state;
        }

        public int moves() {
            return this.moves;
        }

        public SearchNode previous() {
            return this.previous;
        }

        public int priority() {
            return moves + state.estimatedDistanceToGoal();
        }
    }

    private class SearchNodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            return o1.priority() - o2.priority();
        }
    }

    private ArrayList<WorldState> solutionMoveSequence;

    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>(new SearchNodeComparator());
        pq.insert(new SearchNode(initial, 0, null));
        SearchNode goal;
        while (true) {
            SearchNode node = pq.delMin();
            WorldState state = node.state();
            if (state.isGoal()) {
                goal = node;
                break;
            }
            int moves = node.moves();
            for (WorldState neighbor : state.neighbors()) {
                pq.insert(new SearchNode(neighbor, moves + 1, node));
            }
        }
        solutionMoveSequence = new ArrayList<>();
        SearchNode p = goal;
        while (p != null) {
            solutionMoveSequence.add(0, p.state());
            p = p.previous();
        }
    }

    public int moves() {
        return solutionMoveSequence.size();
    }

    public Iterable<WorldState> solution() {
        return solutionMoveSequence;
    }
}
