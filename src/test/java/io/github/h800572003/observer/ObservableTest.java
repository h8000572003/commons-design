package io.github.h800572003.observer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@Slf4j
class ObservableTest {


    private final Teacher teacher = new Teacher("小明老師");
    private Student tom = Mockito.spy(new Student());
    private Student mark = Mockito.spy(new Student());

    static public class Student implements IObserver<TeacherData> {

        @Override
        public void update(IObservable<TeacherData> IObservable) {
            TeacherData data = IObservable.getData();
            log.info("IObservable name:{}, {}",data.getName(), data.getAction());
        }
    }

    @BeforeEach
    void init() {
        this.teacher.addStudent(tom);
        this.teacher.addStudent(mark);

    }

    @Test
    public void test_goIn_then_student_update() {
        this.teacher.rollCall();

        Mockito.verify(tom, Mockito.times(1)).update(Mockito.any());
        Mockito.verify(mark, Mockito.times(1)).update(Mockito.any());
    }

}

