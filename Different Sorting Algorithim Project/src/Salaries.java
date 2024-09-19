public class Salaries {
    private int workYear;
    private String experienceLevel;
    private String jobTitle;
    private int salaryInUSD;
    private String companyLocation;
    private String companySize;

    // Default constructor
    public Salaries() {
    }

    // Parameterized constructor (only include important information)
    public Salaries(int workYear, String experienceLevel, String jobTitle,
                        int salaryInUSD, String companyLocation, String companySize) {
        this.workYear = workYear;
        this.experienceLevel = experienceLevel;
        this.jobTitle = jobTitle;
        this.salaryInUSD = salaryInUSD;
        this.companyLocation = companyLocation;
        this.companySize = companySize;
    }

    // Parameterized constructor (include all columns)

    // Copy constructor for deep copy
    public Salaries(Salaries other) {
        this.workYear = other.workYear;
        this.experienceLevel = new String(other.experienceLevel);
        this.jobTitle = new String(other.jobTitle);
        this.salaryInUSD = other.salaryInUSD;
        this.companyLocation = new String(other.companyLocation);
        this.companySize = new String(other.companySize);
    }

    public int getSalaryInUSD() {
        return salaryInUSD;
    }

    // toString method
    // only include information that I found important
    @Override
    public String toString() {
        return "SalaryRecord{" +
                "workYear=" + workYear +
                ", experienceLevel='" + experienceLevel + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", salaryInUSD=" + salaryInUSD +
                ", companyLocation='" + companyLocation + '\'' +
                ", companySize='" + companySize + '\'' +
                '}';
    }
}
