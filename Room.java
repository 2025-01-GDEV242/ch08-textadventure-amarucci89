import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/** Lab 6 - Super Text Adventure
 *  Class Room - a room in an adventure game.
 *
 *  This class is part of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  
 *
 *  A "Room" represents one location in the scenery of the game.  It is 
 *  connected to other rooms via exits.  For each existing exit, the room 
 *  stores a reference to the neighboring room.
 * 
 *  @author  Alessandro Marucci
 *  @version 2025.03.24
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits; // Stores exits of this room.
    private HashSet<Item> items; // Stores items in this room.
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashSet<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Returns the short description of the room.
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string description for items in the room.
     */
    private String getItemString()
    {
        String returnString = "Items:";
        for(Iterator<Item> iter = items.iterator(); iter.hasNext();) 
        {
            returnString += " " + iter.next().getDescription();
        }
        
        return returnString;
    }
    
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Creates an item to add to the room.
     */
    public void addItem(Item item)
    {
        items.add(item);
    }
}

