package OOP2.Solution;

import OOP2.Provided.FaceOOP;
import OOP2.Provided.Person;

public class FaceOOPImpl implements FaceOOP {
	private HashMap<Integer, Person> persons = new HashMap<>();

	public FaceOOPImpl()
	{
		this.persons = new HashMap<>();
		this.friendships = new HashMap<>();
	}
	
	@Override
	public Person joinFaceOOP(Integer id, String name) throws PersonAlreadyInSystemException {
		if (this.persons.containsKey(id))
			throw new PersonAlreadyInSystemException("Person with this ID already exists.");
		Person newPerson = new PersonImpl(id, name);
		this.persons.put(id, newPerson);
		return newPerson;
	}

	@Override
	public int size() {
		return this.persons.size();
	}

	@Override
	public Person getUser(Integer id) throws PersonNotInSystemException {
		if (!this.persons.containsKey(id))
			throw new PersonNotInSystemException("No such user in the system.");
		return this.persons.get(id);
	}

	@Override
	public void addFriendship(Person p1, Person p2) throws PersonNotInSystemException, SamePersonException, ConnectionAlreadyExistException {
		if (!this.persons.containsValue(p1) || !this.persons.containsValue(p2))
			throw new PersonNotInSystemException("One of the persons is not a user of the system.");
		if (p1.equals(p2))
			throw new SamePersonException("Cannot add oneself as a friend.");
		if (this.friendships.get(p1).contains(p2))
			throw new ConnectionAlreadyExistException("These users are already friends.");
		

		p1.addFriend(p2);
		p2.addFriend(p1);
	}

	@Override
	public StatusIterator getFeedByRecent(Person p) throws PersonNotInSystemException {
		if (!this.persons.containsValue(p))
			throw new PersonNotInSystemException("Person is not a user of the system.");

		return new StatusIteratorImpl(p);
	}

	@Override
	public StatusIterator getFeedByPopular(Person p) throws PersonNotInSystemException {
		if (!this.persons.containsValue(p))
			throw new PersonNotInSystemException("Person is not a user of the system.");

		return new StatusIteratorPopularImpl(p);
	}

	@Override
	public Boolean doInteract(Person p1, Person p2) throws PersonNotInSystemException {
		if (!this.persons.containsValue(p1) || !this.persons.containsValue(p2))
			throw new PersonNotInSystemException("One of the persons is not a user of the system.");

		Boolean areFriends = this.friendships.get(p1).contains(p2);
		Boolean P1likedStatusp2 = false;
		Boolean P2likedStatusp1 = false;

		for (Status status : p2.getStatusesRecent()) {
			if (status.getLikes().contains(p1)) {
				P1likedStatusp2 = true;
				break;
			}
		}

		for (Status status : p1.getStatusesRecent()) {
			if (status.getLikes().contains(p2)) {
				P2likedStatusp1 = true;
				break;
			}
		}

		return areFriends && P1likedStatusp2 && P2likedStatusp1;
	}

	@Override
	public Boolean mutuals(Person p1, Person p2) throws PersonNotInSystemException {
		//check if there ia a path of friends between p1 and p2 (check for loops so we dont get an infinite loop
		if (!this.persons.containsValue(p1) || !this.persons.containsValue(p2))
			throw new PersonNotInSystemException("One of the persons is not a user of the system.");

		//a person is not a mutual of himself
		if (p1.equals(p2)) {
			return false;
		}

		Set<Person> visited = new HashSet<>();
		Queue<Person> queue = new LinkedList<>();

		queue.add(p1);
		visited.add(p1);

		while (!queue.isEmpty()) {
			Person current = queue.poll();
			if (current.equals(p2)) {
				return true;
			}
			for (Person friend : current.getFriends()) {
				if (!visited.contains(friend)) {
					visited.add(friend);
					queue.add(friend);
				}
			}
		}

		return false;
	}


	@Override
	public Person getMostPopularFriend(Person p) throws PersonNotInSystemException {
		if (!this.persons.containsValue(p))
			throw new PersonNotInSystemException("Person is not a user of the system.");

		Person mostPopular = null;
		int maxLikes = -1;

		for (Person friend : p.getFriends()) {
			int totalLikes = 0;
			for (Status status : friend.getStatusesRecent()) {
				totalLikes += status.getLikesCount();
			}
			if (totalLikes > maxLikes) {
				maxLikes = totalLikes;
				mostPopular = friend;
			}
		}

		return mostPopular;
	}

	@Override
	public int countStatusesWith(String s) {
		int count = 0;
		for (Person person : this.persons.values()) {
			for (Status status : person.getStatusesRecent()) {
				if (status.getContent().contains(s)) {
					count++;
				}
			}
		}
		return count;
	}
}
