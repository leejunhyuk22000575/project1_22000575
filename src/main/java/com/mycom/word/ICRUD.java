package com.mycom.word;

public interface ICRUD {
    public Object add(int id);

    public int update(int id);

    public int delete(int id);

    public void selectOne(int id);

    public void saveFile();

    public void loadFile();
}