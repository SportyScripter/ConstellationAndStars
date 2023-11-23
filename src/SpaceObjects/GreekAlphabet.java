package SpaceObjects;
public enum GreekAlphabet {
    //Write greek alphabet here
    ALPHA("Alpha",  1),
    BETA("Beta",  2),
    GAMMA("Gamma",  3),
    DELTA("Delta",  4),
    EPSILON("Epsilon",  5),
    ZETA("Zeta",  6),
    ETA("Eta",  7),
    THETA("Theta",  8),
    IOTA("Iota",  9),
    KAPPA("Kappa",  10),
    LAMBDA("Lambda",  11),
    MU("Mu", 12),
    NU("Nu",  13),
    XI("Xi",  14),
    OMICRON("Omicron",15),
    PI("Pi",  16),
    RHO("Rho",  17),
    SIGMA("Sigma",  18),
    TAU("Tau",  19),
    UPSILON("Upsilon", 20),
    PHI("Phi",  21),
    CHI("Chi",  22),
    PSI("Psi",  23),
    OMEGA("Omega",  24);

    GreekAlphabet(String omega,  int i) {
    }

    public String getGreekAlphabet(int i ){
        GreekAlphabet[] greekAlphabets = GreekAlphabet.values();
        return greekAlphabets[i].toString();
    }

}
