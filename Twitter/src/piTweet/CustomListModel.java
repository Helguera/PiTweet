package piTweet;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class CustomListModel extends AbstractListModel{
 
    private ArrayList<Tweet> lista = new ArrayList<>();
 
    @Override
    public int getSize() {
        return lista.size();
    }
 
    @Override
    public Object getElementAt(int index) {
        Tweet p = lista.get(index);
        return p.getText();
    }
    public void addTweet(Tweet p){
        lista.add(p);
        this.fireIntervalAdded(this, getSize(), getSize()+1);
    }
    public void eliminarPersona(int index0){
        lista.remove(index0);
        this.fireIntervalRemoved(index0, getSize(), getSize()+1);
    }
    public Tweet getTweet(int index){
        return lista.get(index);
    }
}