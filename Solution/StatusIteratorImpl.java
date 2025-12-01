package OOP2.Solution;

import OOP2.Provided.Person;
import OOP2.Provided.Status;
import OOP2.Provided.StatusIterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class StatusIteratorImpl implements StatusIterator {
    private ArrayList<Status> statuses;
    private int currentIndex;

    public StatusIteratorImpl(Person p)
    {
        this.statuses = new ArrayList<Status>();
        this.currentIndex = 0;

        // Gather statuses from friends
        Collection<Person> friends = p.getFriends().sort();
        for (Person friend : friends) {
            for (Status status : friend.getStatusesRecent()) {
                this.statuses.add(status);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return currentIndex < statuses.size();
    }

    @Override
    public Status next() throws IndexOutOfBoundsException {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("No more statuses available.");
        }
        return statuses.get(currentIndex++);
    }
}