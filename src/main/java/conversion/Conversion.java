package conversion;

import java.util.ArrayList;
import java.util.Scanner;

public class Conversion {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Quantities quantities = new Quantities();
        ArrayList<String> baseOfRequest=new ArrayList <> ();
        System.out.println("Type the word 'end', when you finish to enter the text");
        while(true){
            String str = in.nextLine();
            if (str.equals("end"))break;
            if (str.equals(""));
            else if (str.contains("?")){
                baseOfRequest.add(str);
            }
            else {
                String[] lines=str.split(" ");
                try {
                    double d=Double.parseDouble(lines[3])/Double.parseDouble(lines[0]);
                    quantities.addRatio(lines[1], lines[4], d);
                }
                catch (Exception ex) {
                    System.out.println("Incorrect data format!");
                }
            }
        }
        // перевод величин
        for (String request : baseOfRequest) {
            System.out.println(quantities.convert(request));
        }
    }
}
