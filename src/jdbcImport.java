import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;
public class jdbcImport {
    private int id;
    private String name;
    private String description;
    private boolean status;
    jdbcImport(){}
    public jdbcImport(int id, String name, String description, boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "jdbcImport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
    public static DataSource dataSource(){
        PGSimpleDataSource datasource = new PGSimpleDataSource();
        datasource.setUser("postgres");
        datasource.setPassword("sqlpostgres");
        datasource.setDatabaseName("codelang");
        return datasource;
    }
}
