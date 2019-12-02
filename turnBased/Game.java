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
        double userCritical;
        double enemyCritical;
        int enemyChoice;
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
            while (enemy.currHP > 0 && userChar.currHP > 0) {
                Actions action = new Actions(userChar, enemy);
                // Gets a choice from the user on what they want to do during this turn
                userAction = action.getOption();
                // Send userAction to be dealt with
                userActionValue = action.initiateOption(userAction);
                enemyActions enemyAction = new enemyActions(enemy, userChar);
                // Send a random value to determine what the bots action will be
                enemyChoice = rand.nextInt(5);
                enemyActionValue = enemyAction.initiateOption(enemyChoice);
                userCritical = Math.random() * 100;
                // ATTACK
                if (userAction == 1) {
                    // if enemy defends
                    if (enemyChoice == 2) {
                        // CRITICAL
                        if (userCritical < userChar.luck) {
                            System.out.println(enemy.name + " scuffedly blocks your attack and takes "+ ((userActionValue * 2) - (enemy.defense * 2)) + " damage (CRIT)");
                            enemy.currHP -= (userActionValue * 2) - (enemy.defense * 2);
                            // enemy hp decreases by the user attack - (enemy defense + modifier). Then, return
                            // enemy defense back to pre-modified value
                            enemy.defense = enemyActionValue;
                        }
                        else {
                            System.out.println(enemy.name + " defends your attack and takes " + (userActionValue - enemy.defense) + " damage");
                            enemy.currHP -= userActionValue - enemy.defense;
                            enemy.defense = enemyActionValue;
                        }
                    }
                    else {
                        // CRITICAL
                        if (userCritical < userChar.luck) {
                            System.out.println(enemy.name + " revels their weakness to you and you strike for " + ((userActionValue * 2) - (enemy.defense * 2)) + " damage (CRIT)");
                            enemy.currHP -= (userActionValue * 2) - (enemy.defense * 2);
                        }
                        else {
                            System.out.println("You clap " + enemy.name + " for " + (userActionValue - enemy.defense) + " damage");
                            enemy.currHP -= userActionValue - enemy.defense;
                        }
                    }
                }
                // MAGIC
                else if (userAction == 2) {
                    // if enemy defends
                    if (enemyChoice == 2) {
                        // CRITICAL
                        if (userCritical < userChar.luck) {
                            System.out.println(enemy.name + " does their best to withstand you smiting them, you deal "+ ((userActionValue * 2) - ((enemy.defense * 2) * .33)) + " damage (CRIT)");
                            enemy.currHP -= (userActionValue * 2) - ((enemy.defense * 2) * .33);
                            enemy.defense = enemyActionValue;
                        }
                        else {
                            System.out.println(enemy.name + " quickly cover themselves in talismans, you deal " + (userActionValue - (enemy.defense * .33)) + " damage");
                            enemy.currHP -= userActionValue - (enemy.defense * .33);
                            enemy.defense = enemyActionValue;
                        }
                    }
                    else {
                        // CRITICAL
                        if (userCritical < userChar.luck) {
                            System.out.println("you enshrine " + enemy.name + " with a holy light, dealing " + ((userActionValue * 2) - ((enemy.defense * 2) * .33)) + " damage (CRIT)");
                            enemy.currHP -= (userActionValue * 2) - ((enemy.defense * 2) * .33);
                        }
                        else {
                            System.out.println("You blast " + enemy.name + " with a bolt of energy for " + (userActionValue - (enemy.defense * .33)) + " damage");
                            enemy.currHP -= userActionValue - (enemy.defense * .33);
                        }
                    }
                }
                // DEFEND
                else if (userAction == 3) {
                    System.out.println("You defend for " + userChar.defense + " incoming damage");
                    userChar.defense = userActionValue;
                }
                // HEAL
                else if (userAction == 4) {
                    System.out.println("You heal for " + userActionValue + " HP");
                }
                // IDLE
                else {
                    System.out.println("You do nothing");
                }
                if (enemy.currHP > 0) {
                    enemyCritical = Math.random() * 100;
                    // ATTACK
                    if (enemyChoice == 1) {
                        if (userAction == 3) {
                            //CRITICAL
                            if (enemyCritical < enemy.luck) {
                                System.out.println(enemy.name + " fiercely strikes your guard, dealing " + ((enemyActionValue * 2) - (userChar.defense * 2)) + " damage" );
                            }
                            else {
                                System.out.println(enemy.name + " can't break your guard! Deals " + (enemyActionValue - userChar.defense) + " damage");
                            }
                        }

                    }
                }
                else {
                    break;
                }




                //TODO make userActionValue and enemyActionValue interact
                // add leveling in b/w fights
                // Allow stat showing
                // Display enemy HP
                // Round double values
                // When bot dies, give bot x levels. when player dies let player distribute their levels and give bot y levels
                // that won't be distributed until they die
                // !Heal, doNothing, and defend methods return 0 while attacks return doubles
                // !If defense is higher than the expected damage, deals negative damage. FIX, deal 0


            }
        }


    }
}
