import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class RPGUI {
    public RPGManager rpgManager;

    public void levelup(double xp){
        rpgManager.player.addExp(xp);
        rpgManager.player.checklevel();
    }

    public void commandHandler(){
//        try {
//            //load(); TODO
//        } catch (IOException e) {
//            System.out.println("[ERROR-LOAD FAILED:][ " + e.getMessage() + " ]");
//            System.out.println("[NOTICE][ Creating blank rpg manager... ]");
//            rpgManager = new RPGManager(true);
//        }
        rpgManager = new RPGManager(true); //TODO

        Scanner input = new Scanner(System.in);
        System.out.println("***[STARTING RPG GAME INTERFACE]***");
        String userStr = "",name;
        //String numOnlyCheck = "0123456789";

        while (!(userStr.equals("quit"))){
            System.out.println("[?][ Enter your command or 'help' to see a list of commands. ]");
            System.out.println("--------------------");
            System.out.print("[COMMAND][> ");
            userStr = input.nextLine();
            System.out.println();

            switch (userStr.toLowerCase()) {
                case "help":
                    System.out.println("***[AVAILABLE COMMANDS]***");
                    System.out.println("['help'      : Displays this ]");
                    System.out.println("['quit'      : Stops the game, go back to Task Interface ]");
                    //System.out.println("['go'        : Go to a location. You will be shown a list of available locations and prompted for a name. ]");
                    //System.out.println("['locations' : Displays a list of all available locations you can travel to. ]");
                    System.out.println("['look'      : Displays your current location. ]");
                    System.out.println("['shop'      : If in a town, start trading with a merchant. You will be prompted for the merchant's name and other commands." +"]");
                    System.out.println("['talk'      : Talk with an NPC. Prompted for the name of who you want to talk to. ]");
                    System.out.println("['equip'     : Equip an item. Prompted for name or id of the item you want to equip. ]");
                    System.out.println("['char'      : Displays all your character's information, including inventory and stats.]");
                    System.out.println("['fight'     : Fight a monster. You will be prompted for which monster you want to fight and start a combat interface.]");
                    break;
//                case "go":
//                    System.out.println("***[ AVAILABLE LOCATIONS ]***");
//                    //rpgManager.viewLocations();
//                    System.out.println("[?][ Where do you want to go? ]");
//                    System.out.print("[SELECT Location][> ");
//                    name = input.nextLine();
//                    System.out.println(rpgManager.goTo(name)); //will print result
//                    break;
//                case "locations":
//                    System.out.println("***[ AVAILABLE LOCATIONS ]***");
//                    //rpgManager.viewLocations();
//                    System.out.println("[NOTICE][ Unimplemented content.]");
//
//                    break;
//                case "look":
//                    System.out.println("***[ YOUR CURRENT LOCATION]***"); //TODO better header? More like text based adventure game?
//                    //System.out.println(rpgManager.look());
//                    System.out.println("[NOTICE][ Unimplemented content.]");
//                    break;
                case "trade":
                case "shop":
                    System.out.println("[?][ Who do you want to trade with? ]"); //TODO show merchants in area and make sure user is in town
                    System.out.print("[SELECT Merchant][> ");
                    name = input.nextLine();
                    rpgManager.shop(name);
                    //TODO print result
                    break;
                case "talk":
                    System.out.println("[?][ Who do you want to talk to? ]"); //TODO show NPCs in area
                    System.out.print("[SELECT NPC][> ");
                    name = input.nextLine();
                    System.out.println(rpgManager.talk(name));
                    break;
                case "equip":
                    System.out.println("[?][ What do you want to equip? ]"); //TODO show inventory
                    System.out.print("[SELECT ITEM][> ");
                    name = input.nextLine(); //TODO support for selection by item id?
                    System.out.println(rpgManager.equip(name));
                    break;
                case "char":
                    System.out.println("***[ YOUR CHARACTER ]***");
                    System.out.println(rpgManager.viewCharacter());
                    break;
                case "fight":
                    System.out.println("[?][ What/who do you want to fight? ]"); //TODO warning if enemy is higher level?
                    System.out.print("[SELECT ENEMY][> ");
                    name = input.nextLine();
                    rpgManager.fight(name);
                    System.out.println("[NOTICE][ Unimplemented content.]");
                    break;
                case "quit": break; //avoids triggering default case
                default:
                    System.out.println("[ERROR][ Command not recognized. ]");
                    break;
            }
        }
        System.out.println("[NOTICE][ Saving and quitting RPG... ]");
        try {
            save();
        } catch (IOException e) {
            System.out.println("[ERROR-RPG SAVE FAILED:][ " + e.getMessage() + " ]");
        }
    }

    public void save() throws IOException {
        JsonUtil.toJsonFile("src/resources/rpgManager.json", rpgManager);
    }
    public void load() throws IOException {
        rpgManager = JsonUtil.fromJsonFile("src/resources/rpgManager.json", RPGManager.class);
    }

    public void transferEXP(double xp) throws IOException {
        //TODO make sure saving/loading works
        load();
        rpgManager.transferEXP(xp);
        save();
    }


}
