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
        //antall (t verdi)
        Objects.requireNonNull(verdi, "Null-verdier kan ikke søkes etter!");
        int count = 0;
        Node<T> current = rot;

        //Ide 3: ///////
        /*if (current == null) {
            return 0;
        }
        else {
            Node<T> tmp = new Node<>(verdi, null);
            T left = tmp.venstre.verdi;
            T right = tmp.høyre.verdi;
            int sum = 1 + antall(left) + antall(right);
            System.out.println("Sum: " + sum);

            return sum;*/

            //Ide 2: ////////
       /* int compare = comp.compare(verdi, rot.verdi);
        System.out.println("Value: " + verdi + " rot: " + rot.verdi +" Compare: " + compare);

        while (current != null){
            if(compare == 0) {
                count++;
                Node<T> tmp = rot;


            }
            else if (compare < 0){
                current = current.høyre;
            }
            else if(compare > 0){
                current = current.venstre;
            }
            else{
                return 0;
            }
        }
        return count;*/

            //Ide 1: ////
        /*
        //Tilfelle 1 : listen er tom.
        if (tom()){
            return 0;
        }
        //Tilfelle 2 : Dersom root har et venste-barn
        else if (rot.venstre != null){
            if(inneholder(verdi)) count++;
        }
        //Tilfelle 3 : Dersom root har et høyre-barn
        else if (rot.høyre != null){
            if (inneholder(verdi)) count++;
        }
        //Tilfelle 4 : Dersom listen ikke inneholder noen
        else {
            return 0;

        }

        return count; //Returnerer telleren. */

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
    public void postorden(Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    //// OPPGAVE 4 SLUTT /////////////////////////////////////////////

    //// OPPGAVE 5 //////////////////////////////////////////////////
    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    //// OPPGAVE 5 SLUTT ///////////////////////////////////////////

    //// OPPGAVE 6 ////////////////////////////////////////////////
    public boolean fjern(T verdi) {
        if (verdi == null){
            return false;
        }

        Node<T> a = rot, b = null; //b skal være forelder til a.

        while (a != null){ //Leter etter verdi
            int compare = comp.compare(verdi, a.verdi); //Sammenligner
            if (compare < 0) { //Går til venstre
                b = a;
                a = a.venstre;
            }
            else if (compare > 0) {
                b = a;
                a = a.høyre; //Går til høyre
            }
            else {
                break; //Den søkte verdien ligger i a.
            }

            if (a == null){
                return false; //Finner ingen verdi
            }

            if(a.venstre == null || a.høyre == null){ //Tilfelle 1 og 2
                Node<T> c = a.venstre != null ? a.venstre : a.høyre; //c får barn
                if (a == rot){
                    rot = c;
                }
                else if (a == b.venstre){
                    b.venstre = c;
                }
                else {
                    b.høyre = c;
                }
            }
            else { //Tilfelle 3
                Node<T> d = a, e = a.høyre;
                while (e.venstre != null){
                    d = e;
                    e = e.venstre;
                }
                a.verdi = e.verdi;

                if (d != a ){
                    d.venstre = e.høyre;
                }
                else {
                    d.høyre = e.høyre;
                }
            }
        }

        antall--;
        return true;
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå! :) ");
    }



    public void nullstill() {
        if(tom()){
            return;
        }

    }
    //// OPPGAVE 6 SLUTT //////////////////////////////////////////////






    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }








} // ObligSBinTre
