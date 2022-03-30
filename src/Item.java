public class Item extends MapElement{
    private String name;
    private int uses;
    private int damage;

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

    public void setUses(int uses) {
        this.uses = uses;
    }

    @Override
    public String toString()
    {
        return super.toString() + ", Uses: " + uses + ", Damage: " + damage;
    }
}
