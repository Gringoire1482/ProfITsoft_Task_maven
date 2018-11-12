package entity;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import entity.clients.Client;
import entity.clients.ClientType;
import entity.clients.Indemnitee;

public class Contract {
    private int id;
    private LocalDate signed;
    private LocalDate startDate;
    private LocalDate expireDate;
    private Client client;
    private List<Indemnitee> indemnitees;
    private static Comparator<Indemnitee> nameSort;
    private static Comparator<Indemnitee> dateOfBirthSort;

    static {
        nameSort = Comparator.comparing(n -> n.getFullName().toUpperCase());
        dateOfBirthSort = Comparator.comparing(Indemnitee::getDateOfBirth);
    }

    public Contract(int id, LocalDate signed, LocalDate startDate, LocalDate expireDate, Client client) {
        this.id = id;
        this.signed = signed;
        this.startDate = startDate;
        this.expireDate = expireDate;
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return id == contract.id &&
                Objects.equals(signed, contract.signed) &&
                Objects.equals(startDate, contract.startDate) &&
                Objects.equals(expireDate, contract.expireDate) &&
                Objects.equals(client, contract.client) &&
                Objects.equals(indemnitees, contract.indemnitees);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, signed, startDate, expireDate, client, indemnitees);
    }

    @Override
    public String toString() {
        return "Contract{" + System.lineSeparator() +
                "id=" + id + System.lineSeparator() +
                "signed=" + signed + System.lineSeparator() +
                "startDate=" + startDate + System.lineSeparator() +
                "expireDate=" + expireDate + System.lineSeparator() +
                "client=" + client + System.lineSeparator() +
                "indemnitees=" + indemnitees + System.lineSeparator() +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getSigned() {
        return signed;
    }

    public void setSigned(LocalDate signed) {
        this.signed = signed;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Indemnitee> getIndemnitees() {
        return indemnitees;
    }

    public void setIndemnitees(List<Indemnitee> indemnitees) {
        this.indemnitees = indemnitees;
    }

    public double getFullCost() {
        return indemnitees.stream().mapToDouble(Indemnitee::getCost).sum();
    }

    public void sortByNameOutput() {
        indemnitees.sort(nameSort);
        for (Indemnitee a : indemnitees) {
            System.out.println(indemnitees);
        }
    }
    public void  sortByDateOfBirth(){
        indemnitees.sort(dateOfBirthSort);
        indemnitees.forEach(System.out::println);
    }
    public Indemnitee getById(long id){

        for (Indemnitee a : indemnitees) {
          if(a.getId()== id)return a;
        }
        return null;
    }

    public static void main(String[] args) {
        Client a = new Client("Ivanov Ivan Ivanovich", ClientType.PRIVATE, "Gagarina 10",1);
        Indemnitee first = new Indemnitee("Ivanov Oleg Ivanovich", LocalDate.of(1980, Month.AUGUST, 12),10);
        Indemnitee second = new Indemnitee("Ivanova Anna Ivanovna", LocalDate.of(1998, Month.AUGUST, 16),20);
        first.setCost(1000);
        second.setCost(500);
        List<Indemnitee> indemnitees = new ArrayList<>();
        indemnitees.add(first);
        indemnitees.add(second);
        LocalDate signDate = LocalDate.of(2018, Month.OCTOBER, 16);
        LocalDate startDate = LocalDate.of(2018, Month.NOVEMBER, 16);
        LocalDate expireDate = LocalDate.of(2019, Month.NOVEMBER, 16);
        Contract contract = new Contract(1, signDate, startDate, expireDate, a);
        contract.setIndemnitees(indemnitees);
        System.out.println(contract);
        System.out.println(contract.getFullCost());
        first.fullNameToInitialsParser();

    }
}