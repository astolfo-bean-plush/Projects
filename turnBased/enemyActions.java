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
        return enemy.attack - userChar.defense;

    }

    public double magicAttack() {
        return enemy.magic - userChar.defense * .33;
    }

    public double defend() {
        enemy.defense = enemy.defense * 1.5;
        //TODO needs to adjust defense back to normal after turn ends
        return 0;
    }

    public double heal() {
        double healAmount = enemy.maxHP * .2;
        // if enemy heals and the healAmount meets or exceeds their health, currHP = maxHP
        enemy.currHP = Math.min((healAmount + enemy.currHP), enemy.maxHP);
        return 0;
    }

    public double doNothing() {
        return 0;
    }

}
