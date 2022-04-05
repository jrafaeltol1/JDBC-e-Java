package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import DAO.UserPosDAO;
import conexaojdbc.SingleConnection;
import junit.framework.TestCase;
import model.Telefone;
import model.UserPosjava;

public class TesteBancoJdbc {

	@Test
	public void initBanco() {
		UserPosDAO userPosDAO = new UserPosDAO();
		UserPosjava userPosJava = new UserPosjava();

		userPosJava.setNome("Ricardo");
		userPosJava.setEmail("tesdeDeEmail@email.com");

		userPosDAO.salvar(userPosJava);

	}

	@Test
	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<UserPosjava> list = dao.Listar();

			for (UserPosjava userPosjava : list) {
				System.out.println(userPosjava);
				System.out.println("--------------------------------");

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Test
	public void initBuscar() throws Exception {
		UserPosDAO dao = new UserPosDAO();
		UserPosjava userposjava = dao.buscarUmObjeto(2L);

		System.out.println(userposjava);
	}

	@Test
	public void initAtualizar() {
		try {

			UserPosDAO dao = new UserPosDAO();

			UserPosjava objetoBanco = dao.buscarUmObjeto(2L);

			objetoBanco.setNome("Nome atualizado com metodo atualizar.");

			dao.atualizar(objetoBanco);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//
	@Test
	public void testeInsertTelefone() {
		Telefone telefone = new Telefone();
		telefone.setNumero("21-9888-4545");
		telefone.setTipo("celular");
		telefone.setUsuario(1L);
		
		UserPosDAO dao = new UserPosDAO();
		dao.salvarTelefone(telefone);
	}
	
	
	@Test
	public void initDeletar() {
		try {
			
			UserPosDAO dao = new UserPosDAO();
			dao.deletar(3L);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
