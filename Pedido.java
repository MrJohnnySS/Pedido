import java.util.Scanner; // Importe a classe Scanner

// Classe base para os tipos de pizza (Bridge)
abstract class Pizza {
    protected String descricao;

    public Pizza(String descricao) {
        this.descricao = descricao;
    }

    public abstract double preco();
}

// Pizzas concretas (implementando o Bridge)
class PizzaPortuguesa extends Pizza {
    public PizzaPortuguesa() {
        super("Pizza Portuguesa");
    }

    @Override
    public double preco() {
        return 34.99;
    }
}

class PizzaCalabresa extends Pizza {
    public PizzaCalabresa() {
        super("Pizza Calabresa");
    }

    @Override
    public double preco() {
        return 34.99;
    }
}

// Interface para os tipos de pagamento usando Adapter
interface PagamentoAdapter {
    void pagamento(double amount);
}

// Adapter para diferentes formas de pagamento
class CartaoCreditoAdapter implements PagamentoAdapter {
    private String numeroCartaoCredito;

    public CartaoCreditoAdapter(String numeroCartaoCredito) {
        this.numeroCartaoCredito = numeroCartaoCredito;
    }

    @Override
    public void pagamento(double amount) {
        System.out.println("Pagamento de " + amount + " com cartão de crédito (" + numeroCartaoCredito + ")");
    }
}

class CartaoDebitoAdapter implements PagamentoAdapter {
    private String numeroCartaoDebito;

    public CartaoDebitoAdapter(String numeroCartaoDebito) {
        this.numeroCartaoDebito = numeroCartaoDebito;
    }

    @Override
    public void pagamento(double amount) {
        System.out.println("Pagamento de " + amount + " com cartão de débito (" + numeroCartaoDebito + ")");
    }
}

class PagamentoPixAdapter implements PagamentoAdapter {
    private String cnpj;

    public PagamentoPixAdapter(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public void pagamento(double amount) {
        System.out.println("Pagamento de " + amount + " com Pix (CNPJ: " + cnpj + ")");
    }
}

// Decorator para a leitura do teclado
class KeyboardInputDecorator {
    private static Scanner scanner = new Scanner(System.in);

    public static String readInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }
}

// Classe principal
public class Pedido {
    public static void main(String[] args) {
        System.out.println("Bem-vindo ao PizzaDuChefin!");

        // Pedir ao usuário que escolha uma pizza
        String escolhaPizza = KeyboardInputDecorator.readInput("Escolha a pizza (Portuguesa/Calabresa):");

        Pizza pizza;
        if (escolhaPizza.equalsIgnoreCase("Portuguesa")) {
            pizza = new PizzaPortuguesa();
        } else if (escolhaPizza.equalsIgnoreCase("Calabresa")) {
            pizza = new PizzaCalabresa();
        } else {
            System.out.println("Opção de pizza inválida.");
            return;
        }

        // Pedir ao usuário que escolha uma forma de pagamento
        String escolhaPagamento = KeyboardInputDecorator.readInput("Escolha a forma de pagamento (Cartão de Crédito/Cartão de Débito/Pix):");

        PagamentoAdapter pagamentoAdapter;
        if (escolhaPagamento.equalsIgnoreCase("Cartão de Crédito")) {
            String numeroCartaoCredito = KeyboardInputDecorator.readInput("Digite o número do cartão de crédito:");
            pagamentoAdapter = new CartaoCreditoAdapter(numeroCartaoCredito);
        } else if (escolhaPagamento.equalsIgnoreCase("Cartão de Débito")) {
            String numeroCartaoDebito = KeyboardInputDecorator.readInput("Digite o número do cartão de débito:");
            pagamentoAdapter = new CartaoDebitoAdapter(numeroCartaoDebito);
        } else if (escolhaPagamento.equalsIgnoreCase("Pix")) {
            String cnpj = KeyboardInputDecorator.readInput("Digite o CNPJ da loja:");
            pagamentoAdapter = new PagamentoPixAdapter(cnpj);
        } else {
            System.out.println("Forma de pagamento inválida.");
            return;
        }

        // Realizar o pagamento
        double precoTotal = pizza.preco();
        pagamentoAdapter.pagamento(precoTotal);

        // Conclusão do pedido
        System.out.println("Pedido realizado com sucesso!");
    }
}


