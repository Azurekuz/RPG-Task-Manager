import java.util.InputMismatchException;
import java.util.Scanner;

public class CombatUI {
    public Player player;
    public Monster enemy;
    private int currentTurnNum;
    private String result; //W- Win | L- Lose | F- Fled
    Scanner input;

    CombatUI(Player player, Monster enemy) {
        this.player=player;
        this.enemy=enemy;
        currentTurnNum = 0;
        result = "";

        input = new Scanner(System.in);
    }

    public void handleTurn(){
        while(result.equals("")) {
            System.out.println("[ *** TURN " + (currentTurnNum + 1) + " *** ]");
            if (currentTurnNum % 2 == 0) {
                handlePlayerTurn();
            } else if (currentTurnNum % 2 == 1) {
                handleEnemyTurn();
            }
            currentTurnNum = currentTurnNum + 1;
        }
    }

    public void handlePlayerTurn(){
        if(player.getAlive()){
            System.out.println("\t"+player.getName()+ "'s Turn!");
            System.out.println();
            int barFill = (int) Math.ceil(((double) this.enemy.getCurHealth()/(double) this.enemy.getMaxHealth())*10);
            System.out.print("[" + this.enemy.getName() + "][");
            for(int i = 0; i < 10; i++){
                if(i <= (barFill-1)) {
                    System.out.print("+");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println("]");
            System.out.println();
            System.out.println("[" + this.player.getName() + "][" + this.player.getCurHealth() + "/" + this.player.getMaxHealth() + "]");
            commandPrompt();
        }else{
            eventPlayerDeath();
        }
    }

    public void handleEnemyTurn(){
        if(enemy.getAlive()){
            System.out.println("\t"+enemy.getName()+ "'s Turn!");
            actionAttack(this.enemy, this.player);
        }else{
            eventEnemyDeath();
        }
    }

    public void commandPrompt(){
            int action = -1;
        while(action==-1) {
            System.out.println();
            System.out.println("What will you do?");
            System.out.println("[1] ATTACK");
            System.out.println("[2] FLEE");
            System.out.print("[ACTION][> ");
            try {
                action = input.nextInt();
            } catch (InputMismatchException e) {
                action = -1;
            }

            switch (action) {
                case 1:
                    actionAttack(this.player, this.enemy);
                    break;
                case 2:
                    actionFlee(this.player);
                    break;
                default:
                    action = -1;
                    System.out.println("[ERROR][ Invalid action entered! ]");
            }
        }
    }

    public void actionAttack(Actor attacker, Actor target){
        System.out.println("\t"+attacker.getName()+" attacks " + target.getName() + "!");
        int damageOutput = target.getCurHealth();
        attacker.attack(target);
        damageOutput = (damageOutput-target.getCurHealth());
        System.out.println("\t"+attacker.getName()+" dealt " + damageOutput + " damage to " + target.getName() + ".");
    }

    public void actionFlee(Actor runner){
        if(Math.floor(Math.random()*100) > 50){
            System.out.println(runner.getName() + " has fled!");
            result = "F";
        }else{
            System.out.println(runner.getName() + " is unable to flee!");
        }
    }

    public void eventPlayerDeath(){
        result = "L";
        System.out.println("Oh dear, you are dead!");
    }

    public void eventEnemyDeath(){
        result = "W";
        System.out.println("Victory! " + enemy.getName() + " has been defeated!");
        System.out.println("You have looted " + enemy.getCurrency() + " gold.");
        player.grantCurrency(enemy.getCurrency());
    }
}
