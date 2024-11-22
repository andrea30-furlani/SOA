package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Conexao conexao;
    private Connection connection;
      // conecta o banco
    private String driver = "com.mysql.jdbc.Driver"; 
    private String url = "jdbc:mysql://localhost/lista";
    private String usuario="root";
    private String senha = "";

    public Connection conectar(){
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, usuario, senha);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;

        }

    }

    public static synchronized Conexao getConexao() {
        if(conexao == null) {
           conexao = new Conexao();
        }
        return conexao;
    }

    
}
