package service.clients;

public abstract class AbstractClient {
    protected String fullName; // ФИО для физ лица или название компании для юр лица
    protected long id;

    public AbstractClient(String fullname,long id) {
        this.fullName = fullname;
        this.id=id;
    }

    public AbstractClient() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {

        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public long getId() {
        return id;
    }
}
