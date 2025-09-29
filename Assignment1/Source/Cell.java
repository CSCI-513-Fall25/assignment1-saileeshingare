public class Cell {
    int x, y;
    boolean hasShip = false;
    boolean checked = false;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell c = (Cell) o;
        return x == c.x && y == c.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
