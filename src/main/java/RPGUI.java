import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class RPGUI {
    public RPGManager rpgManager;
    public String[] locations = {"cave","ruined tower","deep forest","sewer", "ruin", "mine"};

    public void commandHandler(){
        try {
            load();
        } catch (IOException e) {
            System.out.println("[ERROR-RPG LOAD FAILED:][ " + e.getMessage() + " ]");
            System.out.println("[NOTICE][ Creating blank rpg manager... ]");
            rpgManager = new RPGManager(true);
        }
        try {
            rpgManager.generateDefaultMonsterList();
            rpgManager.generateMerchantInventory();
        } catch (DuplicateObjectException e) {
            System.out.println("[ERROR][ Monster or merchant generation failed:" + e.getMessage()+"]");

        }

        Scanner input = new Scanner(System.in);
        System.out.println("***[STARTING RPG GAME INTERFACE]***");
        System.out.println("[NARRATION][You are in a peaceful town on a bright sunny day. However, some folks have worried looks on their faces." +
                "\nThere is a merchant at a nearby stall and a man standing near the gate.\nYou also hear the sounds of an arena behind you.]");
        String userStr = "",name;

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
                    System.out.println("['shop'      : Start trading with the merchant. You will be prompted for a choice of buying or selling. ]");
                    //System.out.println("['talk'      : Talk with an NPC. Prompted for the name of who you want to talk to. ]");
                    System.out.println("['equip'     : Equip an item. Prompted for name or id of the item you want to equip. ]");
                    System.out.println("['char'      : Displays all your character's information, including inventory and stats.]");
                    System.out.println("['arena'     : Fight a monster with random stats. You will be prompted for a monster's name that you want to fight. ]");
                    System.out.println("['explore'   : Go out and fight a random preset monster.]");
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
                    //System.out.println("***[ YOUR CURRENT LOCATION]***"); //TODO better header? More like text based adventure game?
                    //System.out.println(rpgManager.look());
                    //System.out.println("[NOTICE][ Unimplemented content.]");
                    //System.out.println("You are in a peaceful town on a bright sunny day. However, some folks have worried looks on their faces.");
                    //break;
                case "trade":
                case "shop":
                    System.out.println("[NARRATION][ You go over to the merchant's stall.]");
                    System.out.println("[DIALOGUE][ Hey, got a fine selection for adventurers here. I'll take any items off your hands as well! ]");
                    System.out.println("[Merchant's inventory]: \n" + rpgManager.getMerchantInv().toString());
                    System.out.println("[?][ What do you want to do? 'buy', or 'sell'? ]");
                    shop();

                    break;
//                case "talk":
//                    System.out.println("[?][ Who do you want to talk to? ]"); //TODO show NPCs in area
//                    System.out.print("[SELECT NPC][> ");
//                    name = input.nextLine();
//                    System.out.println(rpgManager.talk(name));
//                    break;
                case "equip":
                    System.out.println("[?][ What do you want to equip? ]");
                    System.out.println("[INVENTORY]\n" + rpgManager.getPlayer().getInventory().toString());
                    System.out.print("[SELECT ITEM][> ");
                    name = input.nextLine(); //TODO support for selection by item id? need proper id system
                    try {
                        System.out.println(rpgManager.equip(name));
                    } catch (NonExistentObjectException e) {
                        System.out.println("[ERROR][ " + e.getMessage() + "]");
                    }
                    break;
                case "char":
                    System.out.println("***[ YOUR CHARACTER ]***");
                    System.out.println(rpgManager.viewCharacter());
                    break;
                case "fight":
                case "arena":
                    System.out.println("[NARRATION][ You go to the arena and greet the arena master.]\n");
                    System.out.println("[DIALOGUE][ Hey there! Welcome to the arena. We don't know much about the monsters that breed below,\n" +
                            "but let us know if you want to fight something and we'll get it out here for you. ]");
                    System.out.println("[?][ What/who do you want to fight? ]");
                    System.out.print("[SELECT ENEMY][> ");
                    name = input.nextLine();
                    rpgManager.fight(name);
                    //System.out.println("[NOTICE][ Unimplemented content.]");
                    break;
                case "fightrand":
                case "explore":
                    Random random = new Random();
                    int idx = random.nextInt(locations.length);
                    System.out.println("[NARRATION][ You go over to the man at the gate.]\n");
                    System.out.println("[DIALOGUE][ Hello traveler! If you're going out to explore, can you help us with our monster infestation? Thanks! ]");
                    System.out.println("[NARRATION][ You head to the nearby " + locations[idx] + " and encounter a monster!]\n");
                    rpgManager.fightrand();
                    break;
                case "admin_reset_player":
                    rpgManager.player = new Player();
                    System.out.println("Player character reset.");
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
        load();
        rpgManager.transferEXP(xp);
        save();
    }

    public void shop(){                         //this ended up being long so I separated it from main UI
        Scanner input = new Scanner(System.in);
        String choice,name;
        System.out.print("[COMMAND][> ");
        choice = input.nextLine();
        if(choice.toLowerCase().equals("buy")) {
            System.out.println("[?][ What do you want to buy? ]");
            System.out.print("[SELECT ITEM][> ");
            name = input.nextLine();
            try {
                rpgManager.buy(name);
            } catch (InsufficientCurrencyException e) {
                System.out.println(e.getMessage());
            } catch (NonExistentObjectException e) {
                System.out.println("Sorry, I don't have anything like that.");
                System.out.println("[ERROR: " + e.getMessage() + "]");
            }
        }
        else if (choice.toLowerCase().equals("sell")){
            System.out.println("[?][ What do you want to sell? ]");
            System.out.println("[Your inventory]: \n" + rpgManager.player.getInventory().toString());
            System.out.print("[SELECT ITEM][> ");
            name = input.nextLine();
            try {
                rpgManager.sell(name);
            }catch (NonExistentObjectException e){
                System.out.println("[ERROR: " + e.getMessage() + "]");
            }
        }else{
            System.out.println("[NARRATION][ The merchant stares at you with a quizzical look on his face.]");
            System.out.println("[DIALOGUE][ Uh, sorry, what? ]");
            System.out.println("[NARRATION][ You leave the stall. ]");

        }
    }

}
