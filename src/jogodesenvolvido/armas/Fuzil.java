/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogodesenvolvido.armas;

/**
 *
 * @author rayna
 */
public class Fuzil extends Atributos implements Arma_IF{

    public Fuzil(int dano, int qtdUsos) {
        super(dano, qtdUsos);
    }

    @Override
    public void usarArma() {
        System.out.println("Usando arma");
    }
    
    @Override
    public int getDano(){
        return dano;
    }
}
