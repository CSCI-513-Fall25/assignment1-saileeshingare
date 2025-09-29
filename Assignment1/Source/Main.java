import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line;
        int gameCount = 1;

        while ((line = br.readLine()) != null) {
            List<Cell> carrier = new ArrayList<>();
            List<Cell> submarine = new ArrayList<>();

            Matcher m = Pattern.compile("\\((\\d+),(\\d+)\\)").matcher(line);
            int count = 0;
            while (m.find()) {
                int x = Integer.parseInt(m.group(1));
                int y = Integer.parseInt(m.group(2));
                if (count < 5) carrier.add(new Cell(x, y));
                else submarine.add(new Cell(x, y));
                count++;
            }

            BattleshipSearch game = new BattleshipSearch();
            game.setupGrid(carrier, submarine);

            System.out.println("Game " + gameCount++ + ":");

            SearchStrategy[] strategies = { new HorizontalSweep(), new RandomSearch(), new StrategicSearch() };
            String[] names = { "Horizontal Sweep", "Random Search", "Strategic Search" };

            for (int i = 0; i < strategies.length; i++) {
                game.setStrategy(strategies[i]);
                SearchResult result = game.executeSearch(carrier, submarine);
                System.out.println("Strategy: " + names[i]);
                System.out.println("Cells searched: " + result.cellsSearched);
                System.out.print("Carrier found: ");
                result.carrierCoords.forEach(c -> System.out.print("(" + c.x + "," + c.y + ")"));
                System.out.println();
                System.out.print("Submarine found: ");
                result.submarineCoords.forEach(c -> System.out.print("(" + c.x + "," + c.y + ")"));
                System.out.println("\n");
            }
        }
    }
}
