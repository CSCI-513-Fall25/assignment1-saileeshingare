import java.util.List;

public class BattleshipSearch {
    private Cell[][] grid = new Cell[25][25];
    private SearchStrategy strategy;

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public SearchResult executeSearch(List<Cell> carrierInput, List<Cell> submarineInput) {
        for (int i = 0; i < 25; i++)
            for (int j = 0; j < 25; j++)
                grid[i][j].checked = false;

        return strategy.search(grid, carrierInput, submarineInput);
    }

    public void setupGrid(List<Cell> carrier, List<Cell> submarine) {
        for (int i = 0; i < 25; i++)
            for (int j = 0; j < 25; j++)
                grid[i][j] = new Cell(i, j);

        for (Cell c : carrier) grid[c.x][c.y].hasShip = true;
        for (Cell c : submarine) grid[c.x][c.y].hasShip = true;
    }
}
