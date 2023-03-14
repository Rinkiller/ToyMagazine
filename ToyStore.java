import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ToyStore {
  
    public void main(String[] args) {
        List<Toy> storage = new ArrayList<>();
        List<Toy> prizeStorage = new ArrayList<>();
        Toys toys = new Toys();
        boolean end = true;
        Scanner Scaner = new Scanner(System.in,"cp866");
        ToyFileManager fileMan = new ToyFileManager();
        if(storage.size() == 0)
                if(fileMan.getCountLineinfile("Toys.db") == 0) {
                    System.out.print("Внимание на складе нет игрушек!! Добавить новую y - да/n - нет");
                    boolean next = true;
                    while(next){
                        String s = Scaner.next();
                        if(s.equals("y")){
                            next = false;
                            storage.add(toys.addToy(1));
                            fileMan.writeFileOfToys(storage, "Toys.db");
                        }
                        else if(s.equals("n")){
                            System.out.println("В магазине нет игрушек!!! Дальнейшая работа невозможна((((");
                            System.exit(0);
                        }
                        else{
                            System.out.println("Пожалуйста выберите y - да/n - нет");
                        }

                    }
                }
                else {
                    storage.addAll(fileMan.readFileOfToys("Toys.db"));
                }
        if(fileMan.getCountLineinfile("Prizes.db") != 0){
            prizeStorage.addAll(fileMan.readFileOfToys("Prizes.db"));
        }      
        
        while(end){
            System.out.println();
            System.out.println("Вас приветствует автомат игрушек");
            System.out.println("Добавить игрушку - 1");
            System.out.println("Редактировать данные игрушки - 2");
            System.out.println("Вывести список разыгрываемых игрушек - 3");
            System.out.println("Провести розыгрыш игрушек - 4");
            System.out.println("Вывести список выигранных игрушек - 5");
            System.out.println("Получить игрушку - 6");
            System.out.println("Закончить работу с автоматом - 7");
            System.out.print("Пожалуйста сделайте ваш выбор: ");
            int number = 0;
            String s = Scaner.nextLine();
            try
                {
                    number  = Integer.parseInt(s.trim());   
                    switch(number){
                         case 1:{
                            int id = 0;
                            if(storage.size() == 0)  id = 1;
                            else  id = storage.get(storage.size() - 1).getId() + 1;
                            storage.add(toys.addToy(id));
                            fileMan.writeFileOfToys(storage, "Toys.db");
                            break;
                        }
                        case 2:{
                            System.out.println();
                            System.out.print("Пожалуйста введите название игрушки: ");
                            Collections.copy(storage, toys.editingToys(storage, Scaner.nextLine()));
                            fileMan.writeFileOfToys(storage, "Toys.db");
                            break;
                        }
                        case 3:{
                            System.out.println();
                            System.out.println(" В магазине на данный момент имеется следующий ассортимент: ");
                            toys.printToys(storage);
                            break;
                        }
                        case 4:{
                            System.out.println();
                            int id = toys.getIdFirstToy(storage);
                            if(id > -1){     //Есть выигрыш
                                prizeStorage.add(storage.get(id));
                                System.out.println("Мои поздравления!!! В этот раз выпала:");
                                System.out.println("Игрушка: " + storage.get(id).getName());
                                if(storage.get(id).getCount() == 1){ storage.remove(id);}//осталась одна игрушка
                            else{
                                storage.get(id).setCount(storage.get(id).getCount() - 1);}
                                fileMan.writeFileOfToys(storage, "Toys.db");
                                fileMan.writeFileOfToys(prizeStorage, "Prizes.db");
                            }
                            else{       //Шиш, а не выигрыш
                                System.out.println("Неповезло! Может в другой раз");
                            }
                            break;}
                        case 5:{//список игрушек
                            System.out.println(" На данный момент ожидают в корзине следующие выпавшие игрушки: ");
                            toys.printToys(prizeStorage);
                            break;
                        }
                        case 6:{
                            System.out.println();
                            System.out.println("Вот ваша игрушка: ");
                            System.out.println(prizeStorage.get(0).getName());
                            String newFileName = "Prize_of_" + prizeStorage.get(0).getName() +".db";
                            fileMan.writePrizeFile(prizeStorage.get(0), newFileName);
                            prizeStorage.remove(0);
                            fileMan.writeFileOfToys(prizeStorage, "Prizes.db");
                            break;
                        }
                        case 7:{
                            end = false;
                            System.out.println();
                            System.out.println("Спасибо что посетили наш магазин игрушек!");
                            break;
                        }
                        default:{System.out.println("Введенные данные не коректны, пожалуйста сделайте выбор одного пункта меню!" );}
                    }            
                }
            catch (NumberFormatException nfe)
                {
                    System.out.println("Введенные данные не коректны, пожалуйста сделайте выбор одного пункта меню!" );
                }
            
        }
    Scaner.close();
    }
    
}
