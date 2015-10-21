![](http://i.imgur.com/8X0qlu4.png)
![](http://i.imgur.com/HsOVI7x.png)

## Supported Features :
```
Z80 CPU emulated with Pool Async.
I/O Ports emulated Async (Video Display - Joypad Input - Timer and Divider Registers - Interrupts).
Reducing Power Consumption in CPU & ROM.
SRAM emulated for LCD display & Working memory.
Multi connector for ROM cartridge connection (ROM - MBC1 - MBC2 - MBC3)
Custom OAM (Sprite Attrib Memory ) & VRAM (Video Ram) & MRAM (Main Ram).
Graphics emulated with ascendency architectural (576i resolution).
Grayscale Display evolutioned to Monochromatic Shader and Polirazed Backlight multi-color.
Scalines emulated (Horizontal & Vertical & Mixes).
Block Tiles represented.
 
Complementary Tools Developed:
  Async Debug Console
  Disassembler for z80 instructions
  Analysis Dump Memory (OAM - VRAM - MRAM - CPU )
```

## Agile Software Development   

#### SOLID ( SRP - OCP - LSP - ISP - DIP )
```
The principles of SOLID are guidelines that can be applied while working on software to remove 
code smells by causing the programmer to refactor the software's source code until it is both 
legible and extensible
```

#### Test-Driven Development (TDD)
```
JUnit3 & JUnit4. Some exception with TestNG.
To Mock: Mockito 1.9.5 & PowerVictorito*

*As PowerMockito. Is based with reflection techniques, has better performance and without
dependency of Mockito. Own development.
```

#### Hollywood Principle - Don’t Call Us, We’ll Call You!
```
It is a useful paradigm that assists in the development of code with high cohesion and low 
coupling that is easier to debug, maintain and test. Dependency Injection (DI), which is a 
form of Inversion of Control (IoC) is an example of the use.
```

#### KISS Principle - Keep it simple, stupid.
```
The code should never overcomplicate design, considering different approach and trade-off and
finally choose the simplest one, given system constraints and business rules. 
```

#### DRY Principle - Don’t Repeat Yourself.
```
Help you to achieve more reusability and automation.
```

#### [Version Control - v.0.36.0](https://github.com/vicboma1/emulators/blob/master/gameboyclassic/semanticVersioning.md)  
```
Semantantic classes : Pixel - Tile - Sprite - Background.
Cached Pool Pixel & Tile.
Optimization and improving of performance CPU. (WIP - Rev 1.2)
Test in commands of the processor. (WIP - 70% )
Recude the power consumption : Halt (WIP - Rev 1.1) 
```



## [Winter FrameWork](https://github.com/vicboma1/emulators/tree/master/gameboyclassic/src/emulator/framework) - Rev 2.0.4 (Access API)
```
Adding Context in framework for persistent async configuration
* Permit access injector
* Permit access CommandMapper
* Immediately Install Extension
* Install Extension Async
* Immediately Configuration Queue
* Configuration Queue Async
* Execute promise Async
* Execute task queue Async
```


## [Development's video playlist](https://www.youtube.com/watch?v=y2GHmVP2p8w&list=PLNph7ndeSqE9E6XqolWg-6Vx4AOeneJSZ) 

[![](http://i.imgur.com/6omXJfT.png)](https://www.youtube.com/watch?v=_oDOJyyZyL0)

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

##Timer (Wip)
Execution time (E.T.) for each instruction is given in microseconds for an assumed 4 MHz clock.
Total machine cycles (M) are indicated with total clock periods (T States).

```
For example:
M Cycles:   2T States: 7  (4,3)  4     MHzE.T.: 1.75
```
Also indicated are the number of T States for each M cycle. 
Indicates that the instruction consists of 2 machine cycles. 
The first cycle contains 4 clock periods (T States). 
The second cycle contains 3 clock periods for a total of 7 clock periods or T States. 
The instruction executes in 1.75 microseconds.
Register format is indicated for each instruction with the most-significant bit to the left and the least-significant bit to the right. ^

Register (First cycle)
* TIMA (timer counter)
* TMA (timer modulo register) 
* TAC (timer control register)
* DIV (Divider Register)

Clock (Second cycle)
* Sub
* Div
* Count

^Time clock: The Z80 holds two types of clock (m and t)

##Registers JoyPad interrupt $FF00
* Bit 3 - P13 in port 
* Bit 2 - P12 in port 
* Bit 1 - P11 in port 
* Bit 0 - P10 in port
* Bit 5 - P15 out port { $8 - Down | $4 - Up | $2 - Left | $1 - Right }
* Bit 4 - P14 out port { $80 - Start | $40 - Select | $20 -B | $10 -A }

##Display
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

####[Shader](https://youtu.be/6Xt7j9bnRRI)
```
    Green Scale                                           Dark Yellow Scale
```
![](http://i.imgur.com/CoW1VWm.png?1)![](http://i.imgur.com/SekcP4j.png?3)

```
   Grey Scale                                             Gb Pocket
```
![](http://i.imgur.com/lZtHAOY.png?1)![](http://i.imgur.com/aM7FRdh.png?1) 

```
   Ligth Yellow Scale                                    Grey Stark Scale
```
![](http://i.imgur.com/c40HMSK.png?1)![](http://i.imgur.com/ksVSBVk.png?3)



####[Shader Invert](https://youtu.be/-VP92QbATH8)
```
    Green Scale                                           Dark Yellow Scale
```
![](http://i.imgur.com/tCvVCG0.png?1)![](http://i.imgur.com/leGBKI0.png?1)

```
   Grey Scale                                             Gb Pocket
```
![](http://i.imgur.com/6Sy8AVt.png?1)![](http://i.imgur.com/udE8F3z.png?1) 

```
   Ligth Yellow Scale                                    Grey Stark Scale
```
![](http://i.imgur.com/OFGflii.png?2)![](http://i.imgur.com/EVNPWue.png?1)



####[Shader Polarized](https://youtu.be/W7DstyFZ_j8)
```
    Green Scale                                           Dark Yellow Scale
```
![](http://i.imgur.com/3Mjvs0c.png?1)![](http://i.imgur.com/qVMaHGq.png?1)

```
   Grey Scale                                             Gb Pocket
```
![](http://i.imgur.com/JPMthue.png?1)![](http://i.imgur.com/MXs3TVp.png?2) 

```
   Ligth Yellow Scale                                    Grey Stark Scale
```
![](http://i.imgur.com/xxwiHgx.png?1)![](http://i.imgur.com/54QrwT8.png?1)


####[Backlight (DMG/Pocket) Custom](https://youtu.be/XElP1Y7MT7c)
```
    Orange                                                 Green 
```
![](http://i.imgur.com/OXJ38E1.png?1)![](http://i.imgur.com/BrUrCgH.png?2)  

```
    Pink                                                   Teal 
```
![](http://i.imgur.com/GUjyxkY.png?2)![](http://i.imgur.com/i22Hl6W.png?1)   

```
    White Blue                                             Red                                                      
```
![](http://i.imgur.com/pbv0YzB.png?1)![](http://i.imgur.com/RdS5dSj.png?2)

```
    Blue                                                   Lemon                                               
```
![](http://i.imgur.com/zwOmmJo.png?1)![](http://i.imgur.com/GIJSBZb.png?1)



####[Backlight (DMG/Pocket) Custom Invert](https://youtu.be/Em2u3XLbHvw)
```
    Orange                                                 Green 
```
![](http://i.imgur.com/dckFcmn.png?1)![](http://i.imgur.com/j6qShkC.png?1)  

```
    Pink                                                   Teal  
```
![](http://i.imgur.com/9C1Mjtw.png?2)![](http://i.imgur.com/2fryDFM.png?2)   

```
    White Blue                                              Red                                                    
```
![](http://i.imgur.com/22zlktq.png?1)![](http://i.imgur.com/gNxqTZx.png?2)

```
    Blue                                                   Lemon    (WIP)                                           
```
![](http://i.imgur.com/NmIdCzZ.png?1)



####[Backlight (DMG/Pocket) Custom Polarized](https://youtu.be/oZMo_Q4DtRY)
```
    Orange                                                 Green 
```
![](http://i.imgur.com/I2abuBO.png?1)![](http://i.imgur.com/srjwJf1.png?1)  

```
    Pink                                                   Teal  
```
![](http://i.imgur.com/G0vEUwF.png?2)![](http://i.imgur.com/yNTLBNU.png?3)   

```
    White Blue                                             Red                                                       
```
![](http://i.imgur.com/ffxkBTP.png?1)![](http://i.imgur.com/wXqSnP6.png?2)

```
    Blue                                                   Lemon (WIP)                             
```
![](http://i.imgur.com/IPPuw2B.png?1)



####[Scanlines Emulated](https://www.youtube.com/watch?v=rKAhKN8gFdw) / click on the image 
```
    Vertical
```
[![](http://i.imgur.com/g5qbrVb.png?3)](https://youtu.be/1-L3qa2kmMk)[![](http://i.imgur.com/gGNqsyv.png?1)](https://youtu.be/1-L3qa2kmMk)

```
    Horizontal
```
[![](http://i.imgur.com/p8tCYBQ.png?1)](https://youtu.be/lJZFqBM-fxQ)[![](http://i.imgur.com/c1VbQTF.png?3)](https://youtu.be/lJZFqBM-fxQ)
 
```
    Mixes 
```
[![](http://i.imgur.com/EhRVMJb.png?2)](https://youtu.be/90i4lERsSbU)[![](http://i.imgur.com/Hsu1SF2.png?1)](https://youtu.be/90i4lERsSbU)



####[Tile Block Graphics](https://youtu.be/LpRdiGlk1zY)
```
   Original                                                w/ Tile Block
```
![](http://i.imgur.com/UfURPZO.png?1)
```
   Original                                                w/ Tile Block
```
![](http://i.imgur.com/y26RHa6.png?1)


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

* PWR Using the HALT Instruction (WIP)
* PWR Using the STOP Instruction
* PWR Disabeling the Sound Controller (WIP)
* PWR Not using CGB Double Speed Mode (Future work)
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


#### Power Up Sequence

```
AF=$01B0
  BC=$0013
  DE=$00D8
  HL=$014D
  Stack Pointer=$FFFE
  [$FF05] = $00   ; TIMA
  [$FF06] = $00   ; TMA
  [$FF07] = $00   ; TAC
  [$FF10] = $80   ; NR10
  [$FF11] = $BF   ; NR11
  [$FF12] = $F3   ; NR12
  [$FF14] = $BF   ; NR14
  [$FF16] = $3F   ; NR21
  [$FF17] = $00   ; NR22
  [$FF19] = $BF   ; NR24
  [$FF1A] = $7F   ; NR30
  [$FF1B] = $FF   ; NR31
  [$FF1C] = $9F   ; NR32
  [$FF1E] = $BF   ; NR33
  [$FF20] = $FF   ; NR41
  [$FF21] = $00   ; NR42
  [$FF22] = $00   ; NR43
  [$FF23] = $BF   ; NR30
  [$FF24] = $77   ; NR50
  [$FF25] = $F3   ; NR51
  [$FF26] = $F1-GB, $F0-SGB ; NR52
  [$FF40] = $91   ; LCDC
  [$FF42] = $00   ; SCY
  [$FF43] = $00   ; SCX
  [$FF45] = $00   ; LYC
  [$FF47] = $FC   ; BGP
  [$FF48] = $FF   ; OBP0
  [$FF49] = $FF   ; OBP1
  [$FF4A] = $00   ; WY
  [$FF4B] = $00   ; WX
  [$FFFF] = $00   ; IE
  ```
  
  
### Raster graphics
The original GameBoy hardware simulates a cathode-ray tube (CRT) in its timings.
![Scanlines and blanking periods](http://imrannazar.com/content/img/jsgb-gpu-blank.png)

The following table states how long the GPU stays in each period, in terms of the CPU's T-clock which runs at 4194304 Hz.

```
          Period     	                GPU mode number  Time spent (clocks)
Scanline (accessing OAM)                  	2               80
Scanline (accessing VRAM)               	  3               172
Horizontal blank                        	  0               204
One line (scan and blank)		                X               456
Vertical blank	                            1           	4560 (10 lines)
Full frame (scans and vblank)		            timeX               70224
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

#### ROM
None (32KByte ROM only).
Small games of not more than 32KBytes ROM do not require a MBC chip for ROM banking

#### [MBC1](https://github.com/vicboma1/emulators/blob/master/gameboyclassic/LoadRomMBC1)
MBC1 is a memory controller that enables the use of 512 Kbits (64 Kbytes) or more of ROM and 256 Kbits (32 Kbytes) of RAM. 
RAM use by MBC1 is restricted to 64 Kbits (8 Kbytes).
```
 0000-3FFF - ROM Bank 00 (Read Only)
 4000-7FFF - ROM Bank 01-7F (Read Only)
 A000-BFFF - RAM Bank 00-03, if any (Read/Write)
 0000-1FFF - RAM Enable (Write Only)
 2000-3FFF - ROM Bank Number (Write Only)
 4000-5FFF - RAM Bank Number - or - Upper Bits of ROM Bank Number (Write Only)
 6000-7FFF - ROM/RAM Mode Select (Write Only)
```

#### MBC2
This memory controller works much like the MBC1 controller
RAM use by MBC1 is restricted to 64 Kbits (8 Kbytes).
```
 0000-3FFF - ROM Bank 00 (Read Only)
 4000-7FFF - ROM Bank 01-0F (Read Only)
 A000-A1FF - 512x4bits RAM, built-in into the MBC2 chip (Read/Write)
 0000-1FFF - RAM Enable (Write Only)
 2000-3FFF - ROM Bank Number (Write Only)
 ```

#### MBC3
This controller is similar to MBC1 except it accesses all 16mbits of ROM without requiring any writes to the 4000-5FFF area. Has a built-in battery-backed Real Time Clock (RTC) not found in any other MBC.
```
 0000-3FFF - ROM Bank 00 (Read Only)
 4000-7FFF - ROM Bank 01-7F (Read Only)
 A000-BFFF - RAM Bank 00-03, if any (Read/Write)
 A000-BFFF - RTC Register 08-0C (Read/Write)
 0000-1FFF - RAM and Timer Enable (Write Only)
 2000-3FFF - ROM Bank Number (Write Only)
 4000-5FFF - RAM Bank Number - or - RTC Register Select (Write Only)
 6000-7FFF - Latch Clock Data (Write Only)
 The Clock Counter Registers
 The Day Counter
 Delays
 ```



* Some games do not work for now. Step by step.
 


##References :
* Game BoyTM CPU Manual, Pan of Anthrox, GABY, Marat Fayzullin, Pascal Felber, Paul Robson, Martin Korth, kOOPa, Bowser.
* ￼Game Boy PROGRAMMING MANUAL v.1.0, DMG-06-4216-001-A Released 11/09/1999 Nintendo.
* Study of the techniques for emulation programming, Victor Moya del Barrio, Agustin Fernandez, 2001.
* GameBoy Faqs http://www.devrs.com/gb/files/faqs.html
* GB microChip Z80 opCodes, http://goldencrystal.free.fr/GBZ80Opcodes.pdf
* http://bgb.bircd.org/pandocs.htm
* http://www.z80.info/z80undoc.htm for Zilog z80 instructions
* http://www.ticalc.org/pub/text/z80/z80.txt for Zilog z80 instructions
* http://sgate.emt.bme.hu/patai/publications/z80guide/app1b.html for Zilog z80 instructions
* Semantic Versioning 2.0.0 http://semver.org
* http://gbdev.gg8.se for technical specification
* Hollywood Principle - "Don't call us, we'll call you" - http://martinfowler.com/bliki/InversionOfControl.html 
* http://blog.cballesterosvelasco.es & http://pspemu.soywiz.com
* My own knowledge.

