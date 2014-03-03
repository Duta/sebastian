package grid.search;

import grid.GridDirection;
import search.AStarFrontier;
import search.interfaces.Frontier;

public class GridAStar extends GridSearch {
	public static void main(String[] args) {
		new GridAStar().search();
	}

	@Override
	protected Frontier<GridNode, GridDirection> getFrontier(GridNode goal) {
		return new AStarFrontier<GridNode, GridDirection>(goal) {
			@Override
			protected int totalPathCost(GridNode node) {
				int pathCost = 0;
				while(node.getParent() != null) {
					if(node.getAction() == node.getParent().getAction()) {
						pathCost++;
					} else {
						pathCost += 3;
					}
					node = node.getParent();
				}
				return pathCost;
			}
		};
	}
}
