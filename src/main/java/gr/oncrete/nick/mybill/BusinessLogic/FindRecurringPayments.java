/*
 *  Copyright (C) 2011 nickapos
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gr.oncrete.nick.mybill.BusinessLogic;

import java.time.LocalDateTime;
import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectAverageExpensesPerCompanyInRange;
import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectAverageExpensesPerCompanyInRange.AnalyticsRecord;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class will compare the transactions of each month and find what are the
 * recurring payments through out the year
 *
 * @author nickapos
 */
public class FindRecurringPayments {
    SelectAverageExpensesPerCompanyInRange getMetrics;
    ;
    public FindRecurringPayments() {
        LocalDateTime now = LocalDateTime.now();
        int currentMonth = now.getMonth().getValue();
        int currentYear = now.getYear();
        int remainingMonthsFromPrevYear = 12 - currentMonth;
        String periodStart = String.format("%s-%s-01", currentYear - 1, remainingMonthsFromPrevYear);
        String periodStop = String.format("%s-%s-31", currentYear, currentMonth);
        getMetrics = new SelectAverageExpensesPerCompanyInRange(periodStart, periodStop, "NumOfRecords");
    }
    
    public List<AnalyticsRecord> selectRecordsOnFrequency (int freq){
        List<AnalyticsRecord> a=getMetrics.getAnalyticsRecordList();
        List<AnalyticsRecord> mostFrequent =a.stream().filter(record -> Integer.parseInt(record.getNumberOfRecords()) > freq ).collect(Collectors.toList());
        return mostFrequent;
    }
    
    public double returnMostFrequentAverage(List<AnalyticsRecord> freqRecords){
        return freqRecords.stream().mapToDouble(n->n.getAvPriceDouble()).average().getAsDouble();
    }
    
    public String toString(List<AnalyticsRecord> freqRecords){
        String results = freqRecords.stream().map(n -> n.toString()).collect(Collectors.joining("\n"));
        //System.out.println(results);
        return results;
    }

}
