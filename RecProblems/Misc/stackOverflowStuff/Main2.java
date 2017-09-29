package stackOverflowStuff;

class T1 extends Thread {
    private Main2 s;
    private int t;
    T1 (Main2 s) { this.s = s; }
    public void run()
    {
        while(true) {
            try { Thread.sleep(1000); }
            catch (InterruptedException e) { e.printStackTrace(); }
            s.setSharedTime (++t);
            System.out.print(t + " ");
        }
    }
}

class T2 extends Thread {
    private Main2 s;
    T2 (Main2 s) { this.s = s; }
    public void run()
    {
        while(true) { 
            @SuppressWarnings("unused")
			int t = s.getSharedTime ();
            System.out.println();
            System.out.println ("7 second message");
        }
    }
}

class T3 extends Thread {
    private Main2 s;
    T3 (Main2 s) { this.s = s; }
    public void run()
    {
        while(true) { 
            @SuppressWarnings("unused")
			int t = s.getSharedTime ();
            System.out.println();
            System.out.println ("15 second message");
        }
    }
}

public class Main2 {
    private int time;
    private boolean share = true;

    public static void main(String[] args) {
        Main2 s = new Main2();
        new T1 (s).start();
        new T2 (s).start();
        new T3 (s).start();
    }

    synchronized void setSharedTime (int s) {
        while (!share) {
            try { wait (); }
            catch (InterruptedException e) {}
        }
        this.time = s;
        if(s % 7 == 0)
            share = false;
        if(s % 15 == 0)
            share = false;
        notify ();
    }

    synchronized int getSharedTime () {
        while (share) {
            try { wait (); }
            catch (InterruptedException e) { }
        }
        share = true;
        notify ();
        return time;
    }
}