import java.io.BufferedReader;
import java.io.InputStreamReader;


class UbuntuCommander{
    public static void main(String[] args) {
        String s;
        Process p;
        try{
            p = Runtime.getRuntime().exec("ls -aF");
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while((s = br.readLine()) != null)
                System.out.println("Line: " + s);
            p.waitFor();
            System.out.println("exit:" + p.exitValue());
            p.destroy();
        }
        catch (Exception e){}

       
        System.out.println("Ghbdtn");
    }
}
