package tabelaHash;

public class hash_table {
    private No[] array;
    private int capacidade, tamanho;

    public hash_table(int capacidade){
        this.tamanho = 0;

        this.array = new No[capacidade];
    }

    private int hash(Object key){
        if(key == null) return 0;

        return Math.abs(key.hashCode()) % capacidade;
    }

    public void insert(Object key, Object elemento){
        if(capacidade == tamanho)
    }
}
