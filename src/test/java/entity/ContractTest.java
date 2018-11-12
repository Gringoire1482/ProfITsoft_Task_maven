package entity;

import entity.clients.Client;
import entity.clients.ClientType;
import entity.clients.Indemnitee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class ContractTest {
    Contract contract;
    Indemnitee first;
    Indemnitee second;
    List<Indemnitee> indemnitees;

    @Before
    public void setUp() throws Exception {
        Client a = new Client("Ivanov Ivan Ivanovich", ClientType.PRIVATE, "Gagarina 10",1);
       first = new Indemnitee("Ivanov Oleg Ivanovich", LocalDate.of(1980, Month.AUGUST, 12),10);
       second = new Indemnitee("Ivanova Anna Ivanovna", LocalDate.of(1998, Month.AUGUST, 16),20);
        first.setCost(1000);
        second.setCost(500);
     indemnitees = new ArrayList<>();
        indemnitees.add(second);
        indemnitees.add(first);
        LocalDate signDate = LocalDate.of(2018, Month.OCTOBER, 16);
        LocalDate startDate = LocalDate.of(2018, Month.NOVEMBER, 16);
        LocalDate expireDate = LocalDate.of(2019, Month.NOVEMBER, 16);
       contract = new Contract(1, signDate, startDate, expireDate, a);
        contract.setIndemnitees(indemnitees);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getById() {
        assertEquals(first,contract.getById(10));
    }

    @Test
    public void sortByNameOutput() {
        contract.sortByNameOutput();
      assertEquals(Arrays.asList(first,second),contract.getIndemnitees());
    }

    @Test
    public void sortByDateOfBirth() {
        contract.sortByDateOfBirth();
        assertEquals(Arrays.asList(first,second),contract.getIndemnitees());
    }

    @Test
    public void getFullCost() {
        assertEquals(1500, contract.getFullCost(),0);
    }
}