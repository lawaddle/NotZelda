import java.util.ArrayList;
import java.util.Scanner;


public class Player extends MapElement{
    private int lifeCount = 5;
    private int atk = 10;
    private int def = 10;
    private int hp = 25;
    private boolean dead = false;
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

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isDead() {
        if(hp <= 0)
        {
            dead = true;
        }
        if(dead)
        {
            lifeCount--;
        }
        return dead;
    }

    public int getHp() {
        return hp;
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

    public static void printHammers(ArrayList<HammerItem> list)
    {
        for (int i = 0; i < list.size(); i++) {
            int display =  i+1;
            System.out.println(display + ": " + list.get(i));
        }
    }

    public static void printExtgusihers(ArrayList<ExtiguisherItem> list)
    {
        for (int i = 0; i < list.size(); i++) {
            int display =  i+1;
            System.out.println(display + ": " + list.get(i));
        }
    }

    @Override
    public String toString() {
        String str = "Name: " + getName();
        str+= "\nAttack: " + atk + " Defense: " +  def + " HP: " + hp;
        str+= "\nInventory: \n";
        for (Item item: inventory) {
            str += item + "\n";
        }
        if(inventory.size() <= 0)
        {
            str += "EMPTY";
        }
        return str;
    }
}
