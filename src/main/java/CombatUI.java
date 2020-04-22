public class CombatUI {
    public PlayerCharacter player;
    public Monster enemy;

    public void commandHandler(PlayerCharacter player, Monster enemy) {
        this.player=player;
        this.enemy=enemy;
    }
}
