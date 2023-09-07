package com.mycom.word;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{

    ArrayList<Word> list;
    Scanner s;
    WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }
    @Override
    public Object add(int id) {
        System.out.print("\n=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.next();
        s.nextLine();

        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();
        return new Word(id, level, word, meaning);
    }

    public void addWord(int id){
        Word one = (Word)add(id);
        list.add(one);
        System.out.println("\n새 단어가 단어장에 추가되었습니다!!!\n");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }

    public void listAll(){
        System.out.println("\n------------------------");
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i).getId() + " " + list.get(i).toString());
        }
        System.out.println("------------------------\n");
    }
}
