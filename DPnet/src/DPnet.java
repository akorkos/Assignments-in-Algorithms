/**
 * Alexandros Korkos
 * 3870
 * alexkork@csd.auth.gr
 */

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DPnet {

    public static class MyFile{
        private final String filename;
        private Integer N, M;
        private Integer[][] SumVMCost, VMCost; // Κόστος Εκτέλεσης, Κόστος Επικοινωνίας

        /**
         * Δέχεται το όνομα του αρχείου, το ανοίγει και διαβάζει τα δεδομένα οπου τα αποθηκεύει σε δυο πίνακες
         * των δυο διαστάσεων,
         * @param filename το όνομα του αρχείου.
         */
        public MyFile(String filename){
            this.filename = filename;

            read();
        }
        private void read(){
            try{
                File file = new File(filename);
                Scanner reader = new Scanner(file);
                N = Integer.parseInt(reader.nextLine());
                M = Integer.parseInt(reader.nextLine());

                // Πίνακας για το συνολικό κόστος να τρέξει μία διεργασία
                // σε ένα τύπο εικονικής μηχανής.
                SumVMCost = new Integer[N][M];

                reader.nextLine(); // διαβάζει την κενή γραμμή

                // διαβάζει τη γραμμή μέσα απο το αρχείο και
                // χωρίζει τους αριθμούς συμφωνά με το κενό που έχουνε ανάμεσα τους.
                for (int i = 0; i < N; i++){
                    String[] line = reader.nextLine().split(" ");
                    for (int j = 0; j < M; j++)
                        SumVMCost[i][j] = Integer.parseInt(line[j]);
                }

                reader.nextLine(); // διαβάζει την κενή γραμμή

                // Πίνακας για το κόστος να στείλει το ένα μηχάνημα δεδομένα στο άλλο.
                VMCost = new Integer[M][M];

                // διαβάζει τη γραμμή μέσα απο το αρχείο και
                // χωρίζει τους αριθμούς συμφωνά με το κενό που έχουνε ανάμεσα τους.
                for (int i = 0; i < M; i++){
                    String[] line = reader.nextLine().split(" ");
                    for (int j = 0; j < M; j++)
                        VMCost[i][j] = Integer.parseInt(line[j]);
                }
                reader.close();
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        public Integer[][] getSumVMCost() {
            return SumVMCost;
        }
        public Integer[][] getVMCost() {
            return VMCost;
        }
        public Integer getN() {
            return N;
        }
        public Integer getM() {
            return M;
        }
    }

    /**
     * Επιστρέφει μια γραμμή απο έναν πίνακα δυο διαστάσεων,
     * @param A πίνακας των δυο διαστάσεων,
     * @param M του μήκος της κάθε σειράς του πίνακα,
     * @param pos η γραμμή που πίνακα που πρέπει να επιστραφεί,
     * @return η γραμμή του πίνακα μήκους Μ.
     */
    public static Integer[] getLine(Integer[][] A, Integer M, Integer pos){
        Integer[] arrayLine = new Integer[M];
        for (int j = 0; j < M; j++)
            arrayLine[j] = A[pos][j];
        return arrayLine;
    }

    /**
     * Βρίσκει και επιστρέφει την ελάχιστη τιμή, αθροίσματος δυο στοιχειών απο δυο πίνακες
     * @param A πρώτος πίνακας,
     * @param B δεύτερος πίνακας,
     * @param M μήκος και των δυο πινάκων,
     * @return ελάχιστη τιμή.
     */
    public static Integer getMin(Integer[]A, Integer[]B, Integer M){
        int min = A[0] + B[0];
        for (int i = 1; i < M; i++){
            if ((A[i] + B[i]) < min)
                min = A[i] + B[i];
        }
        return min;
    }

    public static void main(String[] args){
        MyFile file = new MyFile(args[0]);
        Integer N = file.getN();
        Integer M = file.getM();
        Integer[][] VMCost = file.getVMCost();
        Integer[][] SumVMCost = file.getSumVMCost();

        Integer[][] Costs = new Integer[N][M];

        for (int j = 0; j < M; j++)
            Costs[0][j] = SumVMCost[0][j];

        // υπολογίζει την τιμή του στοιχειού στη θέση (i, j) του πίνακα,
        // βρίσκοντας το ελάχιστο μονοπάτι απο την i-1 γραμμή του πίνακα Costs.
        for (int i = 1; i < N; i++){
            for (int j = 0; j < M; j++){
                Integer[] run = getLine(Costs, M, i - 1);
                Integer[] comm = getLine(VMCost, M, j);
                Costs[i][j] = getMin(run, comm, M) + SumVMCost[i][j];
            }
        }

        System.out.println(Arrays.deepToString(Costs).replace("], ["," \n").replace("], ", " \n").replace("[[", "").replace("]]", "").replace(", "," "));
    }
}
