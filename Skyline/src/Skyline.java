/**
 * Alexandros Korkos
 * alexkork@csd.auth.gr
 * 3870
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Skyline {

    private static class File {
        private Boolean first = true;
        private Integer N;
        private Point[] Points;

        /**
         * Διαβάζει και αποθηκεύει σε ενα πίνακα τα σημεία μέσα απο ενα αρχείο,
         * @param name το όνομα του αρχείου για ανάγνωση.
         */

        public File(String name){
            try {
                BufferedReader file = new BufferedReader(new FileReader(name));
                String text;
                int i = 0;
                while ((text = file.readLine()) != null) {
                    if (first) {
                        N = Integer.parseInt(text);
                        Points = new Point[N];
                        first = false;
                    } else {
                        String[] numbers = text.split("\\s+");
                        Point point = new Point(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
                        Points[i] = point;
                        i++;
                    }
                }
                file.close();
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }

        public Point[] getPoints() {
            return Points;
        }

        public Integer getN() {
            return N;
        }
    }

    /**
    Κλάση που αναπαριστά ένα σημείο στο επίπεδο
     */
    private static class Point{

        private Integer x, y;

        public Point(Integer x, Integer y){
            this.x = x;
            this.y = y;
        }

        public Integer getX() {
            return x;
        }

        public Integer getY() {
            return y;
        }

        /**
         * Μέθοδος για τη σύγκριση δυο σημείων στο επίπεδο.
         * Εξετάζεται αρχικά η συντεταγμένη X, ενω εαν ίδια εξετάζεται η Y.
         * @param point ενα απο τα δυο σημεία που θα πρέπει να συγκριθεί
         * @return επιστρέφεται (αληθής / ψευδής) εαν το σημείο στο οποίο εφαρμόζεται η μέθοδος είναι μικρότερο απο το
         * σημείο που περνά σαν όρισμα.
         */
        public Boolean isSmaller(Point point){
            if (this.getX() < point.getX())
                return true;
            else if (this.getX() > point.getX())
                return false;
            else
                return this.getY() < point.getY();
        }

        @Override
        public String toString(){
            return this.getX() + " " + this.getY();
        }
    }

    /**
     * Επιστρέφει την κορυφογραμμή για τα δεδομένα σημεία.
     * Ουσιαστικά δέχεται τα ταξινομημένα σημεία και μέσα σε μια επαναληπτική διαδικασία
     * κρατά τη χαμηλότερη τιμή Y - συντεταγμένης, κάθε φορά που η τιμή αυτή ενημερώνεται
     * το σημείο προστίθεται στη λύση (καθώς σημαίνει πως βρίσκεται πιο χαμηλά στο επίπεδο).
     * @param Points τα σημεία στο επίπεδο,
     * @param N πληθάριθμος σημείων,
     * @return μια λίστα με τα σημεία της κορυφογραμμής.
     */

    public ArrayList<Point> findSolution(Point[] Points, Integer N){
        ArrayList<Point> solution = new ArrayList<>();
        solution.add(Points[0]);
        Integer minValueOfY = Points[0].getY();
        for (int i = 0; i < N; i++){
            if (Points[i].getY() < minValueOfY){
                minValueOfY = Points[i].getY();
                solution.add(Points[i]);
            }
        }
        return solution;
    }

    /**
     * Υλοποίησή της merge - sort απο: https://www.programiz.com/dsa/merge-sort
     */

    void mergeSort(Point[] Points, Integer lo, Integer hi) {
        if (lo < hi) {
            int m = (lo + hi) / 2;
            mergeSort(Points, lo, m);
            mergeSort(Points, m + 1, hi);
            merge(Points, lo, m, hi);
        }
    }

    private void merge(Point[] Points, int p, int q, int r) {

        int n1 = q - p + 1;
        int n2 = r - q;

        Point[] L = new Point[n1];
        Point[] M = new Point[n2];

        for (int i = 0; i < n1; i++)
            L[i] = Points[p + i];
        for (int j = 0; j < n2; j++)
            M[j] = Points[q + 1 + j];


        int i, j, k;
        i = 0;
        j = 0;
        k = p;

        while (i < n1 && j < n2) {
            if (L[i].isSmaller(M[j])) {
                Points[k] = L[i];
                i++;
            } else {
                Points[k] = M[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            Points[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            Points[k] = M[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        File file = new File(args[0]);
        ArrayList<Point> solution;
        Point[] Points = file.getPoints();
        Skyline skyline = new Skyline();
        skyline.mergeSort(Points, 0, file.getN() - 1);
        solution = skyline.findSolution(Points, file.getN());
        for (Point item:solution)
            System.out.println(item);
    }
}

