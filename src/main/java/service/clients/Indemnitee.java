package service.clients;

import java.time.LocalDate;
import java.util.Objects;

public class Indemnitee extends AbstractClient {
    private LocalDate dateOfBirth;
    private double cost;

    public Indemnitee(String fullname, LocalDate dateOfBirth,double cost, long id) {

        super(fullname, id);
        this.cost=cost;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Indemnitee that = (Indemnitee) o;
        return Double.compare(that.cost, cost) == 0 &&
                Objects.equals(dateOfBirth, that.dateOfBirth);
    }

    @Override
    public String toString() {
        return "Indemnitee{" +  System.lineSeparator()+
                "dateOfBirth=" + dateOfBirth +  System.lineSeparator()+
                ", cost=" + cost +  System.lineSeparator()+
                ", fullName='" + fullName + '\'' +  System.lineSeparator()+
                ", id=" + id +  System.lineSeparator()+
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(dateOfBirth, cost);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }

    @Override
    public void setFullName(String fullName) {
        super.setFullName(fullName);
    }

    @Override
    public String getFullName() {
        return super.getFullName();
    }

    @Override
    public long getId() {
        return super.getId();
    }

    public Indemnitee() {
    }

    public String fullNameToInitialsParser() {
        String[] parts = fullName.split(" ");
        StringBuilder result = new StringBuilder(parts[0]).append(" ");
        for (int i = 1; i < parts.length; i++) {
            result.append(parts[i].charAt(0)).append(". ");
        }
       return result.toString();
    }


}
