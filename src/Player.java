import java.util.ArrayList;
import java.util.Scanner;


public class Player extends MapElement{
    private int lifeCount = 5;
    private int atk = 10;
    private int def = 10;
    private int hp = 25;
    private ArrayList<Item> inventory = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public Player ()
    {
        super(" ", "\uD83D\uDE10");
        userPickName();
    }

    public void userPickName()
    {
        System.out.print("What's your name: ");
        setName(sc.nextLine());
    }

    public void addItemToInven(Item item)
    {
        inventory.add(item);
    }

    public void setLifeCount(int lifeCount) {
        this.lifeCount = lifeCount;
    }

    public int getLifeCount() {
        return lifeCount;
    }

    public ArrayList<HammerItem> hammersInInven()
    {
        ArrayList<HammerItem> hammers = new ArrayList<>();
        for (Item item: inventory) {
           if(item instanceof HammerItem)
           {
               HammerItem hammer = (HammerItem) item;
               hammers.add(hammer);
           }
        }
        return hammers;
    }

    public ArrayList<ExtiguisherItem> extiguishersInInven()
    {
        ArrayList<ExtiguisherItem> extiguishers = new ArrayList<>();
        for (Item item: inventory) {
            if(item instanceof ExtiguisherItem)
            {
                ExtiguisherItem extiguisher = (ExtiguisherItem) item;
                extiguishers.add(extiguisher);
            }
        }
        return extiguishers;
    }

    public static void printItems(ArrayList<Item> list)
    {
        for (int i = 0; i < list.size(); i++) {
            int display =  i+1;
            System.out.println(display + ": " + list.get(i));
        }
    }
}
