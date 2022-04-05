package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import conexaojdbc.SingleConnection;
import model.Telefone;
import model.UserPosjava;

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(UserPosjava userposjava) {
		try {
			String sql = "insert into userposjava ( nome, email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.execute();
			connection.commit(); // salva no banco

		} catch (Exception e) {

			try {
				connection.rollback();// reverte operação
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		}

	}
	
	public void salvarTelefone(Telefone telefone) {
		
		
		
		try {
			String sql = "INSERT INTO public.telefoneuser( numero, tipo, usuariopessoa) VALUES ( ?, ?, ?);";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());
			insert.execute();
			connection.commit();
			
		}catch (SQLException e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
			}catch (SQLException e1) {
				e1.printStackTrace();
				
			}
		}
	}
	

	public List<UserPosjava> Listar() throws Exception {

		List<UserPosjava> list = new ArrayList<UserPosjava>();

		String sql = "select * from userposjava";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			UserPosjava userposjava = new UserPosjava();
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));
			
			list.add(userposjava);
			
			
		}
		
		return list;
	}
	
	public UserPosjava buscarUmObjeto(Long id) throws Exception {

		UserPosjava userposjava = new UserPosjava();

		String sql = "select * from userposjava where id ="+id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));
		}
			
					
		return userposjava;
	}
	
	public void atualizar (UserPosjava userposjava) throws SQLException  {
		
		try {
		
			String sql = "update userposjava set nome = ?  where id= "+userposjava.getId();
		
			PreparedStatement statement  = connection.prepareStatement(sql);
		
			statement.setString(1,userposjava.getNome());
			
			statement.execute();
			connection.commit();
		
		}catch(Exception e) {
			
			connection.rollback();
			
			e.printStackTrace();
		}
		}
	
	public void deletar(Long id) {
		try {
			
			String sql = "delete from userposjava where id = "+id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();			
		}catch (Exception e1) {
			try {
			
		
			}catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
		
	}}
	
	


