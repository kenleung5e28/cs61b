package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;

public class Solver {
    private class SearchNode {
        private WorldState state;
        private int moves;
        private SearchNode previous;

        private int estimatedDistanceToGoal;

        SearchNode(WorldState state, int moves, SearchNode previous) {
            this.state = state;
            this.moves = moves;
            this.previous = previous;
            this.estimatedDistanceToGoal = -1;
        }

        public WorldState state() {
            return this.state;
        }

        private int estimatedDistanceToGoal() {
            if (estimatedDistanceToGoal == -1) {
                estimatedDistanceToGoal = state.estimatedDistanceToGoal();
            }
            return estimatedDistanceToGoal;
        }

        public int moves() {
            return this.moves;
        }

        public SearchNode previous() {
            return this.previous;
        }

        public int priority() {
            return moves + estimatedDistanceToGoal();
        }
    }

    private class SearchNodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            return o1.priority() - o2.priority();
        }
    }

    private ArrayList<WorldState> solutionMoveSequence;
    // private int numOfNodesEnqueued;

    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>(new SearchNodeComparator());
        pq.insert(new SearchNode(initial, 0, null));
        // numOfNodesEnqueued = 1;
        SearchNode goal;
        while (true) {
            SearchNode node = pq.delMin();
            WorldState state = node.state();
            if (state.isGoal()) {
                goal = node;
                break;
            }
            int moves = node.moves();
            SearchNode previous = node.previous();
            for (WorldState neighbor : state.neighbors()) {
                if (previous != null && previous.state.equals(neighbor)) {
                    continue;
                }
                pq.insert(new SearchNode(neighbor, moves + 1, node));
                // numOfNodesEnqueued += 1;
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
        return solutionMoveSequence.size() - 1;
    }

    public Iterable<WorldState> solution() {
        return solutionMoveSequence;
    }

//    public int numOfNodesEnqueued() {
//        return numOfNodesEnqueued;
//    }
}
