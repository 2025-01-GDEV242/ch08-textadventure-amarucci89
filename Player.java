
/** Class Player
 * This class holds information about a player.
 *
 * @author Alessandro Marucci
 * @version 2025.03.31
 */
public class Player
{
    private String name; // The player's name.
    private Room currentRoom; // The room the player is in.
    private Items items = new Items(); // The items the player is holding.
    private double maxWeight; // The maximum weight the player can hold.

    /**
     * Constructor for objects of class Player
     * @param name The player's name
     */
    public Player(String name)
    {
        this.name = name;
        this.maxWeight = 10;
    }
    
    /**
     * Enter the given room.
     * @param room The room entered.
     */
    public void enterRoom(Room room)
    {
        currentRoom = room;
    }
    
    /**
     * Gets the player's current room.
     * @return The current room.
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * Get the name of the player.
     * @return The player's name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Gets a string of all items the player is holding.
     * @return A decription of the items held.
     */
    public String getItemString()
    {
        return "Your inventory currently holds: " + items.getLongDescription();
    }
    
    /**
     * Gets a string containing the player's current location and items held.
     * @return A description of the room and items held.
     */
    public String getLongDescription()
    {
        String returnString = currentRoom.getLongDescription();
        returnString += "\n" + getItemString();
        return returnString;
    }
    
    /**
     * Picks up the item from the current room.
     * @param itemName The item to be picked up.
     * @return If successful, this method will return the item that was picked up.
     */
    public Item pickUpItem(String itemName)
    {
        if(canPickUpItem(itemName))
        {
            Item item = currentRoom.removeItem(itemName);
            items.put(itemName, item);
            return item;
        }
        else
        {
            return null;
        }
    }
}
