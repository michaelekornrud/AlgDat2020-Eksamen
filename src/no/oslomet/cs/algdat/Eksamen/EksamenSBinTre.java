package no.oslomet.cs.algdat.Eksamen;

import java.util.*;

public class EksamenSBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn  //warning pga. ø i høyre.
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
    /** @param endringer  Siden det har blitt klartgjort at denne ikke
     *  trengs å brukes. Men siden endringer ligger i oppgave-koden velger jeg å la den stå selvom
     *  den gir meg en warning.
     */

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
        //Følger programkode 5.2.3 a) og  5.2.11 b)
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
    private static <T> Node<T> førstePostorden(Node<T> p) { //Gir warning pga. ø i metodenavn
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
        //Bruker metoden nextPreorder fra forelesing som eksempel.
        Node<T> root = p.forelder;                  //Finner p.forelder

        if (root == null) return null;              //Dersom forelderNoden til p er null, skal "null" returneres --> p = rot (ingen forelder).
        else if (p == root.høyre){                  //Dersom p =  forelder.høyre, vil neste posisjon være p.forelder.
            p = root;
        }
        else if (p == root.venstre){                //Dersom p er det venstre barnet (p == p.forelder.venstre)
            if (root.høyre == null)p = root;        //Hvis det høyre barnet = null, blir neste i postorden p.forelder
            else {                                  //Hvis det høyre barnet != null, blir neste i postorden = p.forelder.høyre
                p = root.høyre;
                while (p.venstre != null) {         //Så lenge venstrebarnet til p ikke er null
                    p = p.venstre;                  //P oppdateres til venstrebarnet til p.
                }
                while (p.høyre != null){            //Så lenge høyrebarnet til p ikke er null
                    p = p.høyre;                    //P oppdateres til høyrebarnet til p.
                }
            }
        }

        return p;                                   //Returner p eller p.forelder?

    }
    //// OPPGAVE 3 SLUTT ///////////////////////////////////////////////

    //// OPPGAVE 4 ////////////////////////////////////////////////////
    public void postorden(Oppgave<? super T> oppgave) {
        //Ikke bruk hjelpevariabler eller rekursjon
        //Bruk nestePostOrden fra forige oppgave
            // 1. Start med å finne den første noden i postorden (førstePostOrden)
            // 2. Bruk f.eks. en while-løkke til å finne neste node helt til p = null
                //Bruk noe som "p = nestePostOrden(p)

        if (tom()) return;                      // Dersom input er tomt skal metoden returnere.
        Node<T> p = førstePostorden(rot);       // Setter p til første node i postorden
        while (p != null){                      // Så lenge p ikke er null
            oppgave.utførOppgave(p.verdi);      // Skriver ut / lagrer p
            p = nestePostorden(p);              // Oppdaterer p til neste node i input-liste
        }
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        //Fra forelesing om binære trær
        if (p.venstre != null) postordenRecursive(p.venstre, oppgave);
        if (p.høyre != null) postordenRecursive(p.høyre, oppgave);
        oppgave.utførOppgave(p.verdi);                                      //Utfører oppgave
    }

    //Tror denne er ferigkodet
    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave); //Kaller på private void postordenRecursive
    }
    //// OPPGAVE 4 SLUTT /////////////////////////////////////////////

    //// OPPGAVE 5 //////////////////////////////////////////////////
    public ArrayList<T> serialize() {
        //Har brukt metoden printLevelOrder fra forelesing som eksempel
        Node<T> p = rot;                            //Oppretter en hjelpenode for rot
        ArrayDeque<Node<T>> q = new ArrayDeque<>(); //Bruker ArrayDeque for å legge input i en kø
        ArrayList<T> liste = new ArrayList<>();     //En ArrayList som skal returneres

        q.addLast(p);                               //Legger roten (p) inn i kø
        liste.add(p.verdi);                         //Legger roten (p) inn i liste

        while (!q.isEmpty()){                       //Så lenge køen ikke er tom
            //1. Ta ut den første av køen
            Node<T> current = q.removeFirst();      //Fjener den første i køen

            // 2. Legger til current sitt venstre barn hvis ikke lik null
            if (current.venstre != null){
                q.addLast(current.venstre);         //Legger til i køen
                liste.add(current.venstre.verdi);   // Legger til i listen
            }
            // 3. Gjør det samme for høyre barn
            if (current.høyre != null){
                q.addLast(current.høyre);           //Legger til i køen
                liste.add(current.høyre.verdi);     //Legger til i listen
            }
        }
        return liste ;                              //Returnerer en ArrayList
    }

    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        //Dersom input-array = null --> returner null
        if (data.isEmpty()) return null;

        //Oppretter et tre
        EksamenSBinTre<K> tre = new EksamenSBinTre<>(c);

        //Bruken en forbedret for-løkke for å legge nodene inn i treet
        for (K val : data) {
            tre.leggInn(val);           //Legger vberdien inn i treet
        }
        return tre;                     //Returnerer treet.
    }
    /**
     Kan også skrives som "for (int i = 0; i < data.size; ++i) K val = data.get(i);"
     Men skiver det med en forbedret for-løkke da dette ikke gir en warning.
     */

    //// OPPGAVE 5 SLUTT ///////////////////////////////////////////

    //// OPPGAVE 6 ////////////////////////////////////////////////
    public boolean fjern(T verdi) {
        //Følger Programkode 5.2.8 d)
            if (verdi == null) return false;                                //Dersom verdi = null
            Node<T> p = rot, q = null;                                      //Hjelpenoder
            while (p != null) {                                             //Så lenge p ikke er null, så skal jeg...
                //1. sammenligne input med rot-verdien
                int cmp = comp.compare(verdi, p.verdi);
                //2. dersom verdi < roten --> q er forelder til p og p hopper ned til venstre
                if (cmp < 0) {
                    q = p;              //Setter q = p
                    p = p.venstre;      //Oppdaterer p til venstrebarnet.
                }
                //3. dersom verdi > roten --> q er forelder til p og p hopper ned til høyre
                else if (cmp > 0) {
                    q = p;              //Setter q = p
                    p = p.høyre;        //Oppdater p til høyrebarnet
                }
                else break;
            }
            if (p == null) return false;                                   //Dersom p =  null --> Treet er tomt

            if (p.venstre == null || p.høyre == null) {                    //Hvis p.venstre eller p.høyre er lik null
                Node<T> b = p.venstre != null ? p.venstre : p.høyre;       //en node som går til venstre hvis det ikke er en verdi til høyre
                if (p == rot) {                                            //Dersom p = roten
                    rot = b;                                               //Setter roten lik b.
                    if (b != null) {
                        b.forelder = null;                                 // og rotens forelder til null
                    }
                }
                else if (p == q.venstre) {                                 //Hvis p = q.venstre
                    q.venstre = b;                                         //Setter venstrebarnet til q lik b
                    if (b != null)
                        b.forelder = q;                                    //Hvis b ikke lik null --> b sin forelder er lik q
                } else {
                    q.høyre = b;                                           //Hvis ikke så er høyrebarnet til q = b.
                    if (b != null) {
                        b.forelder = q;                                    //Hvis b ikke er null --> b sin forelder er lik q
                    }
                }
            }
            else {
                Node<T> s = p, r = p.høyre;                                 //Nye hjelpenoder
                while (r.venstre != null) {                                 //Så lenge venstrebarnet til r ikke lik null.
                    s = r;                                                  //s hopper en ned til høyre
                    r = r.venstre;                                          //r settes lik venstrebarnet til r
                }
                p.verdi = r.verdi;                                          //Setter p = r.

                if (s != p) {
                    s.venstre = r.høyre;                                    //Dersom s ikke lik p, settes venstrebarnet til s lik høyrebarnet til r.
                    if (r.høyre != null) {                                  //Dersom høyrebarnet til r ikke er null
                        r.forelder.høyre = s;                               //Oppdaterer høyrebarnet til forelder
                    }
                }
                else {
                    s.høyre = r.høyre;                                     //Ellers skal høyrebarnet til s være lik høyrebarnet til r.
                    if (r.høyre != null) {                                 //Hvis høyrebarnet til r ikke er null
                        r.forelder.høyre = s;                              //Oppdaterer høyrebarnet til forelder
                        }
                    }
            }
            antall--;                                                       //Reduserer antall med en
            return true;                                                    //returnerer true
    }

    public int fjernAlle(T verdi) {
        if (rot == null) return 0;              //Dersom rot er lik null (tomt tre) skal 0 returneres
        int counter = 0;                        //Oppretter en teller for antallet som blir fjernet

            while (fjern(verdi)) counter++;     //Så lenge fjern-metoden retunerer true skal counter økes med 1.

        return counter;                         //Returnerer counter.
    }

    public void nullstill() {
        if (rot == null) return;                //Dersom roten er lik null (tomt tre) skal metoden retunnere
           nullStill(rot);                      //Bruker hjelpemetoden til å nullstille pekere
           rot = null;                          //Setter roten lik null
    }
        //Lager en hjelpemetode
        //Bruker en rekursiv hjelpemetode for å nulstille treet.
    private void nullStill (Node<T> p) {
        if (p.venstre != null) nullStill(p.venstre); //Dersom venstrebarnet til inpout-node ikke er null, så kaller jeg rekursivt på nullStill på venstrebarn
        if (p.høyre != null) nullStill(p.høyre);     //Dersom høyrebarnet til input-node ikke er null, så kaller jeg rekursivt på nullStill på høyrebarn

        antall--;           //Reduserer antall
        rot = null;         //Setter rot til null

    }
    //// OPPGAVE 6 SLUTT //////////////////////////////////////////////
} // ObligSBinTre
