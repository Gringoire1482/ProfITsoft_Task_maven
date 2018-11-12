package entity.clients;

import java.util.Objects;

public class Client extends AbstractClient {
    private ClientType type;
    private String address;

    public Client(String fullname, ClientType type, String address,long id) {
        super(fullname,id);
        this.type = type;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return type == client.type &&
                Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, address);
    }

    public ClientType getType() {

        return type;
    }

    @Override
    public String toString() {
        return "Client{" + System.lineSeparator()+
                "type=" + type +  System.lineSeparator()+
                "address='" + address + '\'' +  System.lineSeparator()+
                "fullName='" + fullName + '\'' + System.lineSeparator()+
                '}';
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
