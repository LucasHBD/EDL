public class PilhaRubroNegra {
    private int posicaoVermelha;
    private int posicaoPreta;
    private Object[] PilhaRubroNegra;
    private int capacidade;
    private int FC;

    public PilhaRubroNegra(int capacidade, int crescimento){
        this.capacidade = capacidade;
        this.posicaoVermelha = -1;
        this.posicaoPreta = capacidade;
        this.FC = crescimento;
        if (crescimento <= 0) FC = 0;
        
        PilhaRubroNegra = new Object[capacidade];
    }

    public Object topVermelho(){
        return posicaoVermelha;
    }

    public Object topPreto(){
        return posicaoPreta;
    }

    public int sizeVermelho(){
        return posicaoVermelha + 1;
    }

    public int sizePreto(){
        return capacidade - posicaoPreta;
    }

    public int sizeArray(){
        return sizeVermelho() + sizePreto();
    }

    public int sizeDisponivel(){
        return capacidade - sizeArray();
    }

    public boolean isEmptyVermelho(){
        return posicaoVermelha == -1;
    }

    public boolean isEmptyPreto(){
        return posicaoPreta == capacidade;
    }

    public void pushVermelho(Object o){
        if(posicaoVermelha >= capacidade - 1){
            if (FC == 0){
            capacidade *= 2;
            }
            else{
                capacidade += FC;
            }
            Object novo_array[] = new Object[capacidade];
            for (int i = 0; i < PilhaRubroNegra.length; i++) {
                novo_array[i] = PilhaRubroNegra[i];
            }
            PilhaRubroNegra = novo_array;
        }

        PilhaRubroNegra[++posicaoVermelha] = o;
    }

    public void pushPreto(Object o){
        if(posicaoPreta <= posicaoVermelha + 1){
            if (FC == 0){
                capacidade *= 2;
            }
            else{
                capacidade += FC;
            }
            Object novo_array[] = new Object[capacidade];
            for (int i = 0; i < PilhaRubroNegra.length; i++) {
                novo_array[i] = PilhaRubroNegra[i];
            }
            PilhaRubroNegra = novo_array;
            posicaoPreta = capacidade;
        }

        PilhaRubroNegra[--posicaoPreta] = o;
    }

    public Object popVermelho() throws PilhaVaziaExcecao{
        if (isEmptyVermelho()) throw new PilhaVaziaExcecao("Pilha Vazia!");
        if (sizeArray() <= capacidade/3) reducao();
        PilhaRubroNegra[posicaoVermelha] = null;
        posicaoVermelha = posicaoVermelha - 1;
        return PilhaRubroNegra[posicaoVermelha + 1];
    }

    public Object popPreto() throws PilhaVaziaExcecao{
        if (isEmptyPreto()) throw new PilhaVaziaExcecao("Pilha Vazia!");
        if (sizeArray() <= capacidade/3) reducao();
        PilhaRubroNegra[posicaoPreta] = null;
        posicaoPreta = posicaoPreta + 1;
        return PilhaRubroNegra[posicaoPreta - 1];
    }

    public void reducao(){
        int qtdVermelhos = sizeVermelho();
        int qtdPretos = sizePreto();

        int novaCapacidade = capacidade/2;
        if(novaCapacidade < qtdVermelhos + qtdPretos){
            novaCapacidade = qtdVermelhos + qtdPretos;
        }// Verifica se a nova capacidade é menor que a quantidade de elementos na pilha
        Object novo_array[] = new Object[novaCapacidade];
        
        for (int i = 0; i < qtdVermelhos; i++){
            novo_array[i] = PilhaRubroNegra[i];
        }// faz a cópia dos vermelhos

        int novaPosicaoPreta = novaCapacidade - qtdPretos;
        for(int i = 0; i < qtdPretos; i++){
            novo_array[novaPosicaoPreta + i] = PilhaRubroNegra[posicaoPreta + i];
        }// faz a cópia dos pretos

        PilhaRubroNegra = novo_array;
        capacidade = novaCapacidade;
        posicaoPreta = novaPosicaoPreta;
        posicaoVermelha = qtdVermelhos - 1;
    }

    public static void main(String[] args){

        PilhaRubroNegra p = new PilhaRubroNegra(10, 0);
        System.out.println("Tamanho do Array: " + p.sizeArray());
        p.pushVermelho(1);
        System.out.println("Tamanho do Array: " + p.sizeArray());
        p.pushVermelho(2);
        p.pushVermelho(3);
        p.pushVermelho(4);
        p.pushVermelho(5);
        System.out.println("Tamanho do Array: " + p.sizeArray());
        p.pushPreto(1);
        System.out.println("Tamanho do Array: " + p.sizeArray());
        p.pushPreto(2);
        p.pushPreto(3);
        p.pushPreto(4);
        p.pushPreto(5);
        p.pushPreto(6);
        System.out.println("Tamanho do Array: " + p.sizeArray());

        System.out.println("Tamanho do Array: " + p.sizeArray());
        System.out.print("Pilha Rubro Negra: { ");
        for (int i = 0; i < p.capacidade; i++){
            System.out.print(p.PilhaRubroNegra[i]);
            System.out.print(", ");
        }
        System.out.println(" }");

        p.popVermelho();
        p.popPreto();
        System.out.println("Tamanho do Array: " + p.sizeArray());

        System.out.print("Pilha Rubro Negra: { ");
        for (int i = 0; i < p.capacidade; i++){
            System.out.print(p.PilhaRubroNegra[i]);
            System.out.print(", ");
        }
        System.out.println(" }");

        p.pushVermelho(2);
        p.pushVermelho(3);

        System.out.println(p.topVermelho());
        System.out.println(p.topPreto());
        
    }
}