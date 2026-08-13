package ir.ac.kntu.support;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TicketProcessor {
    private List<SupportTickets> tickets;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public List<SupportTickets> getTickets() {
        return tickets;
    }

    public void setTickets(List<SupportTickets> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(SupportTickets ticket) {
        this.tickets.add(ticket);
    }

    public void startProcessing() {
        scheduler.scheduleAtFixedRate(this::processTickets, 0, 1, TimeUnit.HOURS);
    }

    private void processTickets() {
        LocalDateTime now = LocalDateTime.now();
        for (SupportTickets ticket : tickets) {
            if (ticket.getSupportStatus() == SupportTickets.SupportStatus.UNRESOLVED) {
                long daysPassed = Duration.between(ticket.getDate(), now).toDays();
                if (daysPassed >= 3 && ticket.getAnswer() == null) {
                    ticket.setAnswer("Our support team will call you soon!");
                    ticket.setSupportStatus(SupportTickets.SupportStatus.RESOLVED);
                }
            }
        }
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
