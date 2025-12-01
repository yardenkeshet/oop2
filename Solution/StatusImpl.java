package OOP2.Solution;

import OOP2.Provided.Status;

public class StatusImpl implements Status {
	private Person publisher;
	private String content;
	private Integer id;
	private ArrayList<Person> likes;

	/*
	 * A constructor that receives the status publisher, the text of the status
	 *  and the id of the status.
	 */
	public StatusImpl(Person publisher, String content, Integer id)
	{
		this.publisher = publisher;
		this.content = content;
		this.id = id;
		this.likes = new ArrayList<Person>();
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public Person getPublisher() {
		return this.publisher;
	}

	@Override
	public String getContent() {
		return this.content;
	}

	@Override
	public void like(Person p) {
		if (!this.likes.contains(p))
			this.likes.add(p);
	}

	@Override
	public void unlike(Person p) {
		if (this.likes.contains(p))
			this.likes.remove(p);
	}

	@Override
	public Integer getLikesCount() {
		return this.likes.size();
	}

	@Override
	public Boolean isLikedBy(Person p) {
		return this.likes.contains(p);
	}


	protected boolean eq(Object o) {
		if (!(o instanceof StatusImpl)) return false;
		StatusImpl other = (StatusImpl)o;
		return this.id.equals(other.id) && this.publisher.equals(other.publisher);
	}

	@Override
	public boolean equals(Object o) {
		return (this.eq(o) && ((StatusImpl)o).eq(this));
	}
