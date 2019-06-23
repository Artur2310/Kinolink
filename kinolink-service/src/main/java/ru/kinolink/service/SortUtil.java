package ru.kinolink.service;

import java.util.Arrays;
import java.util.List;

public class SortUtil {

    private SortUtil(){}

    public static class MovieSort {

        private MovieSort(){}


        private static  List<String> myList = Arrays.asList("title", "imdb", "release_date");

        public static boolean isValid(String sort){
            return myList.stream().anyMatch(str -> str.trim().equals(sort.toLowerCase()));
        }

        public static List<String> getList(){
            return myList;
        }
    }
}
