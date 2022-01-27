package Models;

public class Company {
    private String companyName;
    private  String numberOfStaff;
    private String website;


    public Company(String companyName, String numberOfStaff, String website) {
        this.companyName = companyName;
        this.numberOfStaff = numberOfStaff;
        this.website = website;
    }
    public void update(){
        System.out.println("A company were added :)");
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
