import constant.ActionsEnum;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {
  private static CostumerService costumerService = new CostumerServiceImpl();
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int option;
    Map<Integer, Runnable> actions = initializeActions();

    do {
      displayMenu();
      option = getUserOption();
      executeAction(actions, option);
    } while (option != ActionsEnum.EXIT.getValue());
  }

  private static Map<Integer, Runnable> initializeActions() {
    Map<Integer, Runnable> actions = new HashMap<>();
    actions.put(ActionsEnum.EXIT.getValue(), Main::exit);
    actions.put(ActionsEnum.CREATE.getValue(), Main::createCostumer);
    actions.put(ActionsEnum.LIST.getValue(), Main::listCostumers);
    actions.put(ActionsEnum.UPDATE.getValue(), Main::updateCostumer);
    actions.put(ActionsEnum.REMOVE.getValue(), Main::removeCostumer);
    actions.put(ActionsEnum.SEARCH.getValue(), Main::searchCostumer);
    return actions;
  }

  private static void displayMenu() {
    System.out.println("\nEscolha uma opção:");
    System.out.println(ActionsEnum.EXIT.getValue() + " - Sair");
    System.out.println(ActionsEnum.CREATE.getValue() + " - Cadastrar");
    System.out.println(ActionsEnum.LIST.getValue() + " - Listar");
    System.out.println(ActionsEnum.UPDATE.getValue() + " - Atualizar");
    System.out.println(ActionsEnum.REMOVE.getValue() + " - Remover");
    System.out.println(ActionsEnum.SEARCH.getValue() + " - Buscar");
    System.out.print("Digite o número da opção desejada: ");
  }

  private static int getUserOption() {
    try {
      return scanner.nextInt();
    } catch (InputMismatchException e) {
      System.out.println("Entrada inválida. Por favor, digite um número.");
      scanner.next();
      return -1;
    }
  }

  private static void executeAction(Map<Integer, Runnable> actions, int option) {
    Runnable action = actions.get(option);
    if (action != null) {
      action.run();
    } else {
      System.out.println("Opção inválida.");
    }
    confirmContinue();
  }

  private static void confirmContinue() {
    System.out.println("\nPressione Enter para continuar...");
    scanner.nextLine();
    scanner.nextLine();
    cleanScreen();
  }

  private static void cleanScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  private static void exit() {
    System.out.println("Saindo...");
  }

  private static void createCostumer() {
    scanner.nextLine();
    System.out.print("Digite o nome do cliente: ");
    String name = scanner.nextLine();
    System.out.print("Digite o email do cliente: ");
    String email = scanner.nextLine();
    System.out.print("Digite o telefone do cliente: ");
    String phone = scanner.nextLine();

    Costumer costumer = new Costumer(name, email, phone);
    costumerService.create(costumer);
    System.out.println("Cliente cadastrado com sucesso!");
  }

  private static void listCostumers() {
    for (Costumer costumer : costumerService.getAll()) {
      System.out.println(costumerService.toString(costumer));
    }
  }

  private static void updateCostumer() {
    scanner.nextLine();
    System.out.print("Digite o ID do cliente que deseja atualizar: ");
    Long id = scanner.nextLong();
    scanner.nextLine();

    System.out.print("Digite o nome do cliente: ");
    String name = scanner.nextLine();
    System.out.print("Digite o email do cliente: ");
    String email = scanner.nextLine();
    System.out.print("Digite o telefone do cliente: ");
    String phone = scanner.nextLine();

    Costumer costumer = new Costumer(name, email, phone);
    try {
      costumerService.updateById(id, costumer);
      System.out.println("Cliente atualizado com sucesso!");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private static void removeCostumer() {
    scanner.nextLine();
    System.out.print("Digite o ID do cliente que deseja remover: ");
    Long id = scanner.nextLong();
    try {
      costumerService.deleteById(id);
      System.out.println("Cliente removido com sucesso!");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private static void searchCostumer() {
    scanner.nextLine();
    System.out.print("Digite o ID do cliente que deseja buscar: ");
    Long id = scanner.nextLong();
    try {
      Costumer costumer = costumerService.getById(id);
      System.out.println(costumerService.toString(costumer));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
