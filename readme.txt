# CS611-<Assignment 4>
## Legends of Valor
---------------------------------------------------------------------------
Taoyu Chen
mirack@bu.edu
U82740711

Li Xi
lxi@bu.edu
U68710477

## Compiling Instruction
---------------------------------------------------------------------------
1. cd Valor-main (the major folder containing all .java and .txt files)
2. Compile commands:
javac --source-path src -d bin src/*.java
java -cp bin Main

## Potential Bonus Points
---------------------------------------------------------------------------
1. add background game music

2. add ascii arts at the start of the game and colors.

3. add more commands in main menu
'''
System.out.println("W/w: move up");
System.out.println("A/a: move left");
System.out.println("S/s: move down");
System.out.println("D/d: move right");
System.out.println("");
System.out.println("Q/q: quit game");
System.out.println("I/i: show current team information");
System.out.println("B/b: show current Bag information of every team member");
System.out.println("E/e: change equipment");
System.out.println("P/p: drink potions");
System.out.println("M/m: show map");
System.out.println("");
System.out.println("H/h: show help");
'''
4.first born in market place, so player can buy items to be stronger

5.factory design pattern. Use to generate hero instance, monster instance and so on.

6.put all config(txt data) in a new class and can fetch them with an instance.

7.level of monsters is exactly levels of heroes. Like heroes can be level 2, 5, 9; and the monsters they meet will be 2,5,9 too.

## Design
---------------------------------------------------------------------------
Fight: controls the fighting actions of heroes during the fights with monsters, and estimates the HP statistics after the fights.

Items: father of all items in the market, including weapon, armor, potion, spell.

Weapon, Armor, Potion, Spell and their Factory: concrete items have different attributes maybe. And use factory to create their instances.
And Spell has 3 different sons Fire/Ice/Lightning

Bag: store all items of a hero

World, Cell, MarketCell, InaccessibleCell, BushCell, CaveCell, KoulouCell, NexusCell, PlainCell:
World is basically a map, consist of 2-d array cells. Cell has 7 different sons.
NexusCell is the son of MarketCell. The fight cell in the previous version is replaced by Fight.

Role, Monster, Hero and their Factory
Warriors, Sorcerers, Paladins, Dragons, Exoskeletons, Spirits are concrete heroes and monsters, their sons.
Team, HeroTeam and MonsterTeam: Team includes a group of roles, including heroes and monsters. HeroTeam and
MonsterTeam are the sons of Team, which are the implementation of the specific roles.

Config: read all the txt files and store in the class.

Main, Game, LegendsOfValor: main loop of the game, control main things. Heroes' movements and teleport actions
are added and implemented here. Monsters' fighting and moving turn logics are also implemented here.

## Others
---------------------------------------------------------------------------
1.Comment out line 31 in LegendsOfValor.java to turn off background music.
2.When the instructions in the terminal asking for a number input, don't enter a char or string. I haven't solve all the input problem for limited time.
And do not exceed the index.
3.I adjust some math value of the heroes and monsters to make the game easier.
4.There might be some functions I implemented but forgot to print out, sorry for that.


## Instructions
---------------------------------------------------------------------------
Output:
                               |
                 |>>>      _  _|_  _         |>>>
                 |>>>      _  _|_  _         |>>>
                    |        |;| |;| |;|        |
        _  _|_  _    \\.    .  /    _  _|_  _
               |;|_|;|_|;|    \\:. ,  /    |;|_|;|_|;|
               \\..      /    ||;   . |    \\.    .  /
                \\.  ,  /     ||:  .  |     \\:  .  /
                 ||:   |_   _ ||_ . _ | _   _||:   |
                 ||:  .|||_|;|_|;|_|;|_|;|_|;||:.  |
                 ||:   ||.YOUR LEGEND BEGINS HERE!|  ||
                 ||: . || .     . .   .  ,   ||:   |       \,/
                 ||:   ||:  ,  _______   .   ||: , |            /`\
                 ||:   || .   /+++++++\    . ||:   |
                 ||:   ||.    |+++++++| .    ||: . |
                 ||:   ||.    |+++++++| .    ||: . |
        __ ||: . ||: ,  |+++++++|.  . _||_   |
                ____--`~    '--~~__|.    |+++++__|----~    ~`---,              ___
                -~--~                   ~---__|,--~'                  ~~----_____-~'   `~----~~
      WARRIOR                  SORCERER                 PALADIN
       ___                     /o\    /^\          / \
      /___\                   /__\    'V'          |.|
     (|0 0|)                 //..\     I           |.|
   __/{\U/}\_ ___/vvv        \].`[/    I           |:|    \\
 / \  {~}   / _|__P|         /l\/j\   (]         \_|:|_/  /  )
 |  \  ~   /_/    ||        /, ~~ , \/ I           (Oo   / _I_
  |_| (____)      ||         \\L__j^ \/I          +\\ \\ || __|
 \_]/______\   /\_||_/\      \/--v}    I              \ \||___|
    _\_||_/_   |]_||_ [|     |    |    I                \ /,:,\-\
    (_,_||_,_) \/ [] \/     /|    |\   I                 |,:, /-----\
Welcome to Monsters and Heroes game!
Author: Taoyu Chen & Li Xi




A/a: attack monster
B/b: show current Bag information of every team member
E/e: change equipment
I/i: show current team information
M/m: show map
MV/mv: make movement on hero
P/p: drink potions
Q/q: quit game
R/r: recall. hero will back to his home
S/s: cast a spell on monster
T/t: teleport

H/h: show help
Warriors
Name/mana/strength/agility/dexterity/starting money/starting experience
[Gaerdal_Ironhand, 100, 700, 500, 600, 1354, 7]
[Sehanine_Monnbow, 600, 700, 800, 500, 2500, 8]
[Muamman_Duathall, 300, 900, 500, 750, 2546, 6]
[Flandal_Steelskin, 200, 750, 650, 700, 2500, 7]
[Undefeated_Yoj, 400, 800, 400, 700, 2500, 7]
[Eunoia_Cyn, 400, 700, 800, 600, 2500, 6]
Sorcerers
Name/mana/strength/agility/dexterity/starting money/starting experience
[Rillifane_Rallathil, 1300, 750, 450, 500, 2500, 9]
[Segojan_Earthcaller, 900, 800, 500, 650, 2500, 5]
[Reign_Havoc, 800, 800, 800, 800, 2500, 8]
[Reverie_Ashels, 900, 800, 700, 400, 2500, 7]
[Kalabar, 800, 850, 400, 600, 2500, 6]
[Skye_Soar, 1000, 700, 400, 500, 2500, 5]
Paladins
Name/mana/strength/agility/dexterity/starting money/starting experience
[Parzival, 300, 750, 650, 700, 2500, 7]
[Sehanine_Moonbow, 300, 750, 700, 700, 2500, 7]
[Skoraeus_Stonebones, 250, 650, 600, 350, 2500, 4]
[Garl_Glittergold, 100, 600, 500, 400, 2500, 5]
[Amaryllis_Astra, 500, 500, 500, 500, 2500, 5]
[Caliber_Heist, 400, 400, 400, 400, 2500, 8]

Enter W/w for warriors, S/s for sorcerers, P/p for Paladins

Input:
w

Output:
Please choose the hero you want
From top to bottom, first one is num 0, next one is 1 ...
In the corresponding directory

Input:
1

Output:
Now you are Sehanine_Monnbow

Warriors
Name/mana/strength/agility/dexterity/starting money/starting experience
[Gaerdal_Ironhand, 100, 700, 500, 600, 1354, 7]
[Sehanine_Monnbow, 600, 700, 800, 500, 2500, 8]
[Muamman_Duathall, 300, 900, 500, 750, 2546, 6]
[Flandal_Steelskin, 200, 750, 650, 700, 2500, 7]
[Undefeated_Yoj, 400, 800, 400, 700, 2500, 7]
[Eunoia_Cyn, 400, 700, 800, 600, 2500, 6]
Sorcerers
Name/mana/strength/agility/dexterity/starting money/starting experience
[Rillifane_Rallathil, 1300, 750, 450, 500, 2500, 9]
[Segojan_Earthcaller, 900, 800, 500, 650, 2500, 5]
[Reign_Havoc, 800, 800, 800, 800, 2500, 8]
[Reverie_Ashels, 900, 800, 700, 400, 2500, 7]
[Kalabar, 800, 850, 400, 600, 2500, 6]
[Skye_Soar, 1000, 700, 400, 500, 2500, 5]
Paladins
Name/mana/strength/agility/dexterity/starting money/starting experience
[Parzival, 300, 750, 650, 700, 2500, 7]
[Sehanine_Moonbow, 300, 750, 700, 700, 2500, 7]
[Skoraeus_Stonebones, 250, 650, 600, 350, 2500, 4]
[Garl_Glittergold, 100, 600, 500, 400, 2500, 5]
[Amaryllis_Astra, 500, 500, 500, 500, 2500, 5]
[Caliber_Heist, 400, 400, 400, 400, 2500, 8]

Enter W/w for warriors, S/s for sorcerers, P/p for Paladins

Input:
s

Output:
Please choose the hero you want
From top to bottom, first one is num 0, next one is 1 ...

Input:
1

Output:
Now you are Segojan_Earthcaller:

Warriors
Name/mana/strength/agility/dexterity/starting money/starting experience
[Gaerdal_Ironhand, 100, 700, 500, 600, 1354, 7]
[Sehanine_Monnbow, 600, 700, 800, 500, 2500, 8]
[Muamman_Duathall, 300, 900, 500, 750, 2546, 6]
[Flandal_Steelskin, 200, 750, 650, 700, 2500, 7]
[Undefeated_Yoj, 400, 800, 400, 700, 2500, 7]
[Eunoia_Cyn, 400, 700, 800, 600, 2500, 6]
Sorcerers
Name/mana/strength/agility/dexterity/starting money/starting experience
[Rillifane_Rallathil, 1300, 750, 450, 500, 2500, 9]
[Segojan_Earthcaller, 900, 800, 500, 650, 2500, 5]
[Reign_Havoc, 800, 800, 800, 800, 2500, 8]
[Reverie_Ashels, 900, 800, 700, 400, 2500, 7]
[Kalabar, 800, 850, 400, 600, 2500, 6]
[Skye_Soar, 1000, 700, 400, 500, 2500, 5]
Paladins
Name/mana/strength/agility/dexterity/starting money/starting experience
[Parzival, 300, 750, 650, 700, 2500, 7]
[Sehanine_Moonbow, 300, 750, 700, 700, 2500, 7]
[Skoraeus_Stonebones, 250, 650, 600, 350, 2500, 4]
[Garl_Glittergold, 100, 600, 500, 400, 2500, 5]
[Amaryllis_Astra, 500, 500, 500, 500, 2500, 5]
[Caliber_Heist, 400, 400, 400, 400, 2500, 8]

Enter W/w for warriors, S/s for sorcerers, P/p for Paladins

Input:
p

Output:
Please choose the hero you want
From top to bottom, first one is num 0, next one is 1 ...

Input:
1

Output:
Now you are Sehanine_Moonbow

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |    M0 |  | X X X |  |       |  |    M1 |  | X X X |  |       |  |    M2 |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  P - P - P  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  K - K - K  P - P - P  I - I - I  C - C - C  B - B - B

B - B - B  K - K - K  I - I - I  P - P - P  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  K - K - K  I - I - I  P - P - P  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  P - P - P  I - I - I  B - B - B  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  P - P - P  I - I - I  B - B - B  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  K - K - K  P - P - P  I - I - I  K - K - K  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  K - K - K  P - P - P  I - I - I  K - K - K  B - B - B

B - B - B  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  K - K - K  C - C - C

B - B - B  K - K - K  I - I - I  B - B - B  K - K - K  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  K - K - K  I - I - I  B - B - B  K - K - K  I - I - I  C - C - C  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
| H0    |  |       |  | X X X |  | H1    |  |       |  | X X X |  | H2    |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

H as your heroes places, M as monsters places
Hi, Sehanine_Monnbow
Exit Nexus
It is a market, do you want to enter?
M/m to enter, else to quit and make next move

Input:
m

Output:
Welcome to the market!
Hi, Sehanine_Monnbow
Do you want to Buy or Sell?
Enter B/b for buying, S/s for selling, Q/q for quit

Input:
b

Output:
Here are weapons:
Name | cost | level | damage | required hands
Dagger 200 1 250 1
Scythe 1000 6 1100 2
Scythe 1000 6 1100 2
TSwords 1400 8 1600 2
Axe 550 5 850 1
TSwords 1400 8 1600 2
Sword 500 1 800 1
TSwords 1400 8 1600 2
Dagger 200 1 250 1
Here are Armors:
Name | cost | required level | damage reduction
Breastplate 350 3 600
Platinum_Shield 150 1 200
Guardian_Angel 1000 10 1000
Guardian_Angel 1000 10 1000
Platinum_Shield 150 1 200
Full_Body_Armor 1000 8 1100
Wizard_Shield 1200 10 1500
Wizard_Shield 1200 10 1500
Full_Body_Armor 1000 8 1100
Here are Potions:
Name | cost | required level | attribute increase | attribute affected
Ambrosia 1000 8 150 All
Mermaid_Tears 850 5 100 Health/Mana/Strength/Agility
Strength_Potion 200 1 75 Strength
Strength_Potion 200 1 75 Strength
Magic_Potion 350 2 100 Mana
Ambrosia 1000 8 150 All
Ambrosia 1000 8 150 All
Magic_Potion 350 2 100 Mana
Healing_Potion 250 1 100 Health
Here are Spells:
Name | cost | required level | damage | mana cost | type
Heat_Wave 450 2 600 150 Fire
Ice_Blade 250 1 450 100 Ice
Lightning_Dagger 400 1 500 150 Lightning
Hell_Storm 600 3 950 600 Fire
Ice_Blade 250 1 450 100 Ice
Thunder_Blast 750 4 950 400 Lightning
Flame_Tornado 700 4 850 300 Fire
Frost_Blizzard 750 5 850 350 Ice
Thunder_Blast 750 4 950 400 Lightning
There can be duplicate items in the market
CMD：
Press 1 for weapon
Press 2 for armor
Press 3 for potion
Press 4 for spell
Press q to leave
Your gold: 2500	Your level: 1
What do you want to buy?

Input:
1

Output:
Which one do you want to buy
Enter a number, for top to bottom on Weapon list, the first one is 0, second one is 1...

Input:
0

Output:
You have bought: Dagger
CMD：
Press 1 for weapon
Press 2 for armor
Press 3 for potion
Press 4 for spell
Press q to leave
Your gold: 2300	Your level: 1
What do you want to buy?

Input:
q

Output:
Do you want to Buy or Sell?
Enter B/b for buying, S/s for selling, Q/q for quit

Input:
q

Output:
Hi, Segojan_Earthcaller
Exit Nexus
It is a market, do you want to enter?
M/m to enter, else to quit and make next move

Input:
q

Output:
Hi, Sehanine_Moonbow
Exit Nexus
It is a market, do you want to enter?
M/m to enter, else to quit and make next move

Input:
q

Output:
Game Start!
Hi, Sehanine_Monnbow, you are H0
Before making each move, We recommend you use H/h, M/m, I/i B/b for more information about current status.
Enter your command, H/h for help

Input:
h

Output:
A/a: attack monster
B/b: show current Bag information of every team member
E/e: change equipment
I/i: show current team information
M/m: show map
MV/mv: make movement on hero
P/p: drink potions
Q/q: quit game
R/r: recall. hero will back to his home
S/s: cast a spell on monster
T/t: teleport

H/h: show help
Before making each move, We recommend you use H/h, M/m, I/i B/b for more information about current status.
Enter your command, H/h for help

Input:
mv

Output:
W/w: move up
A/a: move left
S/s: move down
D/d: move right
Please enter your next move

Input:
w

Output:
Exit Nexus
Bush spaces increase the dexterity
Hi, Segojan_Earthcaller, you are H1
Before making each move, We recommend you use H/h, M/m, I/i B/b for more information about current status.
Enter your command, H/h for help

______________________________Heroes and monsters moved towards each other_________________________________
Output:
Current map:
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  P - P - P  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  K - K - K  P - P - P  I - I - I  C - C - C  B - B - B

B - B - B  K - K - K  I - I - I  P - P - P  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  K - K - K  I - I - I  P - P - P  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  P - P - P  I - I - I  B - B - B  P - P - P  I - I - I  C - C - C  C - C - C
| H0    |  |    M0 |  | X X X |  | H1    |  |    M1 |  | X X X |  | H2    |  |    M2 |
C - C - C  P - P - P  I - I - I  B - B - B  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  K - K - K  P - P - P  I - I - I  K - K - K  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  K - K - K  P - P - P  I - I - I  K - K - K  B - B - B

B - B - B  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  K - K - K  C - C - C

B - B - B  K - K - K  I - I - I  B - B - B  K - K - K  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  K - K - K  I - I - I  B - B - B  K - K - K  I - I - I  C - C - C  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

H as your heroes places, M as monsters places

Round End

Hi, Sehanine_Monnbow, you are H0
Before making each move, We recommend you use H/h, M/m, I/i B/b for more information about current status.
Enter your command, H/h for help

Input:
a

Output:
Do you want to continue to attack? Q/q to quit, other to continue

Input:
a

Output:
Please enter the coordinate of the monster you want to attack
For example, the left top is (0,0), right top is (0,7) ...
Monster name: Natsunomeryu level: 1 hp: 100 damage: 100 defense: 200 dodge: 10
Monster name: Blinky level: 1 hp: 100 damage: 450 defense: 350 dodge: 35
Monster name: BigBad-Wolf level: 1 hp: 100 damage: 150 defense: 250 dodge: 15
Enter row:

Input:
3

Output:
Enter col:

Input:
1

Output:
Hero Sehanine_Monnbow attacks Natsunomeryu with 50 hp

Hi, Segojan_Earthcaller, you are H1
Before making each move, We recommend you use H/h, M/m, I/i B/b for more information about current status.
Enter your command, H/h for help

Input:
t

Output:
Do you want to continue to tp? Q/q to quit, other to continue

Input:
t

Output:
Please enter the coordinate you are teleporting to
For example, the left top is (0,0), right top is (0,7) ...
Enter row:

Input:
3

Output:
Enter col:

Input:
1

Output:
Exit Bush
Plain, no extra attribute
Hi, Sehanine_Moonbow, you are H2
Before making each move, We recommend you use H/h, M/m, I/i B/b for more information about current status.
Enter your command, H/h for help

Input:
a

Output:
Do you want to continue to attack? Q/q to quit, other to continue

Input:
a

Output:
Please enter the coordinate of the monster you want to attack
For example, the left top is (0,0), right top is (0,7) ...
Monster name: Natsunomeryu level: 1 hp: 50 damage: 100 defense: 200 dodge: 10
Monster name: Blinky level: 1 hp: 100 damage: 450 defense: 350 dodge: 35
Monster name: BigBad-Wolf level: 1 hp: 100 damage: 150 defense: 250 dodge: 15
Enter row:

Input:
3

Output:
Enter col:

Input:
7

Output:
Hero Sehanine_Moonbow attacks BigBad-Wolf with 49 hp
Now is monsters' turn
Monster Natsunomeryu misses attack on Sehanine_Monnbow
Monster Blinky misses attack on Segojan_Earthcaller
Monster BigBad-Wolf misses attack on Sehanine_Moonbow

Round End


Current map:
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  K - K - K  P - P - P  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  K - K - K  P - P - P  I - I - I  C - C - C  B - B - B

B - B - B  K - K - K  I - I - I  P - P - P  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  K - K - K  I - I - I  P - P - P  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  P - P - P  I - I - I  B - B - B  P - P - P  I - I - I  C - C - C  C - C - C
| H0    |  | H1 M0 |  | X X X |  |       |  |    M1 |  | X X X |  | H2    |  |    M2 |
C - C - C  P - P - P  I - I - I  B - B - B  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  K - K - K  P - P - P  I - I - I  K - K - K  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  K - K - K  P - P - P  I - I - I  K - K - K  B - B - B

B - B - B  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  K - K - K  C - C - C

B - B - B  K - K - K  I - I - I  B - B - B  K - K - K  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  K - K - K  I - I - I  B - B - B  K - K - K  I - I - I  C - C - C  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

H as your heroes places, M as monsters places


Round End


Hi, Sehanine_Monnbow, you are H0
Before making each move, We recommend you use H/h, M/m, I/i B/b for more information about current status.
Enter your command, H/h for help

Input:
a

Output:
Do you want to continue to attack? Q/q to quit, other to continue

Input:
a

Output:
Please enter the coordinate of the monster you want to attack
For example, the left top is (0,0), right top is (0,7) ...
Monster name: Natsunomeryu level: 1 hp: 50 damage: 100 defense: 200 dodge: 10
Monster name: Blinky level: 1 hp: 100 damage: 450 defense: 350 dodge: 35
Monster name: BigBad-Wolf level: 1 hp: 51 damage: 150 defense: 250 dodge: 15
Enter row:

Input:
3

Output:
Enter col:

Input:
1

Output:
Hero Sehanine_Monnbow attacks Natsunomeryu with 50 hp
Natsunomeryu is dead. Your successfully killed it.
Hero Sehanine_Monnbow
LEVEL UP!






