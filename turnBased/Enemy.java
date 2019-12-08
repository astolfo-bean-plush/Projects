import java.util.Random;
public class Enemy {
    String name;
    double maxHP;
    double currHP;
    double attack;
    double defense;
    double magic;
    double luck;

    public Enemy(String name, double maxHP, double attack, double defense, double magic, double luck) {
        this.name = name;
        this.maxHP = maxHP;
        currHP = maxHP;
        this.attack = attack;
        this.defense = defense;
        this.magic = magic;
        this.luck = luck;
    }

    public void hitPointGen(int levels) {
        maxHP += levels * .067 * maxHP;

    }

    public void atkGen(int levels) {
        attack += levels * .072 * attack;

    }

    public void defGen(int levels) {
        defense += (levels * .072 * defense);

    }

    public void magGen(int levels) {
        magic += (levels * .068 * magic);

    }

    public void lckGen(int levels) {
        luck += (levels * .2 * luck);
    }

    public int expAllocate(int type, int levels) {
        Random rand = new Random();
        // min 1, max levels
        int allocationAmount = rand.nextInt(levels) + 1;
        if(type == 0) {
            hitPointGen(allocationAmount);
            currHP = maxHP; // If enemy allocates to their HP, heal them
            return levels - allocationAmount;
        }
        else if (type == 1) {
            atkGen(allocationAmount);
            return levels - allocationAmount;
        }
        else if (type == 2) {
            defGen(allocationAmount);
            return levels - allocationAmount;
        }
        else if (type == 3) {
            magGen(allocationAmount);
            return levels - allocationAmount;
        }
        else {
            lckGen(allocationAmount);
            return levels - allocationAmount;
        }
    }

    public void levelUp(int levels, int type) {
        Random rand = new Random();
        while (levels > 0) {
            levels = expAllocate(type, levels);
            type = rand.nextInt(5);
        }
    }
    public void showStats() {
        System.out.println("Name: " + name + " maxHP: " + maxHP + " Attack: " + attack + " Defense: " + defense + " Magic: " + magic + " Luck: " + luck);
    }
}

