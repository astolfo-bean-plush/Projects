import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Random;
public class Game {
    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        String[] enemyNames = {"Paul", "Andres", "Jeremy", "Christoff", "Isaac", "Elijah Kennedy",
                "Tommy", "Frederick", "Jeffery", "Andre Hayes", "Tlaloc"};
        // base stats
        double enemyActionValue;
        double userActionValue;
        int userAction;
        int levels = 10;
        int enemyLevels = 10;
        double maxHealth = 100;
        double enemyMaxHealth = rand.nextDouble() * (((maxHealth / 1.6) + (maxHealth * .10)) - ((maxHealth / 1.6) - (maxHealth * .10))) + ((maxHealth / 1.6) - (maxHealth * .10));
        double attack = 25;
        double enemyAttack = rand.nextDouble() * (((attack / 1.2) + (attack * .10)) - ((attack / 1.2) - (attack *.10))) + ((attack / 1.2) - (attack * .10));
        double defense = 10;
        double enemyDefense = rand.nextDouble() * (((defense / 2) + (defense * .10)) - ((defense / 2) - (defense *.10))) + ((defense / 2) - (defense * .10));
        double magic = 15;
        double enemyMagic = rand.nextDouble() * (((magic / 2) + (magic * .10)) - ((magic / 2) - (magic * .10))) + ((magic / 2) - (magic * .10));
        double luck = 1;
        double enemyLuck = 1;
        //TODO create more calculated starting values + multipliers done

        System.out.println("Enter character name");
        Character userChar = new Character(sc.nextLine(), maxHealth, attack, defense, magic, luck);
        // allow userChar to distribute 10 levels
        userChar.levelUp(0, levels);
        // While the user is alive, keep creating enemies
        while (userChar.currHP > 0) {
            System.out.println("Finding opponent...");
            TimeUnit.SECONDS.sleep(1);
            Enemy enemy = new Enemy(enemyNames[rand.nextInt(enemyNames.length)], enemyMaxHealth, enemyAttack, enemyDefense, enemyMagic, enemyLuck);
            enemy.levelUp(0, enemyLevels, rand.nextInt(5));
            System.out.println(enemy.name + " appears \n");
            // When the enemy is slain (pending) create another
            while (enemy.currHP > 0) {
                Actions action = new Actions(userChar, enemy);
                // Gets a choice from the user on what they want to do during this turn
                userAction = action.getOption();
                // Send userAction to be dealt with
                userActionValue = action.initiateOption(userAction);
                enemyActions enemyAction = new enemyActions(enemy, userChar);
                // Send a random value to determine what the bots action will be
                enemyActionValue = enemyAction.initiateOption(rand.nextInt(5));
                //TODO make userActionValue and enemyActionValue interact
                // add leveling in b/w fights
                // Allow stat showing
                // Display enemy HP
                // Round double values
                // When bot dies, give bot x levels. when player dies let player distribute their levels and give bot y levels
                // that won't be distributed until they die
                // !Heal, doNothing, and defend methods return 0 while attacks return doubles


            }
        }


    }
}
