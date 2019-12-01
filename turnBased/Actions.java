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
                "5 = Do nothing");
        userChoice = sc.nextInt();
        while(userChoice != 1 && userChoice != 2 && userChoice != 3 && userChoice != 4 &&
                userChoice != 5) {
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
        return userChar.attack - enemy.defense;

    }

    public double magicAttack() {
        return userChar.magic - enemy.defense * .33;
    }

    public double defend() {
        userChar.defense = userChar.defense * 1.5;
        //TODO needs to adjust defense back to normal after turn ends
        return 0;
    }

    public double heal() {
        double healAmount = userChar.maxHP * .2;
        // if user heals and the healAmount meets or exceeds their health, currHP = maxHP
        enemy.currHP = Math.min((healAmount + userChar.currHP), userChar.maxHP);
        return 0;
    }

    public double doNothing() {
        return 0;
    }

}
