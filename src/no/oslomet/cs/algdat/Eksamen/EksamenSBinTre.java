package no.oslomet.cs.algdat.Eksamen;


import org.w3c.dom.Node;

import java.util.*;

public class EksamenSBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }


        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public EksamenSBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    //Ferdigkodet//////////
    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }


    //// OPPGAVE 1 ////////////////////////////////////////////////////////
    //Følger programkode 5.2.3 a)
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Nullverdier ikke tillat!");

        Node<T> a = rot, b =  null; //a starter i roten, b = null
        int tmp = 0;                //hjelpevariabel

        while (a != null){          //Fortsetter til a er ute av treet
            b = a;                  //b er forelder til a
            tmp = comp.compare(verdi, a.verdi);     //brukker komparatoren
            a = tmp < 0 ? a.venstre : a.høyre;
        }

        // a er nå null dvs. ute av treet, b er den siste vi passerte
        a = new Node<>(verdi, null);
        if(b == null){
            rot = a;

        }
        else if (tmp < 0 ){
            a.venstre = a;

        }
        else {
            b.høyre = a;
        }
        antall++;
        endringer++;
        return true;
    }
    //// OPPGAVE 1 SLUTT //////////////////////////////////////////////////

    //// OPPGAVE 2 ///////////////////////////////////////////////////////

    //Ferdigkodet//////////
    public boolean tom() { //Ferdigkodet
        return antall == 0;
    }
    //Ferdigkodet//////////
    public boolean inneholder(T verdi) { //Ferdigkodet
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }
        return false;
    }
    //Ferdigkodet//////////
    public int antall() { //Ferdigkodet
        return antall;
    }
    public int antall(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    //// OPPGAVE 2 SLUTT /////////////////////////////////////////////////

    //// OPPGAVE 3 //////////////////////////////////////////////////////
    private static <T> Node<T> førstePostorden(Node<T> p) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    //// OPPGAVE 3 SLUTT ///////////////////////////////////////////////

    //// OPPGAVE 4 ////////////////////////////////////////////////////


    //// OPPGAVE 4 SLUTT /////////////////////////////////////////////
    public void postorden(Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }



    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }





    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
