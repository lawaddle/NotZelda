import java.util.ArrayList;
import java.util.Scanner;


public class Player extends MapElement{
    int lifeCount = 5;
    int atk = 10;
    int def = 10;
    int hp = 25;
    ArrayList<Item> inventory = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

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
}
