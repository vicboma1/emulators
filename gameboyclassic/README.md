_![](https://realboyemulator.files.wordpress.com/2013/01/220px-gameboy.jpg?w=47&h=90) GameBoy Classic Java Emulator (DMG) ![](https://realboyemulator.files.wordpress.com/2013/01/220px-gameboy.jpg?w=47&h=90)_
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

##Registers JoyPad interrupt $FF00
* Bit 3 - P13 in port 
* Bit 2 - P12 in port 
* Bit 1 - P11 in port 
* Bit 0 - P10 in port
* Bit 5 - P15 out port { $8 - Down | $4 - Up | $2 - Left | $1 - Right }
* Bit 4 - P14 out port { $80 - Start | $40 - Select | $20 -B | $10 -A }

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

![Monochrome4-shades](http://fornaxvoid.com/colorpalettes/GameBoy-palette.png)


####Resolution 160x144 = 20x18 tiles
```
    Max # of sprites: 40
    Max # sprites/line: 10
    Max sprite size: 8x16      (Coming soon *)
    Min sprite size: 8x8
    
    1 Tile = ( 8w x 8h ) 64 imagenes = 16 bytes +/-

                                           1 Image = [255 - 0]          
                                                                    
    1 Tile                                 .33333.. = 01111100               Segment 0
                                                      01111100               Segment 1                
    .33333..                               22...22. = 00000000          
    22...22.                                          11000110          
    11...11.                               11...11. = 11000110      
    2222222. <-- digits         =                     00000000              
    33...33.     represent                 22222222 = 00000000                  
    22...22.     color                                11111111              
    11...11.     numbers                   33...33. = 11000110      
    ........                                          11000110                  
                                           22...22. = 00000000      
                                                      11000011                      
                                           11...11. = 11000110                  
                                                      00000000                             
                                           ........ = 00000000                  
                                                      00000000      
 ```

####Shader
```
    Green Scale                    Dark Yellow Scale                  Grey Scale 
```
![Super Mario Land I](http://i.imgur.com/bpHnetb.png)  ![Super Mario Land I](http://i.imgur.com/54PpYgY.png?1) ![Super Mario Land I](http://i.imgur.com/9KAar8E.png)
```
    Gb Pocket                      Ligth Yellow Scale                 Grey Stark Scale         
```
![F1 Race](http://i.imgur.com/qMlY8tS.png)   ![Super Mario Land I](http://i.imgur.com/Te6b2mj.png)  ![Super Mario Land I](http://i.imgur.com/LvSc7V1.png)



####Shader Invert
```
    Green Scale                    Dark Yellow Scale                  Grey Scale 
```
![Super Mario Land I](http://i.imgur.com/CbLBxTZ.png)  ![Super Mario Land I](http://i.imgur.com/NXFALzF.png)  ![Super Mario Land I](http://i.imgur.com/MCHTTxz.png)
```
    Gb Pocket                      Ligth Yellow Scale                 Grey Stark Scale 
```
![Super Mario Land I](http://i.imgur.com/wAVrtVG.png)  ![Super Mario Land I](http://i.imgur.com/SyZy6zr.png)  ![Super Mario Land I](http://i.imgur.com/SPTPFZV.png)



####Shader Polarized
```
    Green Scale                    Dark Yellow Scale                  Grey Scale 
```
![Super Mario Land I](http://i.imgur.com/h8RRcDv.png)  ![Super Mario Land I](http://i.imgur.com/NeOLgAc.png)  ![Super Mario Land I](http://i.imgur.com/Jvc5ZjE.png)
```
    Gb Pocket                      Ligth Yellow Scale                 Grey Stark Scale 
```
![Super Mario Land I](http://i.imgur.com/fK8kXDP.png)  ![Super Mario Land I](http://i.imgur.com/GmicIBa.png)  ![Super Mario Land I](http://i.imgur.com/w4mhueE.png)



#### Backlight (DMG/Pocket) Custom
```
    Orange Scale                     Green Scale                     Pink Scale 
```
![Super Mario Land I](http://s28.postimg.org/5wr80r7il/Screen_Shot_2015_09_01_at_17_32_37.png)  ![Super Mario Land I](http://s28.postimg.org/gywaz71l9/Screen_Shot_2015_09_01_at_17_34_57.png)  ![Super Mario Land I](http://s28.postimg.org/4izl5g899/Screen_Shot_2015_09_01_at_17_36_35.png)  

```
    Teal Scale                      White Blue Scale                 Red scale                         
```
![Super Mario Land I](http://i.imgur.com/DVLegQZ.png)   ![Super Mario Land I](http://i.imgur.com/KZ5mEsE.png)
![Super Mario Land I](http://i.imgur.com/cmAP6uD.png)

```
    Blue Scale                                                    
```
![Super Mario Land I](http://i.imgur.com/gBvD48t.png?1)


#### Backlight (DMG/Pocket) Custom Invert
```
    Orange Scale                     Green Scale                     Pink Scale 
```
![Super Mario Land I](http://i.imgur.com/hGL75yN.png?1)  ![Super Mario Land I](http://i.imgur.com/6Dz5wPE.png?1)  ![Super Mario Land I](http://i.imgur.com/8Sqk1J3.png?1)  

```
    Teal Scale                      White Blue Scale                 Red scale                         
```
![Super Mario Land I](http://i.imgur.com/1TVOR79.png)   ![Super Mario Land I](http://i.imgur.com/NMjF9qI.png)
![Super Mario Land I](http://i.imgur.com/SAh26JO.png)

```
    Blue Scale                                                    
```
![Super Mario Land I](http://i.imgur.com/aAlZp6X.png)



#### Backlight (DMG/Pocket) Custom Polarized
```
    Orange Scale                     Green Scale                     Pink Scale 
```
![Super Mario Land I](http://i.imgur.com/LBsea7q.png)  ![Super Mario Land I](http://i.imgur.com/ZloM5eI.png)  ![Super Mario Land I](http://i.imgur.com/QABFYQ1.png)  

```
    Teal Scale                      White Blue Scale                 Red scale                         
```
![Super Mario Land I](http://i.imgur.com/M4Zjj9G.png)   ![Super Mario Land I](http://i.imgur.com/vwugztY.png)
![Super Mario Land I](http://i.imgur.com/fMnWyan.png)

```
    Blue Scale                                                    
```
![Super Mario Land I](http://i.imgur.com/W2jDY65.png)

 


##Sound (WIP)
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

##CPU
* Acc : Accumulator (8 bits)
* Aux Reg: B,C,D,E,H,L -> (BC), (DE), (HL) (8 bits)
* PC: Program Counter (16 bits)
* SP: Stack Pointer (16 bits),  SP + 2 (Pop) || SP - 2 (Push)
* F: Flag Register (8 bits)


###Reducing Power Consumption

Can be used to recude the power consumption of the gameboy, and to extend the life of the batteries.

* PWR Using the HALT Instruction
* PWR Using the STOP Instruction
* PWR Disabeling the Sound Controller
* PWR Not using CGB Double Speed Mode
* PWR Using the Skills

####Sumary

```
C | H | N | Z |  CYCL | OpCodes
```

* Z - Zero Flag
* N - Subtract Flag
* H - Half Carry Flag
* C - Carry Flag
* [OpCodes](http://www.pastraiser.com/cpu/gameboy/gameboy_opcodes.html)

#### Game Loop
```
Main:
 halt                    ; stop system clock
                         ; return from halt when interrupted
 nop                     ; (See WARNING above.)
 ld      a,(VblnkFlag)
 or      a               ; V-Blank interrupt ?
 jr      z,Main          ; No, some other interrupt
 xor     a
 ld      (VblnkFlag),a   ; Clear V-Blank flag
 call    Controls        ; button inputs
 call    Game            ; game operation

 jr      Main
```
```
 **** V-Blank Interrupt Routine ****
Vblnk:
 push    af
 push    bc
 push    de
 push    hl
 call    SpriteDma       ; Do sprite updates
 ld      a,1
 ld      (VblnkFlag),a
 pop     hl
 pop     de 
 pop     bc
 pop     af

reti
```

### Raster graphics
The original GameBoy hardware simulates a cathode-ray tube (CRT) in its timings.
![Scanlines and blanking periods](http://imrannazar.com/content/img/jsgb-gpu-blank.png)

The following table states how long the GPU stays in each period, in terms of the CPU's T-clock which runs at 4194304 Hz.

```
          Period	                GPU mode number  Time spent (clocks)
Scanline (accessing OAM)                  	2               80
Scanline (accessing VRAM)               	3               172
Horizontal blank                        	0               204
One line (scan and blank)		        X               456
Vertical blank	                                1           	4560 (10 lines)
Full frame (scans and vblank)		        X               70224
```



##Interrupt Procedure
* V-Blank
* LCDC Status
* Timer Overflow
* Serial Transfer Completion
* Joy P10-P13

##Monitor Rom
When power on the hardware is turned on, the monitor ROM checks for errors in the ‘Nintendo’ logo character data within the game software.
If the data is correct, the Nintendo logo is displayed and the program is then started. 
If there is an error in the data, the screen flashes repeatedly.

The conditions required for starting the user program are as follows.
Starting Address 0x150.
The starting address can be freely set by writing a jump destination address at 0x102 and 0x103.

##Game Boy Memory Controllers (MBC)

#### [MBC1](https://github.com/vicboma1/emulators/blob/master/gameboyclassic/LoadRomMBC1)
MBC1 is a memory controller that enables the use of 512 Kbits (64 Kbytes) or more of ROM and 256 Kbits (32 Kbytes) of RAM. 
RAM use by MBC1 is restricted to 64 Kbits (8 Kbytes).
* Register 0 : 0x0000-0x1FFF 
* Register 1 : 0x2000-0x3FFF 
* Register 2 : 0x4000-0x5FFF
* Register 3 : 0x6000-0x7FFF 

##References :
* Game BoyTM CPU Manual, Pan of Anthrox, GABY, Marat Fayzullin, Pascal Felber, Paul Robson, Martin Korth, kOOPa, Bowser.
* ￼Game Boy PROGRAMMING MANUAL v.1.0, DMG-06-4216-001-A Released 11/09/1999 Nintendo.
* Study of the techniques for emulation programming, Victor Moya del Barrio, Agustin Fernandez, 2001.
* GB microChip Z80 opCodes, http://goldencrystal.free.fr/GBZ80Opcodes.pdf
* http://bgb.bircd.org/pandocs.htm
* My own know.


##[Development's video playlist](https://www.youtube.com/watch?v=zCy8_IkdMeE&list=PLNph7ndeSqE9E6XqolWg-6Vx4AOeneJSZ&index=1) 
