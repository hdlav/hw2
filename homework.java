import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserInfoProcessor {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите данные в формате: Фамилия Имя Отчество Дата_рождения Номер_телефона Пол (f/m)");
            String input = scanner.nextLine();

            // Разделяем введенные данные по пробелам
            String[] parts = input.split(" ");

            if (parts.length != 6) {
                throw new Exception("Неверное количество данных");
            }

            String lastName = parts[0];
            String firstName = parts[1];
            String middleName = parts[2];
            String birthDate = parts[3];
            long phoneNumber = Long.parseLong(parts[4]);
            char gender = parts[5].charAt(0);

            // Проверка формата даты рождения
            if (!isValidDate(birthDate)) {
                throw new Exception("Неверный формат даты рождения. Используйте dd.mm.yyyy");
            }

            // Проверка пола (допустимы только 'f' и 'm')
            if (gender != 'f' && gender != 'm') {
                throw new Exception("Неверный формат пола. Используйте 'f' или 'm'");
            }

            // Создаем или открываем файл с именем фамилии и записываем данные
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(lastName + ".txt", true))) {
                String userInfo = lastName + " " + firstName + " " + middleName + " " + birthDate + " " + phoneNumber + " " + gender;
                writer.write(userInfo);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Данные успешно сохранены в файл.");

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static boolean isValidDate(String date) {
        // Проверка формата даты рождения (dd.mm.yyyy)
        String[] parts = date.split("\\.");
        if (parts.length != 3) {
            return false;
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return day >= 1 && day <= 31 && month >= 1 && month <= 12 && year >= 1900 && year <= 9999;
    }
}
