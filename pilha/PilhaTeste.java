public class PilhaTeste{
    public static void main(String[] args){
        Pilha p = new PilhaArray(10, 0);
        System.out.print("Pilha Vazia? ");
        System.out.println(p.isEmpty());
        System.out.print("Tamanho da Pilha: ");
        System.out.println(p.size());
        p.push(10);
        p.push(20);
        p.push(25);
        p.push(0);
        p.push(2);
        System.out.print("Pilha Vazia? ");
        System.out.println(p.isEmpty());
        System.out.print("Tamanho da Pilha: ");
        System.out.println(p.size());
        
        System.out.print("Pilha Array: { ");
        for (int i = 0; i < p.size(); i++){
            System.out.print(((PilhaArray)p).get(i));
            System.out.print(", ");
        }
        System.out.println(" }");

        p.pop();
        System.out.print("Tamanho da Pilha: ");
        System.out.println(p.size());

        System.out.print("Pilha Array: { ");
        for (int i = 0; i < p.size(); i++){
            System.out.print(((PilhaArray)p).get(i));
            System.out.print(", ");
        }
        System.out.println(" }");

        p.pop();
        
        System.out.print("Pilha Array: { ");
        for (int i = 0; i < p.size(); i++){
            System.out.print(((PilhaArray)p).get(i));
            System.out.print(", ");
        }
        System.out.println(" }");

        p.push(4);

        System.out.print("Pilha Array: { ");
        for (int i = 0; i < p.size(); i++){
            System.out.print(((PilhaArray)p).get(i));
            System.out.print(", ");
        }
        System.out.println(" }");

        p.push(50);
        p.push(60);
        p.push(70);
        p.push(80);
        p.push(90);
        p.push(100);
        
        System.out.print("Pilha Array: { ");
        for (int i = 0; i < p.size(); i++){
            System.out.print(((PilhaArray)p).get(i));
            System.out.print(", ");
        }
        System.out.println(" }");
        
        System.out.println("Tamanho da Pilha: " + p.size());
        System.out.println("Topo da Pilha: " + p.top());

        p.push(19);
        System.out.println("Tamanho da Pilha: " + p.size());
        System.out.println("Topo da Pilha: " + p.top());

        System.out.print("Pilha Array: { ");
        for (int i = 0; i < p.size(); i++){
            System.out.print(((PilhaArray)p).get(i));
            System.out.print(", ");
        }
        System.out.println(" }");
        
    }
}