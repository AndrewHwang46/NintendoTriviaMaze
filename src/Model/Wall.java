package Model;

public enum Wall {
    EMPTY,
    WALL;

    public boolean isWall() {
        return this == WALL;
    }
}

    class SpaceChecker {
    public static void checkSpace(Wall space) {
        if (space.isWall()) {
            System.out.println("Alert: Current space is a wall!");
        } else {
            System.out.println("Current space is empty.");
        }
    }

    public static void main(String[] args) {
        Wall currentSpace = Wall.WALL;
        checkSpace(currentSpace);
    }

}
