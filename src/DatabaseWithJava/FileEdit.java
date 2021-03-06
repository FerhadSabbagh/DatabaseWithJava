package DatabaseWithJava;

import javax.sql.rowset.serial.SQLInputImpl;
import java.io.*;

import java.sql.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.*;

import static java.lang.Integer.parseInt;

public class FileEdit {


    //Die Methode liest die Datei X ein
    public static String fileReader(String fileLocation) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLocation));
            String s;
//    while ((s = br.readLine()) != null) {
//      System.out.println(s);
//
//                boolean Comment = s.startsWith("COMMENT");
//                boolean alterTable = s.startsWith("ALTER TABLE");
//                boolean update = s.startsWith("UPDATE");
//
//                if(Comment == true) {
//                System.out.println(s.replaceAll("[^0-9]", ""));
//      //            bw.newLine();
//                }
//                if(alterTable == true) {
//                System.out.println(s);
//      //            bw.newLine();
//      //            bw.flush();
//                }
//                else if(update == true) {
//                System.out.println(s);
//      //            bw.newLine();
//                }
//    }
            br.close();
        }
        catch (IOException e) {
            System.out.println("The file does not exist.");
            e.printStackTrace();
        }
        return null;
    }

    //Die Methode liest die Datei X ein
    public static void fileWriter(String fileLocation){
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileLocation));
            BufferedWriter bw = new BufferedWriter(new FileWriter("F:\\Provadis-Hochschule\\1-Semester\\WAB\\Test\\migration_End.sql"));
            String s;
            while ((s = br.readLine()) != null) {
                bw.write(s);
                bw.flush();
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException e) {
            System.out.println("The file does not exist.");
            e.printStackTrace();
        }
    }


    //Die Methode gibt Information ??ber die Datei X ein
    public void fileInformation(String fileLocation) {
        File fileObj = new File(fileLocation);

        if (fileObj.exists()) {
            System.out.println("File name: " + fileObj.getName()); //Datei Name
            System.out.println("Absolute path: " + fileObj.getAbsolutePath()); //Datei Name
            System.out.println("Writeable: " + fileObj.canWrite()); //Man kann in dieser Datei schreiben
            System.out.println("Readable: " + fileObj.canRead()); //Man kann in dieser Datei einlesen
            System.out.println("File size in bytes: " + fileObj.length()); //Datei Gr????e
        } else {
            // Wenn die Datei nicht gefunden, zeigt diesem Text
            System.out.println("The file does not exist.");
        }
    }

    //Die Methode guckt ab die Ordner Dateien hat und zeigt die Dateien an
    public void folderListVersion(String fileLocation) {
        try {
            File Test = new File(fileLocation);
            File[] paths = Test.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name)
                {
                    return name.startsWith("migration_") && name.endsWith(".sql");
                }
            });
            for (File file : paths) {
                System.out.println(file.getName().replaceAll("[^0-9]", ""));
            }
        }
        // Falls der Ordner Leer ist, zeigt diesem Text
        catch (Exception e) {
            System.out.println("The folder is empty");
        }
    }

    public static void folderList(String fileLocation) {
        try {
            File test = new File(fileLocation);

            //Alle Dateien im Ordner werden gezeigt und mit einem Bindestrich davor
            //            System.out.println("\n------------------------SQL files------------------------");

            File[] paths = test.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name)
                {
                    return name.startsWith("migration_") && name.endsWith(".sql");
                }
            });
            for (File file : paths) {

                System.out.println(file.getName());
            }
        }

        // Falls der Ordner Leer ist, zeigt diesem Text
        catch (Exception e) {
            System.out.println("The folder is empty");
        }
    }

    public static List<File> folderToList(String fileLocation ,String lastVersion) {
        List<File> files = new LinkedList<>();
        try {
            File test = new File(fileLocation);
            File[] paths = test.listFiles((dir, name) -> name.startsWith("migration_") && name.endsWith(".sql"));
            assert paths != null;
            files = new LinkedList<>(Arrays.asList(paths));
            files.removeIf(x->x.getName().replaceAll("[^0-9]", "").compareTo(lastVersion.replaceAll("[^0-9]", ""))<=0);
            files.sort(Comparator.comparingInt(x -> parseInt(x.getName().replaceAll("[^0-9]", ""))));
        }
        catch (Exception e) {
            System.out.println("The folder is empty");
        }
        return files;
    }


    //The Method connect with the database and give the last comment in crdmain as number
    public static void sqlConnection(String url, String userName, String password) /*throws SQLException*/ {
        Connection con;
        try {
/*          Class.forName("com.mysql.CJ.jdbc.Driver"); //Use jdbc-Driver to make the connection
          Class.forName("com.mysql.jdbc.Driver");
            Class.forName("org.postgresql.odbc.Driver");
            Class.forName("org.postgresql.Driver");*/
            con = DriverManager.getConnection(url, userName, password);
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT pg_catalog.shobj_description(d.oid, 'pg_database') AS \"Description\" FROM   pg_catalog.pg_database d WHERE  datname = 'crdmain';");
            List<File> allVersions = null;
            while (rs.next()) {
                System.out.println(rs.getString(1));
                allVersions = folderToList("F:\\Provadis-Hochschule\\1-Semester\\WAB\\Test\\Neuer Ordner (2)", rs.getString(1));

            }
            allVersions.forEach(file -> System.out.println(file.getName()));
            allVersions.forEach(file -> {
                try{
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String s;
                    String SQLCmd = "";
                    while ((s = br.readLine()) != null) {
                        System.out.println(s);
                        SQLCmd += s + "\n";
                    }
//                    ResultSet rsds = stmt.executeQuery(SQLCmd);
//                    PreparedStatement esdqs = con.prepareStatement(SQLCmd);
                    stmt.execute(SQLCmd);

                } catch (SQLException throwables) {
                    System.out.println("fileReader Filed");
                    throwables.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
/*                List<File> allVersions = folderToList("F:\\Provadis-Hochschule\\1-Semester\\WAB\\Test\\",commentName);
               allVersions.forEach(file ->System.out.println(file.getName()));
            allVersions.forEach(file ->fileReader(String.valueOf(file)));*/

        System.out.println("The connection is passed");
        double startTime = System.nanoTime();
        double endTime = System.nanoTime();
        System.out.println("Execution Time in Seconds: " + ((endTime - startTime) / 1000000000));
        con.close();
    } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) { //If the connection failed, the program show this message
            System.out.println("The connection with the database is failed");
            e.printStackTrace();
        }
    }

}
