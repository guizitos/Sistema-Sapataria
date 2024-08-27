package aplicacao;

import DAO.CalcadoService;
import entity.Calcado;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static CalcadoService calcadoService = new CalcadoService();
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
}
