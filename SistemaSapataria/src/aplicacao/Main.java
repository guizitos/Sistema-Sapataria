package aplicacao;

import DAO.CalcadoService;
import DAO.VendaService;
import entity.Calcado;
import entity.Venda;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static CalcadoService calcadoService = new CalcadoService();
    private static VendaService vendaService = new VendaService();
    private static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String[] args) {
        int option;
        do {
            System.out.println("Menu:");
            System.out.println("1. Adicionar Calçado");
            System.out.println("2. Alterar Valor do Calçado");
            System.out.println("3. Excluir Calçado");
            System.out.println("4. Consultar Calçado");
            System.out.println("5. Listar Todos os Calçados");
            System.out.println("6. Registrar Venda");
            System.out.println("7. Mostrar Vendas Registradas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            option = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (option) {
                case 1:
                    adicionarCalcado();
                    break;
                case 2:
                    alterarValorCalcado();
                    break;
                case 3:
                    excluirCalcado();
                    break;
                case 4:
                    consultarCalcado();
                    break;
                case 5:
                    listarTodosCalcados();
                    break;
                case 6:
                    registrarVenda();
                    break;
                case 7:
                    mostrarVendasRegistradas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0);
        
        scanner.close();
    }
    

    private static void adicionarCalcado() {
        System.out.println("Adicionando um novo calçado.");

        System.out.print("Digite o código do calçado: ");
        int codigoCalcado = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Digite o nome do calçado: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a marca do calçado: ");
        String marca = scanner.nextLine();

        double valor = 0.0;
        boolean valorValido = false;
        while (!valorValido) {
            try {
                System.out.print("Digite o valor do calçado: ");
                valor = scanner.nextDouble();
                scanner.nextLine(); // Limpar o buffer
                valorValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Por favor, insira um número decimal com ponto (.) como separador.");
                scanner.nextLine(); // Limpar o buffer
            }
        }

        System.out.print("Digite a cor do calçado: ");
        String cor = scanner.nextLine();

        Calcado calcado = new Calcado(codigoCalcado, nome, marca, valor, cor);
        calcadoService.salvar(calcado);

        System.out.println("Calçado adicionado com sucesso!");
    }

    private static void alterarValorCalcado() {
        System.out.println("Alterando o valor de um calçado.");
    
        System.out.print("Digite o código do calçado: ");
        int codigoCalcado = scanner.nextInt();
        scanner.nextLine(); 
    
        Calcado calcado = calcadoService.consultar(codigoCalcado);
    
        if (calcado == null) {
            System.out.println("Calçado não encontrado.");
            return;
        }
    
        System.out.print("Digite o novo valor do calçado: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();
        calcado.setValor(valor);
    
        calcadoService.alterarValor(calcado); 
    
        System.out.println("Valor do calçado atualizado com sucesso!");
    }

    private static void excluirCalcado() {
        System.out.println("Excluindo um calçado.");

        System.out.print("Digite o código do calçado: ");
        int codigoCalcado = scanner.nextInt();
        scanner.nextLine(); 

        calcadoService.excluir(codigoCalcado);

        System.out.println("Calçado excluído com sucesso!");
    }

    private static void consultarCalcado() {
        System.out.println("Consultando um calçado.");
    
        System.out.print("Digite a marca do calçado: ");
        String marca = scanner.nextLine();
    
        List<Calcado> calcados = calcadoService.consultarPorMarca(marca);
    
        if (calcados.isEmpty()) {
            System.out.println("Nenhum calçado encontrado para a marca especificada.");
        } else {
            System.out.printf("%-10s %-30s %-20s %-10s %-10s%n", "Código", "Nome", "Marca", "Valor", "Cor");
            System.out.println("--------------------------------------------------------------------------------------");
            for (Calcado calcado : calcados) {
                System.out.printf("%-10d %-30s %-20s %-10.2f %-10s%n",
                    calcado.getCodigoCalcado(),
                    calcado.getNome(),
                    calcado.getMarca(),
                    calcado.getValor(),
                    calcado.getCor());
            }
        }
    }

    private static void listarTodosCalcados() {
        System.out.println("Listando todos os calçados.");
    
        List<Calcado> calcados = calcadoService.listarTodos();
        if (calcados.isEmpty()) {
            System.out.println("Nenhum calçado encontrado.");
        } else {
            System.out.printf("%-10s %-30s %-20s %-10s %-10s%n", "Código", "Nome", "Marca", "Valor", "Cor");
            System.out.println("---------------------------------------------------------------------------------------");
            for (Calcado calcado : calcados) {
                System.out.printf("%-10d %-30s %-20s %-10.2f %-10s%n",
                    calcado.getCodigoCalcado(),
                    calcado.getNome(),
                    calcado.getMarca(),
                    calcado.getValor(),
                    calcado.getCor());
            }
        }
    }    

    private static void registrarVenda() {
        System.out.println("Registrando uma nova venda.");
    
        System.out.print("Digite o código do calçado vendido: ");
        int codigoCalcado = scanner.nextInt();
        scanner.nextLine(); 
    
        Calcado calcado = calcadoService.consultar(codigoCalcado);
        if (calcado == null) {
            System.out.println("Calçado não encontrado.");
            return;
        }
    
        System.out.print("Digite o código do vendedor: ");
        int codigoVendedor = scanner.nextInt();
        scanner.nextLine(); 
    
        System.out.print("Digite a quantidade vendida: ");
        int quantidadeVendida = scanner.nextInt();
        scanner.nextLine();
    
        double valorTotal = quantidadeVendida * calcado.getValor();
    
        // Atualize o estoque
        boolean estoqueAtualizado = calcadoService.atualizarEstoque(codigoCalcado, quantidadeVendida);
        if (!estoqueAtualizado) {
            System.out.println("Não foi possível registrar a venda. Estoque insuficiente.");
            return;
        }
    
        // Registrar a venda
        Venda venda = new Venda(0, new java.sql.Date(System.currentTimeMillis()), codigoCalcado, codigoVendedor, quantidadeVendida, valorTotal);
        vendaService.salvar(venda);
    
        System.out.println("Venda registrada com sucesso!");
    }
    
    private static void mostrarVendasRegistradas() {
        System.out.println("Listando todas as vendas registradas.");
    
        List<Venda> vendas = vendaService.listarTodas();
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            System.out.printf("%-10s %-15s %-15s %-15s %-10s %-10s%n", "Código", "Data", "Código Calçado", "Código Vendedor", "Quantidade", "Valor Total");
            System.out.println("--------------------------------------------------------------------------");
            for (Venda venda : vendas) {
                System.out.printf("%-10d %-15s %-15d %-15d %-10d %-10.2f%n",
                    venda.getCodigoVenda(),
                    venda.getDataVenda(),
                    venda.getCodigoCalcado(),
                    venda.getCodigoVendedor(),
                    venda.getQuantidadeVendida(),
                    venda.getValorTotal());
            }
        }
    }
    
}
