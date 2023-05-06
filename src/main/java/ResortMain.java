import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class ResortMain {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ServiceException, IOException {
        int choice;
        String endpoint = "http://localhost:8080/axis/Resort.jws";
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new URL(endpoint));

        while (true) {
            while (true) {
                try {
                    do {
                        System.out.println("\nВыберите действие: " +
                                "\n 1. Список всех туров" +
                                "\n 2. Поиск по курорту" +
                                "\n 3. Поиск по цене" +
                                "\n 0. Выход");
                        choice = Integer.parseInt(scanner.next());
                    } while (choice < 0 || choice > 3);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Неверный ввод!");
                }
            }

            switch (choice) {
                case 1:
                    String allResponse = (String) call.invoke("findAll", new Object[]{"all"});
                    printResponse(allResponse);
                    break;
                case 2:
                    System.out.println("Введите название курорта:");
                    String name = scanner.next();
                    String nameResponse = (String) call.invoke("findByName", new Object[]{name});
                    printResponse(nameResponse);
                    break;
                case 3:
                    System.out.println("Введите цену:");
                    String price = scanner.next();
                    String priceResponse = (String) call.invoke("findByPrice", new Object[]{price});
                    printResponse(priceResponse);
                    break;
                case 0:
                    return;
            }
        }
    }

    private static void printResponse(String response) {
        String[] result = response.split(";");
        if (result.length > 0) {
            String formattedResult = String.join("\n", result);
            System.out.println(formattedResult);
        } else {
            System.out.println("Нет результатов.");
        }
    }
}
