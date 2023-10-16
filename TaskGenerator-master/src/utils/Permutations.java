/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author djordje
 */
public class Permutations<T> {
//returns all permutations of argument

    public List<T> permute(List<T> start) {
        List<T> result = new ArrayList<>();
        recurse(start, result, 0, start.size() - 1);
        return result;
    }

    private void recurse(List<T> current, List<T> result, int start, int end) {
        if (start == end) {
            result.addAll(current);
            return;
        }
        for (int i = start; i <= end; i++) {
            T tmp = current.get(start);
            current.set(start, current.get(i));
            current.set(i, tmp);
            
            recurse(current, result, start + 1, end);
            
            tmp = current.get(start);
            current.set(start, current.get(i));
            current.set(i, tmp);
        }
    }

  

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
//        list.add(5);
        Permutations<Integer> p = new Permutations<>();
        List<Integer> ps = p.permute(list);
        for (int i = 1; i <= ps.size(); i++) {
            System.out.print(ps.get(i - 1));
            if (i % list.size() == 0 && i > 0) {
                System.out.println();
            }
        }
    }

}
