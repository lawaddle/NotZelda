public class Item extends MapElement{
    String name;
    int uses;
    int damage;

    public Item (String name, int uses, int damage, String mapDisplay)
    {
        super(name, mapDisplay);
        this.uses = uses;
        this.damage = damage;
    }


    public int getUses() {
        return uses;
    }

    public int getDamage() {
        return damage;
    }
}
