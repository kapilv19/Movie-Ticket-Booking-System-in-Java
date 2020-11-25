import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class TestClass {

    private static ArrayList<Screen> mScreens;

    private static class Screen {
        String id;
        ArrayList<Integer> aisleSeats;
        int rows, columns;
        boolean[][] reservedSeats;

        Screen(String id, int rows, int columns) {
            this.id = id;
            aisleSeats = new ArrayList<>();
            this.rows = rows;
            this.columns = columns;
            reservedSeats = new boolean[rows][columns];
        }

        boolean addAisleSeat(int seatId) {
            if (seatId > this.columns) return false;
            if (!aisleSeats.contains(seatId)) aisleSeats.add(seatId-1);
            return true;
        }

        boolean reserveSeat(int row, int seatId) {
            if (reservedSeats[row-1][seatId-1]) return false;

            reservedSeats[row-1][seatId-1] = true;
            return true;
        }

        void unreserveSeat(int row, int seatId) {
            reservedSeats[row-1][seatId-1] = false;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof Screen)) return false;
            if (o == null) return false;

            Screen s = (Screen) o;
            return (s.id == null ? this.id == null : s.id.equals(this.id));
        }

        ArrayList<Integer> getUnreservedSeats(int row) {
            if (row > this.rows) return null;
            ArrayList<Integer> seats = new ArrayList<>();
            for (int i = 0; i<this.columns; i++) {
                if (!reservedSeats[row-1][i]) seats.add(i+1);
            }
            return seats;
        }

        ArrayList<Integer> getContagiousSeats(int row, int n, int c) {
            if (row > this.rows) return null;
            ArrayList<Integer> seats = new ArrayList<>();
            if (reservedSeats[row-1][c-1]) return seats;

            Collections.sort(aisleSeats);
            int rc = 0, lc = 0;
            for (int i = c-1; ;i++) {
                rc++;
                if ((aisleSeats.contains(i) && aisleSeats.indexOf(i)%2 == 0) || i == this.rows - 1) break;
            }

            for (int i = c-1; ;i--) {
                lc++;
                if ((aisleSeats.contains(i) && aisleSeats.indexOf(i)%2 == 1) || i == 0) break;
            }

            if (rc < n && lc < n) return seats;

            if (rc >= n) {
                for (int i = 0; i<n; i++) seats.add(c + i);
            } else if (lc >= n) {
                for (int i = n; i>0; i--) seats.add(c - i + 1);
            }
            
            return seats;
        }

        static Screen findScreen(ArrayList<Screen> screens, String id) {
            for (Screen s : screens) {
                if (s.id.equals(id)) return s;
            }
            return null;
        }
    }

    private static boolean addScreen(String[] args) {
        Screen s = new Screen(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        if (mScreens.contains(s)) return false;

        for (int i = 4; i<args.length; i++) {
            if (!s.addAisleSeat(Integer.parseInt(args[i]))) return false;
        }

        mScreens.add(s);

        return true;
    } 

    private static boolean reserveSeat(String[] args) {
        Screen s = Screen.findScreen(mScreens ,args[1]);
        if (s == null) return false;

        int row = Integer.parseInt(args[2]);

        for (int i = 3; i<args.length; i++) {
            if (!s.reserveSeat(row, Integer.parseInt(args[i]))) {
                for (int j = i-1; j >= 3; j--) {
                    s.unreserveSeat(row, Integer.parseInt(args[j]));
                }
                return false;
            }
        }

        return true;
    } 

    private static ArrayList<Integer> getUnreservedSeats(String[] args) {
        Screen s = Screen.findScreen(mScreens ,args[1]);
        if (s == null) return null;
        return s.getUnreservedSeats(Integer.parseInt(args[2]));
    }

    private static ArrayList<Integer> getContiguousSeats(String[] args) {
        Screen s = Screen.findScreen(mScreens,args[1]);
        if (s == null) return null;
        return s.getContagiousSeats(Integer.parseInt(args[3]), Integer.parseInt(args[2]), Integer.parseInt(args[4]));
    }

    private static void init() {
        mScreens = new ArrayList<>();
    }

    public static void main(String args[] ) throws Exception {
        init();

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t >= 0) {
            String s = sc.nextLine();
            String[] argsIn = s.split(" ");

            switch(argsIn[0]) {
                case "add-screen":
                    if (addScreen(argsIn)) System.out.println("success");
                    else System.out.println("failure");
                    break;

                case "reserve-seat":
                    if (reserveSeat(argsIn)) System.out.println("success");
                    else System.out.println("failure");
                    break;

                case "get-unreserved-seats":
                    ArrayList<Integer> seats = getUnreservedSeats(argsIn);
                    if (seats == null) System.out.println("failure");
                    else if (seats.size() == 0) System.out.println("none");
                    else {
                        for (int i=0;i<seats.size();i++) {
                            if (i!=seats.size() -1)
                            System.out.print(seats.get(i) + " ");
                            else System.out.println(seats.get(i));
                        }
                    }
                    break;

                case "suggest-contiguous-seats":
                    ArrayList<Integer> cseats = getContiguousSeats(argsIn);
                    if (cseats == null) System.out.println("failure");
                    else if (cseats.size() == 0) System.out.println("none");
                    else {
                        for (int i=0;i<cseats.size();i++) {
                            if (i!=cseats.size() -1)
                            System.out.print(cseats.get(i) + " ");
                            else System.out.println(cseats.get(i));
                        }
                    }
                    break;
            }
            t--;
        }
        sc.close();
    }
}
