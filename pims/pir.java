package pims;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

//import javax.xml.crypto.dsig.keyinfo.KeyValue;


public class pir{
    public int[] t_numero = {0,0,0,0};


    public void set_t_numero(int[] qualquer){

        t_numero = qualquer;
    }

    
    public void delay(int milis){

        /*BufferedReader leitor_x = new BufferedReader((new InputStreamReader(System.in)));

        try{

            String stx = leitor_x.readLine();
        }catch(Exception e){}*/

        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void novi_mais(){
    
        for(int i = 0; i <= t_numero.length - 1; i++){
    
            if (t_numero[i] >= 10){

                t_numero[i] = 0;
                    
                t_numero[i-1] ++;

                novi_mais();
            }
        }
    }


    public void n_mais(int a_somar){

        for(int i = 0; i < a_somar; i++){

            t_numero[t_numero.length-1] += 1;

            novi_mais();
        }
    }


    public void print_numero(){

        for(int i = 0; i < t_numero.length; i++){
            System.out.print(t_numero[i]+",");
        }
        System.out.println(" ");
        
    }


    public void File_create(){
        try {
            File myObj = new File("mer.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public String File_reader(){

        String data = "";

        try {
            File myObj = new File("mer.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return(data);
    }


    public String array_to_string(int[] array){

        String abstoni = "";

        for(int i = 0; i < array.length; i++){
            abstoni += array[i];
        }

        return abstoni;
    }


    public void File_writ(String write){
        try {
            FileWriter myWriter = new FileWriter("mem.txt");
            myWriter.write(write);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void digitas(int delay){

        boolean dg = false;

        Robot telef = null;
        try{
            telef = new Robot();
        } catch (AWTException e){
            e.printStackTrace();
        }

        for(int i = 0; i < t_numero.length; i++){

            delay(delay);

            switch (t_numero[i]) {
                case 0:
                telef.keyPress(KeyEvent.VK_0);
                telef.keyRelease(KeyEvent.VK_0);
                    break;
                
                case 1:
                telef.keyPress(KeyEvent.VK_1);
                telef.keyRelease(KeyEvent.VK_1);
                    break;

                case 2:
                telef.keyPress(KeyEvent.VK_2);
                telef.keyRelease(KeyEvent.VK_2);
                    break;

                case 3:
                telef.keyPress(KeyEvent.VK_3);
                telef.keyRelease(KeyEvent.VK_3);
                    break;
                
                case 4:
                telef.keyPress(KeyEvent.VK_4);
                telef.keyRelease(KeyEvent.VK_4);
                    break;

                case 5:
                telef.keyPress(KeyEvent.VK_5);
                telef.keyRelease(KeyEvent.VK_5);
                    break;

                case 6:
                telef.keyPress(KeyEvent.VK_6);
                telef.keyRelease(KeyEvent.VK_6);
                    break;

                case 7:
                telef.keyPress(KeyEvent.VK_7);
                telef.keyRelease(KeyEvent.VK_7);
                    break;

                case 8:
                telef.keyPress(KeyEvent.VK_8);
                telef.keyRelease(KeyEvent.VK_8);
                    break;
                
                case 9:
                telef.keyPress(KeyEvent.VK_9);
                telef.keyRelease(KeyEvent.VK_9);
                    break;

                default:
    
                if(dg == false){
                    System.out.println("digitas não ta de digitas");
                    System.out.println("verifique mim.txt");
                }
                dg = true;
                    break;
            }
        }
    }


    public boolean check_color_in_pos(int posx, int posy, int R, int G, int B){

        if(enchergas_R(posx, posy) == R && enchergas_G(posx, posy) == G && enchergas_B(posx, posy) == B){
            return true;
        }

        return false;
    }


    public void File_reader_reader(){

        String to_read = File_reader();

        Integer bs = 0;

        for(int i = 0; i < to_read.length(); i++){

            bs = Integer.valueOf(to_read.charAt(i));

            t_numero[i] = bs - 48;
        }
    }


    public int enchergas_R(int posx, int posy){

        Robot olho = null;
        try{
            olho = new Robot();
        } catch (AWTException e){
            e.printStackTrace();
        }

        Color olhas = olho.getPixelColor(posx, posy);

        return (olhas.getRed());
    }

    public int enchergas_G(int posx, int posy){

        Robot olho = null;
        try{
            olho = new Robot();
        } catch (AWTException e){
            e.printStackTrace();
        }

        Color olhas = olho.getPixelColor(posx, posy);

        return (olhas.getGreen());
    }

    public int enchergas_B(int posx, int posy){

        Robot olho = null;
        try{
            olho = new Robot();
        } catch (AWTException e){
            e.printStackTrace();
        }

        Color olhas = olho.getPixelColor(posx, posy);

        return (olhas.getBlue());
    }


    public void paras(){

        File_create();
        File_writ(array_to_string(t_numero));

        //System.out.println("---------------------------------");

        Runtime.getRuntime().halt(0);
    }


    public void check_paras(){

        if(t_numero[1] >= 50){
            paras();
        }
    }


    public void print_mouse_stuff(){

        //BufferedReader leitor_x = new BufferedReader((new InputStreamReader(System.in)));

        try{

            //String stx = leitor_x.readLine();

            Point p = MouseInfo.getPointerInfo().getLocation();
            int x = 0;
            int y = 0;

            x = p.x;
            y = p.y;

            //System.out.println("move_mouse(int " + x + ", int " + y + ");");
            System.out.println("x: " + x + ", y: " + y + " | " + " R: " + enchergas_R(x, y) + " G: " + enchergas_G(x, y) + " B: " + enchergas_B(x, y));
        }catch(Exception e){}
    }


    public void look_for_the_square(){

        int x = 2080;
        int y = 150;

        System.out.println("ying...");

        while(enchergas_B(x, y) != 255 && y <= 1079){

            y += 1;
        }

        x = 1780;

        System.out.println("xing...");

        while(enchergas_B(x, y + 20) != 255 && y <= 3285){

            x += 1;
        }

        x++;
        y++;

        System.out.println(x + "," + y);
    }


    public int string_to_int(String s){

        int foo = 0;

        try {
            foo = Integer.parseInt(s);
        } catch (Exception e) {
            System.out.println(e);
            foo = 0;
        }

        return foo;
    }


    public void look_for_the_square_manual(){

        int x = 3198;
        int y = 119;

        while(true){
            

            //BufferedReader leitor_x = new BufferedReader((new InputStreamReader(System.in)));
            //BufferedReader leitor_y = new BufferedReader((new InputStreamReader(System.in)));

            try{

                /*String stx = leitor_x.readLine();
                x = string_to_int(stx);

                String sty = leitor_y.readLine();
                y = string_to_int(sty);*/

                System.out.println("x: " + x + ", y: " + y + " | " + " R: " + enchergas_R(x, y) + " G: " + enchergas_G(x, y) + " B: " + enchergas_B(x, y));

            }catch(Exception e){

                System.out.println(e);
            }

        }
    }


    public void start(){

        File_reader_reader();
        
        //look_for_the_square_manual();
    }


    public void move_mouse(int x, int y){

        try {
            Robot robot = new Robot();

            robot.mouseMove(x, y);
        } catch (AWTException e) {

            e.printStackTrace();
        }
    }


    public void do_the_thing(int thing_to_do){

        Robot robot = null;
        try{
            robot = new Robot();
        } catch (AWTException e){
            e.printStackTrace();
        }

        switch (thing_to_do){

            case 1:

                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            break;

            case 2:

                robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
            break;

            case 3:

                robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
            break;

            case 4:

                robot.keyPress(KeyEvent.VK_CONTROL);
                delay(50);
                robot.keyPress(KeyEvent.VK_C);
                delay(50);
                robot.keyRelease(KeyEvent.VK_C);
                delay(50);
                robot.keyRelease(KeyEvent.VK_CONTROL);
            break;

            case 5:

                robot.keyPress(KeyEvent.VK_CONTROL);
                delay(50);
                robot.keyPress(KeyEvent.VK_V);
                delay(50);
                robot.keyRelease(KeyEvent.VK_V);
                delay(50);
                robot.keyRelease(KeyEvent.VK_CONTROL);
            break;

            case 6:

                robot.keyPress(KeyEvent.VK_DOWN);
                delay(50);
                robot.keyRelease(KeyEvent.VK_DOWN);
            break;

            case 7:

                robot.keyPress(KeyEvent.VK_SHIFT);
                delay(50);
                robot.keyPress(KeyEvent.VK_HOME);
                delay(50);
                robot.keyRelease(KeyEvent.VK_HOME);
                delay(50);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            break;

            default:
                System.err.println("invalue valid");
            break;
        }
    }


    public void wait_for_the_RGB(int posx, int posy, int varR, int varG, int varB){

        int R = enchergas_R(posx, posy);
        int G = enchergas_G(posx, posy);
        int B = enchergas_B(posx, posy);

        while(R != varR || G != varG || B != varB){
            
            R = enchergas_R(posx, posy);
            G = enchergas_G(posx, posy);
            B = enchergas_B(posx, posy);

            //System.out.println(".");

            delay(200);
        }
    }


    public void download_loop(){

        do_the_thing(7);
        delay(500);

        do_the_thing(4);
        delay(500);

        //////////////////////
        //////////////////////
        //////////////////////
        move_mouse(2147, 1064);
        delay(1000);

        do_the_thing(1);
        delay(1000);

        do_the_thing(5);
        delay(1000);

        move_mouse(2582, 726);
        delay(1000);

        do_the_thing(1);
        delay(1000);

        wait_for_the_RGB(2548, 533, 255, 255, 255);

        move_mouse(2274, 480);
        delay(1000);

        do_the_thing(1);
        delay(1000);
        //////////////////////
        //////////////////////
        //////////////////////

        move_mouse(2274, 1);
        delay(1000);

        do_the_thing(1);
        delay(1000);

        do_the_thing(6);
        delay(500);
    }


    public void m_loop(){

        //n_mais(1);
        //print_numero();
        //print_mouse_stuff();
        //check_paras();
        //paras();
        //look_for_the_square_manual();

        download_loop();

        n_mais(1);
        //check_paras();

        delay(50);

        print_numero();
    }


    public static void main(String[] args){

        pir pir = new pir();

        //pir.delay(3000);

        //pir.start();

        while (true){

            pir.m_loop();
        }
    }
}
