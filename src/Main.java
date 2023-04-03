import java.sql.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        jdbcImport obj = new jdbcImport();
        Connection conn = jdbcImport.dataSource().getConnection();
        System.out.println("1. select record");
        System.out.println("2. select by id");
        System.out.println("3. select by select by name");
        System.out.println("4. Insert a record");
        System.out.println("5. update a record by id");
        System.out.println("6. Delete record by id");
        switch (input.nextInt()){
            case 1-> {
                // select record
                try {
                    // create sql statement:

                    String selectSQL = "select * from codelangtable";
                    PreparedStatement statement = conn.prepareStatement(selectSQL);
                    // execute SQL Statement:
                    ResultSet Resultset = statement.executeQuery();
                    // process result with result set:
                    List<jdbcImport> topic = new ArrayList<>();
                    while(Resultset.next()){
                        int id = Resultset.getInt("id");
                        String name = Resultset.getString("name");
                        String description = Resultset.getString("desciption");
                        boolean status = Resultset.getBoolean("status");
                        topic.add(new jdbcImport(id, name, description, status));
                    }
                    topic.forEach(System.out::println);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 2->{
                try {
                    String selectid = "select * from codelangtable where id = 1";
                    PreparedStatement select_by_id = conn.prepareStatement(selectid);
                    ResultSet resultset = select_by_id.executeQuery();
                    while (resultset.next()){
                        System.out.println("\nSelected by id: ");
                        String name = resultset.getString("name");
                        String description = resultset.getString("desciption");
                        boolean status = resultset.getBoolean("status");
                        System.out.println("Name: " + name);
                        System.out.println("Description: " + description);
                        System.out.println("Status: " + status);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            case 3 ->{
                try {
                    String selectname = "select * from codelangtable where name = 'c++'";
                    PreparedStatement nameSearch = conn.prepareStatement(selectname);
                    ResultSet result = nameSearch.executeQuery();
                    while (result.next()){
                        System.out.println("\nSelected by name: ");
                        String name = result.getString("name");
                        String description = result.getString("desciption");
                        boolean status = result.getBoolean("status");
                        int id = result.getInt("id");
                        System.out.println("ID: " + id);
                        System.out.println("Name: " + name);
                        System.out.println("Description: " + description);
                        System.out.println("Status: " + status);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            case 4-> {
                // insert record:
                System.out.println("=============== insert the record =====================");
                System.out.println("Name: "); obj.setName(input.nextLine()); input.next();
                System.out.println("Desciption: "); obj.setDescription(input.nextLine());input.next();
                System.out.println("Status: "); obj.setStatus(Boolean.parseBoolean(input.nextLine())); input.next();
                try {
                    String insertsql = "insert into jdbcImport(name, description, status) values (?, ? , ? , ?)";
                    PreparedStatement statement = conn.prepareStatement(insertsql);
                    statement.setString(1, obj.getName());
                    statement.setString(2, obj.getDescription());
                    statement.setBoolean(3, obj.isStatus());
                    // execute:
                    statement.executeUpdate();
                    System.out.println("Data inserted!");
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            case 5->{
                // update record by id:
                try {
                    System.out.println("Enter id: ");
                    int id = input.nextInt();
                    System.out.println("Enter name: "); input.nextLine();
                    String name = input.nextLine();
                    System.out.println("Desciption: ");
                    String desc = input.nextLine();
                    String updatesql = "update codelangtable set name = ?, desciption = ? where id = ?";
                    PreparedStatement update = conn.prepareStatement(updatesql);
                    update.setString(1, name);
                    update.setString(2, desc);
                    update.setInt(3, id);
                    update.executeUpdate();
                    System.out.println("Updated!");
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            case 6 -> {
                System.out.println("Enter id: ");
                int id = input.nextInt();
                try {
                    String deletesql = "delete from codelangtable where id = ?";
                    PreparedStatement delete = conn.prepareStatement(deletesql);
                    delete.setInt(1, id);
                    delete.executeUpdate();
                    System.out.println("Delete success!");
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}