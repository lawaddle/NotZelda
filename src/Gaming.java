public class Gaming {
    private HoleMapElement hole = new HoleMapElement();
    private FireMapElement fire = new FireMapElement();
    private WallMapElement wall = new WallMapElement();
    private BlankMapElement blank = new BlankMapElement();
    private HammerItem hammer = new HammerItem("Hammer", 10, 5);
    private SwordItem sword = new SwordItem("Sword", 15, 3);
    private ExtiguisherItem extinguisher = new ExtiguisherItem("Extinguisher", 7, 1);
    private Player player = new Player();
    private MapElement[][] room1;
    private MapElement[][] room2;

    public Gaming()
    {
        makeRoom1();
        makeRoom2();
    }

    public void play()
    {
        System.out.println("Game Start");
        System.out.println("Room 1 Start");
        Chamber chamber1 = new Chamber(room1, 0, 0, 3,3, player);
        chamber1.game();
        System.out.println("Room 2 Start");
        Chamber chamber2 = new Chamber(room2, 0, 0, 3,3, player);
        chamber2.game();
        System.out.println("Game End");
    }

    public void makeRoom1()
    {
        room1 = new MapElement[5][5];
        room1[1][1] = wall;
        room1[0][3] = fire;
        room1[0][2] = fire;
        room1[2][4] = hole;
        room1[4][0] = extinguisher;
        room1[1][3] = hammer;
        fillWithBlanks(room1);
    }

    public void makeRoom2()
    {
        room2 = new MapElement[5][5];
        room2[1][1] = wall;
        room2[0][3] = fire;
        room2[0][2] = fire;
        room2[2][4] = hole;
        room2[4][0] = extinguisher;
        room2[1][3] = hammer;
        fillWithBlanks(room2);
    }

    public void fillWithBlanks(MapElement[][] room)
    {
        for (int i = 0; i < room.length; i++) {
            for (int f = 0; f < room[0].length; f++) {
                if (room[i][f] == null) {
                    room[i][f] = blank;
                }
            }
        }
    }

}
