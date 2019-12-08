public class enemyActions {
    Enemy enemy;
    Character userChar;
    public enemyActions(Enemy enemy, Character userChar) {
        this.enemy = enemy;
        this.userChar = userChar;
    }
    public double initiateOption(int enemyAction) {
        if (enemyAction == 0)
            return attack();
        else if (enemyAction == 1)
            return magicAttack();
        else if (enemyAction == 2)
            return defend();
        else if (enemyAction == 3)
            return heal();
        else
            return doNothing();
     }

    public double attack() {
        return enemy.attack;

    }

    public double magicAttack() {
        return enemy.magic - userChar.defense * .33;
    }

    public double defend() {
        double originalDefense = enemy.defense;
        enemy.defense = enemy.defense * 1.5;
        return originalDefense;
    }

    public double heal() {
        double healAmount = enemy.maxHP * .2;
        // if user heals and the healAmount meets or exceeds their health, currHP = maxHP
        enemy.currHP = Math.min((healAmount + enemy.currHP), enemy.maxHP);
        // if healAmount ends up meets or exceeds their health, return 0 for display purposes
        healAmount = enemy.maxHP - (healAmount + enemy.currHP);
        return healAmount;
    }

    public double doNothing() {
        return 0;
    }

}
