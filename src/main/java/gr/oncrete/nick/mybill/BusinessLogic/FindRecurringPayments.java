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

import java.time.LocalDate;
import java.time.LocalDateTime;
import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectAverageExpensesPerCompanyInRange;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class will compare the transactions of each month and find what are the
 * recurring payments through out the year
 *
 * @author nickapos
 */
public class FindRecurringPayments {

    public FindRecurringPayments() {

    }

    private void createPeriodOfTwelveMonthsBack() {
        LocalDateTime now = LocalDateTime.now();
        int currentMonth= now.getMonth().getValue();
        int currentYear = now.getYear();
        SelectAverageExpensesPerCompanyInRange getMetrics;
        List<Integer> months = IntStream.rangeClosed(1, currentMonth).boxed().collect(Collectors.toList());
        months.forEach(it-> System.out.println(it));
        
    }
}
