package search;

import java.util.Stack;

import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;

public class EightPuzzleDepthFirst {
	public static void main(String[] args) {
		EightPuzzleState start = new EightPuzzleState(EightPuzzle.randomEightPuzzle());
		EightPuzzleState goal = new EightPuzzleState(EightPuzzle.orderedEightPuzzle());
		
		System.out.println("Start State:");
		System.out.println(start);
		System.out.println("Goal State:");
		System.out.println(goal);
		
		Stack<EightPuzzleState> path = Search.search(
				start, goal,
				new DepthFirstFrontier<EightPuzzleState, PuzzleMove>());
		
		System.out.println("Path (Length " + path.size() + "):");
		while(true) {
			if(path.empty()) {
				break;
			}
			
			EightPuzzleState state = path.pop();
			System.out.println(state.getAction());
		}
	}
}
