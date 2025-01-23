# Zadanie 65

![image](https://github.com/user-attachments/assets/05df9a18-e65b-4cc8-9fd7-306b5ca8c7e2)

Program wyznacza kolejne węzły w drzewie stanów gry, bazując na opisanych zasadach. Następnie od dołu wyznacza MNE w każdym węźle, uwzględniając przypadki strategii zdominowanych (mamy wtedy 1 PNE). Wynik to drzewo stanów gry, wyświetlające węzły w formacie 
```
[ D(liczba_nieobecności_Dawida, zysk_Dawida), E(liczba_nieobecności_Ewy, zysk_Ewy) ] MNE(d: ppb_Dawida_pójścia_na_wykład, e: ppb_Ewy_pójścia_na_wykład)
```

Uzyskane drzewo, przedstawiające postać ekstensywną gry oraz ppb do równowagi doskonałej (MNE):
```
└── [ D(0, 0), E(0, 0) ] MNE(d: 0,82, e: 0,85)
    ├── [ D(0, 0), E(0, 0) ] MNE(d: 0,67, e: 0,68)
    │   ├── [ D(0, 0), E(0, 0) ] MNE(d: 0,74, e: 0,71)
    │   │   ├── [ D(0, 0), E(0, 0) ]
    │   │   ├── [ D(0, -5), E(1, 5) ]
    │   │   ├── [ D(1, 4), E(0, -4) ]
    │   │   └── [ D(1, -15), E(1, -18) ]
    │   ├── [ D(0, 3), E(1, -3) ] MNE(d: 0,00, e: 1,00)
    │   │   ├── [ D(0, -7), E(1, 7) ]
    │   │   ├── [ D(0, -2), E(2, 2) ]
    │   │   ├── [ D(1, -3), E(1, 3) ]
    │   │   └── [ D(1, -22), E(2, -11) ]
    │   ├── [ D(1, -3), E(0, 3) ] MNE(d: 1,00, e: 0,00)
    │   │   ├── [ D(1, 7), E(0, -7) ]
    │   │   ├── [ D(1, 2), E(1, -2) ]
    │   │   ├── [ D(2, 1), E(0, -1) ]
    │   │   └── [ D(2, -8), E(1, -25) ]
    │   └── [ D(1, -9), E(1, -9) ] MNE(d: 0,74, e: 0,71)
    │       ├── [ D(1, -9), E(1, -9) ]
    │       ├── [ D(1, -14), E(2, -4) ]
    │       ├── [ D(2, -5), E(1, -13) ]
    │       └── [ D(2, -24), E(2, -27) ]
    ├── [ D(0, 4), E(1, -4) ] MNE(d: 0,83, e: 0,84)
    │   ├── [ D(0, 4), E(1, -4) ] MNE(d: 0,00, e: 1,00)
    │   │   ├── [ D(0, -6), E(1, 6) ]
    │   │   ├── [ D(0, -1), E(2, 1) ]
    │   │   ├── [ D(1, -2), E(1, 2) ]
    │   │   └── [ D(1, -21), E(2, -12) ]
    │   ├── [ D(0, 7), E(2, -7) ] MNE(d: 1,00, e: 1,00)
    │   │   ├── [ D(0, -3), E(2, 3) ]
    │   │   ├── [ D(0, 2), E(3, -2) ]
    │   │   ├── [ D(1, -9), E(2, 9) ]
    │   │   └── [ D(1, -18), E(3, -15) ]
    │   ├── [ D(1, 1), E(1, -1) ] MNE(d: 0,74, e: 0,71)
    │   │   ├── [ D(1, 1), E(1, -1) ]
    │   │   ├── [ D(1, -4), E(2, 4) ]
    │   │   ├── [ D(2, 5), E(1, -5) ]
    │   │   └── [ D(2, -14), E(2, -19) ]
    │   └── [ D(1, -5), E(2, -13) ] MNE(d: 0,00, e: 1,00)
    │       ├── [ D(1, -15), E(2, -3) ]
    │       ├── [ D(1, -10), E(3, -8) ]
    │       ├── [ D(2, -11), E(2, -7) ]
    │       └── [ D(2, -30), E(3, -21) ]
    ├── [ D(1, -4), E(0, 4) ] MNE(d: 0,88, e: 0,74)
    │   ├── [ D(1, -4), E(0, 4) ] MNE(d: 1,00, e: 0,00)
    │   │   ├── [ D(1, 6), E(0, -6) ]
    │   │   ├── [ D(1, 1), E(1, -1) ]
    │   │   ├── [ D(2, 0), E(0, 0) ]
    │   │   └── [ D(2, -9), E(1, -24) ]
    │   ├── [ D(1, -1), E(1, 1) ] MNE(d: 0,74, e: 0,71)
    │   │   ├── [ D(1, -1), E(1, 1) ]
    │   │   ├── [ D(1, -6), E(2, 6) ]
    │   │   ├── [ D(2, 3), E(1, -3) ]
    │   │   └── [ D(2, -16), E(2, -17) ]
    │   ├── [ D(2, -7), E(0, 7) ] MNE(d: 1,00, e: 1,00)
    │   │   ├── [ D(2, 3), E(0, -3) ]
    │   │   ├── [ D(2, 8), E(1, -8) ]
    │   │   ├── [ D(3, -3), E(0, 3) ]
    │   │   └── [ D(3, -12), E(1, -21) ]
    │   └── [ D(2, -13), E(1, -5) ] MNE(d: 1,00, e: 0,00)
    │       ├── [ D(2, -3), E(1, -15) ]
    │       ├── [ D(2, -8), E(2, -10) ]
    │       ├── [ D(3, -9), E(1, -9) ]
    │       └── [ D(3, -18), E(2, -33) ]
    └── [ D(1, -12), E(1, -12) ] MNE(d: 0,67, e: 0,68)
        ├── [ D(1, -12), E(1, -12) ] MNE(d: 0,74, e: 0,71)
        │   ├── [ D(1, -12), E(1, -12) ]
        │   ├── [ D(1, -17), E(2, -7) ]
        │   ├── [ D(2, -8), E(1, -16) ]
        │   └── [ D(2, -27), E(2, -30) ]
        ├── [ D(1, -9), E(2, -15) ] MNE(d: 0,00, e: 1,00)
        │   ├── [ D(1, -19), E(2, -5) ]
        │   ├── [ D(1, -14), E(3, -10) ]
        │   ├── [ D(2, -15), E(2, -9) ]
        │   └── [ D(2, -34), E(3, -23) ]
        ├── [ D(2, -15), E(1, -9) ] MNE(d: 1,00, e: 0,00)
        │   ├── [ D(2, -5), E(1, -19) ]
        │   ├── [ D(2, -10), E(2, -14) ]
        │   ├── [ D(3, -11), E(1, -13) ]
        │   └── [ D(3, -20), E(2, -37) ]
        └── [ D(2, -21), E(2, -21) ] MNE(d: 0,74, e: 0,71)
            ├── [ D(2, -21), E(2, -21) ]
            ├── [ D(2, -26), E(3, -16) ]
            ├── [ D(3, -17), E(2, -25) ]
            └── [ D(3, -36), E(3, -39) ]
```



