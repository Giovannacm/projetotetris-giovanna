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
import javafx.scene.shape.Rectangle;

/**
 * Projeto Tetris - POO 2019.
 * Aluna: Giovanna Carreira Marinho
 * Baseado em material do Prof. Jose Fernando Junior e Prof. Luiz Eduardo (USP)
 */
public class GameController {
    //Uso das constantes da classe Consts, dos atributos do GameScreen e atibutos para controle do jogo
    private static double Gravidade = Consts.Gravidade;
    private static Componente[][]Tela = GameScreen.getTela();
    private static Pane pane = GameScreen.getPane();
    private static Scene scene = GameScreen.getScene();
    private static int pontuacao = 0;
    
    
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
	Componente a = new Componente(Consts.TamRec-1, Consts.TamRec-1, false);   //False pois será uma peca, e não obstáculo
        Componente b = new Componente(Consts.TamRec-1, Consts.TamRec-1, false);
        Componente c = new Componente(Consts.TamRec-1, Consts.TamRec-1, false);
        Componente d = new Componente(Consts.TamRec-1, Consts.TamRec-1, false);
        switch(valorAleatorio.nextInt(7))   //Pegando um valor aleatório (0 à 6) para determinar qual das 7 peça serão colocadas no jogo
        {
            case(0):    //Verificando o valor aleatório e dando set na posição de cada retangulo de acordo com o formato da peça
                a.setX(Consts.XMAX / 2 - Consts.TamRec - Consts.TamRec);
		b.setX(Consts.XMAX / 2 - Consts.TamRec);
		c.setX(Consts.XMAX / 2);
		d.setX(Consts.XMAX / 2 + Consts.TamRec);
		nome = 'I';
                cor = Color.LIGHTBLUE;
                break;
            case(1):
                a.setX(Consts.XMAX / 2 - Consts.TamRec);
                b.setX(Consts.XMAX / 2);
                c.setX(Consts.XMAX / 2 - Consts.TamRec);
                c.setY(Consts.TamRec);
                d.setX(Consts.XMAX / 2);
                d.setY(Consts.TamRec);
                nome = 'O';
                cor = Color.YELLOW;
                break;
            case(2):
                a.setX(Consts.XMAX / 2 - Consts.TamRec);
		b.setX(Consts.XMAX / 2);
		c.setX(Consts.XMAX / 2);
		c.setY(Consts.TamRec);
		d.setX(Consts.XMAX / 2 + Consts.TamRec);
		nome = 'T';
                cor = Color.PURPLE;
                break;
            case(3):
                a.setX(Consts.XMAX / 2 + Consts.TamRec);
                b.setX(Consts.XMAX / 2);
                c.setX(Consts.XMAX / 2);
                c.setY(Consts.TamRec);
                d.setX(Consts.XMAX / 2 - Consts.TamRec);
                d.setY(Consts.TamRec);
                nome = 'S';
                cor = Color.GREEN;
                break;
            case(4):
                a.setX(Consts.XMAX / 2 - Consts.TamRec);
                b.setX(Consts.XMAX / 2);
                c.setX(Consts.XMAX / 2);
                c.setY(Consts.TamRec);
                d.setX(Consts.XMAX / 2 + Consts.TamRec);
                d.setY(Consts.TamRec);
                nome = 'Z';
                cor = Color.RED;
                break;
            case(5):
                a.setX(Consts.XMAX / 2 - Consts.TamRec);
                a.setY(Consts.TamRec);
                b.setX(Consts.XMAX / 2);
                b.setY(Consts.TamRec);
                c.setX(Consts.XMAX / 2 + Consts.TamRec);
                c.setY(Consts.TamRec);
                d.setX(Consts.XMAX / 2 - Consts.TamRec);
                nome = 'J';
                cor = Color.DARKBLUE;
                break;
            case(6):
                a.setX(Consts.XMAX / 2 - Consts.TamRec);
                a.setY(Consts.TamRec);
                b.setX(Consts.XMAX / 2);
                b.setY(Consts.TamRec);
                c.setX(Consts.XMAX / 2 + Consts.TamRec);
                c.setY(Consts.TamRec);
                d.setX(Consts.XMAX / 2 + Consts.TamRec);
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
            Componente obstaculo = new Componente(Consts.TamRec-1, Consts.TamRec-1, true);    //True pois é fixo, ou seja, é obstáculo
            obstaculo.setX(valorAleatorio.nextInt(Consts.CMatriz) * Consts.TamRec);
            obstaculo.setY(valorAleatorio.nextInt(Consts.LMatriz-5) * Consts.TamRec + 5 * Consts.TamRec); //Evita que a peça fique muito em cima
            obstaculo.mudaCor(Color.BLACK);
            pane.getChildren().addAll(obstaculo.getR());                            //Adicionando os retangulos desse componente no pane
            Tela[obstaculo.getY() / Consts.TamRec][obstaculo.getX() / Consts.TamRec] = obstaculo; //Colocando esse componente em sua respectiva posição na matriz
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
                        peca.moveDireita();
                        break;
                    case LEFT:
                        peca.moveEsquerda();
                        break;
                    case DOWN:
                        peca.moveBaixo();
                        break;
                    case SPACE:
                        if(peca.getNome()!='O')//Se for a peça O não tem necessidade de rotacionar
                            peca.rotaciona();
                        break;
		}
            }
	});
    }
        
    
    /**
     * Método que verifica se é válido mover para a direita (verificação por meio da posição X + TamRec).
     * É verificado se não há nenhuma peça nequela posição (o valor da matriz naquela posição é null) e se está dentro do limite da tela (gráfica)
     * @param r
     * @return 
     */
    public static boolean movimentoValidoD(Componente r)
    {
        if((r.getX() + Consts.TamRec <= Consts.XMAX - Consts.TamRec)&&((Tela[r.getY()/Consts.TamRec][r.getX()/Consts.TamRec + 1] == null)))
            return true;
        return false;
    }
    
    
    /**
     * Método que verifica se é válido mover para a esquerda (verificação por meio da posição X - TamRec).
     * É verificado se não há nenhuma peça nequela posição (o valor da matriz naquela posição é null) e se está dentro do limite da tela (gráfica)
     * @param r
     * @return 
     */
    public static boolean movimentoValidoE(Componente r)
    {
        if((r.getX() - Consts.TamRec >= 0)&&(Tela[r.getY()/Consts.TamRec][r.getX()/Consts.TamRec - 1] == null))
            return true;
        return false;
    }
     
    
    /**
     * Método que verifica se é válido mover para baixo (verificação por meio da posição Y + TamRec).
     * É verificado se não há nenhuma peça nequela posição (o valor da matriz naquela posição é null) e se está dentro do limite da tela (gráfica)
     * @param r
     * @return 
     */
    public static boolean movimentoValidoB(Componente r)
    {
        if((r.getY() + Consts.TamRec < Consts.YMAX) && (Tela[r.getY()/Consts.TamRec + 1][r.getX()/Consts.TamRec] == null))
            return true;
        return false;
    }
    
    
    /**
     * Método responsável por fazer a peça cair até que a base seja atingida.
     * Inicialmente é verificado se algum retangulo já cregou na base, ou esta em cima de alguma peça.
     * Caso essa situação seja verdadeira (pelo menos 1 retangulo da peça chegou na base ou em cima de alguma peça), a respectiva posição 
     * na matriz recebe o componente em sua devida posição, pois essa peça ficou naquele lugar. Caso contrátrio, faz apenas a peça descer por meio da função moveBaixo.
     * @param peca 
     * @return  
     */
    public static boolean fazerCair(Peca peca)
    {
        if(chegouNaBase(peca.getA()) || chegouNaBase(peca.getB()) || chegouNaBase(peca.getC()) || chegouNaBase(peca.getD())) 
        {
            Tela[peca.getA().getY() / Consts.TamRec][peca.getA().getX() / Consts.TamRec] = peca.getA();
            Tela[peca.getB().getY() / Consts.TamRec][peca.getB().getX() / Consts.TamRec] = peca.getB();
            Tela[peca.getC().getY() / Consts.TamRec][peca.getC().getX() / Consts.TamRec] = peca.getC();
            Tela[peca.getD().getY() / Consts.TamRec][peca.getD().getX() / Consts.TamRec] = peca.getD();
            completouLinha();   //Verificando se completou linha para remove-lá
            return false;
	}
        peca.moveBaixo();
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
        if((r.getY() + Consts.TamRec == Consts.YMAX) || (Tela[(r.getY()/Consts.TamRec) + 1][r.getX()/Consts.TamRec] != null))
            return true;
        return false;
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
        int xDest = xMatriz + 3*Consts.TamRec - ((int)r.getY() - yMatriz); //A posição de destino é calculada com base na transformação linear T(x,y)=(3-y, x+1)
        int yDest = yMatriz + Consts.TamRec + ((int)r.getX() - xMatriz);
        //Se o x e y de destino está dento do limite e não tem nenhum componente na posição de destino da matriz lógica, então pode rotacionar
        if((xDest <= Consts.XMAX-Consts.TamRec && xDest >= 0) && (yDest!=Consts.YMAX && yDest>=0) && (Tela[yDest/Consts.TamRec][xDest/Consts.TamRec] == null))
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
            if(elemento.getX()>Consts.XMAX)                //Se o retangulo for aquele que repesenta a proxima peca (ou seja, está fora da área gráfica do jogo), continua, pois nada será feito com ele
                continue;
            if(elemento.getY() == linha * Consts.TamRec && (!Tela[(int)elemento.getY()/Consts.TamRec][(int)elemento.getX()/Consts.TamRec].isFixo()))  //Se esse retangulo está na linha que será eliminada e não é fixo, ou seja, não é um obstáculo
            {
                Tela[(int)elemento.getY()/Consts.TamRec][(int)elemento.getX()/Consts.TamRec] = null;  //A posição correspondende na matriz recebe null, pois será removido da matriz de componentes
                pane.getChildren().remove(node);                                        //Remove o elemento da tela (gráfica)
            } 
            else if(elemento.getY() < linha * Consts.TamRec && (!Tela[(int)elemento.getY()/Consts.TamRec][(int)elemento.getX()/Consts.TamRec].isFixo())) //Se esse retangulo está antes da linha a ser eliminada e não é um obstáculo, eles devem "descer"
            {
                if(!temObstaculoNaColuna((int)elemento.getX()/Consts.TamRec))  //Se não tiver obstáculo na coluna, o elemento pode descer
                {
                    Tela[(int)elemento.getY()/Consts.TamRec][(int)elemento.getX()/Consts.TamRec] = null;  //A posição correspondente na matriz recebe null, pois ele é removido dessa posição na matriz, irá para a próxima linha
                    elemento.setY(elemento.getY() + Consts.TamRec);          //O valor de Y é mudado (pois o retangulo irá descer)
                }
                else if(temObstaculoNaColuna((int)elemento.getX()/Consts.TamRec))  //Se tiver obstáculo na coluna, é analisado os casos em relação ao obstáculo
                {
                    if(linhaDoObstaculo((int)elemento.getX()/Consts.TamRec)<linha && (int)elemento.getY()/Consts.TamRec < linha && (int)elemento.getY()/Consts.TamRec > linhaDoObstaculo((int)elemento.getX()/Consts.TamRec)) //Se o obstáculo está acima da linha a ser removida pode descer sem restrição
                    {
                        Tela[(int)elemento.getY()/Consts.TamRec][(int)elemento.getX()/Consts.TamRec] = null;  //A posição correspondente na matriz recebe null, pois ele é removido dessa posição na matriz, irá para a próxima linha
                        elemento.setY(elemento.getY() + Consts.TamRec);          //O valor de Y é mudado (pois o retangulo irá descer)
                    }
                    else if(linhaDoObstaculo((int)elemento.getX()/Consts.TamRec)>linha && (int)elemento.getY()/Consts.TamRec < linha && (int)elemento.getY()/Consts.TamRec < linhaDoObstaculo((int)elemento.getX()/Consts.TamRec))    //Se o obstáculo está abaixo do elemento, só pode descer os elemntos se a linha a ser removida não é do obstáculo
                    {
                        Tela[(int)elemento.getY()/Consts.TamRec][(int)elemento.getX()/Consts.TamRec] = null;  //A posição correspondente na matriz recebe null, pois ele é removido dessa posição na matriz, irá para a próxima linha
                        elemento.setY(elemento.getY() + Consts.TamRec);          //O valor de Y é mudado (pois o retangulo irá descer)
                    }
                }
                Componente c = new Componente(Consts.TamRec-1, Consts.TamRec-1, false);          //Criação um componente não obstáculo
                c.setR(elemento);                                                  //Dando set no retangulo do componente para esse novo retangulo
                novoscomp.add(c);                 //Ele é adicionado em outro array list (de novos componentes)
            }
            else if(Tela[(int)elemento.getY()/Consts.TamRec][(int)elemento.getX()/Consts.TamRec].isFixo())    //Se é um obstáculo, continua onde está, então não muda nada, apenas cria um componente para adicionar no arrayList
            {
                Componente c = new Componente(Consts.TamRec-1, Consts.TamRec-1, true);            //Criação um componente não obstáculo
                c.setR(elemento);                                                   //Dando set no retangulo do componente para esse novo retangulo
                novoscomp.add(c);                                                   //Ele é adicionado em outro array list (de novos componentes)
            }
	}
	for(Componente comp : novoscomp)        //Percorrendo o array list dos componentes novos (que ficarão depois da eliminação da linha)
        {
            elemento = comp.getR();                                                 //Pegando o retangulo desse elemento
            Tela[(int)elemento.getY()/Consts.TamRec][(int)elemento.getX()/Consts.TamRec] = comp;  //Colocando esse componente na posição correspondente (o valor de Y foi mudado anteriormente, se ele não é obstáculo)
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
        for(int i=0 ; i<Consts.LMatriz ; i++)
        {
            if(Tela[i][j]!=null)    //Se tem algum componente
            {
                if(Tela[i][j].isFixo()) //É obstáculo
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
        for(int i=0 ; i<Consts.LMatriz ; i++)
        {
            if(Tela[i][j]!=null)    //Se existe um componente
            {
                if(Tela[i][j].isFixo()) //É obstáculo
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
    public static void setPontuacao(int pontuacao)
    {
        GameController.pontuacao = pontuacao;
    }
    public static double getGravidade() 
    {
        return Gravidade;
    }
    public static void setGravidade(double g)
    {
       Gravidade = g;
    }
}