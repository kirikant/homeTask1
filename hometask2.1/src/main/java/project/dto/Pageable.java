package project.dto;

public class Pageable {
    private int amount;
    private int page;

    public Pageable(int amount, int page) {
        this.amount = amount;
        this.page = page;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
