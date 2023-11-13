/* ***************************************************************
* Autor............: Caíque Santos Santana
* Matricula........: 202010643
* Inicio...........: 12/11/2023
* Ultima alteracao.: 12/11/2023
* Nome.............: Classe Controle do Programa Barbeiro dorminhoco
* Funcao...........: A classe controle inclui atributos relacionados a elementos visuais da interface gráfica (por exemplo, ImageView), botões, sliders, semáforos e outras configurações. Além disso, ela possui métodos associados a ações do usuário, como iniciar o sistema, avançar etapas, controlar a velocidade de geração e aplicação, entre outras.
****************************************************************/

package controller;

import java.util.concurrent.Semaphore;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import model.Aplicador;
import model.CriadorDeClientes;

public class controle {
	@FXML
	private ImageView inicio;

	@FXML
	private ImageView guia;

	@FXML
	private ImageView cenario;

	@FXML
	private ImageView aplicador;

	@FXML
	private ImageView aplicando;

	@FXML
	private ImageView forte;

	@FXML
	private ImageView saindo1;

	@FXML
	private ImageView magro1;

	@FXML
	private ImageView magro2;

	@FXML
	private ImageView magro3;

	@FXML
	private ImageView magro4;

	@FXML
	private ImageView magro5;

	@FXML
	private ImageView magroFilaCheia;

	@FXML
	private ImageView magroAplicando;

	@FXML
	private ImageView fila;

	@FXML
	private ImageView bomba;

	@FXML
	private Button botaoAvancar;

	@FXML
	private Button botaoIniciar;

	@FXML
	private Slider velocidadeGeracao;

	@FXML
	private Slider velocidadeAplicacao;

	Aplicador a;
	CriadorDeClientes cdc;

	// Criação dos semáforos e variáveis de controle
	public static final int CADEIRAS = 5;
	public static Semaphore clientes;
	public static Semaphore aplicadores;
	public static Semaphore mutex;
	public static int esperando;

	/**
	 * Método: initialize
	 * 
	 * Inicializa a interface do usuário, configurando a visibilidade dos
	 * componentes e
	 * definindo opções iniciais.
	 * 
	 * @return void
	 */
	public void initialize() {
		inicializar();
	}

	/**
	 * Inicializa a interface do usuário, configurando a visibilidade dos
	 * componentes e
	 * definindo opções iniciais.
	 * 
	 * @return void
	 */
	private void inicializar() {
		guia.setVisible(false);
		cenario.setVisible(false);
		inicio.setVisible(true);
		botaoIniciar.setVisible(false);
		botaoIniciar.setDisable(true);
		aplicador.setVisible(false);
		aplicando.setVisible(false);
		magroAplicando.setVisible(false);
		magro1.setVisible(false);
		magro2.setVisible(false);
		magro3.setVisible(false);
		magro4.setVisible(false);
		magro5.setVisible(false);
		forte.setVisible(false);
		magroAplicando.setVisible(false);
		magroFilaCheia.setVisible(false);
		fila.setVisible(false);
		bomba.setVisible(false);
		velocidadeAplicacao.setVisible(false);
		velocidadeGeracao.setVisible(false);
		saindo1.setVisible(false);

	}

	/**
	 * Método: avancar
	 * 
	 * Avança para a próxima etapa da aplicação, a etapa de guia ,ajustando a
	 * visibilidade dos componentes.
	 * 
	 * @return void
	 */
	public void avancar() {
		inicio.setVisible(false);
		guia.setVisible(true);
		botaoAvancar.setVisible(false);
		botaoAvancar.setDisable(true);
		botaoIniciar.setVisible(true);
		botaoIniciar.setDisable(false);
	}

	/**
	 * Método: iniciar
	 * 
	 * Inicia a execução da aplicação, configurando a visibilidade dos componentes e
	 * inicializando threads necessárias.
	 * 
	 * @return void
	 */
	public void iniciar() {

		guia.setVisible(false);
		cenario.setVisible(true);
		botaoIniciar.setVisible(false);
		botaoIniciar.setDisable(true);
		velocidadeAplicacao.setVisible(true);
		velocidadeGeracao.setVisible(true);
		aplicador.setVisible(true);
		aplicador.setX(300);
		aplicador.setY(250);
		aplicando.setX(300);
		aplicando.setY(250);
		magro1.setY(-20);
		magro1.setX(860);
		magro2.setY(-20);
		magro2.setX(1050);
		magro3.setY(140);
		magro3.setX(1050);
		magro4.setY(300);
		magro4.setX(1050);
		magro5.setY(460);
		magro5.setX(1050);
		forte.setY(220);
		forte.setX(380);
		saindo1.setX(380);
		saindo1.setY(470);
		magroAplicando.setY(220);
		magroAplicando.setX(380);
		magroFilaCheia.setX(700);
		magroFilaCheia.setY(450);
		fila.setX(920);
		fila.setY(590);
		bomba.setX(86);
		bomba.setY(590);
		fila.setVisible(true);
		bomba.setVisible(true);
		// cria os semáforos que serão usados na aplicação
		clientes = new Semaphore(0);
		aplicadores = new Semaphore(0);
		mutex = new Semaphore(1);
		// Instancia as threads
		a = new Aplicador(this);
		cdc = new CriadorDeClientes(this);
		// Torna as threads daemon, para que elas finalizem junto a aplicação
		a.setDaemon(true);
		cdc.setDaemon(true);
		// inicia as threads
		cdc.start();
		a.start();

	}

	/**
	 * Método: preparadoParaAplicar
	 * 
	 * Define a visibilidade do componente magroAplicando para indicar que está
	 * preparado para aplicar.
	 * 
	 * @return void
	 */
	public void preparadoParaAplicar() {
		magroAplicando.setVisible(true);
	}

	/**
	 * Método: aplicar
	 * 
	 * Ativa a animação de aplicação.
	 * 
	 * @return void
	 */
	public void aplicar() {
		aplicador.setVisible(false);
		aplicando.setVisible(true);
	}

	/**
	 * Método: pararDeAplicar
	 * 
	 * Para a animação de aplicação e restaura a visibilidade do aplicador.
	 * 
	 * @return void
	 */
	public void pararDeAplicar() {
		aplicando.setVisible(false);
		aplicador.setVisible(true);
	}

	/**
	 * Método: deixarBombado
	 * 
	 * Ativa a imagem "forte" indicando que a aplicação foi concluída com sucesso.
	 * 
	 * @return void
	 */
	public void deixarBombado() {
		magroAplicando.setVisible(false);
		forte.setVisible(true);
	}

	/**
	 * Método: finalizarAtendimento
	 * 
	 * Ativa a animação de saída após a conclusão do atendimento.
	 * 
	 * @return void
	 */
	public void finalizarAtendimento() {
		saindo1.setVisible(true);
		forte.setVisible(false);
	}

	/**
	 * Método: fecharPorta
	 * 
	 * Faz com que o cliente bombado deixe a interface gráfica.
	 * 
	 * @return void
	 */
	public void fecharPorta() {
		saindo1.setVisible(false);
	}

	/**
	 * Método: aparecerNaPorta
	 * 
	 * Ativa a imagem indicando que a fila está cheia e alguém está esperando na
	 * porta.
	 * 
	 * @return void
	 */
	public void aparecerNaPorta() {
		magroFilaCheia.setVisible(true);
	}

	/**
	 * Método: irEmbora
	 * 
	 * Desativa a imagem de alguém esperando na porta indicando que a pessoa foi
	 * embora.
	 * 
	 * @return void
	 */
	public void irEmbora() {
		magroFilaCheia.setVisible(false);
	}

	/**
	 * Método: getVelocidadeGeracao
	 * 
	 * Obtém a velocidade de geração de clientes a partir do slider.
	 * 
	 * @return int - A velocidade de geração.
	 */
	public int getVelocidadeGeracao() {
		double aux = velocidadeGeracao.getValue();// pega o valor do slider e armazena em uma vari�vel auxiliar
		int retorno = (int) aux; // transforma o valor em inteiro
		return retorno; // retorna o valor
	}

	/**
	 * Método: getVelocidadeAplicacao
	 * 
	 * Obtém a velocidade de aplicação a partir do slider.
	 * 
	 * @return int - A velocidade de aplicação.
	 */
	public int getVelocidadeAplicacao() {
		double aux = velocidadeAplicacao.getValue();// pega o valor do slider e armazena em uma vari�vel auxiliar
		int retorno = (int) aux; // transforma o valor em inteiro
		return retorno; // retorna o valor
	}

	// Getters das imagens e sliders abaixo

	public ImageView getMagro1() {
		return magro1;
	}

	public ImageView getMagro2() {
		return magro2;
	}

	public ImageView getMagro3() {
		return magro3;
	}

	public ImageView getMagro4() {
		return magro4;
	}

	public ImageView getMagro5() {
		return magro5;
	}

	public ImageView getForte() {
		return forte;
	}

	public ImageView getMagroAplicando() {
		return magroAplicando;
	}

	public ImageView getMagroFilaCheia() {
		return magroFilaCheia;
	}

	public ImageView getAplicador() {
		return aplicador;
	}

}
