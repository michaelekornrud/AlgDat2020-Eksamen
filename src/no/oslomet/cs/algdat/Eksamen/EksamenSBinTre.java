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

    public boolean leggInn(T verdi) {
        //Følger programkode 5.2.11 b)
        Node<T> a = rot;            //a starter i roten
        Node<T> b =  null;          //Hjelpevariabel
        int tmp = 0;                //hjelpevariabel

        while (a != null){          //Fortsetter til a er ute av treet
            b = a;                  //b er forelder til a
            tmp = comp.compare(verdi, a.verdi);     //brukker komparatoren
            a = tmp < 0 ? a.venstre : a.høyre;      //flytter a
        }

        // a er nå null dvs. ute av treet, b er den siste vi passerte
        a = new Node<>(verdi, b);              //Oppretter en ny node

        if(b == null) rot = a;                 // a blir rotnode
        else if (tmp < 0 ) b.venstre = a;      // venstre barn til q
        else b.høyre = a;                      // høyre barn til q.

        antall++;           //Oppdaterer antall
        endringer++;        //Oppdaterer endringer

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

    /** Denne skal være ferdig, mulig jeg må endre på denne dersom jeg velger å endre på leggInn(T verdi).
     *
     * @param verdi ..
     * @return returner antall av en verdi i et tre.
     */

    public int antall(T verdi) {
        if (antall() > 0 && inneholder(verdi)) {                    //Sjekker om treet er tomt og om det inneholder minst en av verdien
            int count = 0;                                          //Hjelpevariabel --> brukes for å telle antall
            Node<T> current = rot;                                  //Hjelpevariabel --> Slik at jeg alltid kan referere til roten

            while (current != null) {                               //Så lenge roten != null
                int compare = comp.compare(verdi, current.verdi);   //Bruker comparator på input-verdi og verdien av roten
                if (compare == 0) {                                 //Dersom input-veri == rot.verdi
                    count++;                                        //Øker count med 1.
                current = current.høyre;                            //Setter roten til høyre barn
            }
                else if (compare > 0)current = current.høyre;       //Dersom compare = -1 setter jeg neste rot til høyre barn
                else current = current.venstre;                     //Dersom compare = 1 setter jeg neste rot til venste barn
            }
            return count;                                           //Returnerer counter
        }
        else return 0;                                              //Retunerer 0 dersom treet et tomt eller ikke inneholder imput-verdien

        }

    //// OPPGAVE 2 SLUTT /////////////////////////////////////////////////

    //// OPPGAVE 3 //////////////////////////////////////////////////////
    //Følger eksemplene i delkapittel 5.1.7 i læreboka.
    //
    private static <T> Node<T> førstePostorden(Node<T> p) {
        if (p == null) {                                //Sjekker om p har verdi eller ikke
            return null;                                //Returnerer null dersom p ikke har noen verdi.
        }

        while (true) {
            if (p.venstre != null) p = p.venstre;       //Dersom p.venstre != null --> p settes lik p.venstre
            else if (p.høyre != null) p = p.høyre;      //Dersom p.venstre = null og p.høyre != null --> p settes lik p.høyre
            else return p;                              //Hvis p.venstre og p.høyre == null --> returnerer kun p.
        }

    }


    private static <T> Node<T> nestePostorden(Node<T> p) {
        //Bruker


        Node<T> root = p.forelder;                  //Finner p.forelder

        if (root == null) return null;              //Dersom forelderNoden til p er null, skal "null" returneres --> p = rot (ingen forelder).
        else if (p == root.høyre){                  //Dersom p =  forelder.høyre, vil neste posisjon være p.forelder.
            p = root;
        }
        else if (p == root.venstre){                //Dersom p er det venstre barnet (p == p.forelder.venstre)
            if (root.høyre == null)p = root;        //Hvis det høyre barnet = null, blir neste i postorden p.forelder
            else {                                  //Hvis det høyre barnet != null, blir neste i postorden = p.forelder.høyre
                p = root.høyre;
                while (p.venstre != null) {         // Hvis p er en foreldreNode, hopp en orden ned
                    p = p.venstre;
                }
            }
        }

        return p;                                   //Returner p eller p.forelder?

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
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }



    public void nullstill() {
        if(tom()){
            return;
        }

    }
    //// OPPGAVE 6 SLUTT //////////////////////////////////////////////





    //Tror denne er ferigkodet
    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }








} // ObligSBinTre
