package com.example.projektrent.entiteti;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.*;

public class Serijaliziraj {

        public static Hatchback deserialize(String fileName) throws IOException,
                ClassNotFoundException {
            FileInputStream fileIn = new FileInputStream("dat/promjene.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Hatchback hatchback = (Hatchback) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Deserialized Hatchback:");
            System.out.println(hatchback.toString());
            return hatchback;
        }


        public static void serialize(Hatchback hatch)
                throws IOException {

            FileOutputStream fileOut = new FileOutputStream("dat/promjene.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(hatch);
            out.close();
            fileOut.close();

            fileOut.close();
        }

    }


