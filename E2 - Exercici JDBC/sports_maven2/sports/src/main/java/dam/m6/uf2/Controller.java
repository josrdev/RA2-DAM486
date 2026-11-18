package dam.m6.uf2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Controller {
	Connection conn;

	public static void main(String[] args) {
		System.out.println("Current directory (aquí heu de posar el XML): " + System.getProperty("user.dir"));
		Controller controller = new Controller();
		controller.init();
	}

	private void init() {
		this.conn = ConnectionManager.getConnection("database.xml");
		if (this.conn == null) {
			System.err.println("No s'ha pogut establir la connexio amb la base de dades.");
			return;
		}

		MainView mainView = new MainView();
		EsportDAO esportDAO = new EsportDAO(conn);
		AtletaDAO atletaDAO = new AtletaDAO(conn);

		int option;
		do {
			option = mainView.mainMenu();
			switch (option) {
				case 1:
					Esport esport = mainView.esportForm();
					if (esport != null) {
						esportDAO.add(esport);
					}
					break;

				case 2:
					List<Esport> esports = esportDAO.getAll();
					Atleta atleta = mainView.atletaForm(esports);
					if (atleta != null) {
						atletaDAO.add(atleta);
					}
					break;

				case 3:
					List<Esport> esportsForList = esportDAO.getAll();
					mainView.llistaEsports(esportsForList);
					int esportId = mainView.demanaEsport(esportsForList);
					if (esportId >= 0) {
						mainView.llistaAtletes(atletaDAO.getBySport(esportId));
					}
					break;

				case 0:
					System.out.println("Sortint...");
					break;

				default:
					System.out.println("Opcio no valida.");
					break;
			}
		} while (option != 0);

		try {
			conn.close();
		} catch (SQLException e) {
			System.err.println("Error tancant la connexio: " + e.getMessage());
		}
	}
}
