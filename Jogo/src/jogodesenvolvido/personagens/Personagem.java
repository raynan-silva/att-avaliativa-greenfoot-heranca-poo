/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package jogodesenvolvido.personagens;

import javax.swing.ImageIcon;
import jogodesenvolvido.armas.Arma_IF;

/**
 *
 * @author rayna
 */
public interface Personagem {
    ImageIcon desenhar();
    ResultadoAtaque arma(Personagem inimigo);
    void setArma(Arma_IF arma);
    int getVida();
    int receberDano(int dano);
    int amenizarDano(int dano);
}
