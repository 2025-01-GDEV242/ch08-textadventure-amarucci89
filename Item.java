
/**Lab 6 - Super Text Adventure
 * This class stores the details of items that can be found in a room.
 * Some of the details include a description of the item and it's weight.
 *
 * @author Alessandro Marucci
 * @version 2025.03.31
 */
public class Item
{
    private String name; // The name of the item.
    private String description; // A description of the item.
    private double weight; // The weight of the item.

    /**
     * Create a new item with the given description and weight.
     * @param name The Item's name
     * @param description The item's description
     * @param weight The item's weight
     */
    public Item(String name, String description, double weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * Return the item's name.
     * @return The name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Return the item's weight.
     * @return The weight
     */
    public double getWeight()
    {
        return weight;
    }
    
    /**
     * Return the item's description.
     * @return The description
     */
    public String getDescription()
    {
        return description;
    }
}
