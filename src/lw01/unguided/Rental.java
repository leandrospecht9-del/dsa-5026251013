package lw01.unguided;

public abstract class Rental implements Chargeable {
    protected String id;
    protected int days;

    public Rental(String id, int days) {
        if(days <= 0 || id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Days must be greater than zero.");
        }
        this.id = id;
        this.days = days;
    }


    public int calculateCharge(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("Units must be greater than zero.");
        }
        return units * calculateCharge();
    }

    public String summary() {
        return id + " | " + rent() + " | " + calculateCharge();
    }

    protected abstract String rent();

    public int getDays() {
        return days;
    }


}
