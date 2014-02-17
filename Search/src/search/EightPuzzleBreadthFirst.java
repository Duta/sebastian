package search;

import java.util.Stack;

import rp13.search.problem.puzzle.EightPuzzle;

public class EightPuzzleBreadthFirst {
	public static void main(String[] args) {
		EightPuzzleState start = new EightPuzzleState(EightPuzzle.randomEightPuzzle());
		EightPuzzleState goal = new EightPuzzleState(EightPuzzle.orderedEightPuzzle());
		
		System.out.println("Start State:");
		System.out.println(start);
		System.out.println("Goal State:");
		System.out.println(goal);
		
		Stack<EightPuzzleState> path = Search.search(
				start, goal,
				new BreadthFirstFrontier<EightPuzzleState>());
		
		System.out.println("Path (Length " + path.size() + "):");
		while(true) {
			if(path.empty()) {
				break;
			}
			
			EightPuzzleState state = path.pop();
			System.out.println(state);
		}
	}
}
