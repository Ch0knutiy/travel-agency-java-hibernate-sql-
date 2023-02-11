package Find;

import Entity.TicketEntity;

import java.util.List;

public class TicketMapper extends MapperAbstract<TicketEntity>{
    @Override
    protected Class<TicketEntity> getType() {
        return TicketEntity.class;
    }

    @Override
    protected String getTableName() {
        return "ticket";
    }

    public void printAll(List<TicketEntity> tickets) {
        for (TicketEntity ticket: tickets) {
            System.out.println( ticket.getId() + ". " + ticket.getSeat()+ " " + ticket.getIdClient().getSurname());
        }
    }

    public void printAllInfoTickets(List<TicketEntity> tickets) {
        for (TicketEntity ticket: tickets) {
            System.out.println("Flight's num: "+ticket.getFlightNum().getNum());
            System.out.println("Seat: "+ticket.getSeat());
            System.out.println("Client: "+ticket.getIdClient().getSurname());
            System.out.println("***");
        }
        System.out.println();
    }

    public List<TicketEntity> findBySeat(int seat) {
        return findByParameter(".bySeat", seat);
    }

    public List<TicketEntity> findByClient(Object client) {
        return findByParameter(".byClient", "%" + client + "%");
    }
}
