package grid;

public class TestMain {
    public static void main(String[] args) {
        Grid grid = new Grid(10, 5);
        int startX = 0;
        int startY = 0;
        int goalX = 9;
        int goalY = 4;
        GridSearcher searcher = new GridSearcher(grid, startX, startY, goalX, goalY);
        searcher.search();
    }
}
