package ppj14_cwiczenia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StreamTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        //Zad1
        long start = System.currentTimeMillis();
        String zm1 = "";
        StringBuffer zm2 = new StringBuffer();
        
        for(int i=0;i<100000;i++) {
            //zm1 += "a";
            //zm2.append('a');
        }
        System.currentTimeMillis();
        //System.out.println(zm2);
        //System.out.println(System.currentTimeMillis()-start);
        
        //Zad2
        System.out.println("");
        System.out.println("//Zad2");
        
        StringBuffer str1 = new StringBuffer();
        int wrt;
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Lucas\\Documents\\NetBeansProjects\\ppj\\ppj_14\\src\\ppj14_cwiczenia\\1.txt");
            while((wrt = fis.read()) != -1) {
                str1.append((char)wrt);
            }
            System.out.println(str1);
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        System.out.println();
        //Zad3
        StringBuffer str2 = new StringBuffer();
        int words=0, numbers=0;
        try {
            FileReader fr = new FileReader("C:\\Users\\Lucas\\Documents\\NetBeansProjects\\ppj\\ppj_14\\src\\ppj14_cwiczenia\\2.txt");
            StreamTokenizer st = new StreamTokenizer(fr);
            System.out.println("//Zad3");
            int wrt2;
            int count=0;
            while((wrt2 = st.nextToken()) != StreamTokenizer.TT_EOF && count<200) {
                switch(wrt2) {
                    case -3 : str2.append(st.sval);break;
                    case -2 : str2.append((int)st.nval);break;
                    default : str2.append((char)wrt2);
                }
                str2.append(" ");
                count++;
            }
            
            System.out.println(str2);
            
            Pattern pWords = Pattern.compile("[a-zA-Z]+?( |\\.|,|\\))");
            Matcher mWords = pWords.matcher(str2);
            
            while(mWords.find()) {
                words++;
                System.out.println(mWords.group());
            }
            
            Pattern pNum = Pattern.compile("\\d+? ");
            Matcher mNum = pNum.matcher(str2);
            
            while(mNum.find()) {
                numbers++;
                System.out.println(mNum.group());
            }
            
            System.out.println("Słowa: "+words);
            System.out.println("Liczby: "+numbers);
            System.out.println();            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        //Zad4
        int wrt3;
        StringBuffer str3 = new StringBuffer();
        try {
            FileReader fr = new FileReader("C:\\Users\\Lucas\\Documents\\NetBeansProjects\\ppj\\ppj_14\\src\\ppj14_cwiczenia\\2.txt");
            //StreamTokenizer st = new StreamTokenizer(fr);
            
            Pattern pat = Pattern.compile(".");
            
            System.out.println("//Zad4 ");
            //System.out.println(fr);
            while((wrt3 = fr.read()) != -1){
                str3.append((char)wrt3);    
            }
            Matcher mac = pat.matcher(str3);
            boolean check = mac.matches();
            System.out.println(check);
        } catch (Exception exc) {
            System.out.println(exc);
        }
        
        //Zad5
        System.out.println("//Zad5");
        //StringBuffer str4 = new StringBuffer();
        //str4.append("wieś w Polsce położona w województwie wielkopolskim, w powiecie kolskim, w gminie Olszówka. W latach 1975-1998 miejscowość położona była w województwie konińskim");
        try {
            FileReader str4 = new FileReader("3");
            StreamTokenizer st1 = new StreamTokenizer(str4);
        
            Pattern pat1 = Pattern.compile(".");
            //Matcher mac1 = pat1.matcher(str4);
            //boolean check1 = mac1.matches();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        
    }
}
