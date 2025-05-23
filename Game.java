import java.util.Stack;

/** Lab 6 - Super Text Adventure
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Alessandro Marucci
 * @version 2025.03.24
 */

public class Game 
{
    private Parser parser;
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player("Alex");
        Room startRoom = createRooms();
        player.enterRoom(startRoom);
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     * @return Returns the starting room.
     */
    private Room createRooms()
    {
        Room outside, theater, pub, lab, office, library, gym, cafe;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        library = new Room("in the school library");
        gym = new Room("in the athletic department gym");
        cafe = new Room("in the cafeteria");
        
        // put items in the room
        outside.addItem(new Item("wooden bench", 45.2));
        theater.addItem(new Item("broken piano", 947.1));
        pub.addItem(new Item("beer", 0.5));
        lab.addItem(new Item("strange organism", 22.4));
        office.addItem(new Item("documents", 3.4));
        library.addItem(new Item("old book", 5.7));
        gym.addItem(new Item("rusty barbell", 35.0));
        cafe.addItem(new Item("fine pastry", 0.2));
        
        // initialize room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        theater.setExit("west", outside);
        pub.setExit("east", outside);
        lab.setExit("north", outside);
        lab.setExit("east", office);
        office.setExit("west", lab);

        return outside;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case LOOK:
                look();
                break;
                
            case BACK:
                goBack(command);
                break;
                
            case TAKE:
                take(command);
    
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    
    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @param command The command for which direction to go.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            player.enterRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
    }
    
    /**
     * Look nearby your current location.
     */
    private void look()
    {
       System.out.println(currentRoom.getLongDescription());
    }
    
    /** 
     * Try to go back to previous room. If you are outside, do nothing.
     * @param command The command for which direction to go.
     */
    private void goBack(Command command) 
    {
        if(command.hasSecondWord()) 
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Back where?");
            return;
        }
        if (roomHistory.isEmpty())
        {
            System.out.println("You can't go back!");
        }
        else 
        {
            Room previousRoom = roomHistory.pop();
            enterRoom(previousRoom);
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Try to take an item from the current room. If the item is there,
     * pick it up. Otherwise, print an error message.
     */
    private void take(Command command)
    {
        if(!command.hasSecondWord()) 
        {
            // if there is no second word, we don't know where to go...
            System.out.println("What do you want to take?");
            return;
        }

        String itemName = command.getSecondWord();
        Item item = player.pickUpItem(itemName);

        if (item == null) 
        {
            System.out.println("Can't pick up " + itemName);
        }
        else 
        {
            System.out.println("Picked up " + item.getDescription());
        } 
    }
}
