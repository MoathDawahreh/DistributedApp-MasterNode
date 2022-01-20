package Models;

public class Companies {
    private String companyName;
    private  String numberOfStaff;
    private String website;


    public Companies(String companyName, String numberOfStaff, String website) {
        this.companyName = companyName;
        this.numberOfStaff = numberOfStaff;
        this.website = website;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setNumberOfStaff(String numberOfStaff) {
        this.numberOfStaff = numberOfStaff;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getNumberOfStaff() {
        return numberOfStaff;
    }

    public String getWebsite() {
        return website;
    }
}