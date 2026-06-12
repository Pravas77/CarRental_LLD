import java.util.Objects;

public class Pair {
    
    private long startDate;
    private long endDate;

    public Pair(long startDate, long endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getStartDate() {
        return startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return startDate == pair.startDate && endDate == pair.endDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
