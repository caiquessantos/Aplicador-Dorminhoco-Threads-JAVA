/* ***************************************************************
* Autor............: Caíque Santos Santana
* Matricula........: 202010643
* Inicio...........: 12/11/2023
* Ultima alteracao.: 12/11/2023
* Nome.............: Classe do aplicador (barbeiro) do Programa Barbeiro dorminhoco
* Funcao...........: Representa uma thread que realiza a aplicação de um tratamento em clientes.
****************************************************************/

package model;

import static controller.controle.aplicadores;
import static controller.controle.clientes;
import static controller.controle.esperando;
import static controller.controle.mutex;
import controller.controle;

public class Aplicador extends Thread {

	private controle controle;

	/**
	 * Construtor da classe Aplicador.
	 *
	 * @param c Controle da academia associado ao cliente.
	 */
	public Aplicador(controle c) {
		this.controle = c;
	}

	/**
	 * Método: run
	 * Funcionalidade: Implementa a lógica de execução da thread Aplicador.
	 * 
	 * @return void
	 */
	public void run() {
		while (true) {
			try {
				sleep(controle.getVelocidadeAplicacao());// Aguarda o tempo definido pela velocidade de aplicação.
				clientes.acquire();// Adquire um cliente da fila, bloqueando se a fila estiver vazia.
				mutex.acquire();// Adquire controle do mutex para acessar a seção crítica.
				esperando--;// Decrementa o número de clientes esperando.
				aplicadores.release();// Libera um recurso para indicar que o aplicador está disponível.
				mutex.release();// Libera o controle do mutex para permitir que outras threads acessem a seção
												// crítica.
				Aplicar();// Realiza a aplicação.
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Método: Aplicar
	 * Funcionalidade: Realiza o processo de aplicação do tratamento.
	 * 
	 * @return void
	 */
	private void Aplicar() {
		boolean terminou = false;
		// Faz a animação de aplicação parar caso o slider seja igual a 800
		while (!terminou) {
			if (controle.getVelocidadeAplicacao() == 800) {
				while (controle.getVelocidadeAplicacao() == 800) {
					try {
						sleep(1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			if (controle.getMagroAplicando().isVisible()) {
				// aplica a bomba
				controle.aplicar();
				try {
					sleep(controle.getVelocidadeAplicacao());
				} catch (Exception e) {
					e.printStackTrace();
				}
				// para de aplicar a bomba e deixa o cliente bombado
				controle.pararDeAplicar();
				controle.deixarBombado();
				try {
					sleep(controle.getVelocidadeAplicacao());
				} catch (Exception e) {
					e.printStackTrace();
				}
				// faz o cliente bombado ir até a porta de saída
				controle.finalizarAtendimento();
				try {
					sleep(controle.getVelocidadeAplicacao());
				} catch (Exception e) {
					e.printStackTrace();
				}
				// O cliente bombado vai embora
				controle.fecharPorta();
				terminou = true;
			}
		}
	}

}
