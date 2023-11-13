/* ***************************************************************
* Autor............: Caíque Santos Santana
* Matricula........: 202010643
* Inicio...........: 12/11/2023
* Ultima alteracao.: 12/11/2023
* Nome.............: Classe que cria os clientes do Programa Barbeiro dorminhoco
* Funcao...........: Classe responsável por criar clientes em uma thread separada.
****************************************************************/

package model;

import controller.controle;

public class CriadorDeClientes extends Thread {

	private controle controle;

	/**
	 * Construtor da classe.
	 * 
	 * @param c Controle associado à instância da classe.
	 */
	public CriadorDeClientes(controle c) {
		this.controle = c;
	}

	/**
	 * Metodo: run
	 * Método principal da thread, responsável pela criação contínua de clientes.
	 * 
	 * @return void
	 */
	public void run() {
		while (true) {
			// Aguarda até que a velocidade de geração seja diferente de 800, pausando assim
			// a execução da thread
			if (controle.getVelocidadeGeracao() == 800) {
				while (controle.getVelocidadeGeracao() == 800) {
					try {
						sleep(1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			// Cria um novo cliente e inicia sua execução como uma thread
			Cliente cliente = new Cliente(controle);
			cliente.setDaemon(true);
			cliente.start();

			// Aguarda o tempo definido pela velocidade de geração
			try {
				Thread.sleep(controle.getVelocidadeGeracao());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
