/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogodesenvolvido.personagens;

import java.util.Random;
import javax.swing.ImageIcon;
import jogodesenvolvido.armas.Arma_IF;

/**
 *
 * @author rayna
 */
public class DragaoAlado extends Atributos implements PersonagemVoador{
    private Arma_IF arma;

    public DragaoAlado() {
        super(2000, 50);
    }

    @Override
    public ImageIcon desenhar() {
        return new ImageIcon("src/assets/dragao.png");
    }

    @Override
    public ResultadoAtaque arma(Personagem inimigo) {
        arma.usarArma();
        int dano = arma.getDano();
        
        Random rand = new Random();
        int acao = rand.nextInt(2) + 1;
        int danoSofrido = 0;
        
        switch(acao) {
            case 1:
                danoSofrido = inimigo.receberDano(dano);
                break;
            case 2:
                danoSofrido = inimigo.amenizarDano(dano);
                break;
        }
        
        return new ResultadoAtaque(acao, danoSofrido);
    }

    @Override
    public void setArma(Arma_IF arma) {
        this.arma = arma;
    }
    
    @Override
    public void voar(){
        System.out.println("Voando");
    }
}
