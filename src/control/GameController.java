package control;

import elements.Componente;
import elements.Peca;
import java.util.ArrayList;
import utils.Consts;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Projeto de POO 2019
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public class GameController {
    //Uso das constantes da classe Consts
    private static int XMAX = Consts.XMAX;
    private static int YMAX = Consts.YMAX;
    private static int TamRec = Consts.TamRec;
    private static Componente[][]Tela = GameScreen.getTela();
    private static Pane pane = GameScreen.getPane();
    private static Scene scene = GameScreen.getScene();
    private static int pontuacao = 0;
    private static Text pontuacaoTexto = new Text("Pontuação atual: " + Integer.toString(pontuacao));
    
    /**
     * Função responsável por desenhar os elementos do cenário.
     * Além de colocar os textos de pontuação e fase atual, adiciona as linhas para desenhar a matriz.
     * @param primaryStage 
     */
    public static void desenhaCenario(Stage primaryStage)
    {
        //Texto para indicar a fase atual
        Text faseTexto = new Text("Fase atual: ");
        faseTexto.setStyle("-fx-font: 20 arial;");
	faseTexto.setX(310);
        faseTexto.setY(20);
        
        //Texto para indicar a pontuação atual
        pontuacaoTexto.setStyle("-fx-font: 20 arial;");
        pontuacaoTexto.setX(310);
        pontuacaoTexto.setY(70);
        
        //Texto para indicar a pontuação atual
        Text proximaPeca = new Text("Próxima peça: ");
        proximaPeca.setStyle("-fx-font: 20 arial;");
        proximaPeca.setX(310);
        proximaPeca.setY(120);
               
        //Adicionando os textos na tela
        pane.getChildren().addAll(faseTexto, pontuacaoTexto);
        
        desenhaLinhas(primaryStage);
        
        //Criando a tela (javaFX)
        primaryStage.setScene(scene);
	primaryStage.setTitle("TETRIS - Giovanna");
    }
    public static void desenhaLinhas(Stage primaryStage)
    {
        int i;
        Line linha;
        for(i=0 ; i<Consts.CMatriz ; i++)
        {
            linha = new Line(TamRec*(i), 0, TamRec*(i), YMAX);
            linha.setStroke(Color.GHOSTWHITE);
            pane.getChildren().addAll(linha);
        }
        linha = new Line(TamRec*(i), 0, TamRec*(i), YMAX);
        linha.setStroke(Color.BLACK);
        pane.getChildren().addAll(linha);
        for(i=0 ; i<=Consts.LMatriz ; i++)
        {
            linha = new Line(0, TamRec*(i), XMAX, TamRec*(i));
            linha.setStroke(Color.GHOSTWHITE);
            pane.getChildren().addAll(linha);
        }
    }
    
    
    /**
     * Função responsável por gerar uma peça aleatória (de acordo com o valorAreatorio) e colocá-la na parte de cima da tela centrada no meio.
     *  Inicialmente é criado um retangulo com o tamanho especificado na constante.
     *  Depois é analisado o valorAleatorio, e de acordo com esse valor, cada retangulo é posicionado no meio de acordo com a peça que sejá gerada.
     * @return peca que foi criada
     */
    public static Peca proximaPeca()
    {        
        int valorAleatorio = (int) (Math.random() * 6);     //Pegando um valor aleatório (0 à 6) para determinar qual das 7 peça serão colocadas no jogo
	String nome = "";
	Componente a = new Componente(TamRec-1, TamRec-1, false);
        Componente b = new Componente(TamRec-1, TamRec-1, false);
        Componente c = new Componente(TamRec-1, TamRec-1, false);
        Componente d = new Componente(TamRec-1, TamRec-1, false);
        switch(valorAleatorio)
        {
            case(0):
                a.setX(XMAX / 2 - TamRec - TamRec);
		b.setX(XMAX / 2 - TamRec);
		c.setX(XMAX / 2);
		d.setX(XMAX / 2 + TamRec);
		nome = "I";
                break;
            case(1):
                a.setX(XMAX / 2 - TamRec);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2 - TamRec);
                c.setY(TamRec);
                d.setX(XMAX / 2);
                d.setY(TamRec);
                nome = "O";
                break;
            case(2):
                a.setX(XMAX / 2 - TamRec);
		b.setX(XMAX / 2);
		c.setX(XMAX / 2);
		c.setY(TamRec);
		d.setX(XMAX / 2 + TamRec);
		nome = "T";
                break;
            case(3):
                a.setX(XMAX / 2 + TamRec);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2);
                c.setY(TamRec);
                d.setX(XMAX / 2 - TamRec);
                d.setY(TamRec);
                nome = "S";
                break;
            case(4):
                a.setX(XMAX / 2 + TamRec);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2 + TamRec);
                c.setY(TamRec);
                d.setX(XMAX / 2 + TamRec + TamRec);
                d.setY(TamRec);
                nome = "Z";
                break;
            case(5):
                a.setX(XMAX / 2 - TamRec);
                b.setX(XMAX / 2 - TamRec);
                b.setY(TamRec);
                c.setX(XMAX / 2);
                c.setY(TamRec);
                d.setX(XMAX / 2 + TamRec);
                d.setY(TamRec);
                nome = "J";
                break;
            case(6):
                a.setX(XMAX / 2 + TamRec);
                b.setX(XMAX / 2 - TamRec);
                b.setY(TamRec);
                c.setX(XMAX / 2);
                c.setY(TamRec);
                d.setX(XMAX / 2 + TamRec);
                d.setY(TamRec);
                nome = "L";
                break;
        }
	return new Peca(a, b, c, d, nome);
    }
    
    
    /**
     * Função de utiliza o javaFx para realizar a leitura do teclado
     *  de acordo com a tecla lida, uma função é chamada.
     * @param peca 
     */
    public static void moverTeclaPressionada(Peca peca)
    {
        Scene cenaJogo = GameScreen.getScene();
        cenaJogo.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case RIGHT:
                        moveDireita(peca);
                        break;
                    case LEFT:
                        moveEsquerda(peca);
                        break;
                    case DOWN:
                        moveBaixo(peca);
                    break;
                    case SPACE:
                        rotaciona(peca);
                        break;
		}
            }
	});
    }
    
    
    /**
     * Função responsável por mover a peça para a direita.
     * Inicialmente é verificado se é possível mover cada retangulo para a direita (verificação por meio da posição X + TamRec), 
     *      se for, é verificado se não há nenhuma peça nequela posição (o valor da
     *      matriz naquela posição é 0), se for, o a posição de cada retângulo é mudada.
     * @param peca 
     */
    public static void moveDireita(Peca peca) 
    {
        //Mover a peça para a direita, implica em mover todos os retangulos pertencentes à ela por meio do incremento da posição x
        if(movimentoValidoD(peca.a) && movimentoValidoD(peca.b)&&movimentoValidoD(peca.c)&&movimentoValidoD(peca.d))
        {
            peca.a.setX(peca.a.getX() + TamRec);
            peca.b.setX(peca.b.getX() + TamRec);
            peca.c.setX(peca.c.getX() + TamRec);
            peca.d.setX(peca.d.getX() + TamRec);
        }
    }
    public static boolean movimentoValidoD(Componente r)
    {
        if((r.getX() + TamRec <= XMAX - TamRec)&&((Tela[r.getY()/TamRec][r.getX()/TamRec + 1] == null)))
        {
            return true;
        }
        return false;
    }
    
    
    /**
     * Função responsável por mover a peça para a esquerda.
     * Inicialmente é verificado se é possível mover cada retangulo para a esquerda (verificação por meio da posição X - TamRec), 
     *      se for, é verificado se não há nenhuma peça nequela posição (o valor da
     *      matriz naquela posição é 0), se for, o a posição de cada retângulo é mudada.
     * @param peca 
     */
    public static void moveEsquerda(Peca peca) 
    { 
        //Mover a peça para a esquerda, implica em mover todos os retangulos pertencentes à ela por meio do decrementos da posição x
        if(movimentoValidoE(peca.a) && movimentoValidoE(peca.b) && movimentoValidoE(peca.c) && movimentoValidoE(peca.d))
        {
            peca.a.setX(peca.a.getX() - TamRec);
            peca.b.setX(peca.b.getX() - TamRec);
            peca.c.setX(peca.c.getX() - TamRec);
            peca.d.setX(peca.d.getX() - TamRec);
        }
    }
    public static boolean movimentoValidoE(Componente r)
    {
        if((r.getX() - TamRec >= 0)&&(Tela[r.getY()/TamRec][r.getX()/TamRec - 1] == null))
        {
            return true;
        }
        return false;
    }
     
    
    /**
     * Função responsável por mover a peça para baixo.
     * Inicialmente é verificado se é possível mover cada retangulo para baixo (verificação por meio da posição Y + TamRec), 
     *      se for, é verificado se não há nenhuma peça nequela posição (o valor da
     *      matriz naquela posição é 0), se for, o a posição de cada retângulo é mudada.
     * @param peca 
     */
    public static void moveBaixo(Peca peca) 
    {
        if(movimentoValidoB(peca.a) && movimentoValidoB(peca.b) && movimentoValidoB(peca.c) && movimentoValidoB(peca.d))
        {
            peca.a.setY(peca.a.getY() + TamRec);
            peca.b.setY(peca.b.getY() + TamRec);
            peca.c.setY(peca.c.getY() + TamRec);
            peca.d.setY(peca.d.getY() + TamRec);
            pontuacaoTexto.setText("Pontuação atual: " + Integer.toString(pontuacao++));
	}
    }
    public static boolean movimentoValidoB(Componente r)
    {
        if((r.getY() + TamRec < YMAX) && (Tela[r.getY()/TamRec + 1][r.getX()/TamRec] == null))
        {
            return true;
        }
        return false;
    }
    
    
    /**
     * Função responsável por fazer a peça cair até que a base seja atingida.
     * Inicialmente é verificado se algum retangulo já cregou na base, ou esta em cima de alguma peça (vê se na matriz, a posição
     *      a frente de Y (Y + 1) já está com 1).
     * Caso essa situação seja verdadeira (pelo menos 1 retangulo da peça chegou na base ou em cima de alguma peça), a respectiva posição 
     * na matriz recebe 1, pois essa peça ficou naquele lugar.
     * Caso contrátrio, faz apenas a peça descer por meio da função moveBaixo.
     * @param peca 
     */
    public static boolean fazerCair(Peca peca)
    {
        if(chegouNaBase(peca.a) || chegouNaBase(peca.b) || chegouNaBase(peca.c) || chegouNaBase(peca.d)) 
        {
            Tela[peca.a.getY() / TamRec][peca.a.getX() / TamRec] = peca.getA();
            Tela[peca.b.getY() / TamRec][peca.b.getX() / TamRec] = peca.getB();
            Tela[peca.c.getY() / TamRec][peca.c.getX() / TamRec] = peca.getC();
            Tela[peca.d.getY() / TamRec][peca.d.getX() / TamRec] = peca.getD();
            completouLinha();   //Verificando se completou linha para remove-lá
            return false;
	}
        moveBaixo(peca);
        return true;
    }
    public static boolean chegouNaBase(Componente r)
    {
        if((r.getY() + TamRec == YMAX) || (Tela[(r.getY()/TamRec) + 1][r.getX()/TamRec] != null))
        {
            return true;
        }
        return false;
    }
    
    
    /**
     * 
     * @param peca 
     */
    private static void rotaciona(Peca peca)
    {
        Rectangle a = peca.a.getR();
	Rectangle b = peca.b.getR();
	Rectangle c = peca.c.getR();
	Rectangle d = peca.d.getR();
        switch (peca.getNome())
        {
            case("O"):  //Não precisa rotacionar
                break;
            default:  //Operador linear T(x,y)=(3-y, x+1)
                OperadorLinear(a);
                OperadorLinear(b);
                OperadorLinear(c);
                OperadorLinear(d);
                break;
        }
    }
    private static void OperadorLinear(Rectangle r)
    {
        int x = (int)r.getX();
        int y = (int)r.getY();
        r.setX(3*TamRec - y);
        r.setY(x+TamRec);
    }

    
    /**
     * Função responsável por percorrer a matriz (tela) para encontrar onde está a peça mais alta.
     * Dessa forma, é possível ter controle sobre o final do jogo (topo==0), uma vez que se uma peça atingir o topo da tela,
     * o jogo termina.
     * @return valor do "topo"
     */
    public static int topo()
    {
        for(int i=0 ; i<Consts.LMatriz ; i++)
        {
            for(int j=0 ; j<Consts.CMatriz ; j++)
            {
                if(Tela[i][j]!=null)
                {
                    return(i);
                }
            }
        }
        return(-1);
    }
    
    
    /**
     * Função responsável por percorrer a matriz e determinar se foi completado alguma linha (todas as posições dessa linha possuem valor 1).
     * Além disso, de acordo com o número de linhas completadas, a pontuação aumenta.
     * @return número de linhas completadas.
     */
    public static void completouLinha()
    {
        int i, j, linha=0;
        for(i=0 ; i<Consts.LMatriz ; i++)
        {
            for(j=0 ; j<Consts.CMatriz && Tela[i][j]!=null ; j++);
            if(j==Consts.CMatriz)   //Tem uma linha com 1
            {
                removeLinha(i);
                linha++;
            }
        }
        if(linha==1)        //Completou uma linha, então adiciona 50 pontos
            pontuacao+=50;
        else if(linha==2)   //Completou duas linha, então adiciona 200 pontos
            pontuacao+=200;
        else if(linha==3)   //Completou tres linha, então adiciona 800 pontos
            pontuacao+=800;
        //return(linha);       //Retorna a quantidade de linhas que completou (se não completou, linha=0; 
    }
    
    
    /**
     * Função responsável por fazer com que todos os elementos da matriz até determinada linha, desça uma posição.
     * Com isso, eliminamos essa linha.
     * @param linha a ser removida
     */
    public static void removeLinha(int linha)
    {
        int i, j;
        Rectangle elemento;
        ArrayList<Node> rec = new ArrayList<Node>();
	ArrayList<Node> novosrec = new ArrayList<Node>();
        for(Node node : pane.getChildren())         //Percorrendo os nodes da tela (gráfica)
        {
            if (node instanceof Rectangle)          //Adicionando os nodes que são retangulos em um arrayList
                rec.add(node);
	}
	for(Node node : rec)                        //Percorrendo os retangulos que estavam na tela (o array list rec)
        {
            elemento = (Rectangle) node;
            if (elemento.getY() == linha * TamRec)  //Se esse retangulo está na linha que será eliminada
            {
                Tela[(int)elemento.getY()/TamRec][(int)elemento.getX()/TamRec] = null;  //A posição correspondende na matriz recebe null
                pane.getChildren().remove(node);    //Remove o elemento da tela (gráfica)
            } 
            else if (elemento.getY() < linha * TamRec)          //Se esse retangulo está antes da linha a ser eliminada, eles devem "descer"
            {
                Tela[(int)elemento.getY()/TamRec][(int)elemento.getX()/TamRec] = null;  //A posição correspondente na matriz recebe null
		elemento.setY(elemento.getY() + TamRec);          //O valor de Y é mudado (pois o retangulo irá descer)
                novosrec.add(node);                 //Ele é adicionado em outro array list (de novos retangulos)
            }
		
	}
	for(Node node : novosrec)      //Percorrendo o array list dos retangulos novos (que ficarão depois da eliminação da linha)
        {
            elemento = (Rectangle) node;
            Componente c = new Componente(TamRec-1, TamRec-1, false);          //Criação um componente
            c.setR(elemento);                                                  //Dando set no retangulo do componente para esse novo retangulo
            Tela[(int)elemento.getY()/TamRec][(int)elemento.getX()/TamRec] = c; //Colocando esse componente na posição correspondente (o valor de Y foi mudado anteriormente)
	}
    }

    
    /**
     * Função para retornar a pontuação.
     * @return pontuacao
     */
    public static int getPontuacao() {
        return pontuacao;
    }
}