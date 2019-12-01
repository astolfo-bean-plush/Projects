public class Enemy {
    String name;
    double HP;
    double attack;
    double defense;
    double magic;
    double luck;

    public Enemy(String name, double HP, double attack, double defense, double magic, double luck) {
        this.name = name;
        this.HP = HP;
        this.attack = attack;
        this.defense = defense;
        this.magic = magic;
        this.luck = luck;
    }
//TODO when bot dies, give bot x levels. when player dies let player distribute their levels and give bot y levels
    //TODO that won't be distributed until they die
}
