import model.Human;
import model.Sex;
import model.Student;


public class Humans {
    public static final Human human1 = new Human("Иван", "Иванович", "Петров", 18, Sex.MALE);
    public static final Human human2 = new Human("Пётр", "Петрович", "Петров", 19, Sex.MALE);
    public static final Human human3 = new Human("Andrew", "White", 25, Sex.MALE);
    public static final Human human4 = new Human("Ann", "Field", 75, Sex.FEMALE);
    public static final Student student1 = new Student("Елена", "Алексеевна", "Алексеева", 18, Sex.FEMALE, "ОмГУ", "ИМИТ", "Прикладная математика и информатика");
    public static final Student student2 = new Student("Kate", "Brown", 22, Sex.FEMALE, "MIT", "School of Science", "Physics");
}
