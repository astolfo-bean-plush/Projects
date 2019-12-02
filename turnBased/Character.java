import java.util.Scanner;
public class Character {
    String name;
    double maxHP;
    double currHP;
    double attack;
    double defense;
    double magic;
    double luck;

    public Character(String name, double maxHP, double attack, double defense, double magic, double luck) {
        this.name = name;
        this.maxHP = maxHP;
        currHP = maxHP - 10;
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
        luck += (levels * .7 * luck);
    }

    public int expAllocate(int type, int levels) {
        String userChoice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter how many points you would like to allocate");
        userChoice = sc.nextLine();
        while (Integer.parseInt(userChoice) > levels || Integer.parseInt(userChoice) < 0) {
            System.out.println("Invalid input, you have " + levels + " levels to allocate");
            userChoice = sc.nextLine();
        }
        if (type == 1) {
            hitPointGen(Integer.parseInt(userChoice));
            return levels - Integer.parseInt(userChoice);
        } else if (type == 2) {
            atkGen(Integer.parseInt(userChoice));
            return levels - Integer.parseInt(userChoice);
        } else if (type == 3) {
            defGen(Integer.parseInt(userChoice));
            return levels - Integer.parseInt(userChoice);
        } else if (type == 4) {
            magGen(Integer.parseInt(userChoice));
            return levels - Integer.parseInt(userChoice);
        } else {
            lckGen(Integer.parseInt(userChoice));
            return levels - Integer.parseInt(userChoice);
        }
    }

    public void levelUp(int progress, int levels) {
        String userChoice = "0";
        Scanner sc = new Scanner(System.in);
        // If you are allocating your first 10 points
        if (progress == 0) {
            while ((levels > 0 && Integer.parseInt(userChoice) != 6)) {
                System.out.println("You have " + levels + " levels, you can allocate them or save them \n" +
                        "1 = Health \n" +
                        "2 = Attack \n" +
                        "3 = Defense \n" +
                        "4 = Magic \n" +
                        "5 = Luck \n" +
                        "6 = save");
                userChoice = sc.nextLine();
                while (!userChoice.equals("1") && !userChoice.equals("2") && !userChoice.equals("3")
                        && !userChoice.equals("4") && !userChoice.equals("5") && !userChoice.equals("6")) {
                    System.out.println("Invalid input, enter again: ");
                    userChoice = sc.nextLine();
                }
                // if user decides to save, don't call expAllocate
                if (!userChoice.equals("6"))
                    levels = expAllocate(Integer.parseInt(userChoice), levels);
            }
        }
    }
    public void showStats() {
        System.out.println(name + " " + maxHP + " " + attack + " " + defense + " " + magic + " " + luck);
    }
}
