package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {
    private static String host;
    private static String port;
    private static String user;
    private static String pass;
    private static String banco;

    public static Connection ConexaoDB() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String URL = "jdbc:mysql://" + getHost() + ":" + getPort() + "/" + getBanco();

        Connection conexao = null;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(URL, getUser(), getPass());
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver não localizado: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Deu erro na conexão com a fonte de dados: " + e.getMessage());
        }
        return conexao;
    }

    public static String getHost() {
        return host;
    }

    public Conexao setHost(String host) {
        Conexao.host = host;
        return this;
    }

    public static String getPort() {
        return port;
    }

    public Conexao setPort(String port) {
        Conexao.port = port;
        return this;
    }

    public static String getUser() {
        return user;
    }

    public Conexao setUser(String user) {
        Conexao.user = user;
        return this;
    }

    public static String getPass() {
        return pass;
    }

    public Conexao setPass(String pass) {
        Conexao.pass = pass;
        return this;
    }

    public static String getBanco() {
        return banco;
    }

    public Conexao setBanco(String banco) {
        Conexao.banco = banco;
        return this;
    }
}
