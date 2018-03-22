function pagination(current, last, intermediateSymbols) {
    var delta = 2,
        left = current - delta,
        right = current + delta + 1,
        range = [],
        rangeWithIntermediateSymbols = [],
        difference;

    for (let i = 1; i <= last; i++) {
        if (i == 1 || i == last || i >= left && i < right) {
            range.push(i);
        }
    }

    for (let i of range) {
        if (difference) {
            if (i - difference === 2) {
            	rangeWithIntermediateSymbols.push(difference + 1);
            } else if (i - difference !== 1) {
            	rangeWithIntermediateSymbols.push(intermediateSymbols);
            }
        }
        rangeWithIntermediateSymbols.push(i);
        difference = i;
    }

    return rangeWithIntermediateSymbols;
}

function paginationWithLinks(current, last, intermediateSymbols) {
	var intermediateSymbols = "...";
	var paginationList = pagination(current, last, intermediateSymbols);
	for (var i = 0; i < paginationList.length; i++) {
		if (!(paginationList[i] == intermediateSymbols)) {
			$("center").append("<a href=\"Controller?selectedPage=" + paginationList[i] + "&parserType=" + "${ requestScope.parserType }" + "\">" + paginationList[i] + "</a>&nbsp;");	
		} else {
			$("center").append(intermediateSymbols + "&nbsp;");
		}
	}
}