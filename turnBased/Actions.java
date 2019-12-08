import java.util.Scanner;
public class Actions {
    Character userChar;
    Enemy enemy;
    public Actions(Character userChar, Enemy enemy) {
    this.userChar = userChar;
    this.enemy = enemy;
    }
    public int getOption() {
        Scanner sc = new Scanner(System.in);
        int userChoice;
        System.out.println("What will you do? \n" +
                "1 = Physical Attack \n" +
                "2 = Magic Attack \n" +
                "3 = Defend \n" +
                "4 = Heal \n" +
                "5 = Do nothing \n" +
                "6 = Show enemy stats");
        userChoice = sc.nextInt();
        while(userChoice != 1 && userChoice != 2 && userChoice != 3 && userChoice != 4 &&
                userChoice != 5 && userChoice != 6) {
            System.out.println("Invalid input, enter again: ");
            userChoice = sc.nextInt();
        }
        return userChoice;
    }

    public double initiateOption(int userAction) {
        if (userAction == 1)
            return attack();
        else if (userAction == 2)
            return magicAttack();
        else if (userAction == 3)
            return defend();
        else if (userAction == 4)
            return heal();
        else
            return doNothing();

    }

    public double attack() {
        return userChar.attack;

    }

    public double magicAttack() {
        return userChar.magic - enemy.defense * .33;
    }

    public double defend() {
        userChar.defense = userChar.defense * 1.5;
        return userChar.defense / 1.5;
    }

    public double heal() {
        double healAmount = userChar.maxHP * .2;
        // if user heals and the healAmount meets or exceeds their health, currHP = maxHP
        userChar.currHP = Math.min((healAmount + userChar.currHP), userChar.maxHP);
        // if healAmount meets or exceeds their health, return 0 for display purposes
        healAmount = userChar.maxHP - (healAmount + userChar.currHP);
        //TODO heal needs to return the value that doesn't exceed maxHP i.e you have 90 HP, heal for 18, and have 100 max HP
        // so, return 10 as that's what you're actually healing for (new: if damage/heal = -, force = 0 )
        return healAmount;
    }

    public double doNothing() {
        return 0;
    }

}
