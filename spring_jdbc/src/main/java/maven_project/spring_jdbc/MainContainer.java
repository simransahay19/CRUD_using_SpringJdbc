package maven_project.spring_jdbc;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class MainContainer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your choice: ");
        System.out.println("1. Insert Data");
        System.out.println("2. Delete Data");
        System.out.println("3. Update Data");
        System.out.println("4. Read Data");
        //System.out.println("5. Exit");


        int choice = sc.nextInt();
        sc.nextLine(); 

        switch (choice) {

            case 1:
                String configFile = "maven_project/spring_jdbc/ApplicationConfigurationFile.xml";
                ApplicationContext context = new ClassPathXmlApplicationContext(configFile);
                JdbcTemplate template = (JdbcTemplate)context.getBean("template");

                System.out.println("Enter the value of ID");
                int id_data = sc.nextInt();
                sc.nextLine(); 

                System.out.println("Enter the value of Name");
                String name_data = sc.nextLine();

                System.out.println("Enter the value of course");
                String course_data = sc.nextLine();

                String query = "insert into student values(?,?,?)";
                int response = template.update(query, id_data, name_data, course_data);
                

                System.out.println(response + " rows affected");
                System.out.println("Data Inserted");
               // continue;
               // break;
                table();
                break;
                
                
            case 2: 
            	 String configFile1 = "maven_project/spring_jdbc/ApplicationConfigurationFile.xml";
                 ApplicationContext context1 = new ClassPathXmlApplicationContext(configFile1);
                 JdbcTemplate template1 = (JdbcTemplate)context1.getBean("template1");

            	System.out.println("Enter the value of ID");
                int del_id = sc.nextInt();
                sc.nextLine();
                
                String del_query="delete from student where id=?";
                int del_response= template1.update(del_query,del_id);
                
                System.out.println(del_response + " rows affected");
                System.out.println("Data Deleted");
            	//break;
                table();
                break;
            	
            case 3:
            	System.out.println("Which field you want to update:");
            	System.out.println("\n1: Name\n2: Course");
            	int ch = sc.nextInt();
                
                String configFile2 = "maven_project/spring_jdbc/ApplicationConfigurationFile.xml";
                ApplicationContext context2 = new ClassPathXmlApplicationContext(configFile2);
                JdbcTemplate template2 = (JdbcTemplate)context2.getBean("template2");
                
                switch(ch) {
                case 1:
                	System.out.println("Enter the id number of the name you want to update?");
                	int id_value=sc.nextInt();
                	sc.nextLine(); // Consume the newline character
                	
                	System.out.println("Enter the new name:");
                	String name_value=sc.nextLine();
                	
                	String query2 = "UPDATE student SET name = ? WHERE id = ?";
                	
                	int response3 = template2.update(query2, name_value, id_value);
                	System.out.println(response3 + " rows affected");
                    System.out.println("Data Updated");
                    table();
                    break;
                	
                case 2:
                	System.out.println("Enter the id number of the name you want to update?");
                	int id_value1=sc.nextInt();
                	sc.nextLine(); // Consume the newline character
                	
                	System.out.println("Enter the new course:");
                	String course_value=sc.nextLine();
                	
                	String query3 = "UPDATE student SET course = ? WHERE id = ?";
                	
                	int response4 = template2.update(query3, course_value, id_value1);
                	System.out.println(response4 + " rows affected");
                    System.out.println("Data Updated");
                    table();
                    break;
                }
                
                
            	break;
            case 4:
            	table();
                break;


            	
            default:
            System.out.println("Invalid choice");
        }

        sc.close();
    }
    
    private static void table() {
    	String configFile_Table = "maven_project/spring_jdbc/ApplicationConfigurationFile.xml";
        ApplicationContext context_Table = new ClassPathXmlApplicationContext(configFile_Table);
        JdbcTemplate template_Table = (JdbcTemplate)context_Table.getBean("template_Table");
        
     // Execute a SELECT query to retrieve all rows from the student table
        String selectQuery = "SELECT * FROM student";

        // Execute the query and retrieve the rows
        List<Map<String, Object>> rows = template_Table.queryForList(selectQuery);

        // Print the table header
        System.out.println("+-------------+--------------+------------+");
        System.out.println("|     id     |    name     |    course    |");
        System.out.println("+--------------+-------------+------------+");

        // Print each row
        for (Map<String, Object> row : rows) {
            System.out.format("| %6d | %-8s | %-18s |\n", 
                              row.get("id"), 
                              row.get("name"), 
                              row.get("course"));
        }

        // Print the table footer
        System.out.println("+--------------+-------------+------------+");
//        break;

    	
    }
}

