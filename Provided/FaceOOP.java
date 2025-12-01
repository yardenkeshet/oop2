package OOP2.Provided;


/**
 * The main graph interface.  Supports adding new users, adding friendships
 *  between existing users, retrieving iterators over friends' statuses
 *  and retrieving rank information in the graph.
 */
public interface FaceOOP extends Iterable<Person>{
	/**
	 * Adds a new person to the system.
	 * 
	 * @param id of person to join faceOOP
	 * @param name of person to join faceOOP
	 * @return the new instance of Person, created according to the received id and name.
	 * @throws PersonAlreadyInSystemException if a person with the same id already exists in the system.
	 */
	public Person joinFaceOOP(Integer id, String name) throws PersonAlreadyInSystemException;

	/**
	 * @return the current number of users
	 */
	public int size();
	
	/**
	 * Using this method, you can get a Person for making changes on them 
	 * (i.e: post statuses)  
	 * 
	 * @param the id of the person that is required.
	 * @return the user with the requested id.
	 * @throws PersonNotInSystemException if there is no such user in the system
	 */
	public Person getUser(Integer id) throws PersonNotInSystemException;
	
	/**
	 * Add a connection between 2 users.
	 * 
	 * @param p1 the first person
	 * @param p2 the second person
	 * @throws PersonNotInSystemException if one of the 
	 * 		persons is not a user of the system.
	 * @throws SamePersonException if p1 and p2 are the same person.
	 * @throws ConnectionAlreadyExistException if the 2 
	 * 		users are already friends.
	 */
	public void addFriendship(Person p1, Person p2) 
			throws PersonNotInSystemException, SamePersonException, ConnectionAlreadyExistException;
	
	
	/**
	 * Returns an iterator implementing the StatusIterator interface.
	 * The iterator returns the statuses of each of p's friends. Each
	 * friend is visited by increasing order of id, and their statuses
	 * are returned from the most recent to the oldest.
	 * 
	 * @param p the person whose feed should be iterated over
	 * 
	 * @return an iterator over the statuses in p's feed.
	 * @throws PersonNotInSystemException in case p is not a user of the system.*/
	public StatusIterator getFeedByRecent(Person p) 
			throws PersonNotInSystemException;
	
	/**
	 * Returns an iterator implementing the StatusIterator interface.
	 * The iterator returns the statuses of each of p's friends. Each
	 * friend is visited by increasing order of id, and their statuses
	 * are returned in order of most likes to fewest. If two statuses have
	 * the same number of likes they should be ordered from newest to oldest.
	 * 
	 * @param p the person whose feed should be iterated over
	 * 
	 * @return an iterator over the statuses in p's feed.
	 * @throws PersonNotInSystemException in case p is not a user of the system.*/
	public StatusIterator getFeedByPopular(Person p) 
			throws PersonNotInSystemException;


	/**
	 * Returns if the 2 people interact, meaning they are friends and like at least one post of each other.
	 *
	 * @param p1 is the first person.
	 * @param p2 is the second person.
	 * @return true iff the 2 people interact.
	 * @throws PersonNotInSystemException if one of the Persons is not in the system.
	 */
	public Boolean doInteract(Person p1, Person p2) throws PersonNotInSystemException;


	/**
	 * Returns if there is a path between the
	 * source and the target (including target).
	 * The path consists of doInteract() connections.
	 * 
	 * @param source is the person that is the start point.
	 * @param target is the person that is the end point.
	 * @return the if there is a path between source and target.
	 * @throws PersonNotInSystemException if one of the Persons is not in the system.
	 */
	public Boolean mutuals(Person source, Person target)
			throws PersonNotInSystemException;


	/**
	 * Returns the most popular friend of a given person, meaning the friend with the most likes
	 *
	 * @param p is the person.
	 * @return the most popular friend of p.
	 * @throws PersonNotInSystemException if the person is not in the system.
	 */
	public Person getMostPopularFriend(Person p)
			throws PersonNotInSystemException;

	/**
	 * Returns the number of statuses in the system that contain a certain substring in them.
	 *
	 * @param s is the certain substring.
	 * @return number of statuses that contain the substring.
	 */
	public int countStatusesWith(String s);
	
}
