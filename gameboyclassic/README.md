GameBoy Classic Java Emulator (DMG)
=========

##Abbreviation
DMG: Game Boy (monochrome), introduced on April 21, 1989

##System DMG
* Dot-matrix LCD unit capable of
* Grayscale display
* 64 Kbit – SRAM (for LCD display)
* 64 Kbit – SRAM (working memory)
* 32-pin connector (for ROM cartridge connection) 
* 6-pin subconnector (for external serial communication) 
* DC-DC converter for power source
* Sound amp
* Keys for operation
* Speaker
* Stereo headphone connector
* Input connector for external power source

##General Memory Map
```
Interrupt Enable Register  --------------------------- FFFF
Internal RAM               --------------------------- FF80
Empty but unusable for I/O --------------------------- FF4C
I/O ports                  --------------------------- FF00
Empty but unusable for I/O --------------------------- FEA0
Sprite Attrib Memory (OAM) --------------------------- FE00
Echo of 8kB Internal RAM   --------------------------- E000
8kB Internal RAM           --------------------------- C000
8kB switchable RAM bank    --------------------------- A000

8kB Video RAM              --------------------------- 8000 |
16kB switchable ROM bank   --------------------------- 4000 | -> 32kB Cartrigbe
16kB ROM bank              --------------------------- 0000 |

NOTE: b = bit, B = byte

```

##Memory
32 KB from 0x0 to 0x7FFF is available as program area :
* 0x000 to 0x0FF: Allocated as the destination address for RST instructions and the starting address for interrupts.
* 0x100 to 0x14F: Allocated as the ROM area for storing data such as the name of the game.
* 0x150: Allocated as the starting address of the user program.
___
* 0x8000 to 0x9FFF : used as RAM for the LCD display (8 KB).
* 0xA000 to 0xBFFF : the area allocated for external expansion RAM (8 KB).
* 0xC000 to 0xDFFF : the work RAM area (8 KB).
* 0xFE00 to 0xFFFF : allocated for CPU internal RAM.
* 0xFE00 to 0xFE9F : OAM-RAM (Holds display data for 40 objects)
* 0xFF00 to 0xFF7F & 0xFFFF: Specified for purposes such as instruction registers and system controller flags.
* 0xFF80 to 0xFFFE : Can be used as CPU work RAM and/or stack RAM.

DMA transfers are controlled by the DMA registers.

DMG allows 40 x 32-bit DMA transfers from 0x8000-0xDFFF to OAM (0xFE00-0xFE9F).

The transfer start address can be specified in increments of 0x100 for 0x8000-0xDFFF.

##Timer (WIP)
* TIMA (timer counter)
* TMA (timer modulo register) 
* TAC (timer control register)

##Display (WIP)
The DMG CPU has 8 KB (64 Kbits) of built-in LCD display RAM.

Data specification ranges for OBJ characters: 
0x00 ≤ character code ≤ 0xFF
0x00 ≤ X ≤ 0xFF
0x00 ≤ Y ≤ 0xFF

The display data for OBJ characters is stored in OAM (Display RAM: 0xFE00~0xFE9F) in the following order:
y-axis & x-axis coordinate
Character code
Attr data

Window display data also can be specified as character codes, beginning from 0x9800 or 0x9C00 in external SRAM.
Frame frequency: 59.7Hz

##Sound
* Sound 1: Generates a rectangle waveform with sweep and envelope functions.
* Sound 2: Generates a rectangle waveform with an envelope function.
* Sound 3: Outputs any waveform from waveform RAM.
* Sound 4: Generates white noise with an envelope function.

####ON Mode

Sounds 1, 2, 4:
* Output level is 0 with the default envelope value set to a value other than 0000 and in DOWN mode
* The step is 0 with the default envelope value set to a value of 0000 and in UP mode (NR12, NR22, and NR42 set to 0x08 and the initialization flag set)

Sound 3:
* With the output level set to mute (bits 5 and 6 of NR32 set to 0)

####OFF Mode

Sounds 1, 2, and 4:
* When the default level is set to 0000 with the envelope in DOWN mode (initialization not required)

Sound 3:
* When the Sound OFF flag (bit 7 of NR30) is set to 0. Setting the Sound OFF flag to 1 cancels OFF mode. Sound 3 is started by re-initialization.

####Sumary

```Reg  |  Addr  |  D7 ... D0 ```

##CPU (WIP)
* Acc : Accumulator (8 bits)
* Aux Reg: B,C,D,E,H,L -> (BC), (DE), (HL) (8 bits)
* PC: Program Counter (16 bits)
* SP: Stack Pointer (16 bits),  SP + 2 (Pop) || SP - 2 (Push)
* F: Flag Register (8 bits)

####Sumary

```
C | H | N | Z |  CYCL | OpCodes
```

* Z - Zero Flag
* N - Subtract Flag
* H - Half Carry Flag
* C - Carry Flag
* [OpCodes](http://www.pastraiser.com/cpu/gameboy/gameboy_opcodes.html)


##Monitor Rom
When power on the hardware is turned on, the monitor ROM checks for errors in the ‘Nintendo’ logo character data within the game software.
If the data is correct, the Nintendo logo is displayed and the program is then started. 
If there is an error in the data, the screen flashes repeatedly.

The conditions required for starting the user program are as follows.
Starting Address 0x150.
The starting address can be freely set by writing a jump destination address at 0x102 and 0x103.



##References :
* Game BoyTM CPU Manual, Pan of Anthrox, GABY, Marat Fayzullin, Pascal Felber, Paul Robson, Martin Korth, kOOPa, Bowser.
*￼Game Boy PROGRAMMING MANUAL v.1.0, DMG-06-4216-001-A Released 11/09/1999 Nintendo.
* Study of the techniques for emulation programming, Victor Moya del Barrio, Agustin Fernandez, 2001.
* GB microChip Z80 opCodes, http://goldencrystal.free.fr/GBZ80Opcodes.pdf
* My own know.
