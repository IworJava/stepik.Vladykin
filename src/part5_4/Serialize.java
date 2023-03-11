package part5_4;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class Serialize {
    public static void main(String[] args) {
        Animal a1 = new Animal("animal1");
        Animal a2 = new Animal("animal2");
        Animal a3 = new Animal("animal3");
        Animal[] animals = {a1, a2, a3};

        byte[] bytes = serializeAnimalArray(animals);
        Animal[] animalsDeserialized = deserializeAnimalArray(bytes);
        System.out.println(Arrays.toString(animalsDeserialized));
    }

    public static byte[] serializeAnimalArray(Animal[] animals) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeInt(animals.length);
            for (Animal a : animals) {
                oos.writeObject(a);
            }
        } catch (IOException ignored) { }
        return baos.toByteArray();
    }

    public static Animal[] deserializeAnimalArray(byte[] data) {
        Animal[] animals;
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            animals = new Animal[ois.readInt()];
            for (int i = 0; i < animals.length; i++) {
                animals[i] = (Animal) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        return animals;
    }
}

