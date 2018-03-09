package by.epam.fifth_task.business_logic;

import java.util.ArrayList;
import java.util.List;

public class Pagination {

	public Pagination() {}
	
    public List<String> paginating(int currentPage, int pagesAmount) {
        int delta = 2,
                left = currentPage - delta,
                right = currentPage + delta + 1;
        List<String> range = new ArrayList<>();
        List<String> rangeWithDots = new ArrayList<>();
        int l = 0;

        for (int i = 1; i <= pagesAmount; i++) {
            if (i == 1 || i == pagesAmount || i >= left && i < right) {
                range.add("" + i);
            }
        }

        for (String i : range) {
            if (l > 0) {
                if (Integer.parseInt(i) - l == 2) {
                    rangeWithDots.add("" + (l + 1));
                } else if (Integer.parseInt(i) - l != 1) {
                    rangeWithDots.add("...");
                }
            }
            rangeWithDots.add(i);
            l = Integer.parseInt(i);
        }

        return rangeWithDots;
    }
	
}
