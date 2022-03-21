import java.util.Scanner;

public class Chamber {
    private MapElement[][] room;
    private int startx;
    private int starty;
    private int endx;
    private int endy;
    private Scanner sc = new Scanner(System.in);
    private int playerPosx;
    private int playerPosy;
    private Player player;

    public Chamber(MapElement[][] room, int startx, int starty, int endx, int endy, Player player)
    {
        this.room = room;
        this.startx = startx;
        this.starty = starty;
        this.endx = endx;
        this.endy = endy;
        this.player = player;
        playerPosx = startx;
        playerPosy = starty;
        room[startx][starty] = player;

    }

    public void printRoom()
    {
        for (int i = 0; i < room.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < room[0].length; j++) {
                if (room[i][j] == null) {
                    System.out.print("  | ");
                } else
                {
                    System.out.print(room[i][j].getMapDisplay() + " | ");
                }
            }
            System.out.println();
        }
    }

    //if blank, just set new space to player space and old place to blank
    //if item, pick up item (make method for that), set new space to player space and old place to blank
    //if oob, don't allow movement
    public void movePlayer()
    {
        boolean lop = true;
        String choice = "";
        while (lop) {
            System.out.println("Where do you want to move? \n(U)p \n(D)own \n(L)eft \n(R)ight");
            choice = sc.nextLine();
            choice = choice.toLowerCase();
            if(choice.equals("u") || choice.equals("up") || choice.equals("d") || choice.equals("down") || choice.equals("l") || choice.equals("left") || choice.equals("r") || choice.equals("right"))
            {
                lop = false;
                choice = choice.substring(0,1);
            } else
            {
                System.out.println("Invaild Option. Please pick again.");
            }
        }
        int newPosx = playerPosx;
        int newPosy = playerPosy;
        if(choice.equals("u"))
        {
            newPosy++;
        } else if (choice.equals("d"))
        {
            newPosy--;
        } else if (choice.equals("l"))
        {
            newPosx--;
        } else
        {
            newPosx++;
        }
        //if movement, change previous player location to blank
        if (newPosx < 0 || newPosy < 0 || newPosx >= room.length || newPosy >= room[0].length)
        {
            System.out.println("Nope, you don't get to go out of bounds.");
        } else if(room[newPosx][newPosy] instanceof WallMapElement)
        {
            //if hammer in inventory, ask to break though walls, stand in place where wall was
            //if hammer not in inventory or player says no, no movement
        } else if(room[newPosx][newPosy] instanceof FireMapElement)
        {
            //if extinguisher in inventory, ask to use to extinguish fire, and move to place where fire was
            //otherwise move back 2
        }  else if(room[newPosx][newPosy] instanceof HoleMapElement)
        {
            //fall in hole and lose a life
        }  else if(room[newPosx][newPosy] instanceof Item)
        {
            //pick up item and add it to inventory
            //move to place where item was
        } else
        {
            //move to new spot
        }
    }



}
