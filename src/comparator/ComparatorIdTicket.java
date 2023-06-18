package comparator;

import entity.Ticket;

import java.util.Comparator;

public class ComparatorIdTicket implements Comparator<Ticket> {
    @Override
    public int compare(Ticket t1, Ticket t2) {
        return t1.getId()-t2.getId();
    }
}
