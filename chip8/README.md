Chip8 Java Emulator  (WIP)
=========

##Tag hierarchy of the project

* [Core]()
* [Emulator]()
  * ```Dispose```
  * ```Instructions```
  * ```Specification```
  * ```Utils ```
* [Windows]()


##Loader Rom:
```
Emulator loaded OK!
Rom size: 280.0 bytes

Stack

 400   6E 05 65 00   6B 06 6A 00   A3 0C DA B1   7A 04 3A 40   
 410   12 08 7B 02   3B 12 12 06   6C 20 6D 1F   A3 10 DC D1   
 420   22 F6 60 00   61 00 A3 12   D0 11 70 08   A3 0E D0 11   
 430   60 40 F0 15   F0 07 30 00   12 34 C6 0F   67 1E 68 01   
 440   69 FF A3 0E   D6 71 A3 10   DC D1 60 04   E0 A1 7C FE   
 450   60 06 E0 A1   7C 02 60 3F   8C 02 DC D1   A3 0E D6 71   
 460   86 84 87 94   60 3F 86 02   61 1F 87 12   47 1F 12 AC   
 470   46 00 68 01   46 3F 68 FF   47 00 69 01   D6 71 3F 01   
 480   12 AA 47 1F   12 AA 60 05   80 75 3F 00   12 AA 60 01   
 490   F0 18 80 60   61 FC 80 12   A3 0C D0 71   60 FE 89 03   
 4A0   22 F6 75 01   22 F6 45 60   12 DE 12 46   69 FF 80 60   
 4B0   80 C5 3F 01   12 CA 61 02   80 15 3F 01   12 E0 80 15   
 4C0   3F 01 12 EE   80 15 3F 01   12 E8 60 20   F0 18 A3 0E   
 4D0   7E FF 80 E0   80 04 61 00   D0 11 3E 00   12 30 12 DE   
 4E0   78 FF 48 FE   68 FF 12 EE   78 01 48 02   68 01 60 04   
 4F0   F0 18 69 FF   12 70 A3 14   F5 33 F2 65   F1 29 63 37   
 500   64 00 D3 45   73 05 F2 29   D3 45 00 EE   E0 00 80 00   
 510   FC 00 AA 00   00 00 00 00   

Load Rom OK!
```

##Screenshots:

* Brix :

![](http://i59.tinypic.com/16hixkj.png)
   * Inputs:
     * ```Q -> Left ```
     * ```E -> Right ```

* Pong :

![](http://i60.tinypic.com/s4mdyp.png)
   * Inputs:
     * ```1 Player 1 -> Up | Q -> Down ```
     * ```2 Player 4 -> Up | R -> Down ```


##References:

* Thomas P. Greene, http://devernay.free.fr/hacks/chip8/C8TECH10.HTM
* David Winter, http://www.pong-story.com/chip8/
* Matthew Mikolay, http://mattmik.com/chip8.html
* Peter Miller, http://chip8.sourceforge.net/chip8-1.1.pdf
* SOLID, http://es.wikipedia.org/wiki/SOLID_(object-oriented_design)
* Software Design Pattern, http://en.wikipedia.org/wiki/Software_design_pattern
* Benjamin winterberg, winterbe.com/posts/2014/03/16/java-8-tutorial/
* My own know.
