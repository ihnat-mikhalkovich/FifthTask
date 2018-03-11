package by.epam.fifth_task.business_logic;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
	
	private int pagesAmount;
	
	private final int DEFAULT_AMOUNT_OF_ADJACENT_NUMBERS = 2;
	
	private int amountOfAdjacentNumbers;
	
	{
		amountOfAdjacentNumbers = DEFAULT_AMOUNT_OF_ADJACENT_NUMBERS;
	}
		
	public Pagination(int pagesAmount) {
		this.pagesAmount = pagesAmount;
	}
	
	public void setAmountOfAdjacentNumbers(int amountOfAdjacentNumbers) {
		this.amountOfAdjacentNumbers = amountOfAdjacentNumbers;
	}
	
	public void setDefaultAmountOfAdjacentNumbers() {
		amountOfAdjacentNumbers = DEFAULT_AMOUNT_OF_ADJACENT_NUMBERS;
	}
	
    public List<String> paginating(int currentPage) {
        int left = currentPage - amountOfAdjacentNumbers;
        int right = currentPage + amountOfAdjacentNumbers + 1;
        List<String> rangeWithoutDots = createPaginationWithoutDots(left, right);
        List<String> rangeWithDots = addDots(rangeWithoutDots);
        return rangeWithDots;
    }
	
    private List<String> createPaginationWithoutDots(int left, int right) {
    	List<String> range = new ArrayList<>();
    	for (int i = 1; i <= pagesAmount; i++) {
            if (i == 1 || i == pagesAmount || i >= left && i < right) {
                range.add("" + i);
            }
        }
    	return range;
    }
    
    private List<String> addDots(List<String> range) {
    	List<String> rangeWithDots = new ArrayList<>();
    	String dots = "...";
        int l = 0;
        for (String i : range) {
            if (l > 0) {
                if (Integer.parseInt(i) - l == 2) {
                    rangeWithDots.add("" + (l + 1));
                } else if (Integer.parseInt(i) - l != 1) {
                    rangeWithDots.add(dots);
                }
            }
            rangeWithDots.add(i);
            l = Integer.parseInt(i);
        }
        return rangeWithDots;
    }
    
}
