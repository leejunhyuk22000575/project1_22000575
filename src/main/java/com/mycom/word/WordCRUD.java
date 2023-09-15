package com.mycom.word;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class WordCRUD implements ICRUD{

    ArrayList<Word> list;
    Scanner s;
    private static final String FILENAME = "Dictionary.txt";
    WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
        loadFile();
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
    public void saveFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (Word word : list) {
                writer.println(word.getId() + "|" + word.getLevel() + "|" + word.getWord() + "|" + word.getMeaning());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFile() {
        list.clear();
        int numWord = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    int level = Integer.parseInt(parts[1]);
                    String word = parts[2];
                    String meaning = parts[3];
                    list.add(new Word(id, level, word, meaning));
                    numWord++;
                }
            }
        } catch (IOException e) {
            // 파일이 없거나 오류 발생 시 새로운 ArrayList를 생성합니다.
            list = new ArrayList<>();
        }
        System.out.println(numWord + "개의 단어 로드 완료!");

    }

    public void addWord(int id){
        Word one = (Word)add(id);
        list.add(one);
        System.out.println("\n새 단어가 단어장에 추가되었습니다!!!\n");
    }

    @Override
    public int update(int id) {
        System.out.print("\n=> 수정할 단어 검색 : ");
        String fixWord = s.next();
        System.out.println("------------------------");
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getWord().contains(fixWord)){
                System.out.println(list.get(i).getId() + " " + list.get(i).toString());
            }
        }
        System.out.println("------------------------");
        System.out.print("=> 수정할 번호 선택 : ");
        int fixNumberWord = s.nextInt();
        s.nextLine();
        System.out.print("=> 뜻 입력 : ");
        String newMeaning = s.nextLine();
        list.get(fixNumberWord-1).setMeaning(newMeaning);
        System.out.println("\n단어 수정이 성공적으로 되었습니다!!");
        return id;
    }

    @Override
    public int delete(int id) {
        System.out.print("\n=> 삭제할 단어 검색 : ");
        String deleteWord = s.next();
        System.out.println("------------------------");
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getWord().contains(deleteWord)){
                System.out.println(list.get(i).getId() + " " + list.get(i).toString());
            }
        }
        System.out.println("------------------------");
        System.out.print("=> 삭제할 번호 선택 : ");
        int deleteNumberWord = s.nextInt();
        System.out.print("=> 정말로 삭제하실래요?(Y/n) ");
        String deleteCheck = s.next();
        if(Objects.equals(deleteCheck, "Y")) {
            list.remove(deleteNumberWord-1);
            System.out.println("\n선택한 단어 삭제 완료!!");
        }
        else{
            System.out.println("삭제 명령 취소!");
        }
        return id;
    }

    @Override
    public void selectOne(int id) {

    }

    public void listAll(){
        System.out.println("\n------------------------");
        for(int i=0; i<list.size(); i++){
            System.out.println(i+1 + " " + list.get(i).toString());
        }
        System.out.println("------------------------\n");
    }

    public void listLevel() {
        int num = 1;
        System.out.print("\n=> 레벨(1:초급, 2:중급, 3:고급) 선택 : ");
        int wordLevel = s.nextInt();
        System.out.println("------------------------");
        for (int i = 0; i < list.size(); i++) {
            if (wordLevel == list.get(i).getLevel()) {
                System.out.println(num + " " + list.get(i).toString());
                num++;
            }
        }
        System.out.println("------------------------");
    }

    public void search(){
        int num = 1;
        System.out.print("\n=> 검색할 단어 입력 : ");
        String searchWord = s.next();
        System.out.println("------------------------");
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getWord().startsWith(searchWord)) {
                System.out.println(num + " " + list.get(i).toString());
                num++;
            }
        }
        System.out.println("------------------------");
    }
}