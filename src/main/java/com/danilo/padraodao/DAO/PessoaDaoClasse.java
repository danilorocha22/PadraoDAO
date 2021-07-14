package com.danilo.padraodao.DAO;


import com.danilo.padraodao.Models.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo Rocha
 */
public class PessoaDaoClasse implements PessoaDaoInterface{

    Connection con;
    public PessoaDaoClasse() throws ErroBancoException
    {
        FabricaConexao fabrica=new FabricaConexao();
        con=fabrica.pegaConexao();
    }
    
    @Override
    public void criaPessoa(Pessoa p) throws SQLException {
        PreparedStatement ps=con.prepareStatement("insert into Pessoa values(null,?,?)");
        ps.setString(1,p.getNome());
        ps.setInt(2, p.getIdade());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public List<Pessoa> pegaPessoas() throws SQLException {
        ArrayList<Pessoa> grupo=new ArrayList<>();
        PreparedStatement ps=con.prepareStatement("select * from Pessoa");
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            Pessoa p=new Pessoa();
            p.setCodigo(rs.getInt("codigo"));
            p.setIdade(rs.getInt("idade"));
            p.setNome(rs.getString("nome"));
            grupo.add(p);
        }
        return grupo;
    }
    public void sair() throws ErroBancoException {
        try {
            con.close();
        } catch (SQLException ex) {
            throw new ErroBancoException("Erro ao sair",ex);
        }
    }

    @Override
    public Pessoa pegaPessoa(int codigo) throws SQLException {
        PreparedStatement ps=con.prepareStatement("select * from Pessoa where codigo=?");
        ps.setInt(1, codigo);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            Pessoa p=new Pessoa();
            p.setCodigo(rs.getInt("codigo"));
            p.setIdade(rs.getInt("idade"));
            p.setNome(rs.getString("nome"));
            return p;
        }
        return null;
    }

    @Override
    public void deletarPessoa(int codigo) throws SQLException {
        PreparedStatement ps=con.prepareStatement("delete from Pessoa where codigo=?");
        ps.setInt(1, codigo);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void deletarPessoa(Pessoa p) throws SQLException {
        deletarPessoa(p.getCodigo());
    }

    @Override
    public void editarPessoa(Pessoa p) throws SQLException {
        PreparedStatement ps=con.prepareStatement("update Pessoa set nome=?, idade=? where Pessoa.codigo=?");
        ps.setString(1, p.getNome());
        ps.setInt(2, p.getIdade());
        ps.setInt(3, p.getCodigo());
        ps.executeUpdate();
    }
   
    
    public static void main(String[] args) {
        try {
            PessoaDaoClasse dao=new PessoaDaoClasse();
            System.out.println("conectado com sucesso");
            Pessoa p=new Pessoa();
            //Atualiza
            p.setCodigo(2);
            p.setNome("Leila");
            p.setIdade(37);
            dao.editarPessoa(p);
            System.out.println("Atualizado com sucesso");
            
            List<Pessoa> pessoas=dao.pegaPessoas();
            
            for(int i=0;i<pessoas.size();i++) {
                System.out.println(pessoas.get(i));
            }
            System.out.print("\n\n\nPessoa de código: 2 ");
            System.out.print(dao.pegaPessoa(2));
            
            dao.sair();
            System.out.println(" Saiu com sucesso");
        } catch (ErroBancoException |SQLException ex) {
            ex.printStackTrace();
        }
    }

    
}
