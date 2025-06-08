/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogodesenvolvido.personagens;

/**
 *
 * @author rayna
 */
public class Atributos {
    int vida;
    int amenizar;
    
    public Atributos(int vida, int amenizar) {
        this.vida = vida;
        this.amenizar = amenizar;
    }
    
    public int receberDano(int dano) {
        this.vida -= dano;
        if (vida <= 0) {
            vida = 0;  
        }
        
        return dano;
    }
    
    public int amenizarDano(int dano){
        int danoReal = dano - this.amenizar;
        
        if (danoReal < 0) danoReal = 0;
        
        this.vida -= danoReal; 
        return danoReal;
    }
    
    public int getVida(){
        return this.vida;
    }
}
