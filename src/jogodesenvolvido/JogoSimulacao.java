package jogodesenvolvido;

import java.awt.*;
import javax.swing.*;
import jogodesenvolvido.armas.*;
import jogodesenvolvido.personagens.*;

public class JogoSimulacao extends JFrame {
    private PersonagemConsciente[] personagens;
    private Arma_IF[] armas;
    
    private PersonagemVoador dragao;
    private JLabel labelDragao;
    private int dragaoTurnosAtivo = 0;
    private int dragaoAtaques = 0;
    private boolean dragaoPresente = false;

    public JogoSimulacao() {  
        setTitle("Simulação de Batalha");
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 600));
        setContentPane(layeredPane);
        
        JLabel backgroundLabel = new JLabel(new ImageIcon("src/assets/cavaleiro-vs-dragao.jpg"));
        backgroundLabel.setBounds(0, 0, 1000, 600);
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        initGame();
        startBattle();
    }

    private void initGame() {
        personagens = new PersonagemConsciente[]{
            new General(),
            new Mago(),
            new LutSumo(),
            new Soldado()
        };
        
        
        armas = new Arma_IF[]{
            new Desarmado(20, -1),
            new Faca(50, 10),
            new Fuzil(150, 5),
            new Revolver(70, 15)
        };

        for (PersonagemConsciente personagem : personagens) {
            personagem.setArma(armas[0]);
        }
    }

    private void startBattle() {
        
        PersonagemConsciente[] jogadores = new PersonagemConsciente[2];
        
        for (int i = 0; i < 2; i++) {
            StringBuilder options = new StringBuilder("Jogador " + (i + 1) +": Escolha um personagem:\n");
            int index = 0;
            for (PersonagemConsciente personagem : personagens) {
                options.append(index + 1).append(": ").append(personagem.getClass().getSimpleName()).append("\n");
                index++;
            }
            int choice = InOut.leInt(options.toString()) - 1;

            // Check for valid choice
            if (choice < 0 || choice >= personagens.length) {
                InOut.MsgDeErro("Erro", "Escolha inválida!");
                //continue;
            }
            
            jogadores[i] = personagens[choice];
            
            JLabel labelPersonagem = new JLabel();
            labelPersonagem.setIcon(jogadores[i].desenhar());
            labelPersonagem.setSize(200, 300);
            
            if (i == 0){
                labelPersonagem.setLocation(50, 300);
            } else {
                labelPersonagem.setLocation(750, 300);
            }
            
            ((JLayeredPane) getContentPane()).add(labelPersonagem, JLayeredPane.PALETTE_LAYER);
            
        }
        int turno = 0;
        while (true) {
            PersonagemConsciente atacante = jogadores[turno % 2];
            PersonagemConsciente defensor = jogadores[(turno + 1) % 2];
            
            if (!dragaoPresente && turno > 0 && turno % 3 == 0){
                dragao = new DragaoAlado();
                dragao.setArma(new Desarmado(450, -1));
                labelDragao = new JLabel(dragao.desenhar());
                labelDragao.setSize(200, 300);
                labelDragao.setLocation(400, 0);
                ((JLayeredPane) getContentPane()).add(labelDragao, JLayeredPane.PALETTE_LAYER);
                dragaoPresente = true;
                dragaoAtaques = 0;
                dragaoTurnosAtivo = 0;
            }
            
            if (dragaoPresente && dragaoAtaques < 2) {
                int indexAleatorio = (int) (Math.random() * jogadores.length);
                PersonagemConsciente alvo = jogadores[indexAleatorio];
                
                InOut.MsgDeInformacao("Ataque do Dragão", "Dragão atacou " + alvo.getClass().getSimpleName() + "!");
                
                ResultadoAtaque resultado = dragao.arma(alvo);
                InOut.MsgDeInformacao("Dano", alvo.getClass().getSimpleName() + " sofreu: " + resultado.getDanoSofrido() + " de dano do Dragão!");
                
                InOut.MsgDeInformacao("Vida", atacante.getClass().getSimpleName() + ": " + atacante.getVida() +
                            " | " + defensor.getClass().getSimpleName() + ": " + defensor.getVida());
                    
                dragaoAtaques++;
                dragaoTurnosAtivo++;
                
                if (alvo.getVida() <= 0){
                    InOut.MsgDeInformacao("Vitória", alvo.getClass().getSimpleName() + " foi eleminado pelo Dragão! O outro jogador venceu!");
                    return;
                }
                
                if (dragaoAtaques == 2) {
                    ((JLayeredPane) getContentPane()).remove(labelDragao);
                    repaint();
                    dragaoPresente = false;
                }
            }

            int action = InOut.leInt(atacante.getClass().getSimpleName() + ": Escolha uma ação:\n 1 - atacar \n 2- fugir \n 3 - trocarArma");
            switch (action) {
                case 1 -> {
                    ResultadoAtaque resultado = atacante.arma(defensor);
                    InOut.MsgDeInformacao("Ataque", atacante.getClass().getSimpleName() + " atacou!");
                    
                    switch(resultado.getTipoAcao()){
                        case 1 -> {
                            InOut.MsgDeInformacao("Dano", defensor.getClass().getSimpleName() + " sofreu dano de: " + resultado.getDanoSofrido());
                        }
                        
                        case 2 -> {
                            InOut.MsgDeInformacao("Dano", defensor.getClass().getSimpleName() + " defendou, mas sofreu dano de: " + resultado.getDanoSofrido());
                        }
                        
                        case 3 -> {
                            InOut.MsgDeInformacao("Defesa", defensor.getClass().getSimpleName() + " defendeu");
                        }
                    }
                    
                    InOut.MsgDeInformacao("Vida", atacante.getClass().getSimpleName() + ": " + atacante.getVida() +
                            " | " + defensor.getClass().getSimpleName() + ": " + defensor.getVida());
                    
                    if(defensor.getVida() <= 0) {
                        InOut.MsgDeInformacao("Vitória", atacante.getClass().getSimpleName() + " venceu a batalha!");
                        return;
                    }
                }
                case 2 -> {
                    InOut.MsgDeInformacao("Fuga", atacante.getClass().getSimpleName() + " fugiu da batalha!");
                    InOut.MsgDeInformacao("Vitória", defensor.getClass().getSimpleName() + " venceu a batalha!");
                    return;
                }
                case 3 -> {
                    int newWeapon = InOut.leInt(atacante.getClass().getSimpleName() + ": Escolha uma nova arma: 1- Desarmado, 2 - Faca, 3 - Fuzil, 4 - Revolver") - 1;
                    if (newWeapon >= 0 && newWeapon < armas.length) {
                        atacante.setArma(armas[newWeapon]);
                        InOut.MsgDeInformacao("Troca de Arma", atacante.getClass().getSimpleName() + " trocou para " + armas[newWeapon].getClass().getSimpleName());
                    } else {
                        InOut.MsgDeErro("Erro", "Arma inválida!");
                    }
                }
                default -> InOut.MsgDeErro("Erro", "Ação inválida!");
            }
            turno++;
        }
    }

    public static void main(String[] args) {
        new JogoSimulacao();
    }
}
