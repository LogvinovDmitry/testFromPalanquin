package sample;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 * <p>
 * <p>
 * package sample;
 * <p>
 * import java.time.LocalDate;
 * import java.util.Collection;
 * import java.util.List;
 * <p>
 * /**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 * <p>
 * Implement in single class. Don't chane constructor and signature method.
 */
public class DateSorter {
    private static final String MONTH_NAME_CONDITION_FACTOR = "R";

    /**
     * The implementation of this method should sort dates.
     * The output should be in the following order:
     * Dates with an 'r' in the month,
     * sorted ascending (first to last),
     * then dates without an 'r' in the month,
     * sorted descending (last to first).
     * For example, October dates would come before May dates,
     * because October has an 'r' in it.
     * thus: (2004-07-01, 2005-01-02, 2007-01-01, 2032-05-03)
     * would sort to
     * (2005-01-02, 2007-01-01, 2032-05-03, 2004-07-01)
     *
     * @param unsortedDates - an unsorted list of dates
     * @return the collection of dates now sorted as per the spec
     */
    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        Comparator<LocalDate> monthsComparator = new Comparator<LocalDate>() {

            @Override
            public int compare(LocalDate d1, LocalDate d2) {

                boolean d1ContainsR = containsRMonth(d1);
                boolean d2ContainsR = containsRMonth(d2);

                if (d1ContainsR && !d2ContainsR) {
                    return -1;
                }

                if (!d1ContainsR && d2ContainsR) {
                    return 1;
                }

                if (d1ContainsR && d2ContainsR) {
                    return d1.compareTo(d2); // Natural ascending order for "R" months
                } else {
                    return d2.compareTo(d1); // Natural descending order for non-"R" months
                }
            }
        };

        return unsortedDates.stream().sorted(monthsComparator).toList();

    }

    private boolean containsRMonth(LocalDate date) {
        return date.getMonth().name().contains(MONTH_NAME_CONDITION_FACTOR);
    }
}