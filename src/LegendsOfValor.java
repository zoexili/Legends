import java.util.Random;
import java.util.Scanner;

public class LegendsOfValor extends Game{
    private final int worldSize = 8;
    private HeroTeam heroTeam;
    private MonsterTeam monsterTeam;
    private World world;
    private Config config;
    private Fight fight;
    private Scanner sc;

    public LegendsOfValor(){
        this.sc = new Scanner(System.in);
        this.config = new Config();
        config.getCondfigs();
        this.world = new World(worldSize,worldSize,config);
        this.fight= new Fight(config);
    }

    @Override
    public void start() {
        printCastle();
        printHeroes();

        System.out.println(Colors.GREEN_BOLD_BRIGHT + "Welcome to Monsters and Heroes game! ");
        System.out.println(Colors.BLUE_BOLD_BRIGHT + "Author: Taoyu Chen & Li Xi");
        System.out.println(Colors.RESET + "\n\n");

        // start the music
        PlayMusic.playMusic("Music/Witcher3_TheTrail.wav");

        printCMD();
        this.heroTeam = new HeroTeam(3,config);
        this.monsterTeam = new MonsterTeam(config);
        monsterTeam.createMonsters(1);
        world.printMap(heroTeam,monsterTeam);

        for(int i=0;i<3;i++){
            Hero hero = heroTeam.heroes[i];
            System.out.println("Hi, "+hero.name);
            intoANewCell(hero.pos,hero.pos, world.getMap(), hero);
        }

        mainLoop();
    }

    public void mainLoop(){
        System.out.println(Colors.RED_BOLD_BRIGHT + "Game Start! " + Colors.RESET);
        int count = 0; // mark rounds. after 4 rounds new monsters will spawn
        while(true){
            count++;
            heroTurn();
            monsterTurn();
            if(count==8){
                count =0;
                monsterTeam.createMonsters(heroTeam.getTeamLevel());
            }
            System.out.println("\n\nRound End\n\n");
            System.out.println("Current map: ");
            world.printMap(heroTeam,monsterTeam);
            System.out.println("\n\nRound End\n\n");
        }
    }

    public void heroTurn(){
        for(int i = 0;i<3;i++){
            Hero hero = heroTeam.heroes[i];
            System.out.println("Hi, "+hero.name+", you are H"+i);
            while (true) {
                System.out.println("Before making each move, We recommend you use H/h, M/m, I/i B/b for more information about current status. ");
                System.out.println("Enter your command, H/h for help");
                sc = new Scanner(System.in);
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("q")) {
                    System.out.println("Quiting the game ...");
                    System.exit(0);
                } else if (input.equalsIgnoreCase("i")) {
                    System.out.println("Hero " + hero.name);
                    hero.printHeroInfo();
                    System.out.println("");
                }else if (input.equalsIgnoreCase("b")){
                    System.out.println("Hero " + hero.name);
                    hero.bag.printItems();
                    System.out.println("");
                }else if (input.equalsIgnoreCase("e")) {
                    System.out.println("Hero " + hero.name);
                    hero.equip();
                    System.out.println("");
                    break;
                }else if (input.equalsIgnoreCase("p")) {
                    System.out.println("Hero " + hero.name);
                    hero.drinkPotion();
                    System.out.println("");
                    break;
                }else if (input.equalsIgnoreCase("m")) {
                    world.printMap(heroTeam,monsterTeam);
                } else if (input.equalsIgnoreCase("h")) {
                    printCMD();
                }else if(input.equalsIgnoreCase("t")){
                    telePort(hero);
                    break;
                }else if(input.equalsIgnoreCase("r")){
                    hero.pos=hero.home;
                    break;
                }else if(input.equalsIgnoreCase("mv")){
                    movement(hero);
                    break;
                }else if(input.equalsIgnoreCase("a")){
                    fight.attack(hero,monsterTeam);
                    break;
                }else if(input.equalsIgnoreCase("s")){
                    fight.cast(hero,monsterTeam);
                    break;
                }
                else{
                    System.out.println("Invalid input, try again");
                }
            }

        }
    }

    // monsters automatically make their move
    // if there is a hero in their attacking range they will attack
    // otherwise they move forward
    public void monsterTurn(){
        System.out.println("Now is monsters' turn");
        for(int i = 0;i<monsterTeam.monsters.size();i++){
            Monster monster = monsterTeam.monsters.get(i);
            Hero target = meetHero(heroTeam,monster);
            // no hero in attacking range, move
            if(target==null){
                int oldX = monster.pos.getX();
                int oldY = monster.pos.getY();
                // if already a monster there, not moving
                if(world.monsterInCell(oldX+1,oldY,monsterTeam)){
                    continue;
                }
                monster.setPos(new Position(oldX+1,oldY));

                if(oldX+1==7){
                    System.out.println("Monster win!");
                    System.exit(0);
                }
            }
            // hero in range. attack him.
            else{
                Random rd = new Random();

                int originDamage = (int) (monster.getDamage() * 0.1);
                int actualDamage;
                if (target.armor != null) {
                    actualDamage = originDamage - (int) (target.defense * 0.1 + target.armor.getReduction() * 0.1);
                } else {
                    actualDamage = originDamage;
                }

                double dodgeRate = target.agility * 0.002;
                double rate = Math.random();
                if (rate < dodgeRate) {
                    System.out.println("Monster " + monster.getName() + " misses attack on " + target.getName());
                } else {
                    System.out.println("Monster " + monster.getName() + " attacks " + target.getName() + " with " + actualDamage + " hp");
                    target.minusHp(actualDamage);
                    if(target.hp<=0){
                        System.out.println(target.name+" is defeated. Now respawn");
                        target.pos = target.home;
                        fight.revive(target);
                    }
                }
            }
        }
    }

    // if hero and monster in the same lane and abd(x) <=1, monster can attack
    public Hero meetHero(HeroTeam heroTeam,Monster monster){
        for(int i=0;i<heroTeam.heroes.length;i++){
            Hero hero = heroTeam.heroes[i];
            if(Math.abs(hero.pos.getX()-monster.pos.getX())<=1&& hero.lane==monster.lane){
                return hero;
            }
        }
        return null;
    }

    public boolean movement(Hero hero) {
        String direction;
        printMoveCMD();
        while(true){
            System.out.println("Please enter your next move");
            sc = new Scanner(System.in);
            direction = sc.nextLine();

            Position oldPos = hero.pos;
            int x = oldPos.getX();
            int y = oldPos.getY();
            if (direction.equalsIgnoreCase("w")) {
                x -= 1;
            }
            else if (direction.equalsIgnoreCase("a")) {
                y -= 1;
            }
            else if (direction.equalsIgnoreCase("s")) {
                x += 1;
            }
            else if (direction.equalsIgnoreCase("d")) {
                y += 1;
            }
            else{
                System.out.println("Invalid input, try again");
                continue;
            }
            // try to move to the new position
            // check legal: in the map , not going to inaccessible cell, and not exceed monsters
            Position newPos = new Position(x, y);
            Cell[][] map = world.getMap();
            if (x >= worldSize || y >= worldSize) {
                System.out.println("Your move out of Map, try again");
                return false;
            } else if (map[x][y] instanceof InaccessibleCell) {
                map[x][y].heroIntoCell(hero);
                return false;
            }else if(x<lowestMonster(hero.lane)){
                System.out.println("Hero cannot move behind a monster! Try again");
                continue;
            }else if(world.heroInCell(x,y,heroTeam)){
                System.out.println("Already a hero in this cell! Try again");
            }
            else if(x==0){
                System.out.println("Hero win!");
                System.exit(0);
            }
            else {
                intoANewCell(oldPos,newPos, map,hero);
                return true;
            }
        }
    }

    public void intoANewCell(Position oldPos,Position pos, Cell[][] map,Hero hero) {
        int oldX = oldPos.getX();
        int oldY = oldPos.getY();
        int x = pos.getX();
        int y = pos.getY();
        map[oldX][oldY].heroExitCell(hero);
        //go into the cell and do correspond thing
        if (map[x][y] instanceof NexusCell) {
            hero.pos=pos;
            System.out.println("It is a market, do you want to enter?");
            System.out.println("M/m to enter, else to quit and make next move");
            sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("m")) {
                ((NexusCell) map[x][y]).generateRandomItems(config); // create selling list
                map[x][y].heroIntoCell(hero);
            }
        } else {
            hero.pos=pos;
            map[x][y].heroIntoCell(hero);
        }
    }

    public void telePort(Hero hero){
        while(true){
            
            sc = new Scanner(System.in);
            System.out.println("Do you want to continue to tp? Q/q to quit, other to continue");
            String input = sc.nextLine();
            if(input.equalsIgnoreCase("q")){
                break;
            }

            System.out.println("Please enter the coordinate you are teleporting to");
            System.out.println("For example, the left top is (0,0), right top is (0,7) ... ");
            sc = new Scanner(System.in);

            System.out.println("Enter row: ");
            while (!sc.hasNextInt()) {
                System.out.println("Not int！Enter again");
                sc.next();
            }
            int row = sc.nextInt();

            System.out.println("Enter col: ");
            while (!sc.hasNextInt()) {
                System.out.println("Not int！Enter again");
                sc.next();
            }
            int col = sc.nextInt();
            // make sure col not in 2 or 5
            int targetLane;
            if(col == 0 || col ==1){
                targetLane =0;
            }
            else if(col == 3 || col==4){
                targetLane=1;
            }
            else if(col==6||col==7){
                targetLane=2;
            }
            else{
                System.out.println("Invalid column, try again");
                continue;
            }

            // make sure not over the hero in target lane
            if(row<tpLimit(targetLane)){
                System.out.println("A hero may not teleport to a space ahead of the hero being teleported to, try again");
                continue;
            }
            // make sure no hero in the target cell
            if(world.heroInCell(row,col,heroTeam)){
                System.out.println("Already a hero in this cell! Try again");
                continue;
            }
            Cell[][] map = world.getMap();
            intoANewCell(hero.pos,new Position(row,col),map,hero);
            break;
        }
    }

    // the upper bound a hero can tp to
    public int tpLimit(int tpLane){
        int height = lowestMonster(tpLane);
        for(int i=0;i<heroTeam.heroes.length;i++){
            Hero hero = heroTeam.heroes[i];
            if(hero.lane==tpLane){
                height = Math.max(hero.pos.getX(),height);
            }
        }
        return height;
    }

    // the upper bound a hero can move to
    public int lowestMonster(int mvLane){
        int height = 0;
        for(int i=0;i<monsterTeam.monsters.size();i++){
            Monster monster = monsterTeam.monsters.get(i);
            if(monster.lane==mvLane){
                height=Math.max(monster.pos.getX(),height);
            }
        }
        return height;
    }

    public void printCMD() {
        System.out.println("");
        System.out.println("A/a: attack monster");
        System.out.println("B/b: show current Bag information of every team member");
        System.out.println("E/e: change equipment");
        System.out.println("I/i: show current team information");
        System.out.println("M/m: show map");
        System.out.println("MV/mv: make movement on hero");
        System.out.println("P/p: drink potions");
        System.out.println("Q/q: quit game");
        System.out.println("R/r: recall. hero will back to his home");
        System.out.println("S/s: cast a spell on monster");
        System.out.println("T/t: teleport");
        System.out.println("");
        System.out.println("H/h: show help");
    }

    public void printMoveCMD(){
        System.out.println("W/w: move up");
        System.out.println("A/a: move left");
        System.out.println("S/s: move down");
        System.out.println("D/d: move right");
    }

    //print a castle as the front page
    public void printCastle() {
        System.out.println(Colors.WHITE_BOLD_BRIGHT + "                               |");
        System.out.println("                 |>>>      _  _|_  _         |>>>");
        System.out.println("                 |>>>      _  _|_  _         |>>>");
        System.out.println("                    |        |;| |;| |;|        |");
        System.out.println("        _  _|_  _    \\\\.    .  /    _  _|_  _");
        System.out.println("               |;|_|;|_|;|    \\\\:. ,  /    |;|_|;|_|;|");
        System.out.println("               \\\\..      /    ||;   . |    \\\\.    .  /");
        System.out.println("                \\\\.  ,  /     ||:  .  |     \\\\:  .  /");
        System.out.println("                 ||:   |_   _ ||_ . _ | _   _||:   |");
        System.out.println("                 ||:  .|||_|;|_|;|_|;|_|;|_|;||:.  |");
        System.out.println("                 ||:   ||." + Colors.RED_BOLD + "YOUR LEGEND BEGINS HERE!" + Colors.WHITE_BOLD_BRIGHT + "|  ||");
        System.out.println("                 ||: . || .     . .   .  ,   ||:   |       \\,/");
        System.out.println("                 ||:   ||:  ,  _______   .   ||: , |            /`\\");
        System.out.println("                 ||:   || .   /+++++++\\    . ||:   |");
        System.out.println("                 ||:   ||.    |+++++++| .    ||: . |");
        System.out.println("                 ||:   ||.    |+++++++| .    ||: . |");
        System.out.println("        __ ||: . ||: ,  |+++++++|.  . _||_   |");
        System.out.println("                ____--`~    '--~~__|.    |+++++__|----~    ~`---,              ___");
        System.out.println("                -~--~                   ~---__|,--~'                  ~~----_____-~'   `~----~~");
    }

    // print warrior
    public void printHeroes() {
        System.out.println(Colors.CYAN_BRIGHT + "      WARRIOR" + Colors.PURPLE_BRIGHT + "                  SORCERER" + Colors.YELLOW_BRIGHT +"                 PALADIN");
        System.out.println(Colors.WHITE_BOLD_BRIGHT + "       ___                     /o\\    /^\\          / \\ ");
        System.out.println("      /___\\                   /__\\    'V'          |.|                            ");
        System.out.println("     (|0 0|)                 //..\\     I           |.|                           ");
        System.out.println("   __/{\\U/}\\_ ___/vvv        \\].`[/    I           |:|    \\\\                      ");
        System.out.println(" / \\  {~}   / _|__P|         /l\\/j\\   (]         \\_|:|_/  /  )                            ");
        System.out.println(" |  \\  ~   /_/    ||        /, ~~ , \\/ I           (Oo   / _I_                                ");
        System.out.println("  |_| (____)      ||         \\\\L__j^ \\/I          +\\\\ \\\\ || __|                               ");
        System.out.println(" \\_]/______\\   /\\_||_/\\      \\/--v}    I              \\ \\||___|                          ");
        System.out.println("    _\\_||_/_   |]_||_ [|     |    |    I                \\ /,:,\\-\\                           ");
        System.out.println("    (_,_||_,_) \\/ [] \\/     /|    |\\   I                 |,:, /-----\\                             ");
    }
}
