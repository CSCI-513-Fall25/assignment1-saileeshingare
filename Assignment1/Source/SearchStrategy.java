import java.util.List;

public interface SearchStrategy {
    SearchResult search(Cell[][] grid, List<Cell> carrierInput, List<Cell> submarineInput);
}
