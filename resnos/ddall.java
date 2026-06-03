import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ddall{

    static int number_of_lines;
    static String[] old_names;
    static String[] new_names;

    static int count_lines() throws IOException{

        String[] r_linha_coluna = new String[1000];

        Path filePath = Paths.get("resnos/old_names");
        
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


    static String[] get_names(String which) throws IOException{

        String[] names = new String[number_of_lines];

        names = new String[number_of_lines];
        for(int i = 0; i < number_of_lines; i++){

            names[i] = "";
        }

        Path filePath = Paths.get(which);

        FileReader reader = new FileReader(filePath.toFile());

        int charInt;

        int l = 0;


        while ((charInt = reader.read()) != -1){

            if(charInt == 10){
                l++;

            }else{

                names[l] += (char) charInt;
                //System.out.println(l + " : " /*+ charInt */+" : " + names[l]);
            }

        }

        reader.close();

        //System.out.println(r_linha_coluna[1]);
        //System.out.println(r_linha_coluna[2]);
        //System.out.println(r_linha_coluna[3]);
        //System.out.println(r_linha_coluna[4]);
        //System.out.println(r_linha_coluna[5]);
        //System.out.println(r_linha_coluna[0]);

        return names;
    }



    static void old_names_array(){

        
    }


    static void rename() throws IOException{

        for(int i = 0; i < number_of_lines; i++){

            // File (or directory) with old name
            File file = new File(old_names[i]);

            // File (or directory) with new name
            File file2 = new File(new_names[i]);

            if (file2.exists())
                throw new java.io.IOException("file exists");

            // Rename file (or directory)
            boolean success = file.renameTo(file2);

            if (!success) {
               // File was not successfully renamed
        }
        }
    }

    static void start_variables() throws IOException{

        number_of_lines = count_lines();

        old_names = new String[number_of_lines];
        for(int i = 0; i < number_of_lines; i++){

            old_names[i] = "";
        }

        new_names = new String[number_of_lines];
        for(int i = 0; i < number_of_lines; i++){

            new_names[i] = "";
        }
    }


    public static void main(String[] args) throws IOException{

        start_variables();
        
        old_names = get_names("resnos/old_names");
        new_names = get_names("resnos/new_names");

        rename();
    }
}