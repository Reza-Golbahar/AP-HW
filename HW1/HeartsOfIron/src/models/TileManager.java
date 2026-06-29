package models;

import models.enums.CountryType;

import java.util.HashMap;
import java.util.Map;

public class TileManager {
    private final Map<Integer, Tile> tiles = new HashMap<>();

    public TileManager() {
        initializeTiles();
        defineNeighbors();
    }

    private void initializeTiles() {
        // Assign each tile an initial owner (hardcoded)
        for (int i = 1; i <= 56; i++) {
            CountryType owner = getInitialOwner(i);
            tiles.put(i, new Tile(i, App.getRunningGame().getCountryByCountryType(owner)));
        }
    }

    public CountryType getInitialOwner(int index) {
        if (index >= 1 && index <= 16) return CountryType.SovietUnion;
        if (index >= 17 && index <= 34) return CountryType.USA;
        if (index >= 35 && index <= 50) return CountryType.Germany;
        if (index >= 51 && index <= 53) return CountryType.Japan;
        if (index >= 54 && index <= 56) return CountryType.UK;
        return null;
    }

    private void defineNeighbors() {
        //just written the neighbors with index bigger than the tile itself, because the adding is BiDirectional
        // Hardcode land neighbors
        addLandNeighbors(1, 2, 5);
        addLandNeighbors(2, 3, 6);
        addLandNeighbors(3, 4, 7);
        addLandNeighbors(4, 8);
        addLandNeighbors(5, 6, 9);
        addLandNeighbors(6, 7, 10);
        addLandNeighbors(7, 8, 11);
        addLandNeighbors(8, 12);
        addLandNeighbors(9, 10, 13);
        addLandNeighbors(10, 11, 14);
        addLandNeighbors(11, 12, 15);
        addLandNeighbors(12, 16, 35);
        addLandNeighbors(13, 14);
        addLandNeighbors(14, 15, 17, 18);
        addLandNeighbors(15, 16, 19, 20);
        addLandNeighbors(16, 21, 22, 39);

        addLandNeighbors(17, 18, 23);
        addLandNeighbors(18, 19, 24);
        addLandNeighbors(19, 20, 25);
        addLandNeighbors(20, 21, 26);
        addLandNeighbors(21, 22, 27);
        addLandNeighbors(22, 28, 43);
        addLandNeighbors(23, 24, 29);
        addLandNeighbors(24, 25, 30);
        addLandNeighbors(25, 26, 31);
        addLandNeighbors(26, 27, 32);
        addLandNeighbors(27, 28, 33);
        addLandNeighbors(28, 34, 47);
        addLandNeighbors(29, 30);
        addLandNeighbors(30, 31);
        addLandNeighbors(31, 32);
        addLandNeighbors(32, 33);
        addLandNeighbors(33, 34);

        addLandNeighbors(35, 36, 39);
        addLandNeighbors(36, 37, 40);
        addLandNeighbors(37, 38, 41, 54);
        addLandNeighbors(38, 42, 55);
        addLandNeighbors(39, 40, 43);
        addLandNeighbors(40, 41, 44);
        addLandNeighbors(41, 42, 45);
        addLandNeighbors(42, 46);
        addLandNeighbors(43, 44, 47);
        addLandNeighbors(44, 45, 48);
        addLandNeighbors(45, 46, 49);
        addLandNeighbors(46, 50);
        addLandNeighbors(47, 48);
        addLandNeighbors(48, 49);
        addLandNeighbors(49, 50);
        addLandNeighbors(50, 51, 52);

        addLandNeighbors(51, 53);
        addLandNeighbors(52, 53);

        addLandNeighbors(54, 55);
        addLandNeighbors(55, 56);

        // Hardcode sea neighbors
        addSeaNeighbors(8, 35, 36, 54);
        addSeaNeighbors(35, 36, 54);
        addSeaNeighbors(36, 54);

        addSeaNeighbors(34, 47, 48, 49, 52);
        addSeaNeighbors(47, 48, 49, 52);
        addSeaNeighbors(48, 49, 52);
        addSeaNeighbors(49, 52);
    }

    private void addLandNeighbors(int tileIndex, int... neighbors) {
        for (int neighbor : neighbors) {
            tiles.get(tileIndex).addLandNeighbor(neighbor);
            tiles.get(neighbor).addLandNeighbor(tileIndex); // Bidirectional
        }
    }

    private void addSeaNeighbors(int tileIndex, int... neighbors) {
        for (int neighbor : neighbors) {
            tiles.get(tileIndex).addSeaNeighbor(neighbor);
            tiles.get(neighbor).addSeaNeighbor(tileIndex); // Bidirectional
        }
    }

    public Tile getTile(int index) {
        return tiles.get(index);
    }
}
