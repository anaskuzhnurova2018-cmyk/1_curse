package hw_threads;

public class Element implements Runnable { //1 задача
    private Integer id; //номер задачи
    private Element[] dep; //какие должны выполниться до этой задачи
    private Thread[] threads;

    public Element(int id, Element[] dep, Thread[] threads) {
        this.id = id;
        this.dep = dep;
        this.threads = threads;
    }


    @Override
    public void run() {
        //ждем завершение всех зависимостей
        for (Element element : dep) {
            try {
                threads[element.id].join(); //join() останавливает текущий поток и ждет, пока указанный поток завершится
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //все зависимости выполнены, переходим к текущему
        try {
            print();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void print() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Выполнил работу " + id);
    }
}
