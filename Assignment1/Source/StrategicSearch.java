import java.util.*;

public class StrategicSearch implements SearchStrategy {

    private void checkCell(Cell[][] grid, int x, int y, SearchResult result, List<Cell> carrierInput, List<Cell> submarineInput, Queue<Cell> queue) {
        if (x < 0 || x >= 25 || y < 0 || y >= 25) return;

        Cell c = grid[x][y];
        if (!c.checked) {
            c.checked = true;
            result.cellsSearched++;

            if (carrierInput.contains(c) && !result.carrierCoords.contains(c)) result.carrierCoords.add(c);
            else if (submarineInput.contains(c) && !result.submarineCoords.contains(c)) result.submarineCoords.add(c);

            if (c.hasShip) {
                queue.add(new Cell(x + 1, y));
                queue.add(new Cell(x - 1, y));
                queue.add(new Cell(x, y + 1));
                queue.add(new Cell(x, y - 1));
            }
        }
    }

    private void huntAndTarget(Cell[][] grid, SearchResult result, List<Cell> carrierInput, List<Cell> submarineInput) {
        Queue<Cell> targetQueue = new LinkedList<>();

        for (int i = 0; i < 25; i++) {
            for (int j = (i % 2 == 0 ? 0 : 1); j < 25; j += 2) {
                if (result.carrierCoords.size() == 5 && result.submarineCoords.size() == 3) return;
                Cell c = grid[i][j];
                if (!c.checked) {
                    checkCell(grid, i, j, result, carrierInput, submarineInput, targetQueue);
                    while (!targetQueue.isEmpty() && (result.carrierCoords.size() < 5 || result.submarineCoords.size() < 3)) {
                        Cell next = targetQueue.poll();
                        checkCell(grid, next.x, next.y, result, carrierInput, submarineInput, targetQueue);
                    }
                }
            }
        }

        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                if (result.carrierCoords.size() == 5 && result.submarineCoords.size() == 3) return;
                Cell c = grid[i][j];
                if (!c.checked) {
                    checkCell(grid, i, j, result, carrierInput, submarineInput, targetQueue);
                    while (!targetQueue.isEmpty() && (result.carrierCoords.size() < 5 || result.submarineCoords.size() < 3)) {
                        Cell next = targetQueue.poll();
                        checkCell(grid, next.x, next.y, result, carrierInput, submarineInput, targetQueue);
                    }
                }
            }
        }
    }

    public SearchResult search(Cell[][] grid, List<Cell> carrierInput, List<Cell> submarineInput) {
        SearchResult result = new SearchResult();
        huntAndTarget(grid, result, carrierInput, submarineInput);
        return result;
    }
}
