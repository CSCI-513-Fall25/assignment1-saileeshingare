import java.util.List;
import java.util.Random;

public class RandomSearch implements SearchStrategy {
    public SearchResult search(Cell[][] grid, List<Cell> carrierInput, List<Cell> submarineInput) {
        SearchResult result = new SearchResult();
        Random rand = new Random();

        while (result.carrierCoords.size() < 5 || result.submarineCoords.size() < 3) {
            int x = rand.nextInt(25);
            int y = rand.nextInt(25);
            Cell c = grid[x][y];
            if (!c.checked) {
                c.checked = true;
                result.cellsSearched++;
                if (carrierInput.contains(c)) result.carrierCoords.add(c);
                else if (submarineInput.contains(c)) result.submarineCoords.add(c);
            }
        }
        return result;
    }
}
