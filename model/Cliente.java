/* ***************************************************************
* Autor............: Caíque Santos Santana
* Matricula........: 202010643
* Inicio...........: 12/11/2023
* Ultima alteracao.: 12/11/2023
* Nome.............: Classe de clientes do Programa Barbeiro dorminhoco
* Funcao...........: Representa uma thread que simula a chegada de clientes à academia.
****************************************************************/

package model;

import static controller.controle.CADEIRAS;
import static controller.controle.aplicadores;
import static controller.controle.clientes;
import static controller.controle.esperando;
import static controller.controle.mutex;
import controller.controle;
import javafx.application.Platform;

public class Cliente extends Thread {

	private controle controle;

	/**
	 * Construtor da classe Cliente.
	 *
	 * @param c Controle da academia associado ao cliente.
	 */
	public Cliente(controle c) {
		this.controle = c;
	}

	/**
	 * Método: run()
	 * Define o comportamento do cliente ao ser executado como uma
	 * thread.
	 * O cliente tenta entrar na fila da academia, aguarda sua vez e, quando
	 * possível, realiza a aplicação.
	 * 
	 * @return void
	 */
	public void run() {
		try {
			mutex.acquire();// Adquire controle do mutex para garantir exclusividade na seção crítica.

			if (esperando < CADEIRAS) {
				esperando++;// Incrementa o número de clientes esperando.
				entrarNaFila();// Simula o cliente entrando na fila e ocupando um lugar.
				clientes.release();// Libera um recurso para indicar que há um cliente na fila.
				mutex.release();// Libera o controle do mutex para permitir que outras threads acessem a seção
												// crítica.
				aplicadores.acquire();// Aguarda a disponibilidade de aplicadores.
				sleep(10);// Aguarda por um curto período simulando o tempo de aplicação.
				irAplicar();// Realiza a aplicação.
			} else {
				// Caso não haja espaço na academia, lida com a situação de academia cheia.
				mutex.release();// Libera o controle do mutex, pois não será necessário mais acessar a seção
												// crítica.
				academiaCheia();// Trata a situação de academia cheia.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método academiaCheia
	 * Trata o caso em que a academia está cheia.
	 * O cliente aguarda até que haja espaço na academia, exibe uma mensagem na
	 * porta, espera um tempo e vai embora.
	 * 
	 * @return void
	 */

	private void academiaCheia() {
		// Aguarda até que a mensagem de fila cheia não seja mais visível.
		if (controle.getMagroFilaCheia().isVisible()) {
			while (controle.getMagroFilaCheia().isVisible()) {
				try {
					sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// Exibe a mensagem na porta da academia.
		Platform.runLater(() -> {
			controle.aparecerNaPorta();
		});
		try {
			// Aguarda um tempo simulando a espera do cliente.
			sleep(300);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Vai embora após o período de espera.
		Platform.runLater(() -> {
			controle.irEmbora();
		});
		try {
			// Aguarda um tempo adicional antes de encerrar o método.
			sleep(300);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método irAplicar
	 * Simula a ação do cliente ao realizar a aplicação.
	 * O cliente ocupa uma cadeira disponível e indica que está preparado para a
	 * aplicação.
	 * 
	 * @return void
	 */
	private void irAplicar() {

		// impede que a cadeira do barbeiro seja ocupada enquanto ele estiver 'dormindo'
		if (controle.getVelocidadeAplicacao() == 800) {
			while (controle.getVelocidadeAplicacao() == 800) {
				try {
					sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// Oculta a cadeira ocupada pelo cliente
		if (controle.getMagro5().isVisible()) {
			controle.getMagro5().setVisible(false);
		} else if (controle.getMagro4().isVisible()) {
			controle.getMagro4().setVisible(false);
		} else if (controle.getMagro3().isVisible()) {
			controle.getMagro3().setVisible(false);
		} else if (controle.getMagro2().isVisible()) {
			controle.getMagro2().setVisible(false);
			;
		} else if (controle.getMagro1().isVisible()) {
			controle.getMagro1().setVisible(false);
		}

		// Indica que está preparado para a aplicação
		controle.preparadoParaAplicar();
	}

	/**
	 * Método entrarNaFila
	 * Simula a ação do cliente ao entrar na fila.
	 * O cliente verifica qual a próxima cadeira disponível e a ocupa.
	 * 
	 * @return void
	 */
	private void entrarNaFila() {

		// Verifica e ocupa a próxima cadeira disponível na fila.
		if (!controle.getMagro1().isVisible()) {
			controle.getMagro1().setVisible(true);
		} else if (!controle.getMagro2().isVisible()) {
			controle.getMagro2().setVisible(true);
		} else if (!controle.getMagro3().isVisible()) {
			controle.getMagro3().setVisible(true);
		} else if (!controle.getMagro4().isVisible()) {
			controle.getMagro4().setVisible(true);
		} else if (!controle.getMagro5().isVisible()) {
			controle.getMagro5().setVisible(true);
		}

	}

}
