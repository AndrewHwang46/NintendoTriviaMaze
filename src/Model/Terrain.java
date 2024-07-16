package Model;

public enum Terrain {
    WALL('X'),

    TILE('-');

    private char myLetter;
    Terrain(final char theLetter) {
        myLetter = theLetter;
    }


}
