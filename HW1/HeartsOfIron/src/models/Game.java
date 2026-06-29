package models;

import models.enums.CountryType;

import java.util.ArrayList;

public class Game {
    private final ArrayList<Player> players;
    private Player currentPlayingPlayer;
    private TileManager tileManager;
    private ArrayList<Faction> allFactions = new ArrayList<>();

    public Game(ArrayList<Player> players) {
        this.players = players;
    }

    public void initializeTiles() {
        this.tileManager = new TileManager();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayingPlayer() {
        return currentPlayingPlayer;
    }

    public void setCurrentPlayingPlayer(Player currentPlayingPlayer) {
        this.currentPlayingPlayer = currentPlayingPlayer;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public ArrayList<Faction> getAllFactions() {
        return allFactions;
    }

    public Player getPlayerByUsername(String username) {
        for (Player player : players) {
            if (player.getUser().getUsername().equals(username)) {
                return player;
            }
        }
        return null;
    }


    public Country getCountryByCountryType(CountryType countryType) {
        for (Player player : players) {
            if (player.getPlayerCountry().getType().equals(countryType)) {
                return player.getPlayerCountry();
            }
        }
        return null; //never happens
    }


    public boolean isTileReachable(Tile tile, Country myCountry) {
        Country tileOwner = tile.getOwner();
        if (tileOwner.equals(myCountry)) {
            return true;
        } else if (myCountry.getPuppets().contains(tileOwner)) {
            return true;
        } else {
            for (Faction faction : myCountry.getFactions()) {
                for (Faction tileOwnerFaction : tileOwner.getFactions()) {
                    if (faction.getName().equals(tileOwnerFaction.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public Faction getFactionByName(String name) {
        for (Faction faction : allFactions) {
            if (faction.getName().equals(name)) {
                return faction;
            }
        }
        return null;
    }


    public Player getPlayerByCountry(Country country) {
        for (Player player : players) {
            if (player.getPlayerCountry().equals(country)) {
                return player;
            }
        }
        return null; //never happens
    }
}
