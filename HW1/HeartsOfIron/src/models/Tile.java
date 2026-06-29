package models;

import models.enums.*;

import java.util.*;

public class Tile {
    private final int index; // Unique ID (1 to 56)
    private Country owner; // The country that owns this tile
    private final ArrayList<Integer> landNeighbors = new ArrayList<>(); // Adjacent tiles (land)
    private final ArrayList<Integer> seaNeighbors = new ArrayList<>(); // Adjacent tiles (sea)
    private final Map<FactoryType, List<Factory>> factories = new HashMap<>();
    private TerrainType terrain = TerrainType.Plain;
    public boolean isTerrainChangeable = true;
    private WeatherType weather = WeatherType.Sunny;
    private final Map<BattalionType, List<Battalion>> battalions = new HashMap<>();

    public Tile(int index, Country owner) {
        this.index = index;
        this.owner = owner;
        for (BattalionType type : BattalionType.values()) {
            battalions.put(type, new ArrayList<>());
        }
        for (FactoryType type : FactoryType.values()) {
            factories.put(type, new ArrayList<>());
        }
    }

    public int getIndex() {
        return index;
    }

    public Country getOwner() {
        return owner;
    }

    public void setOwner(Country owner) {
        this.owner = owner;
    }

    public ArrayList<Integer> getLandNeighbors() {
        return landNeighbors;
    }

    public ArrayList<Integer> getSeaNeighbors() {
        return seaNeighbors;
    }

    public void addLandNeighbor(int neighbor) {
        landNeighbors.add(neighbor);
    }

    public void addSeaNeighbor(int neighbor) {
        seaNeighbors.add(neighbor);
    }

    public TerrainType getTerrain() {
        return terrain;
    }

    public void setTerrain(TerrainType terrain) {
        this.terrain = terrain;
    }

    public WeatherType getWeather() {
        return weather;
    }

    public void setWeather(WeatherType weather) {
        this.weather = weather;
    }

    public List<Battalion> getBattalions(BattalionType type) {
        return battalions.get(type);
    }

    public Map<BattalionType, List<Battalion>> getAllBattalions() {
        return battalions;
    }

    public Battalion getBattalionByName(String name) {
        for (BattalionType battalionType : BattalionType.values()) {
            for (Battalion originTileBattalion : getBattalions(battalionType)) {
                if (originTileBattalion.getName().equals(name)) {
                    return originTileBattalion;
                }
            }
        }
        return null;
    }

    public List<Factory> getFactories(FactoryType type) {
        return factories.get(type);
    }

    public Factory getFactoryByName(String name) {
        for (FactoryType factoryType : FactoryType.values()) {
            for (Factory factory : getFactories(factoryType)) {
                if (factory.getName().equals(name)) {
                    return factory;
                }
            }
        }
        return null;
    }

    public double getBattalionTypeTotalPower(BattalionType battalionType) {
        double result = 0;
        for (Battalion battalion : getBattalions(battalionType)) {
            result += battalion.getBasePower();
        }

        if (battalionType.equals(BattalionType.AirForce)) {
            result = Math.floor(result * terrain.getAirAttack());
            result = Math.floor(result * weather.getAirAttack());
        } else if (battalionType.equals(BattalionType.Infantry) ||
                battalionType.equals(BattalionType.Panzer)) {
            result = Math.floor(result * terrain.getAttack());
            result = Math.floor(result * weather.getAttack());
        }
        return result;
    }

    public double getBattalionTypeCaptureLost(BattalionType battalionType) {
        double result = 0;
        for (Battalion battalion : getBattalions(battalionType)) {
            result += battalion.calculateCaptureLoss();
        }
        return result;
    }

    public void addCaptures(BattalionType battalionType, double totalCaptures) {
        for (Battalion battalion : getBattalions(battalionType)) {
            battalion.addBasePower(Math.floor(totalCaptures / getBattalions(battalionType).size()));
        }
    }
}

