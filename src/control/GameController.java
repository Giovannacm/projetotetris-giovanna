package control;

import elements.Componente;
import elements.Peca;
import java.util.ArrayList;
import java.util.Random;
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
 * Projeto Tetris - POO 2019.
 * Aluna: Giovanna Carreira Marinho
 */
public class GameController {
    //Uso das constantes da classe Consts
    private static int XMAX = Consts.XMAX;
    private static int YMAX = Consts.YMAX;
    private static int CMatriz = Consts.CMatriz;
    private static int LMatriz = Consts.LMatriz;
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
        Random valorAleatorio = new Random();
	char nome = ' ';
        Color cor = null;
	Componente a = new Componente(TamRec-1, TamRec-1, false);
        Componente b = new Componente(TamRec-1, TamRec-1, false);
        Componente c = new Componente(TamRec-1, TamRec-1, false);
        Componente d = new Componente(TamRec-1, TamRec-1, false);
        switch(valorAleatorio.nextInt(7))   //Pegando um valor aleatório (0 à 6) para determinar qual das 7 peça serão colocadas no jogo
        {
            case(0):
                a.setX(XMAX / 2 - TamRec - TamRec);
		b.setX(XMAX / 2 - TamRec);
		c.setX(XMAX / 2);
		d.setX(XMAX / 2 + TamRec);
		nome = 'I';
                cor = Color.LIGHTBLUE;
                break;
            case(1):
                a.setX(XMAX / 2 - TamRec);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2 - TamRec);
                c.setY(TamRec);
                d.setX(XMAX / 2);
                d.setY(TamRec);
                nome = 'O';
                cor = Color.YELLOW;
                break;
            case(2):
                a.setX(XMAX / 2 - TamRec);
		b.setX(XMAX / 2);
		c.setX(XMAX / 2);
		c.setY(TamRec);
		d.setX(XMAX / 2 + TamRec);
		nome = 'T';
                cor = Color.PURPLE;
                break;
            case(3):
                a.setX(XMAX / 2 + TamRec);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2);
                c.setY(TamRec);
                d.setX(XMAX / 2 - TamRec);
                d.setY(TamRec);
                nome = 'S';
                cor = Color.GREEN;
                break;
            case(4):
                a.setX(XMAX / 2 - TamRec);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2);
                c.setY(TamRec);
                d.setX(XMAX / 2 + TamRec);
                d.setY(TamRec);
                nome = 'Z';
                cor = Color.RED;
                break;
            case(5):
                a.setX(XMAX / 2 - TamRec);
                a.setY(TamRec);
                b.setX(XMAX / 2);
                b.setY(TamRec);
                c.setX(XMAX / 2 + TamRec);
                c.setY(TamRec);
                d.setX(XMAX / 2 - TamRec);
                nome = 'J';
                cor = Color.DARKBLUE;
                break;
            case(6):
                a.setX(XMAX / 2 - TamRec);
                a.setY(TamRec);
                b.setX(XMAX / 2);
                b.setY(TamRec);
                c.setX(XMAX / 2 + TamRec);
                c.setY(TamRec);
                d.setX(XMAX / 2 + TamRec);
                nome = 'L';
                cor = Color.ORANGE;
                break;
        }
        a.mudaCor(cor);  //Set na cor de cada retangulo
        b.mudaCor(cor);
        c.mudaCor(cor);
        d.mudaCor(cor);
	return new Peca(a, b, c, d, nome);
    }
    
    
    public static void adicionaObstaculos(int n)
    {
        Random valorAleatorio = new Random();
        for(int i=0 ; i<n ; i++)
        {
            Componente obstaculo = new Componente(TamRec-1, TamRec-1, true);
            obstaculo.setX(5 * TamRec);   //valorAleatorio.nextInt(CMatriz) * TamRec
            obstaculo.setY(17 * TamRec);   //valorAleatorio.nextInt(LMatriz) * TamRec
            obstaculo.mudaCor(Color.BLACK);
            pane.getChildren().addAll(obstaculo.getR());
            Tela[obstaculo.getY() / TamRec][obstaculo.getX() / TamRec] = obstaculo;
        }
    }
    
    /**
     * Função de utiliza o javaFx para realizar a leitura do teclado
     *  de acordo com a tecla lida, uma função é chamada.
     * @param peca 
     */
    public static void moverTeclaPressionada(Peca peca)
    {
        GameScreen.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
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
                        if(peca.getNome()!='O')//Se for a peça O não tem necessidade de rotacionar
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
        if(movimentoValidoD(peca.getA()) && movimentoValidoD(peca.getB())&&movimentoValidoD(peca.getC())&&movimentoValidoD(peca.getD()))
        {
            peca.getA().setX(peca.getA().getX() + TamRec);
            peca.getB().setX(peca.getB().getX() + TamRec);
            peca.getC().setX(peca.getC().getX() + TamRec);
            peca.getD().setX(peca.getD().getX() + TamRec);
        }
    }
    public static boolean movimentoValidoD(Componente r)
    {
        if((r.getX() + TamRec <= XMAX - TamRec)&&((Tela[r.getY()/TamRec][r.getX()/TamRec + 1] == null)))
            return true;
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
        if(movimentoValidoE(peca.getA()) && movimentoValidoE(peca.getB()) && movimentoValidoE(peca.getC()) && movimentoValidoE(peca.getD()))
        {
            peca.getA().setX(peca.getA().getX() - TamRec);
            peca.getB().setX(peca.getB().getX() - TamRec);
            peca.getC().setX(peca.getC().getX() - TamRec);
            peca.getD().setX(peca.getD().getX() - TamRec);
        }
    }
    public static boolean movimentoValidoE(Componente r)
    {
        if((r.getX() - TamRec >= 0)&&(Tela[r.getY()/TamRec][r.getX()/TamRec - 1] == null))
            return true;
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
        if(movimentoValidoB(peca.getA()) && movimentoValidoB(peca.getB()) && movimentoValidoB(peca.getC()) && movimentoValidoB(peca.getD()))
        {
            peca.getA().setY(peca.getA().getY() + TamRec);
            peca.getB().setY(peca.getB().getY() + TamRec);
            peca.getC().setY(peca.getC().getY() + TamRec);
            peca.getD().setY(peca.getD().getY() + TamRec);
            pontuacaoTexto.setText("Pontuação atual: " + Integer.toString(pontuacao++));
	}
    }
    public static boolean movimentoValidoB(Componente r)
    {
        if((r.getY() + TamRec < YMAX) && (Tela[r.getY()/TamRec + 1][r.getX()/TamRec] == null))
            return true;
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
        if(chegouNaBase(peca.getA()) || chegouNaBase(peca.getB()) || chegouNaBase(peca.getC()) || chegouNaBase(peca.getD())) 
        {
            Tela[peca.getA().getY() / TamRec][peca.getA().getX() / TamRec] = peca.getA();
            Tela[peca.getB().getY() / TamRec][peca.getB().getX() / TamRec] = peca.getB();
            Tela[peca.getC().getY() / TamRec][peca.getC().getX() / TamRec] = peca.getC();
            Tela[peca.getD().getY() / TamRec][peca.getD().getX() / TamRec] = peca.getD();
            completouLinha();   //Verificando se completou linha para remove-lá
            return false;
	}
        moveBaixo(peca);
        return true;
    }
    public static boolean chegouNaBase(Componente r)
    {
        if((r.getY() + TamRec == YMAX) || (Tela[(r.getY()/TamRec) + 1][r.getX()/TamRec] != null))
            return true;
        return false;
    }
    
    
    /**
     * Função responsável por rotacionar uma peça.
     * Inicialmente é verificado se a peça é O, pois se for, não há necessidade de rotacioná-la. Em seguida, conseguimos definir a posição 
     *      inicial da matriz imaginária utilizada na rotação (onde foi desenvolvido uma transformação linear para isso) a partir do eixo de rotação.
     * Temos que todas as peças rotacionam em torno do retangulo b, dessa forma a posição inicial da matriz é determinada por meio da posição de b.
     * Após a atribuição de valores para o x e y inicial da matriz, é aplicado um operador linar sobre cada retângulo.
     * @param peca 
     */
    public static void rotaciona(Peca peca)
    {
        Rectangle a = peca.getA().getR();   //Pegango o retangulo de cada componente da peça
	Rectangle b = peca.getB().getR();
	Rectangle c = peca.getC().getR();
	Rectangle d = peca.getD().getR();
        //Variáveis que armazenam o valor de x/y inicial da matriz imaginária 4x4 (utilizada na rotação) de acordo com o eixo de rotação. Como todas as pecas rotacionam em torno da peça b, conseguimos identificar o início da matriz imaginária. 
        int xiM = (int) b.getX() - TamRec;      //Seu x é o x do eixo - 1
        int yiM = (int) b.getY() - 2*TamRec;    //Seu y é o y do eixo - 2
        if(podeRotacionar(a, xiM, yiM)&&podeRotacionar(b, xiM, yiM)&&podeRotacionar(c, xiM, yiM)&&podeRotacionar(d, xiM, yiM))  //Se pode rotacionar todos os retangulos, o operador linear é aplicado em cada componente da peça para rotacionar
        {
            OperadorLinear(a, xiM, yiM);
            OperadorLinear(b, xiM, yiM);
            OperadorLinear(c, xiM, yiM);
            OperadorLinear(d, xiM, yiM);
        }
    }
    public static void OperadorLinear(Rectangle r, int xMatriz, int yMatriz) //Operador linear: T(x,y)=(3-y, x+1)
    {
        int dX = (int)r.getX() - xMatriz;           //dX representa a distancia (com o valor de x) do retangulo até a "matriz" imaginária da rotação
        int dY = (int)r.getY() - yMatriz;           //dY representa a distancia (com o valor de y) do retangulo até a "matriz" imaginária de rotação
        r.setX(xMatriz + 3*TamRec - dY);            //Aplicando a transformação, ou seja, x=3-y
        r.setY(yMatriz + dX + TamRec);              //Aplicando a transformação, ou seja, y=x+1
    }
    public static boolean podeRotacionar(Rectangle r, int xMatriz, int yMatriz)
    {
        int xDest = xMatriz + 3*TamRec - ((int)r.getY() - yMatriz);
        int yDest = yMatriz + TamRec + ((int)r.getX() - xMatriz);
        //Se o x e y de destino está dento do limite e não tem nenhum componente na posição de destino da matriz lógica, então pode rotacionar
        if((xDest <= XMAX-TamRec && xDest >= 0) && (yDest!=YMAX && yDest>=0) && (Tela[yDest/TamRec][xDest/TamRec] == null))
            return true;
        return false;
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
	ArrayList<Componente> novoscomp = new ArrayList<Componente>();
        for(Node node : pane.getChildren())         //Percorrendo os nodes da tela (gráfica)
        {
            if (node instanceof Rectangle)          //Adicionando os nodes que são retangulos em um arrayList
                rec.add(node);
	}
	for(Node node : rec)                        //Percorrendo os retangulos que estavam na tela (o array list rec)
        {
            elemento = (Rectangle) node;            //Colocando em elemento o retangulo do arraylist
            if(elemento.getY() == linha * TamRec && (!Tela[(int)elemento.getY()/TamRec][(int)elemento.getX()/TamRec].isFixa()))  //Se esse retangulo está na linha que será eliminada e não é fixo, ou seja, não é um obstáculo
            {
                Tela[(int)elemento.getY()/TamRec][(int)elemento.getX()/TamRec] = null;  //A posição correspondende na matriz recebe null, pois será removido da matriz de componentes
                pane.getChildren().remove(node);    //Remove o elemento da tela (gráfica)
            } 
            else if(elemento.getY() < linha * TamRec)          //Se esse retangulo está antes da linha a ser eliminada, eles devem "descer"
            {
                //O elemento deve descer apenas se, caso tenha um elemento em baixo, ele não deve ser um obstáculo, ou seja, não deve ser fixo
                if((Tela[(int)elemento.getY()/TamRec + 1][(int)elemento.getX()/TamRec]==null) ||(Tela[(int)elemento.getY()/TamRec + 1][(int)elemento.getX()/TamRec]!=null && !Tela[(int)elemento.getY()/TamRec + 1][(int)elemento.getX()/TamRec].isFixa()))
                {
                    Tela[(int)elemento.getY()/TamRec][(int)elemento.getX()/TamRec] = null;  //A posição correspondente na matriz recebe null, pois ele é removido dessa posição na matriz, irá para a próxima linha
                    elemento.setY(elemento.getY() + TamRec);          //O valor de Y é mudado (pois o retangulo irá descer)
                }
                Componente c = new Componente(TamRec-1, TamRec-1, false);          //Criação um componente não obstáculo
                c.setR(elemento);                                                  //Dando set no retangulo do componente para esse novo retangulo
                novoscomp.add(c);                 //Ele é adicionado em outro array list (de novos componentes)
            }
            else if(Tela[(int)elemento.getY()/TamRec][(int)elemento.getX()/TamRec].isFixa())    //Se é um obstáculo, continua onde está, então não muda nada, apenas cria um componente para adicionar no arrayList
            {
                Componente c = new Componente(TamRec-1, TamRec-1, true);          //Criação um componente não obstáculo
                c.setR(elemento);                                                  //Dando set no retangulo do componente para esse novo retangulo
                novoscomp.add(c);                 //Ele é adicionado em outro array list (de novos componentes)
            }
	}
	for(Componente comp : novoscomp)      //Percorrendo o array list dos componentes novos (que ficarão depois da eliminação da linha)
        {
            elemento = comp.getR();             //Pegando o retangulo desse elemento
            Tela[(int)elemento.getY()/TamRec][(int)elemento.getX()/TamRec] = comp; //Colocando esse componente na posição correspondente (o valor de Y foi mudado anteriormente, se ele não é obstáculo)
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