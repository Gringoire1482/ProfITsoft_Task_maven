package service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import service.clients.Client;

import service.clients.Indemnitee;

public class Contract {
    private long id;
    private LocalDate signed;
    private LocalDate startDate;
    private LocalDate expireDate;
    private Client client;
    private List<Indemnitee> indemnitees;
    private static Comparator<Indemnitee> nameSort;
    private static Comparator<Indemnitee> dateOfBirthSort;
    private static DateTimeFormatter formatter;

    static {
        nameSort = Comparator.comparing(n -> n.getFullName().toUpperCase());
        formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        dateOfBirthSort = Comparator.comparing(Indemnitee::getDateOfBirth);
    }

    public Contract() {
    }

    public Contract(long id, LocalDate signed, LocalDate startDate, LocalDate expireDate, Client client) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    }

    public void sortByDateOfBirth() {
        indemnitees.sort(dateOfBirthSort);

    }

    public Indemnitee getById(long id) {

        for (Indemnitee a : indemnitees) {
            if (a.getId() == id) return a;
        }
        return null;
    }





}