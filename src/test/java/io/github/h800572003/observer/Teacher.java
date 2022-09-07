package io.github.h800572003.observer;

public class Teacher implements TeacherData {

    private Observable<Teacher>observable=new Observable<>(this);

    private String name;
    private Action action=Action.NONE;

    enum  Action{
        ROLL_CALL,//點名
        NONE,//無行為
        ;
    }
    public Teacher(String name){
        this.name=name;
    }
    public void addStudent(ObservableTest.Student student){
        observable.add(student);
    }

    public void rollCall(){
        this.action=Action.ROLL_CALL;
        this.observable.notifyObservers();
    }

    public Action getAction() {
        return action;
    }

    @Override
    public String getName() {
        return name;
    }


}
