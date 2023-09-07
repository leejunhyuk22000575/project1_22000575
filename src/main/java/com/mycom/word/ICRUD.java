package com.mycom.word;

public interface ICRUD {
    public Object add(int id);
    public int update(Object obj);
    public int delete(Object obj);
    public void selectOne(int id);
}