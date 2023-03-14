import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ToyFileManager {
    /**
     * @param fileName
     * @return
     */
    public  int getCountLineinfile(String fileName) {
        int count = 1;
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            while (fr.ready()) {
                if ((char) fr.read() == '\n')
                    count++;
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (count == 0) {
            System.out.println("Файл пустой. Дальнейшая работа невозможна!");
            System.exit(1);
        }
        return count;
    }
    /**
     * @param fileName
     * @return
     */
    public  String[] getFileOfToys(String fileName) {
        String[] strOffile = new String[getCountLineinfile(fileName)];
        int index = 0;
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                strOffile[index] = line;
                line = reader.readLine();
                index++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strOffile;
    }
    /**
     * @param fileName
     * @return
     */
    public  List<Toy> readFileOfToys(String fileName) {
        List<Toy> lstOfToys = new ArrayList<>();
        String[] arrOfToy = getFileOfToys(fileName);
        for(int count = 0; count < arrOfToy.length; count++){
            String[] arr_in_line = arrOfToy[count].trim().split(" ");
            lstOfToys.add(new Toy(Integer.parseInt(arr_in_line[0].trim()), arr_in_line[1], Integer.parseInt(arr_in_line[2].trim()), Integer.parseInt(arr_in_line[3].trim())));
        } 
        return lstOfToys;      
    }
    /**
     * @param storage
     * @param fileName
     */

    public  void writeFileOfToys(List<Toy> storage, String fileName) {
        try{
            FileWriter writer = new FileWriter(fileName);
            int counter = 0;
            for(Toy toy : storage) {
                counter++;
                String id = Integer.toString(toy.getId());
                String name = toy.getName();
                String count = Integer.toString(toy.getCount());
                String drop = Integer.toString(toy.getDropt());
                if(counter < storage.size())
                    writer.write(id + " " + name + " " + count + " " + drop + "\n");
                else
                    writer.write(id + " " + name + " " + count + " " + drop);
            }   
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        
    }
    public void writePrizeFile(Toy prize, String fileName) {
        String text = prize.getName();

        try {
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(text);
            bufferWriter.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        
    }
}
