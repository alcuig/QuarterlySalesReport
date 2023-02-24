
public class Quarterly_Report {

	// create Department class
	public class Department {
		private String name;
		private int[] sales = new int[12]; // assumes input will contain sales data for 12 months
		private int q1TotalSales, q2TotalSales, q3TotalSales, q4TotalSales;
		private int avgLatestQuarterSales;
		private int[] quarterlySalesTotalList = new int[4]; // list where total sales for Q1,Q2,Q3, Q4 are stored

		// define Department constructor
		public Department(String nameIn, int[] salesIn) {
			name = nameIn;
			sales = salesIn;
			// based on input sales figures (for 12 months), will automatically calculate
			// sales total for each quarter
			setQ1TotalSales(salesIn);
			setQ2TotalSales(salesIn);
			setQ3TotalSales(salesIn);
			setQ4TotalSales(salesIn);
			// quarterly sales figures are automatically added into the object's list
			// containing all quarterly sale totals
			quarterlySalesTotalList[0] = getQ1TotalSales();
			quarterlySalesTotalList[1] = getQ2TotalSales();
			quarterlySalesTotalList[2] = getQ3TotalSales();
			quarterlySalesTotalList[3] = getQ4TotalSales();

		}

		// SETTERS AND GETTERS DEFINED BELOW

		// Improvement for getSales() : even if sales data for 2 quarters is input,
		// automatically add 0s where data for that quarter has not been input
		// i.e. if only values for Quarter 1 have been input (Jan, Feb, March), input 0s
		// for following months April, May etc. to December.)
		public int[] getSales() {
			return sales;
		}

		public String getName() {
			return name;
		}

		// Setter methods : for quarterly sales attributes
		// Improvement for setQXTotalSales(): consolidate methods below into single
		// method
		public void setQ1TotalSales(int[] monthlySales) {
			int total = 0;
			for (int i = 0; i < 3; i++) {
				total = total + monthlySales[i];
			}
			q1TotalSales = total;
		}

		public void setQ2TotalSales(int[] monthlySales) {
			int total = 0;
			for (int i = 3; i < 6; i++) {
				total = total + monthlySales[i];
			}
			q2TotalSales = total;
		}

		public void setQ3TotalSales(int[] monthlySales) {
			int total = 0;
			for (int i = 6; i < 9; i++) {
				total = total + monthlySales[i];

			}

			q3TotalSales = total;
		}

		public void setQ4TotalSales(int[] monthlySales) {
			int total = 0;
			for (int i = 9; i < 12; i++) {
				total = total + monthlySales[i];

			}
			q4TotalSales = total;

		}

		// Getter Methods : for quarterly sales attributes
		// Improvement for getQXTotalSales() : synthesize below into single method

		public int getQ1TotalSales() {
			return q1TotalSales;
		}

		public int getQ2TotalSales() {
			return q2TotalSales;
		}

		public int getQ3TotalSales() {
			return q3TotalSales;
		}

		public int getQ4TotalSales() {
			return q4TotalSales;
		}

		public int getAvgLatestQuarterSales() {
			return avgLatestQuarterSales;
		}

		// Getter Method : given a quarter, will fetch department's total sales for that
		// quarter
		public int getDeptTotalSalesOfAQuarter(int quarterIndex) {
			quarterIndex = quarterIndex - 1; // assumes user will enter quarter, so 1 subtracted to get correct index in
												// list
			int qSale = quarterlySalesTotalList[quarterIndex];
			return qSale;
		}

		// Getter Method : to access quarterly sales saved in Department's list
		public int[] getAllQuarterlySales() {
			return quarterlySalesTotalList;
		}

	}

	// Method : will display MONTHLY sales for given quarter
	// Used in context of bestAndWorstPerQuarter()
	public String showQuarterMonthlySales(int quarter, Department dept) {
		StringBuilder builder = new StringBuilder();
		for (int i = quarter * 3 - 3; i <= (quarter * 3 - 1); i++) {
			builder.append("£" + dept.getSales()[i] + " ");
		}
		String quarterSales = builder.toString();
		return quarterSales;
	}

	// DEFINE THE THREE ASSIGNMENT ALGORITHMS

	// 1. Display total sales per quarter for each department

	public void showQuarterSalesAllDepartments(Department[] departments) {
		for (Department item : departments) {
			System.out.println(" ");
			System.out.println(item.getName() + " Sales\n");
			int[] deptSales = item.getSales();
			int month = 0;
			for (int quarter = 1; quarter < 5; quarter++) {
				int quarterSales = 0;
				for (int i = 0; i < 3; i++) {
					quarterSales = quarterSales + deptSales[month];
					month = month + 1;
				}
				System.out.println("Quarter " + quarter + " = £" + quarterSales);
			}

		}

	}

	// 2. Given the average sales for each department across the last reported
	// quarter,provide a new sales target for each department with an increase of
	// 12%
	public void newSalesTarget(Department[] departments) {

		for (Department item : departments) {
			// STEP ONE : check which is last reported quarter (total sales of the quarter
			// will be larger than 0)
			int[] lsQuarterTotals = { item.getDeptTotalSalesOfAQuarter(1), item.getDeptTotalSalesOfAQuarter(2),
					item.getDeptTotalSalesOfAQuarter(3), item.getDeptTotalSalesOfAQuarter(4) };
			int lastReportedQuarter = 0;

			// Reverse loop to cycle backwards through quarter totals
			// The first one that does not have 0 as a total will be the last reported
			// quarter
			for (int i = 3; i > 0; i--) {
				if (lsQuarterTotals[i] != 0) {
					lastReportedQuarter = lastReportedQuarter + (1 + i);
					break;

				} else { // don't do anything, continue cycling backwards in quarterly totals
				}
			}

			// STEP TWO : now that we know what the last reported quarter is
			// calculate the average sales of that quarter

			// Sum sales of quarter / by 3
			// Create method variable that will be used to access monthly sales of relevant
			// quarter

			int[] monthlySales = item.getSales();

			int sumMonthlySales = 0;
			// given a quarter, will find corresponding monthly sales
			for (int i = lastReportedQuarter * 3 - 3; i < (lastReportedQuarter * 3 - 1); i++) {

				sumMonthlySales = sumMonthlySales + monthlySales[i];

			}

			int averagelastReportedQuarter = sumMonthlySales / 3;

			System.out.println(item.getName() + "\n");
			System.out.println("Last Reported Quarter = Quarter " + lastReportedQuarter);
			System.out.println("Average Sales For Last Reported Quarter = £" + averagelastReportedQuarter);

			// take average sales last quarter and increase by 12%
			int newTarget = (int) (averagelastReportedQuarter * 1.12);
			System.out.println("New Average Sales Target For Next Quarter = £" + newTarget);
			System.out.println(" ");
		}

	}

	// 3. The name of the best and worst performing department per quarter, with its
	// respective monthly sales e. “2nd Quarter best: Kitchen, £65, 000, £67,000,
	// £56,000” for the second
	// quarter.

	public void bestAndWorstPerQuarter(Department[] departments) {

		for (int i = 0; i < 4; i++) {
			int quarter = i + 1;
			Department bestDept = departments[0];
			int bestDeptSales = 0;
			int worstDeptSales = departments[0].getAllQuarterlySales()[i]; // initialises variable with first Dept
																			// object in Dept list
			Department worstDept = departments[0];
			boolean missingData = true;

			for (Department item : departments) {
				int quarterSales = (item.getAllQuarterlySales()[i]);
				if (quarterSales != 0) {
					missingData = false;
				}

				if (quarterSales > bestDeptSales) {
					bestDeptSales = quarterSales;
					bestDept = item;
				}
				if (quarterSales < worstDeptSales) {
					worstDeptSales = quarterSales;
					worstDept = item;

				}

			}

			// checks if sales = 0, will display "no data for this quarter" message
			// else will display best / worst depts of that quarter
			if (missingData) {
				System.out
						.println("\n\nQUARTER " + quarter + " \n\nWe have no data from departments for this quarter.");

			} else {
				// Best department name, total sales in quarter, monthly sales
				System.out.println("\n\nQUARTER " + quarter);

				System.out.println("\n>Best Performing Department  : " + bestDept.name);
				System.out.println("\nTotal Sales of Quarter   |" + "\t" + "Monthly Sales of Quarter   |");
				System.out.println("\t£" + bestDeptSales + " \t\t" + showQuarterMonthlySales(quarter, bestDept));

				// Worst department name, total sales in quarter, monthly sales breakdown
				System.out.println("\n\n>Worst Performing Department  : " + worstDept.name);
				System.out.println("\nTotal Sales of Quarter   |" + "\t" + "Monthly Sales of Quarter   |");
				System.out.println("\t£" + worstDeptSales + " \t\t\t" + showQuarterMonthlySales(quarter, worstDept));

			}

		}

	}

	// Final output // i.e. “2nd Quarter best: Kitchen, £65, 000, £67,000, £56,000”
	// for the second quarter.

	// ASSUMPTION : Incoming data from Quarter Sales per department will come in
	// base 1000 format
	// ie raw data will be 68, representing in fact 68 000
	// SOLUTION: below method will times by a thousand all values from data source

	public int[] transform(int[] dataDept) {
		for (int i = 0; i < dataDept.length; i++) {
			dataDept[i] = dataDept[i] * 1000;
		}
		return dataDept;
	}

	// Generates department data based on sample data provided in assignment table
	public Department[] generateDepartments() {

		// Database design : index corresponds implicitly to month, i.e.
		// index 0 = Jan,
		// index 1 = Feb, etc. until 11 = Dec.

		int[] electricalData = { 0, 0, 0, 67, 63, 78, 78, 104, 103, 0, 0, 0 };
		int[] kitchenData = { 0, 0, 0, 65, 67, 56, 45, 56, 72, 0, 0, 0 };
		int[] bathroomData = { 0, 0, 0, 63, 63, 65, 71, 73, 69, 0, 0, 0 };
		int[] softFurnishingData = { 0, 0, 0, 18, 24, 22, 19, 17, 16, 0, 0, 0 };
		int[] accessoriesData = { 0, 0, 0, 16, 23, 21, 19, 20, 19, 0, 0, 0 };

		// Transform values from incoming data into thousands

		electricalData = transform(electricalData);
		kitchenData = transform(kitchenData);
		bathroomData = transform(bathroomData);
		softFurnishingData = transform(softFurnishingData);
		accessoriesData = transform(accessoriesData);

		// Instantiates object of Department class with transformed sales data

		Department kitchen = new Department("Kitchen Department", kitchenData);
		Department electrical = new Department("Electrical Department", electricalData);
		Department bathroom = new Department("Bathroom Department", bathroomData);
		Department softFurnishing = new Department("Soft Furnishing Department", softFurnishingData);
		Department accessories = new Department("Accessories Department", accessoriesData);

		// Create and return array of departments, which will be used to access
		// department sales data elsewhere in application
		Department[] deptList = { electrical, kitchen, bathroom, softFurnishing, accessories };
		return deptList;
	}

	// Run application, produces quarterly report
	public static void main(String[] args) {

		// Create instance of QuarterlyReport to access class methods and nested classes
		// (i..e Department)
		Quarterly_Report n = new Quarterly_Report();

		// Instantiate departments using data from database
		Department[] lsDepartments = n.generateDepartments();

		System.out.println("\n\n***** QUARTERLY REPORT *****\n");

		System.out.println("\n\n***** METHOD 1 : TOTAL SALES OF EACH DEPARTMENT PER QUARTER *****\n");

		n.showQuarterSalesAllDepartments(lsDepartments);

		System.out.println(
				"\n\n***** Method 2 : NEW SALES TARGETS FOR ALL DEPARTMENTS BASED ON LAST QUARTER'S RESULTS *****\n");

		n.newSalesTarget(lsDepartments);

		System.out.println("\n\n***** Method 3 : BEST AND WORST PERFORMING DEPARTMENTS PER QUARTER *****");

		n.bestAndWorstPerQuarter(lsDepartments);

	}

}
