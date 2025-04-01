
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
     */
    public void enterRoom(Room room)
    {
        currentRoom = room;
    }
    
    /**
     * Gets the player's current room.
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    public String getName()
    {
        return name;
    }
}
