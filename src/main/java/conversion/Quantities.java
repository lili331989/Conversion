package conversion;

import java.util.HashMap;
import java.util.ArrayList;

public class Quantities {
    //содержит названия величин и их соотношения
    private HashMap<String, HashMap<String,Double>> quantityMap = new HashMap<>();
    private ArrayList <String> visitedQuantity=new ArrayList <> ();
    private String firstQuantity="";
    private String secondQuantity="";
    private Boolean found;
    private Double multiplier;

    public HashMap<String, HashMap<String, Double>> getQuantityMap() {
        return quantityMap;
    }

    public void setQuantityMap(HashMap<String, HashMap<String, Double>> quantityMap) {
        this.quantityMap = quantityMap;
    }

    // добавляет соотношения двух величин quantityName1 и quantityName2 с коэффициентом ratio
    public void addRatio(String quantityName1, String quantityName2,Double ratio) {
        if (!quantityMap.containsKey(quantityName1)) quantityMap.put(quantityName1, new HashMap<String,Double>());
        if (!quantityMap.containsKey(quantityName2)) quantityMap.put(quantityName2, new HashMap<String,Double>());

        HashMap <String,Double> setOfRatio1=quantityMap.get(quantityName1);
        HashMap <String,Double> setOfRatio2=quantityMap.get(quantityName2);
        setOfRatio1.put(quantityName2, ratio);
        setOfRatio2.put(quantityName1, 1/ratio);
    }
    //перевод величины
    //на входе - строка со знаком вопроса, на выходе - строка с определенным коэффициентом
    public String convert(String str){
        String[] lines=str.split(" ");
        String result="";
        found=false;
        multiplier=1.0;
        visitedQuantity.clear();
        try {
            firstQuantity=lines[1];
            secondQuantity=lines[4];
            search(firstQuantity);
            if (found) result=lines[0]+" "+ lines[1]+" = "+multiplier*Double.parseDouble(lines[0])+" "+lines[4];
            else result="Conversion not possible";
        }
        catch (Exception ex) {
            result="Incorrect data format!";
        }

        return result;
    }
    //поиск нужных соотношений и изменение коэффициента multiplier
    public void search(String quantityName) {
        if (!quantityMap.containsKey(quantityName)) return;
        visitedQuantity.add(quantityName);
        HashMap <String,Double> setOfRatio=quantityMap.get(quantityName);
        if (setOfRatio.keySet().contains(secondQuantity)) {
            found=true;
            multiplier=multiplier*setOfRatio.get(secondQuantity);
        }
        else {
            for (String v : setOfRatio.keySet()) {
                if (visitedQuantity.contains(v)) continue;
                search(v);
                if (found) {
                    multiplier=multiplier*setOfRatio.get(v);
                    break;
                }
            }
        }
    }
}

