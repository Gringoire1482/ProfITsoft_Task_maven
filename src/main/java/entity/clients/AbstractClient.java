package entity.clients;

public abstract class AbstractClient {
    protected String fullName; // ФИО для физ лица или название компании для юр лица
    protected long id;

    public AbstractClient(String fullname,long id) {
        this.fullName = fullname;
        this.id=id;
    }

    public String getFullName() {
        return fullName;
    }

    public long getId() {
        return id;
    }
}
