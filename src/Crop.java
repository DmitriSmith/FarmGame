/**
* The Crop class represents a crop that can be bought by the
* player and planted on their farm
* 
* Last modified: 29-4-20 by Dmitri Smith
*
* Created: 28-4-20
* @author  Dmitri Smith
*/
public class Crop implements Merchandise{
	private String m_name;
	private int m_purchasePrice;
	private int m_sellPrice;
	private int m_daysToGrow;
	private int m_daysUntilHarvest;
	
	/**
	 * Constructor. A new instance of a Crop object has spent no days growing
	 * @param name			Name of the crop
	 * @param purchasePrice Price of the crop when purchased from General Store
	 * @param sellPrice		Price of the crop when harvested and sold
	 * @param daysToGrow	Number of days required to grow the crop without using items
	 */
	public Crop(String name, int purchasePrice, int sellPrice, int daysToGrow)
	{
		m_name = name;
		m_purchasePrice = purchasePrice;
		m_sellPrice = sellPrice;
		m_daysToGrow = daysToGrow;
		m_daysUntilHarvest = daysToGrow;
	}
	
	/**
	 * Attempt to use an item to reduce amount of time until crop is ready to harvest
	 * @param item Item to be used to on Crop
	 * @return message to output to user reporting whether or not the item was successfully used and additional info 
	 * @throws RuntimeException if item could not be used
	 */
	public boolean useItem(Item item)
	{		
		if(item.getForCrops())
		{
			try
			{
				reduceRemainingTime(item.getBoostAmount());
				//TODO: Remove item from player's inventory
				return true;
			}
			catch (Exception e)
			{
				throw new RuntimeException(String.format("%s not used - %s is already ready to harvest.", item.getName(), m_name));
			}
		}
		else
		{
			throw new RuntimeException(String.format("%1$s not used - %1$s cannot be used on crops.", item.getName()));
		}
		
		
	}
	
	/**
	 * Tend to crop without using an item
	 * @return true if successfully tended to
	 * @throws RuntimeException if crop is already ready to harvest
	 */
	public boolean tendToCrop()
	{
		int daysReduced = 1;
		try
		{
			reduceRemainingTime(daysReduced);
			return true;
		}
		catch (Exception e)
		{
			throw new RuntimeException(String.format("Failure - %s is already ready to harvest.", m_name));
		}
	}
	
	/**
	 * Overloaded tendToCrop. Uses item from player's inventory to tend to crop
	 * @param item item to use on crop
	 * @return true if item was successfully used
	 */
	public boolean tendToCrop(Item item)
	{
		try
		{
			useItem(item);
			return true;
		}
		catch(RuntimeException e)
		{
			System.out.println(e);
			return false;
		}
	}
	
	/**
	 * Reduce the number of days until the crop is ready to harvest
	 * @param days number of days to reduce remaining growing time by
	 * @return number of days remaining before ready to harvest
	 * @throws RuntimeException on crop already ready to harvest
	 */
	public int reduceRemainingTime(int days)
	{
		if(m_daysUntilHarvest == 0)
		{
			throw new RuntimeException("Cannot be further reduced");
		}
		m_daysUntilHarvest -= days;
		//Check that daysUntilHarvest is not < 0; if so, set to 0
		if(m_daysUntilHarvest < 0)
		{
			m_daysUntilHarvest = 0;
		}
		
		return m_daysUntilHarvest;
	}
	
	/**
	 * Reset the days until crop is ready to harvest and give player cash bonus
	 * @return Sell price of the crop
	 * @throws RuntimeException if m_dayUntilHarvest isn't zero
	 */
	public int harvest()
	{
		if(m_daysUntilHarvest != 0)
		{
			throw new RuntimeException(String.format("%s isn't ready to harvest!", m_name));
		}
		m_daysUntilHarvest = m_daysToGrow;
		return m_sellPrice;
	}
	
	/**
	 * 
	 * @param source Crop to make deep copy of
	 * @return Reference to deep copy of source
	 */
	public Crop copy(Crop source)
	{
		return (new Crop(m_name, m_purchasePrice, m_sellPrice, m_daysToGrow));
	}
	
	/**
	 * 
	 * @return Name of crop
	 */
	public String getName()
	{
		return m_name;
	}
	
	/**
	 * 
	 * @param name New name for crop
	 */
	public void setName(String name)
	{
		m_name = name;
	}

	/**
	 * @return Price of crop when purchased from General Store
	 */
	public int getPurchasePrice()
	{
		return m_purchasePrice;
	}
	
	/**
	 * @param price New price of crop in General Store
	 */
	public void setPurchasePrice(int price)
	{
		m_purchasePrice = price;
	}
	
	/**
	 * 
	 * @return amount of cash crop sells for when harvested
	 */
	public int getSellPrice()
	{
		return m_sellPrice;
	}
	
	/**
	 * Set amount of cash crop sells for when harvested
	 * @param price amount of cash crop sells for when harvested
	 */
	public void setSellPrice(int price)
	{
		m_sellPrice = price;
	}
	
	/**
	 * 
	 * @return number of days crop takes to grow
	 */
	public int getDaysToGrow()
	{
		return m_daysToGrow;
	}
	
	/**
	 * Set number of days crop takes to grow
	 * @param days number of days crop takes to grow
	 */
	public void setDaysToGrow(int days)
	{
		m_daysToGrow = days;
	}
	
	/**
	 * 
	 * @return number of days until crop is ready to be harvested
	 */
	public int getDaysUntilHarvest()
	{
		return m_daysUntilHarvest;
	}
	
	/**
	 * Set number of days until crop is ready to be harvested
	 * @param days new value for number of days until crop is ready to be harvested
	 */
	public void setDaysUntilHarvest(int days)
	{
		m_daysUntilHarvest = days;
	}
}
