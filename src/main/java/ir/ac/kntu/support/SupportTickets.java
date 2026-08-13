package ir.ac.kntu.support;

import java.time.LocalDateTime;

public class SupportTickets {
    private LocalDateTime date = LocalDateTime.now();
    private String text;
    private String relatedUserUsername;
    private boolean isResolved = false;
    private SupportStatus supportStatus;
    private Access category;
    private String answer;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRelatedUserUsername() {
        return relatedUserUsername;
    }

    public void setRelatedUserUsername(String relatedUserUsername) {
        this.relatedUserUsername = relatedUserUsername;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void setResolved(boolean resolved) {
        isResolved = resolved;
    }

    public SupportStatus getSupportStatus() {
        return supportStatus;
    }

    public void setSupportStatus(SupportStatus supportStatus) {
        this.supportStatus = supportStatus;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Access getCategory() {
        return category;
    }

    public void setCategory(Access category) {
        this.category = category;
    }

    public enum SupportStatus {
        RESOLVED,
        BEING_RESOLVED,
        UNRESOLVED
    }
}
