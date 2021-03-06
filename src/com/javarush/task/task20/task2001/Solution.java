package com.javarush.task.task20.task2001;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Читаем и пишем в файл: Human
*/
public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("c:\\\\5.txt", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home", 999_999.99), new Asset("car", 2999.99));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            inputStream.close();
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны

            if (ivanov.equals(somePerson)) System.out.println("Yes");
            else System.out.println("No");

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            String activ = "";
            int isActiv = this.assets !=null? this.assets.size(): 0;
            if(this.assets!=null) {
                for(int i = 0; i <this.assets.size();i++){
                    activ += this.assets.get(i).getName()+" "+this.assets.get(i).getPrice()+" ";
                }
            }
            String full = isActiv + " " + this.name + " " + activ;


            byte[] b = full.getBytes();
            outputStream.write(b);
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String byff = bufferedReader.readLine();
            String[] inf = byff.split(" ");
            if(Integer.parseInt(inf[0]) != 0) {
                this.name = inf[1];
                for(int i = 0,j=2;i<Integer.parseInt(inf[0]);i++,j+=2) {
                    this.assets.add(new Asset(inf[j],Double.parseDouble(inf[j+1])));
                }
            } else {
                this.name = inf[1];
            }
            bufferedReader.close();
        }
    }
}
