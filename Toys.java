
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Toys {
    public  int getIntCount(String str, boolean drop){
        Scanner Scaner = new Scanner(System.in,"cp866");
        int count = 0;
        boolean end = true;
        while(end){
            System.out.print(str);
            String s = Scaner.nextLine();
            try{
                count  = Integer.parseInt(s.trim());
                if ( count > 0 )
                    end = false;
                    if(drop)
                        if(count > 100){
                            System.out.println("Введенные данные не коректны, пожалуйста введите целое число от 0 до 100" );
                            end = true; 
                        }
                else{
                    System.out.println("Введенные данные не коректны, пожалуйста введите целое число больше 0" ); 
                }
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("Введенные данные не коректны, пожалуйста введите целое число больше 0" );
            }

        }
        return count;
    }
    
    public  Toy addToy(int id){
        Scanner Scaner = new Scanner(System.in,"cp866");
        System.out.print("Введите название игрушки: ");
        String name = Scaner.nextLine();
        int count = getIntCount("Введите колличество данных игрушек: ", false);    
        int dropt = getIntCount("Введите шанс выпадения данных игрушек: ", true);
        System.out.println();
        return new Toy(id,name,count,dropt);
    }
    public  void printToys(List<Toy> lstToys) {
        int size = lstToys.size();
        for(int count = 0; count < size; count++){
            System.out.println("Игрушка: " + lstToys.get(count).getName() + " Колличество: " + lstToys.get(count).getCount() + " Шанс выпадения: " + lstToys.get(count).getDropt());
        }
        System.out.println();
    }
    public  List<Toy> editingToys(List<Toy> lstToy, String name) {
        boolean gatcha = false;
        int idGatcha = 0;
        for(int count = 0; count < lstToy.size(); count++)
            if(lstToy.get(count).getName().equals(name)){
                gatcha = true;
                idGatcha = count;
            }
        if(gatcha){
            lstToy.get(idGatcha).setDropt(getIntCount("Введите шанс выпадения данных игрушек: ",true));
        }
        else{
            System.out.println(" На складе нет игрушек с названием:" + name);
        }
        return lstToy;
        
    }
 
    public  int getIdFirstToy(List<Toy> lstToy) {
        int prizeId = - 1;
        List<Toy> newList = new ArrayList<>(lstToy.size());
        newList.addAll(lstToy);
        newList.sort(null);
        for(int count = 0; count < newList.size(); count++)
        { 
            if(Math.random() * 100 < newList.get(count).getDropt()) {
                prizeId = count;
                break;
            }
        }

        if(prizeId == -1)
            return -1;
        else{
            int count1 = 0;
            for(int i = 0 ; i < lstToy.size(); i++)
            {
                if(lstToy.get(i).getId() == newList.get(prizeId).getId()){
                    count1 = i;    
                    break;
                }
            }
            return  count1;
        }
    }
 
}
