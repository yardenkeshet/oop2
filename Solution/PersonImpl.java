package OOP2.Solution;

import OOP2.Provided.Person;

public class PersonImpl implements Person, Comparable<Person> {
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
		return this.statuses.stream()
				.sorted((s1, s2) -> s2.getId().compareTo(s1.getId()))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<Status> getStatusesPopular() {
		return this.statuses.stream()
				.sorted((s1, s2) -> {
					int cmp = s2.getLikesCount().compareTo(s1.getLikesCount());
					if (cmp == 0) {
						return s2.getId().compareTo(s1.getId());
					}
					return cmp;
				})
				.collect(Collectors.toList());
	}

	@Override
	public int compareTo(Person other) {
		return this.id.compareTo(other.getId());
	}

	protected boolean eq(Object o) {
		if (!(o instanceof PersonImpl)) return false;
		PersonImpl other = (PersonImpl)o;
		return this.id.equals(other.id);
	}

	@Override
	public boolean equals(Object o) {
		return (this.eq(o) && ((PersonImpl)o).eq(this));
	}



}
