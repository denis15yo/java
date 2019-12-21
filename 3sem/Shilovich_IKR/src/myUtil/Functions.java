package myUtil;

import essenses.*;

import java.util.*;
import java.util.stream.Collectors;

public class Functions {
    public static Set<String> organizationsTreeSet(List<Worker> list){
        return list.stream().map(Worker::getOrganization).collect(Collectors.toCollection(TreeSet::new));
    }

    public static Map<String, DoublePair> makeMap(List<Worker> list){
        Map<String, DoublePair> res = new TreeMap<>();
        Map<String, List<Double>> temp = new TreeMap<>();

        for(Worker w : list){
            String organization = w.getOrganization();
            if(temp.containsKey(organization)){
                temp.get(organization).add(w.calcSalary());
            }
            else{
                List<Double> l = new ArrayList<>();
                l.add(w.calcSalary());
                temp.put(organization, l);
            }
        }

        temp.forEach((k, v) -> {
            if(!v.isEmpty()){
                Double min = Collections.min(v);
                Double max = Collections.max(v);
                res.put(k, new DoublePair(min, max));
            }
        });

        return res;
    }
    
}
