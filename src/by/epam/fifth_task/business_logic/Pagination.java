package by.epam.fifth_task.business_logic;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
	
	private int pagesAmount;
	
	private final int DEFAULT_AMOUNT_OF_ADJACENT_NUMBERS = 2;
	
	private final String DEFAULT_INTERMEDIATE_SYMBOLS = "...";
	
	private int amountOfAdjacentNumbers;
	
	private String intermediateSymbols;
	
	{
		amountOfAdjacentNumbers = DEFAULT_AMOUNT_OF_ADJACENT_NUMBERS;
		intermediateSymbols = DEFAULT_INTERMEDIATE_SYMBOLS;
	}
	
	public Pagination() {}
		
	public Pagination(int pagesAmount) {
		this.pagesAmount = pagesAmount;
	}
	
	public int getPagesAmount() {
		return pagesAmount;
	}

	public void setPagesAmount(int pagesAmount) {
		this.pagesAmount = pagesAmount;
	}
	
	public void setAmountOfAdjacentNumbers(int amountOfAdjacentNumbers) {
		this.amountOfAdjacentNumbers = amountOfAdjacentNumbers;
	}
	
	public void setDefaultAmountOfAdjacentNumbers() {
		amountOfAdjacentNumbers = DEFAULT_AMOUNT_OF_ADJACENT_NUMBERS;
	}
	
	public void setIntermediateSymbols(String intermediateSymbols) {
		this.intermediateSymbols = intermediateSymbols;
	}
	
	public void setDefaultIntermediateSymbols() {
		intermediateSymbols = DEFAULT_INTERMEDIATE_SYMBOLS;
	}
	
	public String getIntermediateSymbols() {
		return intermediateSymbols;
	}
	
    public List<String> paginating(int currentPage) {
        int left = currentPage - amountOfAdjacentNumbers;
        int right = currentPage + amountOfAdjacentNumbers + 1;
        List<String> rangeWithoutDots = createPaginationWithoutIntermediateSymbols(left, right);
        List<String> rangeWithDots = addIntermediateSymbols(rangeWithoutDots);
        return rangeWithDots;
    }
	
    private List<String> createPaginationWithoutIntermediateSymbols(int left, int right) {
    	List<String> range = new ArrayList<>();
    	for (int i = 1; i <= pagesAmount; i++) {
            if (i == 1 || i == pagesAmount || i >= left && i < right) {
                range.add("" + i);
            }
        }
    	return range;
    }
    
    private List<String> addIntermediateSymbols(List<String> range) {
    	List<String> rangeWithDots = new ArrayList<>();
        int l = 0;
        for (String i : range) {
            if (l > 0) {
                if (Integer.parseInt(i) - l == 2) {
                    rangeWithDots.add("" + (l + 1));
                } else if (Integer.parseInt(i) - l != 1) {
                    rangeWithDots.add(intermediateSymbols);
                }
            }
            rangeWithDots.add(i);
            l = Integer.parseInt(i);
        }
        return rangeWithDots;
    }
 
}
