package pims;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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


    public int[] novi_mais(int[] numero, int base){
    
        for(int i = 0; i <= numero.length - 1; i++){
    
            if (numero[i] >= base){

                numero[i] = 0;
                    
                numero[i-1] ++;

                novi_mais(numero, base);
            }
        }

        return numero;
    }


    public void n_mais(int a_somar){

        for(int i = 0; i < a_somar; i++){

            t_numero[t_numero.length-1] += 1;

            t_numero = novi_mais(t_numero, 10);
        }
    }


    public void print_numero(){

        for(int i = 0; i < t_numero.length; i++){
            System.out.print(t_numero[i]+",");
        }
        System.out.println(" ");
    }


    public void File_create(String name){
        try {
            File myObj = new File(name + ".txt");
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


    static int count_lines(String name) throws IOException{

        Path filePath = Paths.get(name + ".txt");
        
        FileReader reader = new FileReader(filePath.toFile());

        int charInt;
        
        int lines = 1;
        
        while ((charInt = reader.read()) != -1){

            if(charInt == 10){
                lines++;
            }
        }

        reader.close();

        return lines;
    }


    public String[] File_reader(String name){

        int index = 0;

        String[] data = null;
        try {
            data = new String[count_lines(name)];
        } catch (IOException e) {
            
            e.printStackTrace();
        }

        try{
            File myObj = new File(name + ".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data[index] = myReader.nextLine();
                index ++;
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

        //File_create();
        //File_writ(array_to_string(t_numero));

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


    public boolean[] int_array_to_boolean_array(int[] to_ir){
    
        boolean[] to_return = new boolean[to_ir.length];

        for(int i = 0; i < to_ir.length; i ++){

            //System.out.println(i + "::" + to_ir.length);

            if(to_ir[i] == 1){

                to_return[i] = true;
            }else{

                to_return[i] = false;
            }
        }

        return to_return;
    }


    public void b_cont(){

        for_now for_now = new for_now();

        int[] b = {0,0,0,0,0,0,0,0,0};

        boolean[] boo = {false,false,false,false,false,false,false,false,false,};

        for(int i = 0; i < 378; i++){

            try {

                for_now.keying_the_keys(boo);
            }catch (Exception e){
            }

            b[b.length-1]++;

            novi_mais(b, 2);

            boo = int_array_to_boolean_array(b);

            //delay(50);

            //System.out.println("["+b[0]+"]"+"["+b[1]+"]"+"["+b[2]+"]"+"["+b[3]+"]"+"["+b[4]+"]"+"["+b[5]+"]"+"["+b[6]+"]"+"["+b[7]+"]"+"["+b[8]+"]");
        }
    }


    public void keyboard_doer(boolean[] b){

        for_now for_now = new for_now();

        try {

            for_now.keying_the_keys(b);
        }catch (Exception e){
        }
    }

    public void keyboard_doer(int[] b){

        keyboard_doer(int_array_to_boolean_array(b));
    }

    public void keyboard_doer(Integer[] b){

        //System.out.println("b[].intValue()");

        int[] c = {0,0,0,0,0,0,0,0,0};

        for(int i = 0; i < b.length; i++){

            c[i] = b[i].intValue();

            //System.out.println(b[i] + " : " + c[i]);
        }

        keyboard_doer(c);
    }


    public void File_reader_reader(){

        String[] to_read = File_reader("pims/act");

        System.out.println(to_read);

        Integer[] bs = {0,0,0,0,0,0,0,0,0};

        for(int i = 0; i < to_read.length; i++){

            for(int j = 0; j < to_read[i].length(); j++){

                if(to_read[i].charAt(j) == ';'){

                    //System.out.println();

                    keyboard_doer(bs);
                }else{

                    bs[j] = (Integer.valueOf(to_read[i].charAt(j)) - 48);

                    //System.out.print(bs[j]);
                }
            }
            //t_numero[i] = bs - 48;
        }
    }


    public void m_loop(){

        //n_mais(1);
        //print_numero();
        print_mouse_stuff();
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

        //pir.b_cont();

        pir.File_reader_reader();

        //while (true){

            //pir.m_loop();
        //}
    }
}
