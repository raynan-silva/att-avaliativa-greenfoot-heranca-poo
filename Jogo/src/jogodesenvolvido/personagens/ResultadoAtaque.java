/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogodesenvolvido.personagens;

/**
 *
 * @author rayna
 */
public class ResultadoAtaque {
    private final int tipoAcao;
    private final int danoSofrido;
    
    public ResultadoAtaque(int tipoAcao, int danoSofrido){
        this.tipoAcao = tipoAcao;
        this.danoSofrido = danoSofrido;
    }
    
    public int getTipoAcao(){
        return tipoAcao;
    }
    
    public int getDanoSofrido(){
        return danoSofrido;
    }
}
