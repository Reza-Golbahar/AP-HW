package controllers;

import models.*;
import models.enums.*;

import java.util.*;

public class GameMenuController {
    ArrayList<CountryType> countryTypesChosen = new ArrayList<>();

    public Result showCurrentMenu() {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked");
        }
        return new Result(true, "%s".formatted(App.giveCurrentMenuName()));
    }


    public void exit() {
        App.setCurrentMenu(Menu.ExitMenu);
    }

    public void chooseCountries(Scanner scanner) {
        Result result = chooseCountry("", true);

        while (!result.message().isEmpty()) {
            System.out.println(result);
            result = chooseCountry(scanner.nextLine(), false);
        }
    }


    public Result chooseCountry(String input, boolean isFirstTimeRunning) {
        if (isFirstTimeRunning) return new Result(true,
                "choosing country for %s:".formatted(App.getLoggedInUser().getUsername()));

        CountryType selectedCountry = null;
        for (CountryType country : CountryType.values()) {
            if (country.getDisplayName().equals(input.trim())) {
                selectedCountry = country;
                break;
            }
        }
        if (selectedCountry == null) {
            return new Result(false, "wrong country name");
        }

        for (CountryType countryTypeChosen : countryTypesChosen) {
            if (countryTypeChosen == selectedCountry) {
                return new Result(false, "country already taken");
            }
        }

        App.getRunningGame().getPlayers().get(countryTypesChosen.size()).setPlayerCountry(
                new Country(selectedCountry));

        countryTypesChosen.add(selectedCountry);
        if (countryTypesChosen.size() == 5) {
            App.getRunningGame().setCurrentPlayingPlayer(
                    App.getRunningGame().getPlayers().get(0)
            );
            App.getRunningGame().initializeTiles();
            return new Result(true, "");
        } else {
            return new Result(true, "choosing country for %s:".formatted(
                    App.getRunningGame().getPlayers().get(countryTypesChosen.size()).getUser().getUsername()
            ));
        }
    }


    public Result switchPlayer(String username) {
        Player player = App.getRunningGame().getPlayerByUsername(username);
        if (player == null) {
            return new Result(false, "player doesn't exist");
        } else if (App.getRunningGame().getCurrentPlayingPlayer().getUser().getUsername().equals(username)) {
            return new Result(false, "you can't switch to yourself");
        }
        App.getRunningGame().setCurrentPlayingPlayer(player);
        return new Result(true, "switched to %s".formatted(username));
    }


    public Result showDetail(String countryName) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked");
        }
        countryName = countryName.trim();

        Country country = null;
        for (Player player : App.getRunningGame().getPlayers()) {
            Country playerCountry = player.getPlayerCountry();

            if (playerCountry.getName().equals(countryName)) {
                country = playerCountry;
            }
        }
        if (country == null) {
            return new Result(false, "country doesn't exist");
        }

        StringBuilder result = new StringBuilder();
        result.append("%s\n".formatted(country.getName()) +
                "leader : %s\n".formatted(country.getCurrentLeader().toString().toLowerCase()) +
                "stability : %.0f\n".formatted(country.getStability()) +
                "man power : %.0f\n".formatted(country.getResources().getManpower()) +
                "fuel : %.0f\n".formatted(country.getResources().getFuel()) +
                "sulfur : %.0f\n".formatted(country.getResources().getSulfur()) +
                "steel : %.0f\n".formatted(country.getResources().getSteel()) +
                "faction :");

        if (!country.getFactions().isEmpty())
            result.append(" ");

        int counter = 1;
        for (Faction faction : country.getFactions()) {
            result.append(faction.getName().toLowerCase());
            if (counter++ < country.getFactions().size()) {
                result.append(",");
            }
        }

        result.append("\npuppet :");

        if (!country.getPuppets().isEmpty()) {
            result.append(" ");
            int counter2 = 1;
            for (Country puppet : country.getPuppets()) {
                result.append(puppet.getName());
                if (counter2++ < country.getPuppets().size()) {
                    result.append(",");
                }
            }
        }
        return new Result(true, result.toString());
    }


    public Result tileOwner(String index) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked");
        }
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked");
        }
        int indexCasted = Integer.parseInt(index);
        if (indexCasted > 56 || indexCasted < 1) {
            return new Result(false, "tile doesn't exist");
        }
        return new Result(true, App.getRunningGame().getTileManager()
                .getTile(indexCasted).getOwner().getType().getDisplayName());
    }


    public Result tileNeighbors(String index) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked");
        }
        int indexCasted = Integer.parseInt(index);
        if (indexCasted > 56 || indexCasted < 1) {
            return new Result(false, "tile doesn't exist");
        }
        ArrayList<Integer> neighbors = App.getRunningGame().getTileManager().getTile(indexCasted)
                .getLandNeighbors();

        //making sure it is sorted
        neighbors.sort(Integer::compareTo);

        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (Integer neighbor : neighbors) {
            result.append(neighbor);
            if (counter++ < neighbors.size()) {
                result.append(" , ");
            }
        }
        return new Result(true, result.toString());
    }


    public Result tileSeaNeighbors(String index) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked");
        }
        int indexCasted = Integer.parseInt(index);
        if (indexCasted > 56 || indexCasted < 1) {
            return new Result(false, "tile doesn't exist");
        }
        ArrayList<Integer> seaNeighbors = App.getRunningGame().getTileManager().getTile(indexCasted)
                .getSeaNeighbors();

        if (seaNeighbors.isEmpty())
            return new Result(false, "no sea neighbors");

        //making sure it is sorted
        seaNeighbors.sort(Integer::compareTo);

        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (Integer neighbor : seaNeighbors) {
            result.append(neighbor);
            if (counter++ < seaNeighbors.size()) {
                result.append(" , ");
            }
        }
        return new Result(true, result.toString());
    }


    public Result showWeather(String index) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked");
        }
        int indexCasted = Integer.parseInt(index);
        if (indexCasted > 56 || indexCasted < 1) {
            return new Result(false, "tile doesn't exist");
        }
        return new Result(true, App.getRunningGame().getTileManager().getTile(indexCasted)
                .getWeather().name().toLowerCase());
    }


    public Result showTerrain(String index) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked");
        }
        int indexCasted = Integer.parseInt(index);
        if (indexCasted > 56 || indexCasted < 1) {
            return new Result(false, "tile doesn't exist");
        }
        return new Result(true, App.getRunningGame().getTileManager().getTile(indexCasted)
                .getTerrain().name().toLowerCase());
    }


    public Result showBattalions(String index) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked");
        }
        int indexCasted = Integer.parseInt(index);
        if (indexCasted > 56 || indexCasted < 1) {
            return new Result(false, "tile doesn't exist");
        }
        Tile tile = App.getRunningGame().getTileManager().getTile(indexCasted);
        Country myCountry = App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry();

        if (!App.getRunningGame().isTileReachable(tile, myCountry)) {
            return new Result(false, "can't show battalions");
        }

        StringBuilder result = new StringBuilder();
        String[] names = {"infantry:\n", "panzer:\n", "airforce:\n", "navy:\n"};
        BattalionType[] types = {BattalionType.Infantry, BattalionType.Panzer, BattalionType.AirForce,
                BattalionType.Navy};


        for (int i = 0; i < 4; i++) {
            result.append(names[i]);
            List<Battalion> battalions = tile.getBattalions(types[i]);
            battalions.sort(Comparator.comparing(Battalion::getName, String.CASE_INSENSITIVE_ORDER));

            for (Battalion battalion : battalions) {
                result.append("%s %d %.0f %.0f\n".formatted(
                        battalion.getName(),
                        battalion.getLevel(),
                        battalion.getBasePower(),
                        (100 * battalion.getCapturePercentage())));
            }
            result.append("\n");
        }

        return new Result(true, result.substring(0, result.length() - 2));

    }


    public Result showFactories(String index) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked");
        }
        int indexCasted = Integer.parseInt(index);
        if (indexCasted > 56 || indexCasted < 1) {
            return new Result(false, "tile doesn't exist");
        }
        Tile tile = App.getRunningGame().getTileManager().getTile(indexCasted);
        Country myCountry = App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry();

        if (!App.getRunningGame().isTileReachable(tile, myCountry)) {
            return new Result(false, "can't show factories");
        }

        StringBuilder result = new StringBuilder();
        String[] names = {"fuel refinery:\n", "steel factory:\n", "sulfur factory:\n"};
        FactoryType[] types = {FactoryType.FuelRefinery, FactoryType.SteelFactory, FactoryType.SulfurFactory};

        for (int i = 0; i < 3; i++) {
            result.append(names[i]);
            List<Factory> factories = tile.getFactories(types[i]);
            factories.sort(Comparator.comparing(factory -> factory.getName(), String.CASE_INSENSITIVE_ORDER));

            for (Factory factory : factories) {
                result.append("%s %.0f\n".formatted(factory.getName(),
                        factory.getType().getMaximumProduction() - factory.getProducedCount()));
            }
            result.append("\n");
        }

        return new Result(true, result.substring(0, result.length() - 2));
    }


    public Result setTerrain(String index, String terrainName) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked");
        }
        int indexCasted = Integer.parseInt(index);
        if (indexCasted > 56 || indexCasted < 1) {
            return new Result(false, "you don't own this tile");
        }

        Tile tile = App.getRunningGame().getTileManager().getTile(indexCasted);

        if (!tile.getOwner().equals(
                App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry()
        )) {
            return new Result(false, "you don't own this tile");
        }

        TerrainType terrain = null;
        for (TerrainType terrainType : TerrainType.values()) {
            if (terrainName.equals(terrainType.name().toLowerCase())) {
                terrain = terrainType;
            }
        }

        if (terrain == null) {
            return new Result(false, "terrain doesn't exist");
        } else if (!tile.isTerrainChangeable) {
            return new Result(false, "you can't change terrain twice");
        }

        tile.isTerrainChangeable = false;
        tile.setTerrain(terrain);
        return new Result(true, "terrain set successfully");
    }


    public Result setWeather(String index, String weatherName) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked");
        }
        int indexCasted = Integer.parseInt(index);
        if (indexCasted > 56 || indexCasted < 1) {
            return new Result(false, "tile doesn't exist");
        }

        Tile tile = App.getRunningGame().getTileManager().getTile(indexCasted);

        if (!tile.getOwner().equals(
                App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry()
        )) {
            return new Result(false, "you don't own this tile");
        }

        WeatherType weather = null;
        for (WeatherType weatherType : WeatherType.values()) {
            if (weatherName.equals(weatherType.name().toLowerCase())) {
                weather = weatherType;
            }
        }

        if (weather == null) {
            return new Result(false, "weather doesn't exist");
        }

        tile.setWeather(weather);
        return new Result(true, "weather set successfully");
    }


    public Result addBattalion(String index, String battalionType, String name) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked");
        }
        int indexCasted = Integer.parseInt(index);
        if (indexCasted > 56 || indexCasted < 1) {
            return new Result(false, "tile is unavailable");
        }
        Tile tile = App.getRunningGame().getTileManager().getTile(indexCasted);
        Country myCountry = App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry();

        if (!App.getRunningGame().isTileReachable(tile, myCountry)) {
            return new Result(false, "tile is unavailable");
        }

        BattalionType type = null;
        for (BattalionType value : BattalionType.values()) {
            if (value.name().toLowerCase().equals(battalionType)) {
                type = value;
            }
        }
        if (type == null) {
            return new Result(false, "you can't use imaginary battalions");
        }

        for (BattalionType value : BattalionType.values()) {
            for (Battalion battalion : tile.getBattalions(value)) {
                if (battalion.getName().equals(name)) {
                    return new Result(false, "battalion name already taken");
                }
            }
        }

        Resources myResources = myCountry.getResources();

        Resources resourcesNeeded = type.getResourcesNeeded();
        Resources resourcesNeededCopy = new Resources(resourcesNeeded);

        if (myCountry.getCurrentLeader().getIdeology().equals(Ideology.Democracy)) {
            resourcesNeededCopy.addResources(resourcesNeeded);
        }

        if (myResources.getFuel() < resourcesNeededCopy.getFuel() ||
                myResources.getManpower() < resourcesNeededCopy.getManpower() ||
                myResources.getSteel() < resourcesNeededCopy.getSteel() ||
                myResources.getSulfur() < resourcesNeededCopy.getSulfur()) {
            return new Result(false, "daddy USA plz help us");
        } else if (tile.getBattalions(type).size() == 3) {
            return new Result(false, "you can't add this type of battalion anymore");
        }

        myResources.useResources(resourcesNeededCopy);

        tile.getBattalions(type).add(new Battalion(name, type,
                type.getInitialPower(tile.getOwner().getType()), type.getBaseCapturePercentage(tile.getOwner().getType())));
        return new Result(true, "battalion set successfully");
    }


    public Result moveBattalion(String index, String name, String destinationIndex) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked");
        }
        int indexCasted = Integer.parseInt(index);
        if (indexCasted > 56 || indexCasted < 1) {
            return new Result(false, "tile is unavailable");
        }
        Tile originTile = App.getRunningGame().getTileManager().getTile(indexCasted);
        Country myCountry = App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry();

        int destIndexCasted = Integer.parseInt(destinationIndex);
        if (destIndexCasted > 56 || destIndexCasted < 1) {
            return new Result(false, "tile is unavailable");
        }

        Tile destTile = App.getRunningGame().getTileManager().getTile(destIndexCasted);
        if (!App.getRunningGame().isTileReachable(destTile, myCountry) ||
                !App.getRunningGame().isTileReachable(originTile, myCountry)) {
            return new Result(false, "tile is unavailable");
        }

        Battalion battalion = originTile.getBattalionByName(name);

        if (battalion == null) {
            return new Result(false, "no battalion with the given name");
        } else if (destTile.getBattalions(battalion.getType()).size() == 3) {
            return new Result(false, "maximum battalion of this type in destination exists");
        }

        Battalion battalion1 = destTile.getBattalionByName(name);
        if (battalion1 != null) {
            return new Result(false, "battalion name is already taken in this tile");
        }

        originTile.getBattalions(battalion.getType()).remove(battalion);
        destTile.getBattalions(battalion.getType()).add(battalion);

        return new Result(true, "battalion moved successfully");
    }


    public Result upgradeBattalion(String index, String name) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked");
        }
        int indexCasted = Integer.parseInt(index);
        if (indexCasted > 56 || indexCasted < 1) {
            return new Result(false, "can't upgrade battalions on this tile");
        }
        Tile tile = App.getRunningGame().getTileManager().getTile(indexCasted);
        Country myCountry = App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry();

        if (!App.getRunningGame().isTileReachable(tile, myCountry)) {
            return new Result(false, "can't upgrade battalions on this tile");
        }
        Battalion battalion = tile.getBattalionByName(name);
        if (battalion == null) {
            return new Result(false, "no battalion with the given name");
        } else if (battalion.getLevel() == 3) {
            return new Result(false, "battalion is on highest level");
        }
        Resources resourcesNeeded = battalion.getUpgradeResources(battalion.getType().getResourcesNeeded());
        Resources resourcesNeededCopy = new Resources(resourcesNeeded);

        if (myCountry.getCurrentLeader().getIdeology().equals(Ideology.Democracy)) {
            resourcesNeededCopy.addResources(resourcesNeeded);
        }

        Resources myResources = myCountry.getResources();
        if ((myResources.getSulfur() < resourcesNeededCopy.getSulfur()) ||
                (myResources.getSteel() < resourcesNeededCopy.getSteel()) ||
                (myResources.getFuel() < resourcesNeededCopy.getFuel()) ||
                (myResources.getManpower() < resourcesNeededCopy.getManpower())) {
            return new Result(false, "aww you can't upgrade your battalion");
        }

        myResources.useResources(resourcesNeededCopy);
        battalion.upgrade();
        return new Result(true, "%s upgraded to level %d"
                .formatted(battalion.getName(), battalion.getLevel()));
    }


    public Result createFaction(String name) {
        name = name.trim();

        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked ");
        }
        Faction newFaction = App.getRunningGame().getFactionByName(name);
        if (newFaction != null) {
            return new Result(false, "faction name already taken");
        }

        Country myCountry = App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry();

        newFaction = new Faction(name);
        myCountry.getFactions().add(newFaction);
        newFaction.addMember(myCountry);
        App.getRunningGame().getAllFactions().add(newFaction);
        return new Result(true, "faction created successfully");
    }


    public Result joinFaction(String name) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked ");
        }
        Faction newFaction = App.getRunningGame().getFactionByName(name.trim());
        if (newFaction == null) {
            return new Result(false, "faction doesn't exist");
        }
        Country myCountry = App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry();
        myCountry.getFactions().add(newFaction);
        newFaction.addMember(myCountry);

        return new Result(true, "%s joined %s"
                .formatted(myCountry.getName(), newFaction.getName()));
    }


    public Result leaveFaction(String name) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked ");
        }
        Faction newFaction = App.getRunningGame().getFactionByName(name.trim());
        if (newFaction == null) {
            return new Result(false, "faction doesn't exist");
        }
        Country myCountry = App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry();

        if (myCountry.getFactions().contains(newFaction)) {
            newFaction.getMembers().remove(myCountry);
            myCountry.getFactions().remove(newFaction);
            return new Result(true, "%s left %s"
                    .formatted(myCountry.getName(), newFaction.getName()));
        }

        return new Result(false, "your country isn't in this faction");
    }


    public Result buildFactory(String index, String factoryType, String name) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked ");
        }
        factoryType = factoryType.trim();

        int indexCasted = Integer.parseInt(index);
        if (indexCasted > 56 || indexCasted < 1) {
            return new Result(false, "invalid tile");
        }
        Tile tile = App.getRunningGame().getTileManager().getTile(indexCasted);
        Country myCountry = App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry();

        if (!App.getRunningGame().isTileReachable(tile, myCountry)) {
            return new Result(false, "invalid tile");
        }

        FactoryType type = null;
        for (FactoryType value : FactoryType.values()) {
            if (value.getDisplayName().equals(factoryType)) {
                type = value;
            }
        }
        if (type == null) {
            return new Result(false, "invalid factory type");
        }

        Resources myResources = myCountry.getResources();

        double steelNeeded = Math.floor(type.getSteelCost() * tile.getTerrain().getFactoryCost());
        double manPowerNeeded = Math.floor(type.getManPowerCost() * tile.getTerrain().getFactoryCost());

        if (myCountry.getCurrentLeader().getIdeology().equals(Ideology.Communism)) {
            steelNeeded = Math.floor(steelNeeded / 2.0);
            manPowerNeeded = Math.floor(manPowerNeeded / 2.0);
        }

        if (myResources.getManpower() < manPowerNeeded ||
                myResources.getSteel() < steelNeeded) {
            return new Result(false, "not enough money to build factory");
        } else if (tile.getFactories(type).size() == 3) {
            return new Result(false, "factory limit exceeded");
        }

        myResources.useResources(new Resources(manPowerNeeded, 0, 0, steelNeeded));

        tile.getFactories(type).add(new Factory(name, type));
        return new Result(true, "factory built successfully");
    }


    public Result runFactory(String index, String name, String manPowerCount) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked ");
        }
        int indexCasted = Integer.parseInt(index);
        double manPowerCasted = Integer.parseInt(manPowerCount);
        if (indexCasted > 56 || indexCasted < 1) {
            return new Result(false, "invalid tile");
        }
        Tile tile = App.getRunningGame().getTileManager().getTile(indexCasted);
        Country myCountry = App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry();

        if (!App.getRunningGame().isTileReachable(tile, myCountry)) {
            return new Result(false, "invalid tile");
        }

        Factory factory = tile.getFactoryByName(name);
        if (factory == null) {
            return new Result(false, "this tile doesn't contain this factory");
        } else if (manPowerCasted > myCountry.getResources().getManpower()) {
            return new Result(false, "you are poor!");
        }

        double extracted = manPowerCasted
                * factory.getType().getProductionPerManPower();

        if (factory.getType().equals(FactoryType.FuelRefinery)) {
            extracted = Math.floor(tile.getTerrain().getFuelProduction() * extracted);
        }

        boolean maxProductionReached = false;
        if (extracted >= (factory.getType().getMaximumProduction() - factory.getProducedCount())) {
            extracted = factory.getType().getMaximumProduction() - factory.getProducedCount();
            maxProductionReached = true;
            manPowerCasted = extracted / factory.getType().getProductionPerManPower();
        } else {
            factory.setProducedCount(factory.getProducedCount() + extracted);
        }
        myCountry.getResources().useResources(new Resources(manPowerCasted, 0, 0, 0));

        ArrayList<Country> communistFriends = findCommunistFriends(myCountry);

        double share = Math.floor(extracted / (communistFriends.size() + 1));
        addShares(share, myCountry, communistFriends, factory);

        if (maxProductionReached) {
            tile.getFactories(factory.getType()).remove(factory);
        }

        return new Result(true, "factory extracted %.0f of %s"
                .formatted(extracted, factory.getType().getResourceType()));
    }

    public ArrayList<Country> findCommunistFriends(Country myCountry) {
        ArrayList<Country> result = new ArrayList<>();
        for (Faction faction : myCountry.getFactions()) {
            for (Country member : faction.getMembers()) {
                if (!result.contains(member) && !member.getName().equals(myCountry.getName())) {
                    if (member.getCurrentLeader().getIdeology().equals(Ideology.Communism)) {
                        result.add(member);
                    }
                }
            }
        }
        return result;
    }

    public void addShares(double share, Country myCountry,
                          ArrayList<Country> communistFriends, Factory factory) {

        if (factory.getType().equals(FactoryType.FuelRefinery)) {
            myCountry.getResources().addResources(new Resources(0, share, 0, 0));

            for (Country friend : communistFriends) {
                friend.getResources().addResources(new Resources(0, share, 0, 0));
            }
        } else if (factory.getType().equals(FactoryType.SteelFactory)) {
            myCountry.getResources().addResources(new Resources(0, 0, 0, share));

            for (Country friend : communistFriends) {
                friend.getResources().addResources(new Resources(0, 0, 0, share));
            }
        } else if (factory.getType().equals(FactoryType.SulfurFactory)) {
            myCountry.getResources().addResources(new Resources(0, 0, share, 0));

            for (Country friend : communistFriends) {
                friend.getResources().addResources(new Resources(0, 0, share, 0));
            }
        }
    }


    public Result attack(String myIndex, String enemyIndex, String battalionType) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked ");
        }
        int myIndexCasted = Integer.parseInt(myIndex);
        if (myIndexCasted > 56 || myIndexCasted < 1) {
            return new Result(false, "attacker tile unavailable");
        }
        Tile myTile = App.getRunningGame().getTileManager().getTile(myIndexCasted);
        Country myCountry = App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry();

        if (!myTile.getOwner().equals(myCountry) &&
                !myCountry.getPuppets().contains(myTile.getOwner())) {
            return new Result(false, "attacker tile unavailable");
        }

        BattalionType battalionType1 = null;
        for (BattalionType type : BattalionType.values()) {
            if (type.name().toLowerCase().equals(battalionType)) {
                battalionType1 = type;
            }
        }
        if (battalionType1 == null || myTile.getBattalions(battalionType1).isEmpty()) {
            return new Result(false, "selected tile doesn't have this type of battalion");
        }

        int enemyIndexCasted = Integer.parseInt(enemyIndex);
        if (enemyIndexCasted > 56 || enemyIndexCasted < 1) {
            return new Result(false, "enemy tile unavailable for attacking");
        }
        Tile enemyTile = App.getRunningGame().getTileManager().getTile(enemyIndexCasted);
        if (!isTileAvailableForAttacking(myTile, enemyTile, battalionType1)) {
            return new Result(false, "enemy tile unavailable for attacking");
        } else if (myTile.getOwner().getCurrentLeader().getIdeology().equals(Ideology.Fascism) &&
                enemyTile.getOwner().getCurrentLeader().getIdeology().equals(Ideology.Fascism)) {
            return new Result(false, "we are rivals , not enemies");
        }

        double attackerPower = myTile.getBattalionTypeTotalPower(battalionType1);
        double defenderPower = enemyTile.getBattalionTypeTotalPower(battalionType1);

        if (attackerPower > defenderPower) {
            String winner = myTile.getOwner().getName();
            String loser = enemyTile.getOwner().getName();
            attackerWins(myTile, enemyTile, battalionType1, attackerPower - defenderPower);

            return new Result(true, "war is over\n" +
                    "winner : %s\n".formatted(winner) +
                    "loser : %s".formatted(loser));

        } else if (defenderPower > attackerPower) {
            String loser = myTile.getOwner().getName();
            String winner = enemyTile.getOwner().getName();
            defenderWins(myTile, enemyTile, battalionType1, defenderPower - attackerPower);

            return new Result(true, "war is over\n" +
                    "winner : %s\n".formatted(winner) +
                    "loser : %s".formatted(loser));
        }
        myTile.getAllBattalions().put(battalionType1, new ArrayList<>());
        enemyTile.getAllBattalions().put(battalionType1, new ArrayList<>());
        return new Result(true, "draw");
    }


    public void attackerWins(Tile attacker,
                             Tile defender,
                             BattalionType type,
                             double powerDifference) {
        App.getRunningGame().getCurrentPlayingPlayer().getWinningPoints(
                type, powerDifference);
        App.getRunningGame().getPlayerByCountry(defender.getOwner()).getLosingPoints(
                type, powerDifference);

        attacker.addCaptures(type, defender.getBattalionTypeCaptureLost(type));
        defender.getAllBattalions().put(type, new ArrayList<>());

        attacker.getOwner().setStability(Math.floor(attacker.getOwner().getStability() * 1.5));
        defender.getOwner().setStability(Math.floor(defender.getOwner().getStability() * 0.5));

        defender.setOwner(attacker.getOwner());
    }


    public void defenderWins(Tile attacker,
                             Tile defender,
                             BattalionType type,
                             double powerDifference) {
        App.getRunningGame().getCurrentPlayingPlayer().getLosingPoints(
                type, powerDifference);
        App.getRunningGame().getPlayerByCountry(defender.getOwner()).getWinningPoints(
                type, powerDifference);

        defender.addCaptures(type, attacker.getBattalionTypeCaptureLost(type));
        attacker.getAllBattalions().put(type, new ArrayList<>());

        attacker.getOwner().setStability(Math.floor(attacker.getOwner().getStability() * 0.5));
        defender.getOwner().setStability(Math.floor(defender.getOwner().getStability() * 1.5));

        Resources defenderResources = defender.getOwner().getResources();
        defenderResources.addResources(new Resources(0,
                defenderResources.getFuel() * 10.0,
                defenderResources.getSulfur(),
                defenderResources.getSteel()));

    }


    public boolean isTileAvailableForAttacking(Tile attacker, Tile defender, BattalionType battalionType) {
        if (battalionType.equals(BattalionType.Infantry) ||
                battalionType.equals(BattalionType.Panzer)) {
            if (!attacker.getLandNeighbors().contains(defender.getIndex())) {
                return false;
            }
        } else if (battalionType.equals(BattalionType.Navy)) {
            if (!attacker.getSeaNeighbors().contains(defender.getIndex())) {
                return false;
            }
        }

        Country attackerCountry = attacker.getOwner();
        Country defenderCountry = defender.getOwner();

        if (attackerCountry.equals(defenderCountry))
            return false;

        if (defenderCountry.getPuppets().contains(attackerCountry)) {
            return false;
        }
        for (Faction faction : attackerCountry.getFactions()) {
            if (faction.getMembers().contains(defenderCountry)) {
                return false;
            }
        }

        return true;
    }


    public Result startCivilWar(String tile1, String tile2, String battalionType) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked");
        }
        Country myCountry = App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry();

        // Check if the leader is democratic
        if (myCountry.getCurrentLeader().getIdeology() == Ideology.Democracy) {
            return new Result(false, "no civil war for you");
        }

        int tile1Index = Integer.parseInt(tile1);
        int tile2Index = Integer.parseInt(tile2);

        Tile firstTile = App.getRunningGame().getTileManager().getTile(tile1Index);
        Tile secondTile = App.getRunningGame().getTileManager().getTile(tile2Index);

        // Validate tile ownership
        if (tile1Index > 56 || tile1Index < 1 ||
                !firstTile.getOwner().equals(myCountry) ||
                tile2Index > 56 || tile2Index < 1 ||
                !secondTile.getOwner().equals(myCountry)) {
            return new Result(false, "invalid tiles for civil war");
        }

        // Find battalion type
        BattalionType battalionType1 = null;
        for (BattalionType type : BattalionType.values()) {
            if (type.name().equalsIgnoreCase(battalionType)) {
                battalionType1 = type;
            }
        }

        // Validate battalion type
        if (battalionType1 == null || firstTile.getBattalions(battalionType1).isEmpty()) {
            return new Result(false, "invalid battalion type");
        }


        // Validate adjacency
        if (battalionType1.equals(BattalionType.Infantry) ||
                battalionType1.equals(BattalionType.Panzer)) {
            if (!firstTile.getLandNeighbors().contains(secondTile.getIndex())) {
                return new Result(false, "can't start civil war between these tiles");
            }
        } else if (battalionType1.equals(BattalionType.Navy)) {
            if (!firstTile.getSeaNeighbors().contains(secondTile.getIndex())) {
                return new Result(false, "can't start civil war between these tiles");
            }
        }

        // Calculate battle outcome
        double attackerPower = firstTile.getBattalionTypeTotalPower(battalionType1);
        double defenderPower = secondTile.getBattalionTypeTotalPower(battalionType1);

        if (attackerPower > defenderPower) {
            firstTile.addCaptures(battalionType1, secondTile.getBattalionTypeCaptureLost(battalionType1));
            secondTile.getAllBattalions().put(battalionType1, new ArrayList<>());

            myCountry.setStability(myCountry.getStability() * 0.1);
            return new Result(true, "civil war ended. " + tile1Index + " won.");
        } else if (defenderPower > attackerPower) {
            secondTile.addCaptures(battalionType1, firstTile.getBattalionTypeCaptureLost(battalionType1));
            firstTile.getAllBattalions().put(battalionType1, new ArrayList<>());

            myCountry.setStability(myCountry.getStability() * 0.1);
            return new Result(true, "civil war ended. " + tile2Index + " won.");
        }

        // In case of a draw
        firstTile.getAllBattalions().put(battalionType1, new ArrayList<>());
        secondTile.getAllBattalions().put(battalionType1, new ArrayList<>());
        myCountry.setStability(myCountry.getStability() * 0.1);
        return new Result(true, "man dige harfi nadaram.");
    }


    public Result puppet(String countryName) {
        if (!App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry().isStable()) {
            return new Result(false, "game is locked ");
        }
        Country myCountry = App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry();
        Country puppet = null;
        for (CountryType countryType : CountryType.values()) {
            if (countryType.getDisplayName().equals(countryName.trim())) {
                puppet = App.getRunningGame().getCountryByCountryType(countryType);
            }
        }
        if (puppet == null) {
            return new Result(false, "country doesn't exist");
        }

        if (myCountry.getResources().getManpower() <= puppet.getResources().getManpower()) {
            return new Result(false, "you are not allowed to puppet this country");
        }

        TileManager tileManager = App.getRunningGame().getTileManager();
        for (int tileIndex = 1; tileIndex < 57; tileIndex++) {
            if (tileManager.getInitialOwner(tileIndex).equals(myCountry.getType())) {
                if (tileManager.getTile(tileIndex).getOwner().getType().equals(puppet.getType())) {
                    return new Result(false, "you are not allowed to puppet this country");
                }
            }
        }

        if (myCountry.getCurrentLeader().getIdeology().equals(Ideology.Democracy)) {
            return new Result(false, "you are not allowed to puppet this country");
        }

        for (Faction faction : myCountry.getFactions()) {
            if (faction.getMembers().contains(puppet)) {
                return new Result(false, "you are not allowed to puppet this country");
            }
        }

        puppet.setPuppet(true);
        myCountry.getPuppets().add(puppet);
        return new Result(true, "now %s is my puppet yo ho ha ha ha"
                .formatted(puppet.getName()));
    }


    public void startElection(Scanner scanner) {
        Country myCountry = App.getRunningGame().getCurrentPlayingPlayer().getPlayerCountry();

        Result result = printLeaders(myCountry);
        while (!result.isSuccessful()) {
            System.out.println(result);
            String input = scanner.nextLine();
            result = tryElectLeader(myCountry, input);
        }
    }

    private Result tryElectLeader(Country myCountry, String input) {
        for (LeaderType leader : LeaderType.values()) {
            if (leader.getName().toLowerCase().equals(input.trim()) &&
                    leader.getCountryType().equals(myCountry.getType())) {
                myCountry.setCurrentLeader(leader);
                myCountry.setStability(100.0);
                return new Result(true, "");
            }
        }
        return new Result(false, "leader doesn't exist");
    }


    public Result printLeaders(Country myCountry) {
        // Collect leaders based on ideology order: Democracy -> Communism -> Fascism
        Map<Ideology, LeaderType> availableLeaders = new LinkedHashMap<>();
        for (LeaderType leaderType : LeaderType.values()) {
            if (leaderType.getCountryType().equals(myCountry.getType())) {
                availableLeaders.put(leaderType.getIdeology(), leaderType);
            }
        }

        // Print available leaders in the required order
        StringBuilder result = new StringBuilder("Available leaders:\n");
        for (Ideology ideology : List.of(Ideology.Democracy, Ideology.Communism, Ideology.Fascism)) {
            if (availableLeaders.containsKey(ideology)) {
                result.append(availableLeaders.get(ideology).getName().toLowerCase()).append("\n");
            }
        }
        return new Result(false, result.toString().trim());
    }


    public void finishGame() {
        List<Player> players = App.getRunningGame().getPlayers();
        GameHistoryEntry entry = new GameHistoryEntry(players);
        App.getGameHistory().add(0, entry); // Add at the beginning for recent history
        if (App.getGameHistory().size() > 100) { // Keep history manageable
            App.getGameHistory().remove(App.getGameHistory().size() - 1);
        }

        for (Player player : App.getRunningGame().getPlayers()) {
            player.addPointsToUser();
        }
        App.setRunningGame(null);
        countryTypesChosen = new ArrayList<>();
        App.setCurrentMenu(Menu.MainMenu);
    }


}
