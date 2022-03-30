public class test {
    public static void main(String[] args)
    {
//        SwordItem one = new SwordItem("one", 5, 7);
//        SwordItem two = new SwordItem("two", 5, 7);
//        ArrayList<Item> swords = new ArrayList<>();
//        swords.add(one);
//        swords.add(two);
//
//        for (Item item: swords) {
//            System.out.println(item.getName());
//        }

        HoleMapElement hole = new HoleMapElement();
        FireMapElement fire = new FireMapElement();
        WallMapElement wall = new WallMapElement();
        BlankMapElement blank = new BlankMapElement();
        HammerItem hammer = new HammerItem("Hammer", 10, 5);
        SwordItem sword = new SwordItem("Sword", 15, 3);
        ExtiguisherItem extinguisher = new ExtiguisherItem("Extinguisher", 7, 1);
        Player player = new Player();

        MapElement[][] room = new MapElement[7][7];

        room[1][1] = wall;


        room[0][6] = fire;
        room[0][4] = fire;
        room[0][2] = fire;
        room[2][2] = hole;
        room[2][0] = extinguisher;

        for (int i = 0; i < room.length; i++) {
            for (int f = 0; f < room[0].length; f++) {
                if (room[i][f] == null) {
                    room[i][f] = blank;
                }
            }
        }

        Chamber chamber = new Chamber(room, 0, 0, 6,6, player);
        chamber.game();

    }
}
