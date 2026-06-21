import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

public class atm{

    static String[] old_names;
    static String[] new_names;

    static String[] file_times;
    static String[] file_names;
    static String[] file_names_and_times;
    static String directory = "";
    static int number_of_files = 0;


    static String make_length(String str, int length){

        if(str.length() < length){

            str = make_length(str + "0", length);
        }

        return str;
    }


    static String truncate(String str, int startCount, int endCount){

        // Ensure indices remain within bounds to avoid StringIndexOutOfBoundsException
        if (str != null && str.length() >= (startCount + endCount)){

            String truncated = str.substring(startCount, str.length() - endCount);

            return truncated;
        }
        return "StringIndexOutOfBoundsException";
    }


    static void list_directory(){

        File folder = new File(System.getProperty("user.dir"));
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null){

            for (File file : listOfFiles){

                if (file.isFile()){

                    number_of_files++;
                }
            }

            number_of_files--;

            //System.out.println("number of files: " + number_of_files);

            file_times           = new String[number_of_files];
            file_names           = new String[number_of_files];
            new_names            = new String[number_of_files];
            old_names            = new String[number_of_files];
            file_names_and_times = new String[number_of_files];

            int i = 0;

            for (File file : listOfFiles){

                if (file.isFile() & !file.getName().contains("atm.c")){

                    //System.out.println("F: " + file.getName());

                    file_names[i] = file.getName();
                    i++;

                }else if (file.isDirectory()){

                    //System.out.println("D: " + file.getName());
                }
            }
        }
    }


    static String meta_data_thing(String file_name){

        //System.out.println(directory + file_name);

        Path file =  Paths.get(directory + file_name);
        BasicFileAttributes attr = null;

        try{

            attr = Files.readAttributes(file, BasicFileAttributes.class);
        }catch (IOException e){
        
            e.printStackTrace();
        }
        
        
        //System.out.println("creationTime: " + attr.creationTime());
        //System.out.println("lastAccessTime: " + attr.lastAccessTime());
        //System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
        //System.out.println("isDirectory: " + attr.isDirectory());
        //System.out.println("isOther: " + attr.isOther());
        //System.out.println("isRegularFile: " + attr.isRegularFile());
        //System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
        //System.out.println("size: " + attr.size());

        return "" + attr.creationTime();
    }


    static void start_variables(){

        directory = System.getProperty("user.dir") + "/";

        list_directory();

        for(int i = 0; i < number_of_files; i++){

            file_times[i] = truncate(make_length((meta_data_thing(file_names[i])), 30), 8, 11);

            file_names_and_times[i] = (file_times[i] + " " + file_names[i]);

            //System.out.println("\n" + "------------" + "\n");
            //System.err.println(file_names[i]);
            //System.out.println(file_times[i]);
        }

        Arrays.sort(file_names_and_times);

        //String str = String.valueOf(path); //This is Null Safe
        //System.out.println(str);
    }


    static int[] novi_mais(int[] t_numero){
    
        for(int i = 0; i <= t_numero.length - 1; i++){
    
            if (t_numero[i] >= 10){

                t_numero[i] = 0;
                    
                t_numero[i -1] ++;

                novi_mais(t_numero);
            }
        }
        return t_numero;
    }


    static int[] add_one(int[] t_numero){

            t_numero[t_numero.length-1] += 1;

            for(int i = 0; i < t_numero.length; i++){

                //System.out.println(t_numero[i]);
            }

        t_numero = novi_mais(t_numero);

        return t_numero;
    }


    static String array_to_string(int[] to_to){

        String str = "";

        for(int i = 0; i < to_to.length; i++){

            str = str + to_to[i];
        }

        return str;
    }


    static void make_new_names_and_old_names(){

        int[] number_for_new_file_names_int_arrey;

        String number_for_new_file_names_String;

        if(number_of_files > 999){

            number_for_new_file_names_int_arrey = new int[4];
            number_for_new_file_names_int_arrey[0] = 0;
            number_for_new_file_names_int_arrey[1] = 0;
            number_for_new_file_names_int_arrey[2] = 0;
            number_for_new_file_names_int_arrey[3] = 0;
        }else if(number_of_files > 99){

            number_for_new_file_names_int_arrey = new int[3];
            number_for_new_file_names_int_arrey[0] = 0;
            number_for_new_file_names_int_arrey[1] = 0;
            number_for_new_file_names_int_arrey[2] = 0;
        }else if(number_of_files > 9){

            number_for_new_file_names_int_arrey = new int[2];
            number_for_new_file_names_int_arrey[0] = 0;
            number_for_new_file_names_int_arrey[1] = 0;
        }else{

            number_for_new_file_names_int_arrey = new int[1];
            number_for_new_file_names_int_arrey[0] = 0;
        }

        for(int i = 0; i < number_of_files; i++){

            file_names_and_times[i] = truncate(file_names_and_times[i], 12, 0);

            number_for_new_file_names_int_arrey = add_one(number_for_new_file_names_int_arrey);

            number_for_new_file_names_String = array_to_string(number_for_new_file_names_int_arrey);

            new_names[i] = number_for_new_file_names_String + " " + file_names_and_times[i];
            old_names[i] = file_names_and_times[i];

            //System.out.println(new_names[i] + "\n");
            //System.out.println(old_names[i] + "\n");
        }
    }


    static void rename() throws IOException{

        for(int i = 0; i < number_of_files; i++){

            // File (or directory) with old name
            File file = new File(old_names[i]);

            // File (or directory) with new name
            File file2 = new File(new_names[i]);

            if(file2.exists()){
                throw new java.io.IOException("file exists");
            }

            if(!file.exists()){

                System.err.println("no file");
            }

            // Rename file (or directory)
            boolean success = file.renameTo(file2);

            if (!success){
                //System.out.println("File was not successfully renamed");
            }
        }
    }



    public static void main(String[] args){

        start_variables();
        make_new_names_and_old_names();
        try {
            rename();
            System.out.println("yeaaay");
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}