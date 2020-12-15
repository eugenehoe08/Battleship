package battleship;

public enum SHIPS {
    AIRCRAFTCARRIER("Aircraft Carrier", 5), BATTLESHIP("Battleship", 4), SUBMARINE("Submarine", 3),CRUISER("Cruiser", 3), DESTROYER("Destroyer", 2);


    private final int length;
    private final String name;

    SHIPS(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }
}
