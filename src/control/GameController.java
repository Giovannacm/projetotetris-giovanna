package control;

import elements.Componente;
import elements.Peca;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import javafx.event.ActionEvent;
import utils.Consts;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    //Uso das constantes da classe Consts, dos atributos do GameScreen e atibutos para controle do jogo
    private static int XMAX = Consts.XMAX;
    private static int YMAX = Consts.YMAX;
    private static int CMatriz = Consts.CMatriz;
    private static int LMatriz = Consts.LMatriz;
    private static int TamRec = Consts.TamRec;
    private static double Gravidade = Consts.Gravidade;
    private static Componente[][]Tela = GameScreen.getTela();
    private static Pane pane = GameScreen.getPane();
    private static Scene scene = GameScreen.getScene();
    private static int pontuacao = 0;
    private static Text pontuacaoTexto = new Text("Pontuação: " + Integer.toString(pontuacao)); //Esse texto irá mudar toda vez que a pontuação for alterada
    private static Text faseTexto = new Text("Fase: ");     //Esse texto irá mudar dependendo da fase escolhida
    
    /**
     * Método responsável por desenhar os elementos do cenário.
     * Além de colocar os textos de pontuaçãofase, atual e próxima peça, adiciona as linhas para desenhar a matriz e um botão para sair do jogo.
     * @param primaryStage 
     * @param timer 
     */
    public static void desenhaCenario(Stage primaryStage, Timer timer)
    {
        //Texto para indicar a fase atual
        faseTexto.setStyle("-fx-font-size: 22;");
	faseTexto.setX(310);
        faseTexto.setY(30);
        
        //Texto para indicar a pontuação atual
        pontuacaoTexto.setStyle("-fx-font-size: 22;");
        pontuacaoTexto.setX(310);
        pontuacaoTexto.setY(90);
        
        //Texto para indicar a pontuação atual
        Text proxPeca = new Text("Próxima peca: ");
        proxPeca.setStyle("-fx-font-size: 22;");
        proxPeca.setX(310);
        proxPeca.setY(150);
               
        //Botão para sair do jogo
        Button sair = new Button("Sair");
        sair.setLayoutX(390);
        sair.setLayoutY(390);
        sair.setStyle("-fx-base: white; -fx-font-size: 20;");
        sair.setFocusTraversable(false);
        sair.setOnAction(new EventHandler<ActionEvent>()   //Adicionando um evento no botão para sair do jogo
        {
            @Override
            public void handle(ActionEvent event) 
            {
                timer.cancel();         //Encerrando o timer, descartando qualquer tarefa agendada no momento
                timer.purge();          //Remove todas as tarefas canceladas da fila de tarefas deste timer
                primaryStage.close();   //Fechando o stage
            }
        });
        
        //Adicionando os textos e o botao na tela
        pane.getChildren().addAll(faseTexto, pontuacaoTexto, proxPeca, sair);
        
        desenhaLinhas(primaryStage);
        
        //Criando a tela (javaFX)
        primaryStage.setScene(scene);
	primaryStage.setTitle("TETRIS - Giovanna");
    }
    
    
    /**
     * Método para desenhar as linhas do senário
     * @param primaryStage 
     */
    public static void desenhaLinhas(Stage primaryStage)
    {
        int i;
        Line linha;
        for(i=0 ; i<Consts.CMatriz ; i++)   //Linhas verticais
        {
            linha = new Line(TamRec*(i), 0, TamRec*(i), YMAX);
            linha.setStroke(Color.GHOSTWHITE);
            pane.getChildren().addAll(linha);
        }
        linha = new Line(TamRec*(i), 0, TamRec*(i), YMAX);  //Linha para separação do texto da cena do jogo
        linha.setStroke(Color.BLACK);
        pane.getChildren().addAll(linha);
        for(i=0 ; i<=Consts.LMatriz ; i++)  //Linhas horizontais
        {
            linha = new Line(0, TamRec*(i), XMAX, TamRec*(i));
            linha.setStroke(Color.GHOSTWHITE);
            pane.getChildren().addAll(linha);
        }
    }
    
    
    /**
     * Método responsável por gerar uma peça aleatória (de acordo com o valorAreatorio) e colocá-la na parte de cima da tela centrada no meio.
     *  Inicialmente é criado um componente com o tamanho especificado na constante.
     *  Depois é analisado o valorAleatorio, e de acordo com esse valor, cada retangulo é posicionado no meio de acordo com a peça que sejá gerada.
     * @return peca que foi criada
     */
    public static Peca proximaPeca()
    {        
        Random valorAleatorio = new Random();
	char nome = ' ';
        Color cor = null;
	Componente a = new Componente(TamRec-1, TamRec-1, false);   //False pois será uma peca, e não obstáculo
        Componente b = new Componente(TamRec-1, TamRec-1, false);
        Componente c = new Componente(TamRec-1, TamRec-1, false);
        Componente d = new Componente(TamRec-1, TamRec-1, false);
        switch(valorAleatorio.nextInt(7))   //Pegando um valor aleatório (0 à 6) para determinar qual das 7 peça serão colocadas no jogo
        {
            case(0):    //Verificando o valor aleatório e dando set na posição de cada retangulo de acordo com o formato da peça
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
	return new Peca(a, b, c, d, nome);  //Retornando um objeto do tipo Peca
    }
    
    
    /**
     * Método responsável por adicionar obstáculos na tela.
     * É passado um valor inteiro como parâmetro que indica a quantidade de obstáculos a serem adicionados.
     * Assim que um componente é instanciado, sua posição é alterada de acordo com um valor aleatório, e sua cor sempre será preta.
     * @param n 
     */
    public static void adicionaObstaculos(int n)
    {
        Random valorAleatorio = new Random();
        for(int i=0 ; i<n ; i++)
        {
            Componente obstaculo = new Componente(TamRec-1, TamRec-1, true);
            obstaculo.setX(valorAleatorio.nextInt(CMatriz) * TamRec);
            obstaculo.setY(valorAleatorio.nextInt(LMatriz-5) * TamRec + 5 * TamRec); //Evita que a peça fique muito em cima
            obstaculo.mudaCor(Color.BLACK);
            pane.getChildren().addAll(obstaculo.getR());                            //Adicionando os retangulos desse componente no pane
            Tela[obstaculo.getY() / TamRec][obstaculo.getX() / TamRec] = obstaculo; //Colocando esse componente em sua respectiva posição na matriz
        }
    }
    
    /**
     * Método que utiliza o JavaFx para realizar a leitura do teclado de acordo com a tecla lida, uma função é chamada.
     * @param peca 
     */
    public static void moverTeclaPressionada(Peca peca)
    {
        GameScreen.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() 
        {
            @Override
            public void handle(KeyEvent event) 
            {
                switch (event.getCode()) 
                {
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
     * Método responsável por mover a peça para a direita.
     * Inicialmente é verificado se é possível mover cada retangulo para a direita.
     * Se for, a posição de cada retângulo é mudada, somando 1 no x, ou seja andando um retangulo para a direita.
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
    
    
    /**
     * Método que verifica se é válido mover para a direita (verificação por meio da posição X + TamRec).
     * É verificado se não há nenhuma peça nequela posição (o valor da matriz naquela posição é 0) e se está dentro do limite da tela (gráfica)
     * @param r
     * @return 
     */
    public static boolean movimentoValidoD(Componente r)
    {
        if((r.getX() + TamRec <= XMAX - TamRec)&&((Tela[r.getY()/TamRec][r.getX()/TamRec + 1] == null)))
            return true;
        return false;
    }
    
    
    /**
     * Método responsável por mover a peça para a esquerda.
     * Inicialmente é verificado se é possível mover cada retangulo para a esquerda. 
     * Se for, a posição de cada retângulo é mudada, subtraindo 1 no x, ou seja andando um retangulo para a esquerda.
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
    
    
    /**
     * Método que verifica se é válido mover para a esquerda (verificação por meio da posição X - TamRec).
     * É verificado se não há nenhuma peça nequela posição (o valor da matriz naquela posição é 0) e se está dentro do limite da tela (gráfica)
     * @param r
     * @return 
     */
    public static boolean movimentoValidoE(Componente r)
    {
        if((r.getX() - TamRec >= 0)&&(Tela[r.getY()/TamRec][r.getX()/TamRec - 1] == null))
            return true;
        return false;
    }
     
    
    /**
     * Método responsável por mover a peça para baixo.
     * Inicialmente é verificado se é possível mover cada retangulo para baixo. 
     * Se for, a posição de cada retângulo é mudada, somando 1 no y, ou seja andando um retangulo para baixo.
     * Além disso, é atualizado a pontuação pois toda vez que uma peça move pra baixo a pontuação é incrementada.
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
            pontuacaoTexto.setText("Pontuação: " + Integer.toString(pontuacao++));
	}
    }
    
    
    /**
     * Método que verifica se é válido mover para baixo (verificação por meio da posição Y + TamRec).
     * É verificado se não há nenhuma peça nequela posição (o valor da matriz naquela posição é 0) e se está dentro do limite da tela (gráfica)
     * @param r
     * @return 
     */
    public static boolean movimentoValidoB(Componente r)
    {
        if((r.getY() + TamRec < YMAX) && (Tela[r.getY()/TamRec + 1][r.getX()/TamRec] == null))
            return true;
        return false;
    }
    
    
    /**
     * Método responsável por fazer a peça cair até que a base seja atingida.
     * Inicialmente é verificado se algum retangulo já cregou na base, ou esta em cima de alguma peça (vê se na matriz, a posição a frente de Y (Y + 1) já está com 1).
     * Caso essa situação seja verdadeira (pelo menos 1 retangulo da peça chegou na base ou em cima de alguma peça), a respectiva posição 
     * na matriz recebe 1, pois essa peça ficou naquele lugar. Caso contrátrio, faz apenas a peça descer por meio da função moveBaixo.
     * @param peca 
     * @return  
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
    
    
    /**
     * Método que verifica se o componente chegou na base.
     * É verificado se o seu Y chegou no limite da tela gráfica ou se tem algum elemento na posição de baixo desse componente (é diferente de null na respectiva posição da matriz).
     * @param r
     * @return 
     */
    public static boolean chegouNaBase(Componente r)
    {
        if((r.getY() + TamRec == YMAX) || (Tela[(r.getY()/TamRec) + 1][r.getX()/TamRec] != null))
            return true;
        return false;
    }
    
    
    /**
     * Método responsável por rotacionar uma peça.
     * Inicialmente é definido a posição inicial da matriz imaginária utilizada na rotação (onde foi desenvolvido uma transformação linear para isso) a partir do eixo de rotação (retangulo b de cada peça).
     * Temos que todas as peças rotacionam em torno do retangulo b, dessa forma a posição inicial da matriz é determinada por meio da posição de b.
     * Após a atribuição de valores para o x e y inicial da matriz, é aplicado um operador linar sobre cada retângulo.
     * Esse operador linear só é aplicado se puder rotacionar todos os outros componentes.
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
    
    
    /**
     * Método responsável por aplicar o operador linear desenvolvido para rotacionar a peça.
     * É somado xMatriz e yMatriz em x e y respectivamente, pois estamos trabalhando na tela toda, e não apenas na matriz inaginária.
     * @param r         (retangulo onde será aplicado o operador linear)
     * @param xMatriz   (x inicial da matriz imaginária)
     * @param yMatriz   (y inicial da matriz imaginária)
     */
    public static void OperadorLinear(Rectangle r, int xMatriz, int yMatriz) //Operador linear: T(x,y)=(3-y, x+1)
    {
        int dX = (int)r.getX() - xMatriz;           //dX representa a distancia (com o valor de x) do retangulo até a "matriz" imaginária da rotação
        int dY = (int)r.getY() - yMatriz;           //dY representa a distancia (com o valor de y) do retangulo até a "matriz" imaginária de rotação
        r.setX(xMatriz + 3*TamRec - dY);            //Aplicando a transformação, ou seja, x=3-y
        r.setY(yMatriz + dX + TamRec);              //Aplicando a transformação, ou seja, y=x+1
    }
    
    
    /**
     * Método responsável por verificar se não haverá colisões ao mudar o retangulo para sua posição de destino.
     * É verificado se o x e y da posição de destino está dentro do limite da tela (gráfica) e se não existe nenhum elemento na matriz.
     * @param r       (retangulo onde será aplicado o operador linear)
     * @param xMatriz (x inicial da matriz imaginária)
     * @param yMatriz (y inicial da matriz imaginária)
     * @return 
     */
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
     * Método responsável por percorrer a matriz (lógica) para encontrar onde está a peça mais alta.
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
     * Método responsável por percorrer a matriz e determinar se foi completado alguma linha (todas as posições dessa linha possuem valor 1).
     * Além disso, de acordo com o número de linhas completadas, a pontuação aumenta.
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
    }
    
    
    /**
     * Método responsável por fazer com que todos os elementos da matriz até determinada linha, desça uma posição.
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
            if(elemento.getX()>XMAX)                //Se o retangulo for aquele que repesenta a proxima peca (ou seja, está fora da área gráfica do jogo), continua, pois nada será feito com ele
                continue;
            if(elemento.getY() == linha * TamRec && (!Tela[(int)elemento.getY()/TamRec][(int)elemento.getX()/TamRec].isFixa()))  //Se esse retangulo está na linha que será eliminada e não é fixo, ou seja, não é um obstáculo
            {
                Tela[(int)elemento.getY()/TamRec][(int)elemento.getX()/TamRec] = null;  //A posição correspondende na matriz recebe null, pois será removido da matriz de componentes
                pane.getChildren().remove(node);                                        //Remove o elemento da tela (gráfica)
            } 
            else if(elemento.getY() < linha * TamRec && (!Tela[(int)elemento.getY()/TamRec][(int)elemento.getX()/TamRec].isFixa())) //Se esse retangulo está antes da linha a ser eliminada e não é um obstáculo, eles devem "descer"
            {
                if(!temObstaculoNaColuna((int)elemento.getX()/TamRec))  //Se não tiver obstáculo na coluna, o elemento pode descer
                {
                    Tela[(int)elemento.getY()/TamRec][(int)elemento.getX()/TamRec] = null;  //A posição correspondente na matriz recebe null, pois ele é removido dessa posição na matriz, irá para a próxima linha
                    elemento.setY(elemento.getY() + TamRec);          //O valor de Y é mudado (pois o retangulo irá descer)
                }
                else if(temObstaculoNaColuna((int)elemento.getX()/TamRec))  //Se tiver obstáculo na coluna, é analisado os casos em relação ao obstáculo
                {
                    if(linhaDoObstaculo((int)elemento.getX()/TamRec)<linha && (int)elemento.getY()/TamRec < linha && (int)elemento.getY()/TamRec > linhaDoObstaculo((int)elemento.getX()/TamRec)) //Se o obstáculo está acima da linha a ser removida pode descer sem restrição
                    {
                        System.out.println("passei 1");
                        Tela[(int)elemento.getY()/TamRec][(int)elemento.getX()/TamRec] = null;  //A posição correspondente na matriz recebe null, pois ele é removido dessa posição na matriz, irá para a próxima linha
                        elemento.setY(elemento.getY() + TamRec);          //O valor de Y é mudado (pois o retangulo irá descer)
                    }
                    else if(linhaDoObstaculo((int)elemento.getX()/TamRec)>linha && (int)elemento.getY()/TamRec < linha && (int)elemento.getY()/TamRec < linhaDoObstaculo((int)elemento.getX()/TamRec))    //Se o obstáculo está abaixo do elemento, só pode descer os elemntos se a linha a ser removida não é do obstáculo
                    {
                        System.out.println("passei 2");
                        Tela[(int)elemento.getY()/TamRec][(int)elemento.getX()/TamRec] = null;  //A posição correspondente na matriz recebe null, pois ele é removido dessa posição na matriz, irá para a próxima linha
                        elemento.setY(elemento.getY() + TamRec);          //O valor de Y é mudado (pois o retangulo irá descer)
                    }
                }
                Componente c = new Componente(TamRec-1, TamRec-1, false);          //Criação um componente não obstáculo
                c.setR(elemento);                                                  //Dando set no retangulo do componente para esse novo retangulo
                novoscomp.add(c);                 //Ele é adicionado em outro array list (de novos componentes)
            }
            else if(Tela[(int)elemento.getY()/TamRec][(int)elemento.getX()/TamRec].isFixa())    //Se é um obstáculo, continua onde está, então não muda nada, apenas cria um componente para adicionar no arrayList
            {
                Componente c = new Componente(TamRec-1, TamRec-1, true);            //Criação um componente não obstáculo
                c.setR(elemento);                                                   //Dando set no retangulo do componente para esse novo retangulo
                novoscomp.add(c);                                                   //Ele é adicionado em outro array list (de novos componentes)
            }
	}
	for(Componente comp : novoscomp)        //Percorrendo o array list dos componentes novos (que ficarão depois da eliminação da linha)
        {
            elemento = comp.getR();                                                 //Pegando o retangulo desse elemento
            Tela[(int)elemento.getY()/TamRec][(int)elemento.getX()/TamRec] = comp;  //Colocando esse componente na posição correspondente (o valor de Y foi mudado anteriormente, se ele não é obstáculo)
	}
    }
    
    
    /**
     * Método que verifica se existe algum obstáculo na coluna (j) passada como parâmetro.
     * Essa verificação é feita percorrendo a coluna e verificando se existe algum elemento na matriz que é fixo, ou seja, é obstáculo.
     * @param j (coluna)
     * @return 
     */
    public static boolean temObstaculoNaColuna(int j)
    {
        for(int i=0 ; i<LMatriz ; i++)
        {
            if(Tela[i][j]!=null)
            {
                if(Tela[i][j].isFixa())
                    return true;
            }
        }
        return false;
    }
    
    
    
    /**
     * Método que retorna a linha de um obstáculo, caso ele exista.
     * Percorre-se a coluna (j) e verifica se existe algum obstáculo, se tiver, sua linha (i) é retornada
     * @param j (coluna que deseja verificar)
     * @return 
     */
    public static int linhaDoObstaculo(int j)
    {
        for(int i=0 ; i<LMatriz ; i++)
        {
            if(Tela[i][j]!=null)
            {
                if(Tela[i][j].isFixa())
                    return i;
            }
        }
        return -1;
    }

    
    //Métodos para acesso de alguns atributos. São utilizados no GameScreen para controle da fase/velocidade.
    public static int getPontuacao() 
    {
        return pontuacao;
    }
    public static void setFaseTexto(String text) 
    {
        faseTexto.setText(text);
    }
    public static int getGravidade() 
    {
        return (int)Gravidade;
    }
    public static void setGravidade(double Gravidade)
    {
        GameController.Gravidade = Gravidade;
    }
}