package com.avro;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * focus Create in 16:08 2018/11/19
 */
public class AvroTest {

    public static void main(String[] args) throws IOException {
        // Creating User
        User user1 = new User();
        user1.setName("abble");
        User user2 = new User("Ben", 7, "256");
        User user3 = User.newBuilder().setName("focus").setFavoriteNumber(5).setFavoriteColor("red").build();

        // Serializing
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<>(User.class);
        DataFileWriter<User> userDataFileWriter = new DataFileWriter<>(userDatumWriter);
        userDataFileWriter.create(user3.getSchema(), new File("users.avro"));
        userDataFileWriter.append(user1);
        userDataFileWriter.append(user2);
        userDataFileWriter.append(user3);
        userDataFileWriter.close();

        // Deserializing
        DatumReader<User> userDatumReader = new SpecificDatumReader<>(User.class);
        DataFileReader<User> userDataFileReader = new DataFileReader<>(new File("users.avro"), userDatumReader);
        User user = null;
        while (userDataFileReader.hasNext()) {
            user = userDataFileReader.next(user);
            System.out.println(user);
        }

    }

    public static byte[] getAvroBytes(SpecificRecordBase recordBase) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        SpecificDatumWriter specificDatumWriter = new SpecificDatumWriter(recordBase.getSchema());
        Encoder encoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);
        specificDatumWriter.write(recordBase, encoder);
        encoder.flush();
        byteArrayOutputStream.close();
        return  byteArrayOutputStream.toByteArray();
    }
}
