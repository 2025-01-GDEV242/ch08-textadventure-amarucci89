
/** Class Player
 * This class holds information about a player.
 *
 * @author Alessandro Marucci
 * @version 2025.03.31
 */
public class Player
{
    private String name;
    private Room currentRoom;

    /**
     * Constructor for objects of class Player
     */
    public Player(String name)
    {
        this.name = name;
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
