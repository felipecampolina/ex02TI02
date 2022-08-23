package app;

import java.util.List;
import java.util.Scanner;

import dao.DAO;
import dao.UsuarioDAO;
import model.Usuario;

public class Aplicacao {
	
	public static void apareceMenu() {
		System.out.println("1 - Inserir");
		System.out.println("2 - Listar");
		System.out.println("3 - Atualizar");
		System.out.println("4 - Excluir");
		System.out.println("5 - Sair");
	}
	
	public static void main(String[] args) throws Exception {
		int codigo = 11;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Scanner scanner = new Scanner(System.in);
		apareceMenu();
		int opcao = 0;
		while(opcao!=5) {
			 opcao = scanner.nextInt();
			switch(opcao) {
		case 1 :
			System.out.println("Login:");
			String login;
			login = scanner.next();
			System.out.println("Senha:");
			String senha;
			senha = scanner.next();
			System.out.println("Sexo[M/F]: ");
			String sexo1 = scanner.next();
			char sexo = sexo1.charAt(0);
			codigo++;
			Usuario usuario = new Usuario(codigo, login, senha,sexo);
			if(usuarioDAO.insert(usuario) == true) {
				System.out.println("Inserção com sucesso -> " + usuario.toString());
			}
			break;
		case 2:
			System.out.println("\n\n==== Mostrar usuários ordenados por código === ");
			List<Usuario> usuarios = usuarioDAO.getOrderByCodigo();
			for (Usuario u: usuarios) {
				System.out.println(u.toString());
			}
			break;
		case 3:
			System.out.println("Código do usuario a ser alterado:");
			int codigo1 = scanner.nextInt();
			System.out.println("\n\n==== Atualizar dados (código (" + codigo1 + ") === ");
			System.out.println("Login:");
			login = scanner.next();
			System.out.println("Senha:");
			senha = scanner.next();
			System.out.println("Sexo[M/F]: ");
			sexo1 = scanner.next();
			sexo = sexo1.charAt(0);
			usuario = new Usuario(codigo1, login, senha,sexo);

			for(int i = 0 ; i< codigo ; i++) {
				if(i == usuario.getCodigo()) {
			usuario.setLogin(login);
			usuario.setSexo(sexo);
			usuario.setSenha(DAO.toMD5(senha));
			usuarioDAO.update(usuario);
			i++;
				}
			}
			break;
		case 4 :
			System.out.println("Código do usuario a ser deletado:");
			codigo1 = scanner.nextInt();
			System.out.println("\n\n==== Excluir usuário (código " + codigo1 + ") === ");
			usuarioDAO.delete(codigo1);
			break;
		case 5:
			System.out.println("Tchau!");
			break;
			
		default:
			System.out.println("Opção Invalida");
		}
			if(opcao!=5)apareceMenu();
		}
	}
}
