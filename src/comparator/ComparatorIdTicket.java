package comparator;

import entity.Ticket;

import java.util.Comparator;

public class ComparatorIdTicket implements Comparator<Ticket> {
    @Override
    public int compare(Ticket t1, Ticket t2) {
        if (t1.getId()>t2.getId()){
            return 1;
        }else if (t1.getId()<t2.getId()){
            return -1;
        }else {
            return 0;
        }
    }
}
