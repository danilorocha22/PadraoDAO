package com.danilo.padraodao.DAO;


import com.danilo.padraodao.Models.Pessoa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Danilo Rocha
 */
public interface PessoaDaoInterface {
    public void criaPessoa(Pessoa p) throws SQLException;
    public List<Pessoa> pegaPessoas()throws SQLException;
    public Pessoa pegaPessoa(int codigo) throws SQLException;
    public void deletarPessoa(int codigo) throws SQLException;
    public void deletarPessoa(Pessoa p) throws SQLException;
    public void editarPessoa(Pessoa p) throws SQLException;
    
}
