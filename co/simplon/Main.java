package co.simplon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.jdbc.OracleDriver;

public class Main {

    static String databaseUrl = "jdbc:oracle:thin:simplon/simplon@localhost:1521:xe";

    static String requeteSql = "select ename from EMP where mgr in (select mgr from emp where ename like 'CLARK')";

    public static void main(String[] args) throws Exception {
        DriverManager.registerDriver(new OracleDriver());

        Connection connexion = DriverManager.getConnection(databaseUrl);
        Statement requete = connexion.createStatement();
        ResultSet resultat = requete.executeQuery(requeteSql);
        while (resultat.next()) {
            String nom = resultat.getString("ENAME");
            System.out.println(nom);
        }
        resultat.close();
        requete.close();
        connexion.close();
    }
}
