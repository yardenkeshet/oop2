package OOP2.Solution;

import OOP2.Provided.Person;

public class PersonImpl implements Person {
	private Integer id;
	private String name;
	private ArrayList<Person> friends;
	private ArrayList<Status> statuses;
	private Integer statusIdCounter;

	public PersonImpl(Integer id, String name)
	{
		this.id = id;
		this.name = name;
		this.friends = new ArrayList<Person>();
		this.statuses = new ArrayList<Status>();
		this.statusIdCounter = 0;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public String getName() {	
		return this.name;
	}

	@Override
	public Status postStatus(String content) {
		Status newStatus = new StatusImpl(this, content, this.statusIdCounter);
		this.statuses.add(0, newStatus); // Add to the beginning for recent order
		this.statusIdCounter++;
		return newStatus;
	}

	@Override
	public void addFriend(Person p) 
			throws SamePersonException, ConnectionAlreadyExistException {
		if (this.equals(p))
			throw new SamePersonException("Cannot add oneself as a friend.");
		if (this.friends.contains(p))
			throw new ConnectionAlreadyExistException("This person is already a friend.");
		this.friends.add(p);
	}

	@Override
	public Collection<Person> getFriends() {
		return new ArrayList<Person>(this.friends);
	}

	@Override
	public Iterable<Status> getStatusesRecent() {
		return new ArrayList<Status>(this.statuses);
	}

	@Override
	public Iterable<Status> getStatusesPopular() {
		
	}

}
