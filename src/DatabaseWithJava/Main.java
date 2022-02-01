package DatabaseWithJava;

import java.sql.SQLException;
import java.io.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static DatabaseWithJava.FileEdit.folderToList;

//import static JavaConect2SQL.FileEdit.*;

public class Main {
    public static void main(String[] args) {


        FileEdit fileEdit = new FileEdit();


//        Scanner input = new Scanner(System.in);
//        char press = 0;
//
//        System.out.println("Welcome, -if you want to show all file in the folder press 'f'\n " +
//                "        -If you want to copy the last files in the Database press 'l'");
//
//        press = input.next().charAt(0);
//
//        if (press == 'f') {
//            FileEdit.folderList("F:\\Provadis-Hochschule\\1-Semester\\WAB\\Test\\");
//            System.out.println("\nDo you want to continue (y/n)?");
//            press = input.next().charAt(0);
//            if (press == 'n') {
//                System.out.println("Ok, no problem!");
//            } else if (press == 'y'){
//                System.out.println("The program will copy the last files version in the database");
//                fileEdit.sqlConnection("jdbc:mysql://localhost:3306/school","root","root");
//
//            } else {
//                while (press != 'n' || press != 'y') {
//                    System.out.println("\nDo you want to continue (y/n)?");
//                    press = input.next().charAt(0);
//                    if (press == 'n') {
//                        System.out.println("Ok, no problem!");
//                        break;
//                    } else if (press == 'y'){
//                        System.out.println("do you want to copy the last file version in the database (y/n)?");
//                    }
//                }
//            }
//        } else if (press == 'l') {
//            System.out.println("Welcome, you pressed 'l'");
//        } else {
//            while (press != 'f' || press != 'l') {
//                System.out.println("you dont pressed 'f' or 'l'");
//                System.out.println("Please chose, -if you want to show all file in the folder press 'f'\n " +
//                        "        -If you want to copy the last files in the Database press 'l'");
//                press = input.next().charAt(0);
//                if (press == 'f') {
//                    FileEdit.folderList("F:\\Provadis-Hochschule\\1-Semester\\WAB\\Test\\");
//                    break;
//                } else if (press == 'l') {
//                    System.out.println("Welcome, you pressed 'l'");
//                    break;
//                }
//            }
//        }



//        VersionChecker a = new VersionChecker("migration_3.0.10.sql");
//        VersionChecker b = new VersionChecker("3.0.11");
//        System.out.println(b.compareTo(a));
//        System.out.println(a.equals(b));


//        fileEdit.fileReader("F:\\Provadis-Hochschule\\1-Semester\\WAB\\Test\\migration_3.0.10.sql");
////	    fileEdit.fileReader("T:\\Test\\migration_3.0.11.sql");

//        fileEdit.fileInformation("F:\\Provadis-Hochschule\\1-Semester\\WAB\\Test\\migration_3.0.10.sql");

//        fileEdit.sqlConnection("jdbc:mysql://localhost:3306/school","root","root");

//        fileEdit.fileReader("F:\\Provadis-Hochschule\\1-Semester\\WAB\\Test\\migration_3.0.10.sql");

        /*		fileEdit.folderToList("C:\\Users\\khale\\Desktop\\Test"," ");*/
/*		List<File> allVersions = folderToList("F:\\Provadis-Hochschule\\1-Semester\\WAB\\Test\\", "MigV 3.0.11");
        allVersions.forEach(file ->System.out.println(file.getName()) );*/
//        		sqlConnection("","","");
/*        allVersions.forEach(file ->fileWriter(String.valueOf(file)));*/

        fileEdit.sqlConnection("jdbc:postgresql://localhost:5432/crdmain","postgres","m9<FW~,$V0E!t&eWNHMV");

       /* try {
//            Class.forName("com.mysql.jdbc.Driver"); //Use jdbc-Driver to make the connection
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crdmain", "postgres", "m9<FW~,$V0E!t&eWNHMV");
            System.out.println("The connection is passed");
            con.close();
        }
        catch (Exception e) { //If the connection failed, the program show this message
            System.out.println("The connection with the database is failed");
            e.printStackTrace();
        }*/



    }
}



