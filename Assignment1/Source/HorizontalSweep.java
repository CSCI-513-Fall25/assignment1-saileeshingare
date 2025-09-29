import java.util.List;

public class HorizontalSweep implements SearchStrategy {
    public SearchResult search(Cell[][] grid, List<Cell> carrierInput, List<Cell> submarineInput) {
        SearchResult result = new SearchResult();
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                Cell c = grid[i][j];
                if (!c.checked) {
                    c.checked = true;
                    result.cellsSearched++;
                    if (carrierInput.contains(c)) result.carrierCoords.add(c);
                    else if (submarineInput.contains(c)) result.submarineCoords.add(c);
                }
                if (result.carrierCoords.size() == 5 && result.submarineCoords.size() == 3)
                    return result;
            }
        }
        return result;
    }
}
