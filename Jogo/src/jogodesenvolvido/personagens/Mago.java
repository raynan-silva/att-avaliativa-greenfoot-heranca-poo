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
public class Mago extends Atributos implements PersonagemConsciente {
    private Arma_IF arma;

    public Mago() {
        super(1000, 100);
    }

    @Override
    public ImageIcon desenhar() {
        return new ImageIcon("src/assets/mago.png");
    }

    @Override
    public void falar() {
        System.out.println("Falei");
    }

    @Override
    public ResultadoAtaque arma(Personagem inimigo) {
        arma.usarArma();
        int dano = arma.getDano();
        
        Random rand = new Random();
        int acao = rand.nextInt(3) + 1;
        int danoSofrido = 0;
        
        switch(acao) {
            case 1:
                danoSofrido = inimigo.receberDano(dano);
                break;
            case 2:
                danoSofrido = inimigo.amenizarDano(dano);
                break;
            case 3:
                break;
        }
        
        return new ResultadoAtaque(acao, danoSofrido);
    }

    @Override
    public void setArma(Arma_IF arma) {
        this.arma = arma;
    }
    
    @Override
    public void correr() {
        System.out.println("Correndo");
    }
}
