import java.util.Scanner;
import java.util.ArrayList;

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

    public void game()
    {
        boolean ah = true;
        while (ah)
        {
            printRoom();
            movePlayer();
            if(player.isDead())
            {
                System.out.println("You died");
            }
            if(player.getLifeCount() <= 0)
            {
                ah = false;
                System.out.println("Game Over");
            }
            if(room[endx][endy] instanceof Player)
            {
                if(noMoreItems())
                {
                    ah = false;
                    System.out.println("Room Complete");
                } else
                {
                    System.out.println("You still have items to get.");
                }
            }

        }
    }

    public boolean noMoreItems()
    {
        for (int i = 0; i < room.length; i++) {
            for (int f = 0; f < room[0].length; f++) {
                if (room[i][f] instanceof Item) {
                   return false;
                }
            }
        }
        return true;
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
        int oldPosx = playerPosx;
        int oldPosy = playerPosy;
        int newPosx = playerPosx;
        int newPosy = playerPosy;
        if(choice.equals("d"))
        {
            newPosx++;
        } else if (choice.equals("u"))
        {
            newPosx--;
        } else if (choice.equals("l"))
        {
            newPosy--;
        } else
        {
            newPosy++;
        }
        //if movement, change previous player location to blank
        if (outOfBounds(newPosx, newPosy))
        {
            System.out.println("Nope, you don't get to go out of bounds.");
        } else if(room[newPosx][newPosy] instanceof WallMapElement)
        {
            //if hammer in inventory, ask to break though walls, stand in place where wall was
            //if hammer not in inventory or player says no, no movement
            //order of events
            //check if hammer in inventory
            //ask to use hammer or not
            //show all hammers in inventory
            //pick hammer to use
            //confirm hammer choice
            //change wallElement to blank
            //ask player to move
            //if player doesn't move, doesn't break wall, or doesn't have hammer player stays still
            System.out.println("There is a wall in your way.");
            wallMovement(oldPosx, oldPosy, newPosx, newPosy);
        } else if(room[newPosx][newPosy] instanceof FireMapElement)
        {
            //if extinguisher in inventory, ask to use to extinguish fire, and move to place where fire was
            //otherwise move back 2
            //System.out.println("There is fire in your way.");
            boolean burned = fireMovement(oldPosx, oldPosy, newPosx, newPosy);
            while(burned)
            {
                player.setHp(player.getHp()-3);
                if(choice.equals("d"))
                {
                    newPosx = newPosx - 2;
                } else if (choice.equals("u"))
                {
                    newPosx = newPosx + 2;
                } else if (choice.equals("l"))
                {
                    newPosy = newPosy + 2;
                } else
                {
                    newPosy  = newPosy - 2;
                }
                if(outOfBounds(newPosx, newPosy) || room[newPosx][newPosy] instanceof WallMapElement)
                {
                    burned = false;
                    if(choice.equals("d"))
                    {
                        newPosx++;
                    } else if (choice.equals("u"))
                    {
                        newPosx--;
                    } else if (choice.equals("l"))
                    {
                        newPosy--;
                    } else
                    {
                        newPosy++;
                    }
                    playerMove(oldPosx, oldPosy, newPosx, newPosy);
                }
                if(room[newPosx][newPosy] instanceof HoleMapElement)
                {
                    burned = false;
                    fallDown(oldPosx, oldPosy);
                }
                if(room[newPosx][newPosy] instanceof Item)
                {
                    burned = false;
                    pickUp(oldPosx, oldPosy, newPosx, newPosy);
                }
                if(room[newPosx][newPosy] instanceof BlankMapElement)
                {
                    burned = false;
                    playerMove(oldPosx, oldPosy, newPosx, newPosy);
                }
            }
        }  else if(room[newPosx][newPosy] instanceof HoleMapElement)
        {
            //fall in hole and lose a life, and go back to start
            fallDown(oldPosx, oldPosy);
        }  else if(room[newPosx][newPosy] instanceof Item)
        {
            //pick up item and add it to inventory
            //move to place where item was
            pickUp(oldPosx, oldPosy, newPosx, newPosy);

        } else
        {
            playerMove(oldPosx, oldPosy, newPosx, newPosy);
        }

    }

    public void fallDown(int oldPosx, int oldPosy)
    {
        player.setLifeCount(player.getLifeCount() - 1);
        makeSpaceBlank(oldPosx, oldPosy);
        playerMove(oldPosx, oldPosy, startx, starty);
    }

    public void pickUp(int oldPosx, int oldPosy, int newPosx, int newPosy)
    {
        Item item = (Item) room[newPosx][newPosy];
        player.addItemToInven(item);
        playerMove(oldPosx, oldPosy, newPosx, newPosy);
    }

    public boolean outOfBounds(int newPosx, int newPosy)
    {
        return newPosx < 0 || newPosy < 0 || newPosy >= room.length || newPosx >= room[0].length;
    }

    public void makeSpaceBlank(int x, int y)
    {
        room[x][y] = new BlankMapElement();
    }

    public void playerMove(int oldx, int oldy, int newx, int newy)
    {
        makeSpaceBlank(oldx,oldy);
        room[newx][newy] = player;
        playerPosx = newx;
        playerPosy = newy;
    }

    public void wallMovement(int oldPosx, int oldPosy, int newPosx, int newPosy)
    {
        boolean stayStill = true;
        ArrayList<HammerItem> hammersInInven = player.hammersInInven();
        if(hammersInInven.size() != 0)
        {
            String ans = "";
            boolean spin = true;
            while (spin) {
                System.out.print("Do you want to break this wall? (Y)es or (N)o");
                ans = sc.nextLine();
                if(ans.length()>0) {
                    ans = ans.substring(0, 1).toLowerCase();
                }
                if(ans.equals("y") || ans.equals("n"))
                {
                    spin = false;
                }
            }
            if(ans.equals("y"))
            {
                spin = true;
            }
            int anss = -1;
            while (spin)
            {
                System.out.println("Choose which hammer you want to use");
                Player.printHammers(hammersInInven);
                String temp = sc.nextLine();
                anss = Integer.parseInt(temp);
                anss--;
                if(anss >= 0 && anss < hammersInInven.size())
                {
                    spin = false;
                    System.out.println("Not a hammer number.");
                }
            }
            if(anss != -1)
            {
                room[newPosx][newPosy] = new BlankMapElement();
                hammersInInven.get(anss).setUses(hammersInInven.get(anss).getUses()-1);
                System.out.println("You broke the wall!");
                spin = true;
                ans = "";
                while (spin)
                {
                    System.out.println("Do you want to move? (Y)es or (N)o");
                    ans = sc.nextLine();
                    if(ans.length()>0) {
                        ans = ans.substring(0, 1).toLowerCase();
                    }
                    if(ans.equals("y") || ans.equals("n"))
                    {
                        if(ans.equals("y"))
                        {
                            stayStill = false;
                        }
                        spin = false;
                    }
                }
            }
        } else
        {
            System.out.println("You have no hammers to break this wall.");
        }
        if(!stayStill)
        {
            playerMove(oldPosx, oldPosy, newPosx, newPosy);
        }
    }

    public boolean fireMovement(int oldPosx, int oldPosy, int newPosx, int newPosy)
    {
        boolean stayStill = true;
        boolean burn = true;
        ArrayList<ExtiguisherItem> extiguishersInInven = player.extiguishersInInven();
        if(extiguishersInInven.size() != 0)
        {
            String ans = "";
            boolean spin = true;
            while (spin) {
                System.out.print("Do you want to remove the fire? (Y)es or (N)o");
                ans = sc.nextLine();
                if(ans.length()>0) {
                    ans = ans.substring(0, 1).toLowerCase();
                }
                if(ans.equals("y") || ans.equals("n"))
                {
                    spin = false;
                    System.out.println("Not an extinguisher number.");
                }
            }
            if(ans.equals("y"))
            {
                spin = true;
            }
            int anss = -1;
            while (spin)
            {
                System.out.println("Choose which extinguisher you want to use");
                Player.printExtgusihers(extiguishersInInven);
                String temp = sc.nextLine();
                anss = Integer.parseInt(temp);
                anss--;
                if(anss >= 0 && anss < extiguishersInInven.size())
                {
                    spin = false;
                }
            }
            if(anss != -1)
            {
                room[newPosx][newPosy] = new BlankMapElement();
                extiguishersInInven.get(anss).setUses(extiguishersInInven.get(anss).getUses()-1);
                System.out.println("You removed the fire!");
                burn = false;
                spin = true;
                ans = "";
                while (spin)
                {
                    System.out.println("Do you want to move? (Y)es or (N)o");
                    ans = sc.nextLine();
                    if(ans.length()>0) {
                        ans = ans.substring(0, 1).toLowerCase();
                    }
                    if(ans.equals("y") || ans.equals("n"))
                    {
                        if(ans.equals("y"))
                        {
                            stayStill = false;
                        }
                        spin = false;
                    }
                }
            }
            if(!stayStill)
            {
                playerMove(oldPosx, oldPosy, newPosx, newPosy);
            }
        }


        return burn;
    }


}
