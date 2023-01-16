import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main{
public static void main(String[] args) throws IOException {
program();
}

//recursion endless loop of the program
public static void program() throws IOException{
    selection(menu());
}

//menu function that returns a number 
public static int menu(){
    Scanner cin = new Scanner(System.in);
    int num =0;
    System.out.println("1. Get instructor information");
    System.out.println("2. Insert a new instructor");
    System.out.println("3. Insert a new department");
    System.out.println("4. Get department information");
    System.out.println("5. Exit");
    System.out.print("input: ");
    num = cin.nextInt();
    if(num<=0||num>=6){
        return menu();
    }
return num;
}

public static boolean checkDepartment(String n) throws IOException{
    Scanner cin = new Scanner(System.in);
    java.io.File file = new java.io.File("/Users/rahulramjeawon/Desktop/java final project/department (1).txt");
    cin = new Scanner(file);
    boolean found = false;
    while (cin.hasNextLine()) {
      String line = cin.nextLine();
      String[] fields = line.split(",");
      String depart = fields[0];
       if(depart.equals(n)){
           found=true; 
           break;
      }
    }
    cin.close();
    return found;
}

public static boolean checkID(String n) throws IOException{
    Scanner cin = new Scanner(System.in);
    java.io.File file = new java.io.File("/Users/rahulramjeawon/Desktop/java final project/instructor.txt");
    cin = new Scanner(file);
    boolean found = false;
    while (cin.hasNextLine()) {
      String line = cin.nextLine();
      String[] fields = line.split(",");
      String depart = fields[0];
       if(depart.equals(n)){
           found=true; 
           break;
      }
    }
    cin.close();
    return found;
}

public static void getAllInstuctorFromDepartment(String depratment)throws IOException{
    Scanner cin = new Scanner(System.in);
    java.io.File file = new java.io.File("/Users/rahulramjeawon/Desktop/java final project/instructor.txt");
    cin = new Scanner(file);
    System.out.println("Intructor:");
    while (cin.hasNextLine()){
    String line = cin.nextLine();
    String[] fields = line.split(",");
       if(fields[2].equals(depratment)){
           System.out.println(fields[0]+" "+fields[1]+" "+fields[2]);
      }
    }
    System.out.println("");
    cin.close();
}

public static void getInstuctor() throws IOException{
    Scanner cin = new Scanner(System.in);
    System.out.print("Enter the instructor ID: ");
    String iD = cin.next();
    java.io.File file = new java.io.File("/Users/rahulramjeawon/Desktop/java final project/instructor.txt");
    cin = new Scanner(file);
    boolean found = false;
    String name="";
    String department="";
    while (cin.hasNextLine()) {
      String line = cin.nextLine();
      String[] fields = line.split(",");
      String score = fields[0];
       if(score.equals(iD)){
           found=true; 
           name = fields[1];
           department=fields[2];
           break;
      }
    }
    cin.close();
    if(found){
        System.out.println("\nFound!");
        System.out.println("ID: "+iD);
        System.out.println("Name: "+name);
        System.out.println("Department: "+department);
        System.out.println("");
    }
    else {
        Scanner input = new Scanner(System.in);
        System.out.println("\nNot found!");
        System.out.print("Would you like to search a for another instructor y/n:");
        char userInput=input.next().charAt(0);
        if(userInput=='y'){
            System.out.println("");
            getInstuctor();
        }
        else 
        System.out.println("");
    }
}

public static void addNewInstuctor() throws IOException{
    Scanner cin = new Scanner(System.in); 
    System.out.print("Please enter Instructors ID: ");
    String id = cin.nextLine();
    while(checkID(id)){
        System.out.println("ID already exist");
        System.out.print("Please enter Instructors ID: ");
        id = cin.nextLine();
    }
    System.out.print("Please enter Instructors full name: ");
    String name = cin.nextLine();
    System.out.print("Please enter Instructors Department: ");
    String department = cin.next();
    department=department.toUpperCase();
    while(!checkDepartment(department)){
        System.out.println("Department does not exist\ncannot add instructor");
        System.out.print("would you like to enter a new department y/n: ");
        char userinput = cin.next().charAt(0);
        if (userinput=='y'){
            System.out.print("Please enter Instructors Department: ");
            department = cin.next();
            department=department.toUpperCase();
        }
        else
            break;
    }
    if(checkDepartment(department)){  
    File file = new File("/Users/rahulramjeawon/Desktop/java final project/instructor.txt");    
    FileWriter fw = new FileWriter(file, true); //true indicates append mode
    try (PrintWriter output = new PrintWriter(fw)) {
        output.print(id);
        output.print(","+name);
        output.println(","+department);
        }
    }
        System.out.println("");
}

public static void getDepartmentInformation() throws IOException{
    Scanner cin = new Scanner(System.in);
    System.out.print("Enter the Deparment name: ");
    String department = cin.next();  
    department = department.toUpperCase();
    java.io.File file = new java.io.File("/Users/rahulramjeawon/Desktop/java final project/department (1).txt");
    cin = new Scanner(file);
    boolean found = false;
    String location="";
    String funds="";
    while (cin.hasNextLine()){
      String line = cin.nextLine();
      String[] fields = line.split(",");
      String depart = fields[0];
       if(depart.equals(department)){
           found=true; 
           location = fields[1];
           funds=fields[2];
           break;
      }
    }
    cin.close();
    if(found){
        System.out.println("\nFound!");
        System.out.println("Department: "+department);
        System.out.println("Loction: "+location);
        System.out.println("Funds: "+funds);
        System.out.println("");
        getAllInstuctorFromDepartment(department);
    }
    else {
        Scanner input = new Scanner(System.in);
        System.out.println("\nNot found!");
        System.out.print("Would you like to search for another department y/n:");
        char userInput=input.next().charAt(0);
        if(userInput=='y'){
           getDepartmentInformation();
        }
        else 
        System.out.println("");
    }
}

public static void addNewDepartment() throws IOException{
    Scanner cin = new Scanner(System.in); 
    System.out.print("Please enter Department name: ");
    String name = cin.next();
    while(name.length()!=4){
    System.out.print("Can only be 4 charactor\nPlease enter Department name: ");
    name = cin.next();
    }
    name = name.toUpperCase();
    if(checkDepartment(name)){
        System.out.println("\n"+name+" Department already exist");
    }
    else{
    System.out.print("Please enter Department location: ");
    String location = cin.next();
    location=location.toUpperCase();
    System.out.print("Enter the Budget for the Department: ");
    String funds = cin.next();
    while(Integer.parseInt(funds)<=0||Integer.parseInt(funds)>=100001){
    System.out.print("note buget a number between 1 and 100,000\n Please enter Department Budget: ");
    funds = cin.next();
    }
    File file = new File("/Users/rahulramjeawon/Desktop/java final project/department (1).txt");    
    FileWriter fw = new FileWriter(file, true); //true indicates append mode
    try (PrintWriter output = new PrintWriter(fw)) {
        output.print(name);
        output.print(","+location);
        output.println(","+funds);
    }
}
System.out.println("");
}

public static void selection(int num) throws IOException{
    switch(num){
        case 1:
        getInstuctor() ;
        program();
        break;

        case 2:
        addNewInstuctor();
        program();
        break;

        case 3:
        addNewDepartment();
        program();
        break;

        case 4:
        getDepartmentInformation();
        program();
        break;

        case 5:
        System.out.println("Program ended...");
        break;
    }
  }
}
