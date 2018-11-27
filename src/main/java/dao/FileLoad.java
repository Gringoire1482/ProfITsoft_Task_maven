package dao;

import dict.ClientType;
import service.Contract;
import service.clients.Client;
import service.clients.Indemnitee;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileLoad {
    private static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String filepath;

    public FileLoad(String filepath) {
        this.filepath = filepath;
    }

    public  List<Contract> loadFromFile() {
        List<Contract> contractList = new ArrayList<>();
        Contract contract;
        Client client;
        String[] strings;

        try (Scanner scanner = new Scanner(new File(filepath), "cp1251")) {
            while (scanner.hasNext()) {
                strings = scanner.nextLine().split(",");
                LocalDate s = LocalDate.parse(strings[1], formatter);
                LocalDate st = LocalDate.parse(strings[2], formatter);
                LocalDate e = LocalDate.parse(strings[3], formatter);
                client = new Client(strings[4], ClientType.valueOf(strings[5]), strings[6], Long.valueOf(strings[7]));
                contract = new Contract(Integer.valueOf(strings[0]), s, st, e, client);
                String[] indemnitiesInfo = new String[strings.length - 8];
                System.arraycopy(strings, 8, indemnitiesInfo, 0, strings.length - 8);
                contract.setIndemnitees(readIndemnities(indemnitiesInfo));
                contractList.add(contract);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return contractList;
    }
    private static List<Indemnitee> readIndemnities(String[] data){
        List<Indemnitee> result= new ArrayList<>();
        for (int i=0;i<data.length;i+=4){

            result.add(new Indemnitee(data[i],LocalDate.parse(data[i+1],formatter),Double.valueOf(data[i+2]),Long.valueOf(data[i+3])));
        }
        return  result;
    }
}
