import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Random;
public class Game {
    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        String[] enemyNames = {"Paul", "Andres", "Jeremy", "Christoff", "Isaac", "Elijah Kennedy",
                "Tommy", "Frederick", "Jeffery", "Andre", "Tlaloc"};
        // base stats
        int levels = 10;
        int enemyLevels;
        double health = 100;
        double enemyHealth = rand.nextDouble() * (((health / 2) + (health * .10)) - ((health / 2) - (health * .10))) + ((health / 2) - (health * .10));
        double attack = 25;
        double enemyAttack = rand.nextDouble() * (((attack / 2) + (attack * .10)) - ((attack / 2) - (attack *.10))) + ((attack / 2) - (attack * .10));
        double defense = 10;
        double enemyDefense = rand.nextDouble() * (((defense / 2) + (defense * .10)) - ((defense / 2) - (defense *.10))) + ((defense / 2) - (defense * .10));
        double magic = 15;
        double enemyMagic = rand.nextDouble() * (((magic / 2) + (magic * .10)) - ((magic / 2) - (magic * .10))) + ((magic / 2) - (magic * .10));
        double luck = 1;
        double enemyLuck = rand.nextDouble() * (((luck / 2) + (luck * .10)) - ((luck / 2) - (luck * .10))) + ((luck / 2) - (luck * .10));
        //TODO give more calculated starting values

        System.out.println("Enter character name");
        Character userChar = new Character(sc.nextLine(), health, attack, defense, magic, luck);
        // allow userChar to distribute 10 levels
        userChar.levelUp(0, levels);
        while (userChar.HP > 0) {
            System.out.println("Finding opponent...");
            TimeUnit.SECONDS.sleep(1);
            Enemy enemy = new Enemy(enemyNames[rand.nextInt(enemyNames.length)], enemyHealth, enemyAttack, enemyDefense, enemyMagic, enemyLuck);
            // TODO allow bot to distribute 10 levels w/ rand
        }


    }
}
