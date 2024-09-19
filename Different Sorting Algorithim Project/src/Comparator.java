public class Comparator {

    public static int compare(Salaries r1, Salaries r2) {
        return Integer.compare(r1.getSalaryInUSD(), r2.getSalaryInUSD());
    }

}
