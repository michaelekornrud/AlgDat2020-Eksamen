package no.oslomet.cs.algdat.Eksamen;

import java.util.Comparator;

public class main {
    public static void main(String [] args){

        ////Oppgave 0 -- Sjekker for syntaksfeil//////////////////////////
        EksamenSBinTre<String> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre.antall()); // Utskrift: 0

        ////Oppgave 1 -- ... ///////////////////////
        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        EksamenSBinTre<Integer> tre1 = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre1.leggInn(verdi);
        System.out.println(tre1.antall()); // Utskrift: 10


    }
}
