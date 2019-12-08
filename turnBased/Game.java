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
        String userInput;
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
        userChar.levelUp(levels);
        // While the user is alive, keep creating enemies
        while (userChar.currHP > 0) {
            System.out.println("Finding opponent...");
            TimeUnit.SECONDS.sleep(1);
            Enemy enemy = new Enemy(enemyNames[rand.nextInt(enemyNames.length)], enemyMaxHealth, enemyAttack, enemyDefense, enemyMagic, enemyLuck);
            enemy.levelUp(enemyLevels, rand.nextInt(5));
            System.out.println(enemy.name + " appears");
            // When the enemy is slain (pending) create another
            while (enemy.currHP > 0 && userChar.currHP > 0) {
                Actions action = new Actions(userChar, enemy);
                // Gets a choice from the user on what they want to do during this turn
                System.out.println("__________________________________________________________________");
                System.out.println("You currently have " + userChar.currHP + "/" + userChar.maxHP + " HP");
                System.out.println(enemy.name + " currently has " + enemy.currHP + "/" + enemy.maxHP + " HP");
                userAction = action.getOption();
                while (userAction == 6) { // Keep asking for input until it's not "show enemy stats"
                    enemy.showStats();
                    userAction = action.getOption();
                }
                // Send userAction to be dealt with
                userActionValue = action.initiateOption(userAction);
                enemyActions enemyAction = new enemyActions(enemy, userChar);
                // Send a random value to determine what the enemy's action will be
                enemyChoice = rand.nextInt(5);
                enemyActionValue = enemyAction.initiateOption(enemyChoice);
                userCritical = Math.random() * 100;
                // ATTACK
                if (userAction == 1) {
                    // if user action does negative damage to enemy, do nothing
                    if (userActionValue < enemy.defense) {
                        System.out.println(enemy.name + " is immune to physical attacks, deal 0 damage.");
                    }
                    // if enemy defends
                    else if (enemyChoice == 2) {
                        // CRITICAL
                        if (userCritical < userChar.luck) {
                            System.out.println(enemy.name + " scuffedly blocks your attack and takes "+ ((userActionValue * 2) - (enemy.defense * 2)) + " damage (CRIT)");
                            enemy.currHP -= (userActionValue * 2) - (enemy.defense * 2);
                            // enemy hp decreases by the user attack - (enemy defense + modifier). Then, return
                            // enemy defense back to pre-modified value
                        }
                        else {
                            System.out.println(enemy.name + " defends your attack and takes " + (userActionValue - enemy.defense) + " damage");
                            enemy.currHP -= userActionValue - enemy.defense;
                        }
                        enemy.defense = enemyActionValue;
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
                    // if user action does negative damage to enemy, do nothing
                    if (userActionValue < enemy.defense) {
                        System.out.println(enemy.name + " is immune to your magical attacks, deal 0 damage.");
                    }
                    // if enemy defends
                    else if (enemyChoice == 2) {
                        // CRITICAL
                        if (userCritical < userChar.luck) {
                            System.out.println(enemy.name + " does their best to withstand you smiting them, you deal "+ ((userActionValue * 2) - ((enemy.defense * 2) * .33)) + " damage (CRIT)");
                            enemy.currHP -= (userActionValue * 2) - ((enemy.defense * 2) * .33);
                        }
                        else {
                            System.out.println(enemy.name + " quickly cover themselves in talismans, you deal " + (userActionValue - (enemy.defense * .33)) + " damage");
                            enemy.currHP -= userActionValue - (enemy.defense * .33);
                        }
                        enemy.defense = enemyActionValue;
                    }
                    else {
                        // CRITICAL
                        if (userCritical < userChar.luck) {
                            System.out.println("You enshrine " + enemy.name + " with a holy light, dealing " + ((userActionValue * 2) - ((enemy.defense * 2) * .33)) + " damage (CRIT)");
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
                if (enemy.currHP > 0) { // if enemy is still alive after user's actions, display their action
                    enemyCritical = Math.random() * 100;
                    // ATTACK
                    if (enemyChoice == 0) {
                        // if enemy deals negative damage to user, do nothing
                        if (enemyActionValue < userChar.defense) {
                            System.out.println(enemy.name + " physically attacks you, but you're immune! Take 0 damage.");
                        }
                        // if user defends
                        else if (userAction == 3) {
                            //CRITICAL
                            if (enemyCritical < enemy.luck) {
                                System.out.println(enemy.name + " fiercely strikes your guard, dealing " + ((enemyActionValue * 2) - (userChar.defense * 2)) + " damage" );
                                userChar.currHP -= (enemyActionValue * 2) - (userChar.defense * 2);
                            }
                            else {
                                System.out.println(enemy.name + " can't break your guard! Deals " + (enemyActionValue - userChar.defense) + " damage");
                                userChar.currHP -= (enemyActionValue - userChar.defense);
                            }
                            userChar.defense = userActionValue;
                        }
                        else {
                            // CRITICAL
                            if (enemyCritical < enemy.luck) {
                                System.out.println(enemy.name + " can see your weak point and strikes at it (CRIT)");
                                userChar.currHP -= (enemyActionValue * 2) - (userChar.defense * 2);
                            }
                            else {
                                System.out.println(enemy.name + " claps you out for " + (enemyActionValue - userChar.defense));
                                userChar.currHP -= (enemyActionValue - userChar.defense);
                            }

                        }
                    }
                    // MAGIC
                    else if (enemyChoice == 1) {
                        // if enemy deals negative damage to user, do nothing
                        if (enemyActionValue < userChar.defense) {
                            System.out.println(enemy.name + " magically attacks you, but you're immune! Take 0 damage.");
                        }
                        // if user defends
                        else if (userAction == 3) {
                            // CRITICAL
                            if (enemyCritical < enemy.luck) {
                                System.out.println(enemy.name + " blasts your guard with their strongest spell, dealing " + ((enemyActionValue * 2) - ((userChar.defense * 2) * .33)) + " damage (CRIT)");
                                userChar.currHP -= (enemyActionValue * 2) - ((userChar.defense * 2) * .33);
                            }
                            else {
                                System.out.println("You can see through" + enemy.name + " actions, you defend and take " + (enemyActionValue - (userChar.defense * .33)) + " damage");
                                userChar.currHP -= enemyActionValue - (userChar.defense * .33);
                            }
                            userChar.defense = userActionValue;
                        }
                        else {
                            // CRITICAL
                            if (enemyCritical < enemy.luck) {
                                System.out.println("Blasts you with the wraith of the heavens, dealing " + ((enemyActionValue * 2) - ((userChar.defense * 2) * .33) + " damage"));
                                userChar.currHP -= (enemyActionValue * 2) - ((userChar.defense * 2) * .33);
                            }
                            else {
                                System.out.println(enemy.name + " tosses a bolt of lightning your way, dealing " + (enemyActionValue - (userChar.defense * .33)) + " damage");
                                userChar.currHP -= enemyActionValue - (userChar.defense * .33);
                            }
                        }
                    }
                    // DEFEND
                    else if (enemyChoice == 2) {
                        System.out.println(enemy.name + " Is defending for " + enemy.defense + " incoming damage");
                        enemy.defense = enemyActionValue;
                    }
                    // HEAL
                    else if (enemyChoice == 3) {
                        System.out.println(enemy.name + " heals for " + userActionValue + " HP");
                    }
                    // IDLE
                    else {
                        System.out.println(enemy.name + " stands around like an oaf");
                    }
                }
                else if (enemy.currHP < 0) {
                    System.out.println(enemy.name + " has been defeated! You gain 8 levels (used after defeat) and future enemies become stronger.");
                    // if enemy is defeated, give them 12 more levels to allocate on spawn and store user levels
                    enemyLevels += 12;
                    levels += 8;
                }
                if (userChar.currHP < 0) {
                    // if player is defeated, give enemy 3 points to a random skill
                    enemy.expAllocate(rand.nextInt(5), 3);
                    System.out.println("You have been defeated by " + enemy.name + ", press \"1\" to respawn");
                    userInput = sc.nextLine();
                    if (userInput.equals("1")) {
                        userChar.levelUp(levels);
                        userChar.currHP = userChar.maxHP; // if player decides to respawn, fully heal to avoid breaking loop
                        levels = 0; // reset levels after allocation
                    }
                }
            }
        }
    }
}
